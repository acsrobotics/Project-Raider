<img align="left" src="http://mcmechtech.weebly.com/uploads/1/0/8/1/10811098/_5798496_orig.jpg" width="100" height="100" /> <img align="right" src="http://www.akoustik.ca/sites/default/files/4716.jpg" width="100" height="100" />
<h1 align="center">ACS ROBOTICS - 4716 2016 Programming Design Document </h1>

### Game Description

This year’s game is about seizing castles by launching boulders through a high or low goal and then capturing the castle by the end of the match. There are two teams against each other composed of three robots each. Each alliance has battlements that are randomized for each map at the discretion of the audience and the opposing alliance. The objective this year is to score points by breaking defences and throwing boulders at the opposing castle. The team with the most points wins the match.

### Robot Description 

The objective of our robot this year is to break the defences. The robot will have the capability to go through all obstacles with ease while passing up boulders to a member of the same alliance. If required, our robot will be able to score in the low goal. To add, our robot will have the capability to climb by the end of the match to score points. 

### Subsystems
1. Drive Train
2. Scoop
3. Climber 
  
- Drive Train

 In the Drive Train, we have 4 wheels that can adjust the height of our drive train with pneumatics. Only the back wheels are powered as the front wheels are unpowered omni-wheels. We have two gear boxes with Greyhound encoders on each side.  We have a gyro and accelerometer to measure the angle of our robot. Ultrasonic sensors will be used in autonomous when crossing obstacles.
 
| Tasks | Sensors | Problems | Solutions |
| ----- |:------- |:-------- |:---------|
| Drive with Controller | None | Nothing | Easy |
| Drive "Perfectly" Straight Automatically | None | Nothing | Make a command | 
| Turn "Perfectly" Automatically | Gyro | Keeping it straight w/o overshoot | Already coded |
| Drive Towards Low Goal | Gyro | Turn w/o overshoot | Already coded |
  


| Tasks (Obstacles) | Sensors | Problems | Solutions | Difficulty (_/5) (Auto) |
| ---- |:------- |:-------- |:--------- |:---------------- |
| Low Bar | Optional: Ultrasonic, Gyro, Camera | Keep under the height of 1ft 4”. Mind the Angle of Ramp | Adjust back wheels/Drive Straight Through Keep in down position. | 1 |
| Portcullis(Gate) | Required: Ultrasonic, Potentiometer Optional: Gyro, Camera | Lift the gate while moving straight. Need to know distance of gate from robot. | Either Lift Gate/ Drive Straight Through. Keep in down position. | 2 |
| Cheeval de Frise (Dynamic Ramps) | Required: Ultrasonic, Potentiometer Optional: Gyro, Camera | Dynamic Ramps means we need to push down the  ramps that aren’t aligned | Push down the ramps that aren’t aligned and drive through. Keep in down position. | 3 |
| Moat | Required: Ultrasonic, Potentiometer Optional: Gyro, Camera | Drive over a bump right after the ramp. | Push through. Keep in up position. | 2 |
| Ramparts | Required: Ultrasonic, Potentiometer Optional: Gyro, Camera | Drive over fixed ramps going on opposite directions. | Drive through. Maybe adjust each side. Keep in up position. | 2 |
| Drawbridge (Bridge) | Required: Ultrasonic, Potentiometer Optional: Gyro, Camera | Spring loaded door that’s required to pull down. | Pull a door down. Back up. Drive over the door. Keep in down position. | 4 | 
| Sally Port (Door) | Required: Ultrasonic, Potentiometer, Gyro Optional: Camera | Spring loaded door that opens like a regular door.  |  Pull door to middle of robot. Move door to side with climber. Drive through. Keep in down position. | 5 |
| Rock Wall | Required: Ultrasonic, Potentiometer Optional: Gyro, Camera | 4 ½ " bump on a platform. | Drive over it. Keep in up position. | 2 |
| Rough Terrain | Required: Ultrasonic, Potentiometer Optional: Gyro, Camera | Literally rough terrain. May be hard to keep consistent results, so sensors will be used more.  | Drive over it. Keep in up position. | 3 |

- Scoop
- Climber

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
