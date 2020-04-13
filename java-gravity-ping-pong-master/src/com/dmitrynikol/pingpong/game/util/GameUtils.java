package com.dmitrynikol.pingpong.game.util;

import java.awt.Rectangle;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.dmitrynikol.pingpong.game.core.Entity;

/**
 * Miscellaneous OpenGL utility functions.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public final class GameUtils {
	
	/**
	 * Angle of gaming surface.
	 */
	private static int angle = 0;

	/**
     * Only constructed by ourselves. Class contains only static methods.
     */
	private GameUtils() {}
	
	/**
	 * Rotating with an angle theta rotates points on the x axis toward the y axis.
	 * 
	 * @param theta - the angle of rotation in radians 
	 */
	public static void rotate(float theta) {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glRotatef(theta, 0f, 0f, 1f);
		GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	/**
	 * Determines whether or not this game object intersect. 
	 * Two rectangles intersect if their intersection is nonempty. 
	 * 
	 * @param obj1 specified first game object
	 * @param obj2 specified second game object
	 * @return
	 */
	public static boolean checkCollisions(Entity obj1, Entity obj2) {
		Rectangle rect1 = new Rectangle((int) obj1.getX(), (int) obj1.getY(), 
				(int) obj1.getWidth(), (int) obj1.getHeight());
		Rectangle rect2 = new Rectangle((int) obj2.getX(), (int) obj2.getY(), 
				(int) obj2.getWidth(), (int) obj2.getHeight());
		
		return rect1.intersects(rect2);
	}
	
	/**
	 * Sets whether full screen is enabled, true - enable, false - disable.
	 * The native cursor position is also reset.
	 * 
	 * @param enable full screen or not
	 * @throws LWJGLException If the mode switch fails
	 */
	public static void setEnableFullscreen(boolean enable) throws LWJGLException {
		if (enable) {
			DisplayMode mode = findDilspayMode(Display.getWidth(), 
					Display.getHeight(), Display.getDisplayMode().getBitsPerPixel());
			Display.setDisplayModeAndFullscreen(mode);
		} else {
			Display.setDisplayMode(new DisplayMode(800, 600));
		}
	}
	
	/**
	 * Method return DisplayMode class that
	 * encapsulates the properties for a given display mode.
	 * 
	 * @param width properties of the display mode
	 * @param height properties of the display mode
	 * @param bpp properties of the display mode
	 * @return the desktop display mode
	 * @throws LWJGLException if the mode switch fails
	 */
	private static DisplayMode findDilspayMode(int width, int height, int bpp) throws LWJGLException {
		DisplayMode[] modes = Display.getAvailableDisplayModes();
		for (DisplayMode mode : modes) {
			if (mode.getWidth() == width && mode.getHeight() == height &&
					mode.getBitsPerPixel() >= bpp && mode.getFrequency() <= 60) {
				return mode;
			}
		}
		
		return Display.getDesktopDisplayMode();
	}
	
	/**
	 * Clockwise movement in the right direction.
	 */
	private static void anticlockwiseDirection() {
		angle += 10;
		GameUtils.rotate(GameUtils.angle);
	}
	
	/**
	 * Clockwise movement in the opposite direction.
	 */
	private static void clockwiseDirection() {
		angle -= 10;
		GameUtils.rotate(GameUtils.angle);
	}
	
	/**
	 * Randomly rotate surface 10 degrees in any direction
	 */
	public static void randomlyRotateSurface() {
		if (new java.util.Random().nextBoolean()) {
			anticlockwiseDirection();
		} else {
			clockwiseDirection();
		}
	}
	
	/**
	 * Align surface to horizontal position.
	 */
	public static void alignGameSurface() {
		if (angle > 0) {
			clockwiseDirection();
		} else if (angle < 0) {
			anticlockwiseDirection();
		}
	}
}