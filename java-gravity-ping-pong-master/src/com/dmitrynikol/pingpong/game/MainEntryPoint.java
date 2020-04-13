package com.dmitrynikol.pingpong.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.dmitrynikol.pingpong.game.main.Game;
import com.dmitrynikol.pingpong.game.sound.SoundManagerFactory;
import com.dmitrynikol.pingpong.game.sound.SoundManagerFactory.SoundResource;
import com.dmitrynikol.pingpong.game.util.GameUtils;

/**
 * Entry-point class for the application.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class MainEntryPoint {
	
	private static Game game;
	
	/** Time at last frame */
	private static long lastFrame;
 
	/** Frames per second */
	private static int fps;
	
	/** Last fps time */
	private static long lastFPS;
	
	public static void main(String[] args) {
		initDisplay();
		initGL();
		initGame();
		
		getDelta(); // call once before loop to initialize lastFrame
		lastFPS = getTime(); // call before loop to initialize fps timer
		
		// register sound 
		for (SoundResource sound : SoundResource.values()) {
			SoundManagerFactory.getSoundManager().registerSound(sound);
		}
		
		// start background sound
		//SoundManagerFactory.getSoundManager().playSound(SoundResource.IMPERIAL_MARCH);
		
		gameLoop();
		cleanUp();
	}
	
	private static void initGame() {
		game = new Game();
	}
	
	private static void getInput() {
		game.getInput();
	}
	
	private static void update() {
		game.update();
	}
	
	private static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glLoadIdentity();
		
		GL11.glColor3f(0.25f, 0.75f, 0.5f);
		
		game.render();
		
		Display.update();
		// set fps to 60fps
		Display.sync(60);
	}
	
	private static void gameLoop() {
		while (!Display.isCloseRequested() && game.isRunning()) {
			int delta = getDelta();
			
			update();
			render();
			getInput();
			
			// update FPS counter
			updateFPS(); 
		}
		
		cleanUp();
	}
	
	/**
	 * Initialize an OpenGL, that all we need for application.
	 */
	private static void initGL() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	/**
	 * Any finally operation before we close the application.
	 */
	private static void cleanUp() {
		Display.destroy();
		Keyboard.destroy();
		SoundManagerFactory.getSoundManager().destroy();
	}
	
	/**
	 * Initialize
	 */
	private static void initDisplay() {
		try {
			GameUtils.setEnableFullscreen(false);
			Display.create();
			Display.setVSyncEnabled(true);
			Keyboard.create();
			Mouse.setGrabbed(false);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the accurate system time.
	 * 
	 * @return The system time in milliseconds
	 */
	public static long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/**
	 * Calculate the FPS and set it in the title bar.
	 */
	public static void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps + ", game score: player=" + 
					15 + ", opponent: " + 13);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	/** 
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public static int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
 
	    return delta;
	}
}