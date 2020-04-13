package com.dmitrynikol.pingpong.game.math;

/**
 * 2D dimensions of an object.
 * 
 * @author Dmitry Nikolaenko
 * 
 */
public class Size2D {
	private float width;
	private float height;
	
	public Size2D(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "Size: (" + width + ", " + height + ")";
	}
}