package com.dmitrynikol.pingpong.game.main;

import org.lwjgl.opengl.GL11;

import com.dmitrynikol.pingpong.game.core.Entity;
import com.dmitrynikol.pingpong.game.math.Position2D;
import com.dmitrynikol.pingpong.game.math.Size2D;
import com.dmitrynikol.pingpong.game.sound.SoundManagerFactory;
import com.dmitrynikol.pingpong.game.sound.SoundManagerFactory.SoundResource;
import com.dmitrynikol.pingpong.game.util.GameUtils;

/**
 * The entity that represents the players game object.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class Player extends Entity {
	
	public static final int WIDTH = 16;
	public static final int HEIGHT = WIDTH * 5;
	public static final int MIN_HEIGHT = WIDTH * 3;
	public static final int DECREASE_UNIT = 12;
	public static final float SPEED = 4f;
	
	private Ball ball;
	
	public Player(float x, float y, Ball ball) {
		this.setPosition(new Position2D(x, y));
		this.setSize(new Size2D(WIDTH, HEIGHT));
		
		this.ball = ball;
	}

	@Override
	public void update() {
		if (GameUtils.checkCollisions(this, ball)) {
			SoundManagerFactory.getSoundManager().playSound(SoundResource.BUMP);
			// specially that the ball will not pass through the object
			ball.setX(ball.getX() + ball.getWidth());
			ball.reverseX(getCenter());
		}
	}
	
	@Override
	public void render() {
		GL11.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
		super.render();
	}
	
	public void moveY(float magnitude) {
		this.setY(this.getY() + SPEED * magnitude);
	}
	
	public void moveX(float magnitude) {
		this.setX(this.getX() + SPEED * magnitude);
	}
	
	public void increaseObject() {
		this.setHeight(this.getHeight() + DECREASE_UNIT);
		moveY(-1);
	}
	
	public void decreaseObject() {
		if (this.getHeight() > MIN_HEIGHT) {
			this.setHeight(this.getHeight() - DECREASE_UNIT);
			moveY(1);
		}
	}
}
