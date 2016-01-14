<img align="left" src="http://mcmechtech.weebly.com/uploads/1/0/8/1/10811098/_5798496_orig.jpg" width="100" height="100" /> <img align="right" src="https://camo.githubusercontent.com/3a58a6d2c0886dac6554661b3c1221d72f6b2d6e/68747470733a2f2f6d656469612e6c6963646e2e636f6d2f6d70722f6d70722f736872696e6b6e705f3430305f3430302f702f382f3030352f3039372f3034342f306462333432392e6a7067" width="100" height="100" />
<h1 align="center">ACS ROBOTICS - 4716 2016 Programming Design Document </h1>

### Game Description

This year’s game is about seizing castles by launching boulders through a high or low goal and then capturing the castle by the end of the match. There are two teams against each other composed of three robots each. Each alliance has battlements that are randomized for each map at the discretion of the audience and the opposing alliance. The objective this year is to score points by breaking defences and throwing boulders at the opposing castle. The team with the most points wins the match.

### Robot Description 

The objective of our robot this year is to break the defences. The robot will have the capability to go through all obstacles with ease while passing up boulders to a member of the same alliance. If required, our robot will be able to score in the low goal. To add, our robot will have the capability to climb by the end of the match to score points. 

### Subsystems
1. Drive Train

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
| Portcullis(Gate) |  | | | 2 |
| Cheeval de Frise (Dynamic Ramps) | | | | 3 |
| Moat | | | | 2 |
| Ramparts | | | | 2 |
| Drawbridge (Bridge) | | | | 4 |
| Sally Port (Door) | | | | 5 |
| Rock Wall | | | | 2 |
| Rough Terrain | | | | 3 |

2. Scoop
2. Climber

