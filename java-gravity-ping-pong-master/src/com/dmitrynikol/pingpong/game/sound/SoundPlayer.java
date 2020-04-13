package com.dmitrynikol.pingpong.game.sound;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

/**
 * The SoundPlayer class for OpenAL enables to easily include sounds in applications.
 * It can play sound files in different formats.
 * 
 * You shouldn't work with this class directly, instead you should use SoundManager, 
 * it will fit your needs.
 * 
 * @author Dmitry Nikolaenko
 * 
 */
public final class SoundPlayer {
	
	/** Support at most 256 buffers*/
	private int[] buffers = new int[256];

	/** Number of sources is limited by the user */
	private int[] sources;
	 
	/** Buffers hold sound data, our internal scratch buffer */
	private IntBuffer scratchBuffer = BufferUtils.createIntBuffer(256);
	 
	/** Whether we're running in no sound mode */
	private boolean soundOutput;
	 
	/** Current index in our buffers */
	private int bufferIndex;
	 
	/** Current index in our source list */
	private int soundResourceIndex;
	
	protected SoundPlayer() {
		initialize(8);
	}
	
	/**
	 * Initialize everything that OpenAL needs to do for us.
	 * 
	 * @param channels the number of channels to create
	 */
	public void initialize(int channels) {
		try {
			// creates a single OpenAL context through Alc and sets it to current
			AL.create();

			// allocate sources
			scratchBuffer.limit(channels);
			// bind the buffer with the source.
			AL10.alGenSources(scratchBuffer);
			scratchBuffer.rewind();
			scratchBuffer.get(sources = new int[channels]);

			// could we allocate all channels?
			if (AL10.alGetError() != AL10.AL_NO_ERROR) {
				throw new LWJGLException("Unable to allocate " + channels + " sources");
			}

			// we have sound
			soundOutput = true;
		} catch (LWJGLException exception) {
			exception.printStackTrace();
			System.out.println("Sound disabled");
		}
	}
	
	/**
	 * Adds a sound to the Sound Managers pool.
	 * 
	 * @param path the path to file to load
	 * @return index into sound buffer list
	 */
	public int addSound(String path) {
		// generate 1 buffer entry
		scratchBuffer.rewind().position(0).limit(1);
		// create the buffer objects and store them in the variable we passed it
		AL10.alGenBuffers(scratchBuffer);
		buffers[bufferIndex] = scratchBuffer.get(0);

		// load wave data from buffer
		WaveData wavefile = null;
		try {
			// ALUT is not available in the LWJGL binding, due to license issues
			// so we use the WaveData class to load sound files instead.
			wavefile = WaveData.create(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// copy to buffers
		AL10.alBufferData(buffers[bufferIndex], wavefile.format, wavefile.data, wavefile.samplerate);

		// unload file again
		wavefile.dispose();
		
		// return index for this sound
		return bufferIndex++;
	}
	
	/**
	 * Plays a sound effect.
	 * 
	 * @param buffer the index to play gotten from addSound
	 */
	public void playEffect(int buffer) {
		if (soundOutput) {
			// make sure we never choose last channel, since it is used for special sounds
			int channel = sources[(soundResourceIndex++ % (sources.length - 1))];

			// link buffer and source, and play it
			AL10.alSourcei(channel, AL10.AL_BUFFER, buffers[buffer]);
			AL10.alSourcePlay(channel);
		}
	}
	
	/**
	 * Plays a sound on last source.
	 * 
	 * @param buffer the index to play gotten from addSound
	 */
	public void playSound(int buffer) {
		if (soundOutput) {
			AL10.alSourcei(sources[sources.length - 1], AL10.AL_BUFFER, buffers[buffer]);
			AL10.alSourcePlay(sources[sources.length - 1]);
		}
	}
	
	/**
	 * Set pause mode for current sound.
	 */
	public void pause() {
		if (soundOutput) {
			AL10.alSourcePause(sources[sources.length - 1]);
		}
	}
	
	/**
	 * Stop play back of this sound.
	 */
	public void stop() {
		if (soundOutput) {
			AL10.alSourceStop(sources[sources.length - 1]);
		}
	}
	
	/**
	 * Destroy current sound.
	 * We have allocated memory for our buffers and sources which needs
     * to be returned to the system. This function frees that memory.
	 */
	public void destroy() {
		if (soundOutput) {
			// stop playing sounds
			scratchBuffer.position(0).limit(sources.length);
			scratchBuffer.put(sources).flip();
			AL10.alSourceStop(scratchBuffer);

			// destroy sources
			AL10.alDeleteSources(scratchBuffer);

			// destroy buffers
			scratchBuffer.position(0).limit(bufferIndex);
			scratchBuffer.put(buffers, 0, bufferIndex).flip();
			AL10.alDeleteBuffers(scratchBuffer);

			// destroy OpenAL
			AL.destroy();
		}
	}

	/**
	 * Whether a sound is playing on last source.
	 * 
	 * @return true if a source is playing right now on source n
	 */
	public boolean isPlayingSound() {
		return AL10.alGetSourcei(sources[sources.length - 1], AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
	}
	
	/**
	 * Sets the muting state of the audio.
	 * 
	 * @param mute true for muting, false otherwise
	 */
	public void setMute(boolean mute) {
		//currentSound.setVolume(mute ? 0 : 100);
	}
	
	/**
	 * Set this sound's volume (range 0..100).
	 * 
	 * @param volume new value for sound volume
	 */
	public void setVolume(int volume) {
		//currentSound.setVolume(volume);
	}
	
	/**
	 * Set the left/right speaker balance (range -100..100).
	 * 
	 * @param balance new balance (range -100..100)
	 */
	public void setBalance(int balance) {
		//currentSound.setBalance(balance);
	}
}
