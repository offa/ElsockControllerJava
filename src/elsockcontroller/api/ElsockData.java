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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The class <code>ElsockData</code> is a collection of
 * {@link ElsockData.ElsockElement data element}'s. Each these element
 * represents a device and it's status. However it's not limited to the physical
 * devices available.
 * 
 * <p>Objects of this class are generally used to send or receive data
 * to / from the controller.</p>
 * 
 * @version     0.1
 * @since       0.0.1
 * @author      offa
 */
public class ElsockData
{
    private List<ElsockElement> elements;
    
    
    public ElsockData()
    {
        this.elements = new ArrayList<>();
    }
    
    public ElsockData(Device device, Status status)
    {
        this();
        this.elements.add(new ElsockElement(device, status));
    }
    
    public ElsockData(List<Device> devices, Status initStatus)
    {
        this();
        
        for( Device dev : devices )
        {
            this.elements.add(new ElsockElement(dev, initStatus));
        }
    }
    
    public ElsockData(Device devices[], Status initStatus)
    {
        this(Arrays.asList(devices), initStatus);
    }
    
    
    
    /**
     * Inserts an element consisting of the given data.
     * 
     * @param device        Device
     * @param status        Status
     */
    public void insert(Device device, Status status)
    {
        insert(new ElsockElement(device, status));
    }
    
    
    /**
     * Inserts the <code>element</code>.
     * 
     * @param element       Element
     */
    public void insert(ElsockElement element)
    {
        elements.add(element);
    }

    
    /**
     * Returns the list of elements.
     * 
     * @return      Elements
     */
    public List<ElsockElement> getElements()
    {
        return elements;
    }
    
    
    /**
     * Returns the element at position <code>pos</code>.
     * 
     * @param pos       Position (<code>0 &le; pos &lt; size</code>)
     * @return          Element at <code>pos</code>
     */
    public ElsockElement get(int pos)
    {
        return elements.get(pos);
    }
    
    
    /**
     * Returns the element of device <code>dev</code>.
     * 
     * @param dev       Device
     * @return          Element or <tt>null</tt> if not found
     */
    public ElsockElement get(Device dev)
    {
        for( ElsockElement element : elements )
        {
            if( element.getDevice() == dev )
            {
                return element;
            }
        }
        
        return null;
    }
    
    
    /**
     * Returns the status of device <code>dev</code>.
     * 
     * @param dev       Device
     * @return          Status
     */
    public Status getStatus(Device dev)
    {
        final ElsockElement element = get(dev);
        
        return ( element == null ? Status.UNKNOWN : element.getStatus() );
    }
    
    
    /**
     * Returns the number of elements.
     * 
     * @return      Number of elements
     */
    public int size()
    {
        return elements.size();
    }
    
    
    /**
     * Returns whether the data is empty (contains no elements) or not.
     * 
     * @return      Returns <tt>true</tt> if there are no elements or
     *              <tt>false</tt> otherwise
     */
    public boolean isEmpty()
    {
        return elements.isEmpty();
    }
    
    
    /**
     * Returns the string representation.
     * 
     * @return      String representation
     */
    @Override
    public String toString()
    {
        return "ElsockData{" + "elements=" + elements + '}';
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
        hash = 89 * hash + Objects.hashCode(this.elements);
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
        final ElsockData other = (ElsockData) obj;
        
        return Objects.equals(this.elements, other.elements);
    }

    
    /**
     * The class <code>QueryElement</code> represents a element of a data set.
     * Each element holds a device id and it's status.
     */
    public static class ElsockElement
    {
        private final Device device;
        private Status status;

        
        public ElsockElement(Device device, Status status)
        {
            this.device = device;
            this.status = status;
        }

        
        /**
         * Returns the device.
         * 
         * @return      Device
         */
        public Device getDevice()
        {
            return device;
        }

        
        /**
         * Returns the status.
         * 
         * @return      Status
         */
        public Status getStatus()
        {
            return status;
        }

        
        /**
         * Sets the status.
         * 
         * @param status        Status
         */
        public void setStatus(Status status)
        {
            this.status = status;
        }

        
        /**
         * Returns the string representation.
         * 
         * @return      String representation
         */
        @Override
        public String toString()
        {
            return "ElsockElement{" + "device=" + device 
                    + ", status=" + status + '}';
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
            hash = 23 * hash + Objects.hashCode(this.device);
            hash = 23 * hash + Objects.hashCode(this.status);
            return hash;
        }

        
        /**
         * Tests whether this and <code>obj</code> are equal.
         * 
         * @param obj       Other object
         * @return          Returns <tt>true</tt> if equal or
         *                  <tt>false</tt> if not
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
            
            final ElsockElement other = (ElsockElement) obj;
            
            if( this.device != other.device )
            {
                return false;
            }
            
            return this.status == other.status;
        }
    } 
}
