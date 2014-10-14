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
import java.util.List;

/**
 * The enum <code>Device</code> enumerates real (physical) devices or groups of
 * devices.
 * 
 * <p>While {@link #values() values()} returns <i>all</i> elements - including
 * groups, {@link #getDevices() getDevices()} can be used to get only those,
 * which represent real devices (no groups).</p>
 * 
 * @version     0.1
 * @since       0.0.1
 * @author      offa
 */
public enum Device
{
    /** All devices <i>(Group)</i>. */
    ALL("A", true),
    /** Device #0. */
    DEVICE_0("a", false),
    /** Device #1. */
    DEVICE_1("b", false),
    /** Device #2. */
    DEVICE_2("c", false),
    /** Device #3. */
    DEVICE_3("d", false),
    /** Device #4. */
    DEVICE_4("e", false),
    /** Device #5. */
    DEVICE_5("f", false),
    /** Device #6. */
    DEVICE_6("g", false),
    /** Device #7. */
    DEVICE_7("h", false),
    /** Device #8. */
    DEVICE_8("i", false),
    /** Device #9. */
    DEVICE_9("j", false);
    
    private final String id;
    private final boolean group;

    
    private Device(String id, boolean group)
    {
        this.id = id;
        this.group = group;
    }

    
    /**
     * Returns the id.
     * 
     * @return      Id
     */
    public String getId()
    {
        return id;
    }

    
    /**
     * Returns whether the element descripes a group or a physical device.
     * 
     * @return      Returns <tt>true</tt> for a group, or <tt>false</tt> for a
     *              real device.
     */
    public boolean isGroup()
    {
        return group;
    }
    
    
    /**
     * Returns the string representation.
     * 
     * @return      String representation
     */
    @Override
    public String toString()
    {
        return "Device{" + "id=" + id + ", group=" + group + '}';
    }

    
    /**
     * Returns all (real) devices - this does not include generalised
     * devices like {@link Device#ALL <code>ALL</code>}.
     *
     * @return      Devices
     */
    public static List<Device> getDevices()
    {
        List<Device> devices = new ArrayList<>();
        
        for( Device dev : values() )
        {
            if( dev.isGroup() == false )
            {
                devices.add(dev);
            }
        }
        
        return devices;
    }
}
