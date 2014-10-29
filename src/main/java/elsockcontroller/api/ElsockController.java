/*
 * ElsockControllerJava is a controller for elsock devices.
 * Copyright (C) 2014  offa
 * 
 * This file is part of ElsockControllerJava.
 *
 * ElsockControllerJava is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ElsockControllerJava is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ElsockControllerJava.  If not, see <http://www.gnu.org/licenses/>.
 */

package elsockcontroller.api;

import elsockcontroller.api.ElsockData.ElsockElement;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * The class <code>ElsockController</code> provides access to Elsock-devices.
 * 
 * @version     0.1
 * @since       0.0.1
 * @author      offa
 */
public class ElsockController
{
    private static final int DEFAULT_TIMEOUT = 5000;
    private static final String STREAM_DELIMETER = ",";
    private static final Pattern PATTERN_EMPTY = Pattern.compile("^(?is)\\s$");
    private final Protocol protocol;
    private final String address;
    private int connectionTimeout;

    
    public ElsockController(Protocol protocol, String address)
    {
        this.protocol = protocol;
        this.address = address;
        this.connectionTimeout = DEFAULT_TIMEOUT;
    }
    
    
    
    /**
     * Changes the status of the device to <code>status</code>.
     * 
     * <p><b>Note:</b> This method does not return a response; to receive status
     * information use {@link #getStatus() getStatus()} instead.</p>
     * 
     * @param device        Device
     * @param status        New status
     * @throws              IOException On a network failure or timeout
     */
    public void sendCommand(Device device, Status status) throws IOException
    {
        execute(new ElsockData(device, status));
    }
    
    
    /**
     * Toggles the device.
     * 
     * @param device        Device
     * @throws              IOException On a network failure or timeout
     */
    public void sendToggle(Device device) throws IOException
    {
        sendCommand(device, Status.TOGGLE);
    }
    
    
    /**
     * Toggles all devices.
     * 
     * @throws              IOException On a network failure or timeout
     */
    public void sendToggleAll() throws IOException
    {
        sendCommand(Device.ALL, Status.TOGGLE);
    }
    
    
    /**
     * Returns the status information of all devices. The status information
     * returned contains <i>all</i> (real) devices and their status.
     * 
     * @return      Status information
     * @throws      IOException On a network failure or timeout
     */
    public ElsockData getStatus() throws IOException
    {
        return execute(new ElsockData(Device.ALL, Status.GET_STATUS));
    }
    
    
    /**
     * Returns the protocol used for the connection.
     * 
     * @return      Protocol
     */
    public Protocol getProtocol()
    {
        return protocol;
    }

    
    /**
     * Returns the host address used for the connection.
     * 
     * @return      Address
     */
    public String getAddress()
    {
        return address;
    }

    
    /**
     * Returns the timeout used for the connection.
     * 
     * @return      Timeout (ms)
     */
    public int getConnectionTimeout()
    {
        return connectionTimeout;
    }

    
    /**
     * Sets the timeout used for the connection.
     * 
     * @param connectionTimeout         Timeout (ms)
     * @exception                       IllegalArgumentException If
     *                                  <code>connectionTimeout</code> is
     *                                  negative
     */
    public void setConnectionTimeout(int connectionTimeout)
    {
        if( connectionTimeout < 0 )
        {
            throw new IllegalArgumentException("Negative timeout not allowed!");
        }
        
        this.connectionTimeout = connectionTimeout;
    }
    
    
    /**
     * Returns the string representation.
     * 
     * @return      String representation
     */
    @Override
    public String toString()
    {
        return "ElsockController{" + "protocol=" + protocol 
                + ", address=" + address 
                + ", connectionTimeout=" + connectionTimeout + '}';
    }
    
    
    /**
     * Returns the hash code.
     * 
     * @return      Hash code
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.protocol);
        hash = 89 * hash + Objects.hashCode(this.address);
        hash = 89 * hash + this.connectionTimeout;
        return hash;
    }
    
    
    /**
     * Tests whether this and <code>obj</code> are equal.
     * 
     * @param obj       Other object
     * @return          Returns <tt>true</tt> if equal or <tt>false</tt> if not
     */
    @Override
    public boolean equals(Object obj)
    {
        if( obj == null )
        {
            return false;
        }
        
        if( getClass() != obj.getClass() )
        {
            return false;
        }
        
        final ElsockController other = (ElsockController) obj;
        
        if( this.protocol != other.protocol )
        {
            return false;
        }
        
        if( !Objects.equals(this.address, other.address) )
        {
            return false;
        }
        
        return this.connectionTimeout == other.connectionTimeout;
    }
    

    /**
     * Executes the command with the given data and returns the result.
     * 
     * @param data      Data
     * @return          Result or <tt>null</tt> if there's no / empty response
     *                  data
     * @throws          IOException On a network failure or timeout
     */
    private ElsockData execute(ElsockData data) throws IOException
    {
        ElsockData result = new ElsockData(Device.getDevices(), Status.UNKNOWN);
        URL url = constructQuery(data);
        
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(connectionTimeout);
        
        Scanner s = new Scanner(conn.getInputStream()).useDelimiter(STREAM_DELIMETER);
        int pos = 0;
        
        if( s.hasNext(PATTERN_EMPTY) == true )
        {
            return null;
        }

        while( s.hasNext() == true )
        {
            final String next = s.next().trim();
            
            switch(next)
            {
                case "0":
                    result.get(pos++).setStatus(Status.OFF);
                    break;
                case "1":
                    result.get(pos++).setStatus(Status.ON);
                    break;
                default:
                    break;
            }
        }
        
        return result;
    }
    
    
    /**
     * Constructs the query url for the given data.
     * 
     * @param data      Data
     * @return          Query url
     * @throws          MalformedURLException If a unknown protocol is used
     */
    private URL constructQuery(ElsockData data) throws MalformedURLException
    {
        StringBuilder queryStr = new StringBuilder("/q?");
        int n = data.size();
        
        for( ElsockElement element : data.getElements() )
        {
            queryStr.append("L").append(element.getDevice().getId())
                    .append("=").append(element.getStatus().getId());
            
            if( n-- <= 0 )
            {
                break;
            }
        }

        return new URL(protocol.getName(), address, queryStr.toString());
    }
}
