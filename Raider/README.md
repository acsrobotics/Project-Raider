# Project Raider 
### ConfigClient
ConfigClient is designed to fetch robot configuration data from controlling computer during the runtime. In order to use this class, please make sure to downlaod `json-20151123.jar` file from <a href="http://mvnrepository.com/artifact/org.json/json/20151123">here</a> and place it under `$HOME/wpilib/user/lib/`. Otherwise, the project will not be able to be successfully built. 
Also, before deploying the robot code to roboRio, import `Raider_Server` project into Eclipse, and run it in the background. This will open up a socket server on port 3002. All configuration data can be modified in `config.json` file which can be found at the root of `Raider_Server` project. 
When changing the network configuration of the controlling computer, make sure change the static IP address to `10.47.16.6`, the ConfigClient is configured to query this address. This can be achieved using following commands in `cmd` (opened as <b>Administrator</b>)
``` batch
> netsh interface ip set address "<Connection>" static 10.47.16.6 255.255.255.0
```