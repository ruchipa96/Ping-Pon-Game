package com.dmitrynikol.pingpong.game.core;

import com.dmitrynikol.pingpong.game.draw.Draw;
import com.dmitrynikol.pingpong.game.math.Position2D;
import com.dmitrynikol.pingpong.game.math.Size2D;

/**
 * An entity represents any element that appears in the game.
 * Every game element should extends this class.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public abstract class Entity {
	
	/**
	 * The current coordinate of this entity.
	 */
	private Position2D position;
	
	/**
	 * The current size - width and height of this entity.
	 */
	private Size2D size;
	
	public abstract void update();
	
	public void render() {
		Draw.rect(position.getX(), position.getY(), size.getWidth(), size.getHeight());
	}
	
	public float getX() {
		return position.getX();
	}
	
	public float getY() {
		return position.getY();
	}
	
	public void setX(float x) {
		position.setX(x);
	}
	
	public void setY(float y) {
		position.setY(y);
	}
	
	public float getWidth() {
		return size.getWidth();
	}
	
	public float getHeight() {
		return size.getHeight();
	}
	
	public void setHeight(float height) {
		size.setHeight(height);
	}
	
	public void setWidth(float width) {
		size.setWidth(width);
	}
	
	public float getCenter() {
		return getY() + getHeight() / 2;
	}
	
	public Size2D getSize() {
		return size;
	}

	public void setSize(Size2D size) {
		this.size = size;
	}
	
	public Position2D getPosition() {
		return position;
	}

	public void setPosition(Position2D position) {
		this.position = position;
	}
}