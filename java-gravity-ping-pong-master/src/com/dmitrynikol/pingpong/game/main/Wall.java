package com.dmitrynikol.pingpong.game.main;

import org.lwjgl.opengl.GL11;

import com.dmitrynikol.pingpong.game.core.Entity;
import com.dmitrynikol.pingpong.game.math.Position2D;
import com.dmitrynikol.pingpong.game.math.Size2D;
import com.dmitrynikol.pingpong.game.util.GameUtils;

/**
 * An entity which represents wall in the game.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class Wall extends Entity {
	
	/**
	 * Enumeration represents the wall type.
	 */
	enum Type {
		/**
		 * Wall from which the ball bounced back into play.	
		 */
		PRIMARY,
		/**
		 * Wall like a game grid.
		 */
		SECONDARY;
	}
	
	public static final int STANDARD_SIZE = 16;
	
	private Ball ball;
	private Type type;
	
	public Wall(float x, float y, float width, float height, Ball ball, Type type) {
		this.setPosition(new Position2D(x, y));
		this.setSize(new Size2D(width, height));
		
		this.ball = ball;
		this.type = type;
	}

	@Override
	public void update() {
		if (GameUtils.checkCollisions(this, ball)) {
			ball.reverseY();
		}
	}
	
	@Override
	public void render() {
		GL11.glColor4f(0.7f, 0.7f, 0.7f, type.equals(Type.PRIMARY) ? 1.0f : 0.3f);
		super.render();
	}
}
