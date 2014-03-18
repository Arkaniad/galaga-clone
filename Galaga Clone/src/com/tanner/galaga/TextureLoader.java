package com.tanner.galaga;

import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;
/**
 * Utility class to load textures for OpenGL. Explicitly 2D graphics.
 * Borrowed from LWJGL Space Invaders example. All license retained therein.
 * @author Tanner Danzey <arkaniad@gmail.com>
 *
 */
public class TextureLoader {
	/** Table of textures loaded in this instance */
	private HashMap<String, Texture> table = new HashMap<String, Texture>();
	/** Color model */
	private ColorModel glAlphaColorModel;
	private ColorModel glColorModel;
	
	/** Buffer for ID's */
	private IntBuffer textureIDBuffer = BufferUtils.createIntBuffer(1);
	
	/**
	 * Create new texture loader based on game panel
	 */
	public TextureLoader() {
		glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
													new int[] {8,8,8,8},
													true,
													false,
													ComponentColorModel.TRANSLUCENT,
													DataBuffer.TYPE_BYTE);
	    glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
	    										new int[] {8,8,8,0},
	    										false,
	    										false,
	    										ComponentColorModel.OPAQUE,
	    										DataBuffer.TYPE_BYTE);
	    										
	}
	
	/**
	 * Create a new texture ID.
	 * @return A new texture ID
	 */
	private int createTextureID() {
		GL11.glGenTextures(textureIDBuffer);
		return textureIDBuffer.get(0);
	}
	
	/**
	 * Load a texture.
	 * @param resourceName The location of resource to load
	 * @return The loaded texture
	 * @throws IOException indicates a resource failure
	 */
	public Texture getTexture(String resourceName) throws IOException {
		Texture tex = table.get(resourceName);
		if(tex != null) {
			return tex;
		}
		tex = getTexture(resourceName, GL_TEXTURE_2D, GL_RGBA, GL_LINEAR, GL_LINEAR);
		table.put(resourceName, tex);
		return tex;
	}
	
	/**
	 * Load texture into OpenGL from an image reference on disk.
	 * 
	 * @param resourceName The location of resource to load
	 * @param target The GL Target to load texture against
	 * @param dstPixelFormat The pixel format of the screen
	 * @param minFilter minimising filter
	 * @param magFilter magnification filter
	 * @return Loaded texture
	 * @throws IOException indicates failure to access resource
	 */
	public Texture getTexture(String resourceName, int target, int dstPixelFormat, int minFilter, int magFilter) throws IOException {
		int srcPixelFormat;
		//TODO Finish this method
		
		// Create texture ID
		int textureID = createTextureID();
		Texture texture = new Texture(target, textureID);
				
		// Bind texture
		GL11.glBindTexture(target, textureID);
		
		BufferedImage bufferedImage = loadImage(resourceName);
		texture.setWidth(bufferedImage.getWidth());
		texture.setHeight(bufferedImage.getHeight());
	}
}
