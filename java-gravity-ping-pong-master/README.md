### Welcome to Gravity ping-pong game

This is an example game that recreates the classic game ping pong but with my personal vision. I called it gravity ping pong. 
The game uses [LWJGL](http://lwjgl.org/)(for graphics) and [OpenAL](http://connect.creativelabs.com/openal/default.aspx)(for sound effects) technology. Let's take a closer look at them.

### 1) What is LWJGL?
[LWJGL](http://lwjgl.org/) (Lightweight Java Game Library) provides developers access to different high performance crossplatform libraries. 
It provides not just a graphics binding but a binding to OpenAL(open standards sound system). 
In addition, it also gives you access to input devices. This means that your game could be controlled from a gamepad, 
all from one library. LWJGL provides it’s own keyboard handling objects, so we don’t need use for that AWT anymore. 
We can simple using [Keyboard](http://lwjgl.org/javadoc/org/lwjgl/input/Keyboard.html) class. Drawing a sprite on the screen is identical to the JOGL. It’s very easy.

### 2) What is OpenAL?
[OpenAL](http://connect.creativelabs.com/openal/default.aspx) (Open Audio Library) is a library for creation of a virtual world of sound. It easily combined with [OpenGL](http://www.opengl.org/) 3D 
world and can very well be used to create games. The OpenAL API is designed to be cross-platform and easy to use.
     For more information, see:
- [LWJGL Wiki](http://lwjgl.org/wiki/index.php)
- [OpenAL for Android](http://pielot.org/2010/12/14/openal-on-android/)
- [Game Engines and Libraries Using LWJGL](http://lwjgl.org/wiki/index.php?title=Game_Engines_and_Libraries_Using_LWJGL)
- [LWJGL General FAQ](http://www.lwjgl.org/wiki/index.php?title=General_FAQ)
- [OpenAL specification](http://connect.creativelabs.com/openal/Documentation/Forms/AllItems.aspx)

### 3) So let's look at the game!
What the goal of the game? Ping pong is one of the most famous games of the world. The game rules are very simple. 
You play this game with the arrow keys on your keyboard. Two players hit a ball back and forth using table tennis 
rackets(in the game it's simple rectangles with different colors). Points are scored when a player fails to return the ball within the rules. 
In my version of this game if player(from the left side) fails to return the ball, the surface is turning clockwise or vice versa 
by 10 degrees(it's a random rotation) and player rectangle is reduced, but the opponent rectangle is increased. 
But if the opponent misses the ball, the platform is rotated by 10 degrees and tends to the horizontal position. 
In the title of the window you can see current frame rate([FPS](http://en.wikipedia.org/wiki/Frame_rate)) of the game and the game score. That's it :)

Controls
 - Esc - quit game
 - Enter - enable/disable fullscreen mode
 - Arrow keys (up, down, left, right) or (W, S, A, D) - spaceship movement
 
Audio

The game has a background music and the sound of the collision. The magic of the sound was done using OpenAL.
Here you can find the package with [implementation of the sound](https://github.com/dmitrynikol/java-gravity-ping-pong/tree/master/src/com/dmitrynikol/pingpong/game/sound).

### 4) My ideas how to improve the game? 
- Add Sprite/Texture to the game
- DIfferent collision object effects
- Draw a trajectory of the ball and the gradual disappearance
- More real physics based on some realtime physics engine
- Powerups or bonuses that will allow to add extra abilities to the player for some time as a game mechanism, they can help in difficult situations or add some dynamic scene to the gameplay
- Additional barriers during gameplay
- Ability to fire at enemy objects

We are done. It's a simple example how to build a game with OpenGL. Have fun with the code! 
