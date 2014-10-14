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
 * The enum <code>Status</code> enumerates status information and -commands.
 * 
 * @version     0.1
 * @since       0.0.1
 * @author      offa
 */
public enum Status
{
    /** Off. */
    OFF('0'),
    /** On. */
    ON('1'),
    /** Toggle. */
    TOGGLE('t'),
    /** Get status. */
    GET_STATUS('s'),
    /** Unknown. */
    UNKNOWN('-');
    
    
    private final char id;

    
    private Status(char id)
    {
        this.id = id;
    }

    
    
    /**
     * Returns the id.
     * 
     * @return      Id
     */
    public char getId()
    {
        return id;
    }

    
    /**
     * Returns the string representation.
     * 
     * @return      String representation
     */
    @Override
    public String toString()
    {
        return "Status{" + "id=" + id + '}';
    }
    
}
