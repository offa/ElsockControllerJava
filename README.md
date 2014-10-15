# [Elsock Controller *Java*](https://github.com/offa/ElsockControllerJava)

A controller library for [Elsock](https://github.com/forflo/arduino_elsock)-devices.

**Note:** There's also a ***C++*** version: [Elsock Controller Cpp](https://github.com/offa/ElsockControllerCpp)

## Requirements

 * **JDK 7+** - Not tested with JDK6 or earlier

## Building

Create a new (library) project from existing source in your IDE.

**NetBeans:**

`New Project -> Java -> Java Project with Existing Source`

*If there are any wishes according to IDE support / build tools, please open an issue*

## Example

```java
// Connect to elsock host
ElsockController ec = new ElsockController(Protocol.HTTP, "127.0.0.1");

// Set device #7 to ON
ec.sendCommand(Device.DEVICE_7, Status.ON);
// Set device #9 to ON
ec.sendCommand(Device.DEVICE_9, Status.ON);

// Toggle all devices
ec.sendToggleAll(); 

// Receive status
ElsockData response = ec.getStatus();

// Get status of device #4
Status statusDev4 = response.getStatus(Device.DEVICE_4);
// Get status of device #9
Status statusDev9 = response.getStatus(Device.DEVICE_9);

// Print status of devices #4 and #9
System.out.println("Device #4: " + statusDev4.name() 
        + "\n\"Device #9: " + statusDev9.name());

// Turn off all devices
ec.sendCommand(Device.ALL, Status.OFF);
```
*Don't forget to add **Elsock library**!*


## License

**GPLv3:**

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
