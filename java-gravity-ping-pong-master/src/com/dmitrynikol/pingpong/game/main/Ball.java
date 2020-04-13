package com.dmitrynikol.pingpong.game.main;

import org.lwjgl.opengl.GL11;

import com.dmitrynikol.pingpong.game.core.Entity;
import com.dmitrynikol.pingpong.game.math.Position2D;
import com.dmitrynikol.pingpong.game.math.Size2D;

/**
 * An entity which represents ball that jumps on the game space.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class Ball extends Entity {
	
	public static final int SIZE = 16;
	public static final float MAX_SPEEDX = 4f;
	public static final float MAX_SPEEDY = 8f;
	public static final float DAMPING = 0.05f;
	
	private float velocityX;
	private float velocityY;
	
	public float startX;
	public float startY;
	
	public Ball(float x, float y) {
		this.setPosition(new Position2D(x, y));
		this.setSize(new Size2D(SIZE, SIZE));
		
		startX = x;
		startY = y;
		
		velocityX = -MAX_SPEEDX;
		velocityY = 0;
	}

	@Override
	public void update() {
		this.setX(this.getX() + velocityX);
		this.setY(this.getY() + velocityY);
	}
	
	@Override
	public void render() {
		GL11.glColor3f(1.0f, 1.0f, 0.0f);
		super.render();
	}
	
	/**
	 * Change the trajectory of the ball to the opposite on the horizontal axis.
	 * 
	 * @param center of the interacting object
	 */
	public void reverseX(float center) {
		velocityX *= -1;
		velocityY += (getCenter() - center) * DAMPING;
		
		if (velocityY > MAX_SPEEDY) {
			velocityY = MAX_SPEEDY;
		}
		if (velocityY < -MAX_SPEEDY) {
			velocityY = -MAX_SPEEDY;
		}
	}
	
	public void reverseY() {
		velocityY *= -1;
	}
	
	public void resetPosition() {
		this.setX(startX);
		this.setY(startY);
		
		velocityX *= -1;
		velocityY = 0;
	}
}
