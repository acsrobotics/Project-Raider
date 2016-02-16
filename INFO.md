# Project Raider 
### ConfigClient
ConfigClient is designed to fetch robot configuration data from controlling computer during the runtime. In order to use this class, please make sure to downlaod `json-20151123.jar` file from <a href="http://mvnrepository.com/artifact/org.json/json/20151123">here</a> and place it under `$HOME/wpilib/user/lib/`. Otherwise, the project will not be able to be successfully built. 

The procedure of importing library jar into project is as following:
  1. Right click at the project
  2. Click at `Build Path`
  3. Click at `Add Libraries ...`
  4. Select `User Library`
  5. Click `Next`
  6. Click `User Libraries ...`
  7. Click `New...`
  8. Name it `Json` and then click `OK`
  9. Select `Json` and then click `Add External JARs`
  10. Select the `json-20151123.jar` that you just downloaded, then click `Open`
  11. Click `OK`
  12. You are good to go

Also, before deploying the robot code to roboRio, import `Raider_Server` project into Eclipse, and run it in the background. This will open up a socket server on port 3002. All configuration data can be modified in `config.json` file which can be found at the root of `Raider_Server` project. 

When changing the network configuration of the controlling computer, make sure change the static IP address to `10.47.16.6`, the ConfigClient is configured to query this address. This can be achieved using following commands in `cmd` (opened as <b>Administrator</b>)
``` batch
> netsh interface ip set address "<Connection>" static 10.47.16.6 255.255.255.0
```
