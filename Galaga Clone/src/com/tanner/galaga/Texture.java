package com.tanner.galaga;
import org.lwjgl.opengl.GL11;

/**
 * Defines a texture. 
 * Borrowed from LWGL SpaceInvaders example, all license retained therein.
 * @author Tanner Danzey <arkaiad@gmail.com>
 *
 */
public class Texture {
	// GL Target Type
	private int target;
	// GL Texture ID
	private int textureID;
	// Image dimensions
	private int height;
	private int width;
	
	// Texture dimensions
	private int texWidth;
	private int texHeight;
	
	// Image -> Texture ratios
	private float widthRatio;
	private float heightRatio;
	
	/**
	 * Create a new texture.
	 * 
	 * @param target the GL target
	 * @param textureID the GL texture ID
	 */
	public Texture(int target, int textureID) {
		this.target = target;
		this.textureID = textureID;
	}
	
	/**
	 * Bind specified GL context to texture
	 */
	public void bind() {
		GL11.glBindTexture(target, textureID);
	}
	
	/**
	 * Set width of image.
	 * 
	 * @param width The width of the image
	 */
	public void setWidth(int width) {
		this.width = width;
		setWidth();
	}
	
	/**
	 * Set height of image.
	 * 
	 * @param height The height of the image
	 */
	public void setHeight(int height) {
		this.height = height;
		setHeight();
	}
	
	/**
	 * Get image height.
	 * @return Height of original image
	 */
	public float getImageHeight() {
		return height;
	}
	
	/**
	 * Get image width.
	 * @return Width of original image
	 */
	public float getImageWidth() {
		return width;
	}
	
	/**
	 * Get height of physical texture.
	 * @return Height of physical texture
	 */
	public float getHeight() {
		return heightRatio;
	}
	
	/**
	 * Get width of physical texture.
	 * @return Width of physical texture
	 */
	public float getWidth() {
		return widthRatio;
	}
	
	/**
	 * Set height of texture
	 * @param texHeight The height of the texture
	 */
	public void setTextureHeight(int texHeight) {
		this.texHeight = texHeight;
		setHeight();
	}
	
	/**
	 * Set width of texture
	 * @param texWidth The width of the texture
	 */
	public void setTextureWidth(int texWidth) {
		this.texWidth = texWidth;
		setWidth();
	}
	
	/**
	 * Set height of texture. Updates ratio as well.
	 */
	private void setHeight() {
		if (texHeight != 0) {
			heightRatio = ((float) height) / texHeight;
		}
	}
	
	/**
	 * Set width of texture. Updates ratio as well.
	 */
	private void setWidth() {
		if (texWidth != 0) {
			widthRatio = ((float) width) / texWidth;
		}
	}
}
