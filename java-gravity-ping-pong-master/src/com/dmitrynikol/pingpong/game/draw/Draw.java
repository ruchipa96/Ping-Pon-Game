package com.dmitrynikol.pingpong.game.draw;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 * Utility class for drawing geometric primitives with OpenGL.
 * Provide more sophisticated control over geometry, 
 * coordinate transformations and color management.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class Draw {
	
	/**
	 * Draws the outline of the specified rectangle by filling it 
	 * with the background color of the current drawing surface.
	 * 
	 * @param x the x coordinate of the rectangle to be drawn
	 * @param y the y coordinate of the rectangle to be drawn
	 * @param width the width of the rectangle to be drawn
	 * @param height the height of the rectangle to be drawn.
	 */
	public static void rect(float x, float y, float width, float height) {
		rect(x, y, width, height, 0);
	}
	
	/**
	 * Draws the outline of the specified rectangle by filling it 
	 * with the background color of the current drawing surface.
	 * 
	 * @param x the x coordinate of the rectangle to be drawn
	 * @param y the y coordinate of the rectangle to be drawn
	 * @param width the width of the rectangle to be drawn
	 * @param height the height of the rectangle to be drawn
	 * @param rotate the angle of rotation in radians
	 */
	public static void rect(float x, float y, float width, float height, float rotate) {
		glPushMatrix();
		{
			glTranslatef(x, y, 0);
			glRotatef(rotate, 0, 0, 1);
			
			glBegin(GL_QUADS);
			{
				glVertex2f(0, 0);
				glVertex2f(0, height);
				glVertex2f(width, height);
				glVertex2f(width, 0);
			}
			glEnd();
		}
		glPopMatrix();
	}
}
