package com.dmitrynikol.pingpong.game.main;

import org.lwjgl.opengl.GL11;

import com.dmitrynikol.pingpong.game.core.Entity;
import com.dmitrynikol.pingpong.game.math.Position2D;
import com.dmitrynikol.pingpong.game.math.Size2D;
import com.dmitrynikol.pingpong.game.sound.SoundManagerFactory;
import com.dmitrynikol.pingpong.game.sound.SoundManagerFactory.SoundResource;
import com.dmitrynikol.pingpong.game.util.GameUtils;

/**
 * An entity which represents enemy or players opponent.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class Opponent extends Entity {
	
	public static final int WIDTH = 16;
	public static final int HEIGHT = WIDTH * 5;
	public static final float MAX_SPEED = 8f;
	public static final float DAMPING = 0.05f;
	public static final int MIN_HEIGHT = WIDTH * 3;
	public static final int DECREASE_UNIT = 12;
	
	private Ball ball;
	
	public Opponent(float x, float y, Ball ball) {
		this.setPosition(new Position2D(x, y));
		this.setSize(new Size2D(WIDTH, HEIGHT));
		
		this.ball = ball;
	}

	@Override
	public void update() {
		if (GameUtils.checkCollisions(this, ball)) {
			SoundManagerFactory.getSoundManager().playSound(SoundResource.BUMP);
			// exclude situation when a ball gets stuck in the object
			ball.setX(ball.getX() - ball.getWidth());
			ball.reverseX(getCenter());
		}
		
		float speed = (ball.getCenter() - getCenter()) * DAMPING;
		
		if (speed > MAX_SPEED) {
			speed = MAX_SPEED;
		}
		if (speed < -MAX_SPEED) {
			speed = -MAX_SPEED;
		}
		
		this.setY(this.getY() + speed);
	}
	
	@Override
	public void render() {
		GL11.glColor4f(1.0f, 0.0f, 0.5f, 1.0f);
		super.render();
	}
	
	public void increaseObject() {
		this.setHeight(this.getHeight() + DECREASE_UNIT);
	}
	public void decreaseObject() {
		if (this.getHeight() > MIN_HEIGHT) {
			this.setHeight(this.getHeight() - DECREASE_UNIT);
		}
	}
}
