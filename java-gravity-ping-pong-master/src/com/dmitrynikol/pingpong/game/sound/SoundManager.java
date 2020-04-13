package com.dmitrynikol.pingpong.game.sound;

import java.util.ArrayList;
import java.util.List;

import com.dmitrynikol.pingpong.game.sound.SoundManagerFactory.SoundResource;

/**
 * SoundManager for OpenAL that takes care of the sound you want to play.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class SoundManager {
	
	private SoundPlayer soundPlayer;
	private List<SoundResource> soundList;
	private boolean mute = false;
	
	public SoundManager() {
		soundPlayer = new SoundPlayer();
		soundList = new ArrayList<SoundResource>();
	}
	
	/**
	 * Register sound in sound system
	 * 
	 * @param resource the enumeration item of sound which will be registered
	 */
	public void registerSound(SoundResource sound) {
		if (!soundList.contains(sound)) {
			soundList.add(sound);
			soundPlayer.addSound(sound.getUri());
		}
	}
	
	/**
	 * Unregisters the sound
	 * 
	 * @param resource in sound system
	 */
	public void unregisteredSound(SoundResource resource) {
		// not implemented yet
		//soundMap.remove(resource).stop();
	}
	
	/**
	 * Whether the sound with this key is registered.
	 * 
	 * @param sound in sound system
	 * @return whether a sound is registered 
	 */
	public boolean isSoundRegistered(SoundResource sound) {
		return soundList.contains(sound);
	}
	
	/**
	 * Start playing the sound.
	 * 
	 * @param sound in sound system
	 */
	public void playSound(SoundResource sound) {
		if (soundList.contains(sound) && mute == false) {
			soundPlayer.playSound(sound.ordinal());
		}
	}
	
	/**
	 * Plays a sound effect.
	 * 
	 * @param sound in sound system
	 */
	public void playEffect(SoundResource sound) {
		if (soundList.contains(sound) && mute == false) {
			soundPlayer.playEffect(sound.ordinal());
		}
	}
	
	/**
	 * Stop the sound.
	 * 
	 * @param sound in sound system
	 */
	public void stopSound(SoundResource sound) {
		if (soundList.contains(sound) && mute == false) {
			soundPlayer.stop();
		}
	}
	
	/**
	 * Stop all sound.
	 * 
	 * @param sound in sound system
	 */
	public void stopAll() {
		soundPlayer.stop();
	}
	
	/**
	 * Set pause for current song.
	 * 
	 * @param sound in sound system
	 */
	public void pauseSound(SoundResource sound) {
		if (soundList.contains(sound) && mute == false) {
			soundPlayer.pause();
		}
	}
	
	/**
	 * Sets a loop on current sound and play it immediately.
	 * 
	 * @param loop responsible for loop mode
	 */
	public void setLoop(SoundResource sound, boolean loop) {
		if (soundList.contains(sound)) {
			// not implemented yet
		}
	}
	
	/**
	 * Sets the muting state on current sound.
	 * 
	 * @param mute true for muting, false otherwise
	 */
	public void setMute(final boolean mute) {
		this.mute = mute;
		
		if (mute) {
			soundPlayer.setMute(mute);
		}
	}
	
	/**
	 * Clean up all resources.
	 */
	public void destroy() {
		soundPlayer.destroy();
	}
}
