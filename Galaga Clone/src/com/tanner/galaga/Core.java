package com.tanner.galaga;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Core {
	private Logger log;
	private final int x_res = 800;
	private final int y_res = 600;
	private final String title = "Tanner's Galaga Clone";
	private long last_time;

	// Game variables
	float xpos = 400;
	float ypos = 400;
	float rotation = 0;
	long lastFrame;
	int fps;
	long lastFPS;
	private Sprite test_sprite;
	private TextureLoader textureLoader;

	/**
	 * @param args
	 *            Command line arguments.
	 */
	public void start() {
		// Initialization
		initLogger();
		initDisplay();
		initGL();
		initTime();
		initTexture();
		initSprite();

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(60);
		}
		log.dbg("Closing.");
		Display.destroy();
	}

	private void pollInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
					log.dbg("Key pressed: SPACE");
				}
			}
		}
	}

	private void initLogger() {
		log = new Logger("[Galaga]", System.out);
		log.dbg("Logging initialized.");
	}

	private void initDisplay() {
		log.info("Initializing screen subsystem...");
		try {
			Display.setDisplayMode(new DisplayMode(x_res, y_res));
			Display.create();
			log.info("Screen initialized.");
		} catch (LWJGLException e) {
			log.error("Caught exception. Info: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void initGL() {
		log.info("Initializing OpenGL");
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, x_res, y_res, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	private void initTime() {
		lastFPS = getTime();
	}
	
	private void initTexture() {
		log.info("Initializing texture engine..");
		textureLoader = new TextureLoader();
	}
	private void initSprite() {
		log.info("Initializing test sprite.");
		test_sprite = new Sprite(textureLoader, "test.gif");
	}

	private void update(int delta) {
		rotation += 0.15f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			xpos -= 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			xpos += 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_UP))
			ypos -= 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			ypos += 0.35f * delta;

		// Keep quad on screen
		if (xpos < 0)
			xpos = 0;
		if (xpos > x_res)
			xpos = x_res;
		if (ypos > y_res)
			ypos = y_res;
		if (ypos < 0)
			ypos = 0;

		updateFPS();
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle(title + " | " + "FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	private void renderGL() {
		clearGL();
		test_sprite.draw((int) xpos, (int) ypos);
		//drawQuad();
	}

	private void clearGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	private void drawQuad() {
		// TODO adapt to suit needs
		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		GL11.glPushMatrix();
		GL11.glTranslatef(xpos, ypos, 0);
		GL11.glRotatef(rotation, 0f, 0f, 1f);
		GL11.glTranslatef(-xpos, -ypos, 0);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(xpos - 50, ypos - 50);
		GL11.glVertex2f(xpos + 50, ypos - 50);
		GL11.glVertex2f(xpos + 50, ypos + 50);
		GL11.glVertex2f(xpos - 50, ypos + 50);
		GL11.glEnd();

		GL11.glPopMatrix();
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - last_time);
		last_time = time;
		return delta;
	}

}
