package com.dmitrynikol.pingpong.game.main;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.dmitrynikol.pingpong.game.core.Entity;
import com.dmitrynikol.pingpong.game.main.Wall.Type;
import com.dmitrynikol.pingpong.game.sound.SoundManagerFactory;
import com.dmitrynikol.pingpong.game.sound.SoundManagerFactory.SoundResource;
import com.dmitrynikol.pingpong.game.util.GameUtils;

/**
 * Class with all the game objects and their handlers.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class Game {
	
	private List<Entity> objects;
	private Player player;
	private Opponent opponent;
	private Ball ball;
	
	private int playerScore;
	private int opponentScore;
	
	private boolean running = true;
	
	public Game() {
		objects = new ArrayList<Entity>();
		
		playerScore = 0;
		opponentScore = 0;
		
		ball = new Ball(Display.getWidth() / 2 - Ball.SIZE / 2, 
				Display.getHeight() / 2 - Ball.SIZE / 2);
		
		player = new Player(Player.WIDTH * 3, Display.getHeight() / 2 - Player.HEIGHT / 2, ball);
		
		opponent = new Opponent(Display.getWidth() - Opponent.WIDTH * 2, 
				Display.getHeight() / 2 - Opponent.HEIGHT / 2, ball);
		
		Wall northWall = new Wall(Wall.STANDARD_SIZE * 2, Display.getHeight() - Wall.STANDARD_SIZE * 3, 
				Display.getWidth() - Wall.STANDARD_SIZE * 4, Wall.STANDARD_SIZE, ball, Type.PRIMARY);
		Wall southWall = new Wall(Wall.STANDARD_SIZE * 2, Wall.STANDARD_SIZE * 2, 
				Display.getWidth() - Wall.STANDARD_SIZE * 4, Wall.STANDARD_SIZE, ball, Type.PRIMARY);
		
		Wall westWall = new Wall(Wall.STANDARD_SIZE, Wall.STANDARD_SIZE * 2, 
				Wall.STANDARD_SIZE, Display.getHeight() - Wall.STANDARD_SIZE * 4, ball, Type.SECONDARY);
		Wall eastWall = new Wall(Display.getWidth() - Wall.STANDARD_SIZE * 2, Wall.STANDARD_SIZE * 2, 
				Wall.STANDARD_SIZE, Display.getHeight() - Wall.STANDARD_SIZE * 4, ball, Type.SECONDARY);
		
		objects.add(ball);
		objects.add(player);
		objects.add(opponent);
		
		objects.add(northWall);
		objects.add(southWall);
		objects.add(westWall);
		objects.add(eastWall);
	}
	
	public void getInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.moveY(1);
		} 
		else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.moveY(-1);
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.moveX(1);
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.moveX(-1);
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			running = !running;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			// here should be implement pause
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			// here should be implement logic to start the game
		} else if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			try {
				GameUtils.setEnableFullscreen(!Display.isFullscreen());
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		for (Entity object : objects) {
			object.update();
		}
		
		if (ball.getX() > Display.getWidth()) {
			playerScore++;
			ball.resetPosition();
			player.increaseObject();
			opponent.decreaseObject();
			GameUtils.alignGameSurface();
			SoundManagerFactory.getSoundManager().stopAll();
			SoundManagerFactory.getSoundManager().playSound(SoundResource.FREZEE);
		}
		
		if (ball.getX() < 0) {
			opponentScore++;
			ball.resetPosition();
			opponent.increaseObject();
			player.decreaseObject();
			GameUtils.randomlyRotateSurface();
			SoundManagerFactory.getSoundManager().stopAll();
			SoundManagerFactory.getSoundManager().playSound(SoundResource.FREZEE);
		}
	}
	
	public void render() {
		for (Entity object : objects) {
			object.render();
		}
	}
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	public int getOpponentScore() {
		return opponentScore;
	}
}
