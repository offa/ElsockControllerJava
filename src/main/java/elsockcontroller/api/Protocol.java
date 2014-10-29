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

/**
 * The enum <code>Protol</code> enumerates supported protocolls.
 * 
 * @version     0.1
 * @since       0.0.1
 * @author      offa
 */
public enum Protocol
{
    /** Http protocol. */
    HTTP("http");
    
    
    private final String name;

    
    private Protocol(String name)
    {
        this.name = name;
    }

    
    
    /**
     * Returns the name.
     * 
     * @return      Name
     */
    public String getName()
    {
        return name;
    }

    
    /**
     * Returns the string representation.
     * 
     * @return      String representation
     */
    @Override
    public String toString()
    {
        return "Protocol{" + "name=" + name + '}';
    }

}
