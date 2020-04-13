package com.dmitrynikol.pingpong.game.sound;

/**
 * Factory that allow to get access to the sound manager.
 * 
 * @author Dmitry Nikolaenko
 * 
 */
public class SoundManagerFactory {
	
	public enum SoundResource {
		SHIELD("resources/sound/shield.wav"),
		FREZEE("resources/sound/frezee.wav"),		
		BUMP("resources/sound/bump.wav"),
		IMPERIAL_MARCH("resources/sound/imperial_march.wav");
		
		private String name;
		
		private SoundResource(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name().toLowerCase();
		}
		
		public String getUri() {
			return name;
		}
	}
	
	private static SoundManager INSTANCE = null;
	
	public static SoundManager getSoundManager() {
		if (INSTANCE == null) {
			INSTANCE = new SoundManager();
		}
		return INSTANCE;
	}
}
