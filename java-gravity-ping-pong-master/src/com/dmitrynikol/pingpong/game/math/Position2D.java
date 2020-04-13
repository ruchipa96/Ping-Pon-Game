package com.dmitrynikol.pingpong.game.math;

/**
 * A basic 2D position components.
 * 
 * @author Dmitry Nikolaenko
 * 
 */
public class Position2D {
	private float x;
	private float y;

	public Position2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Position2D(Position2D position) {
		this.x = position.x;
		this.y = position.y;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Position: (" + x + ", " + y + ")";
	}
}