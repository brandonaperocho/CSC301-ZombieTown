package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;
import java.io.InputStream;
import org.lwjgl.input.*;

public class LoginState extends BasicGameState {

	private final int ID;
	private Image BGs, HDR, LOGIN, LOGIN_HOVER, 
			LOGO, REGISTER, REGISTER_HOVER, 
			QUIT, QUIT_HOVER, CURSOR, VOLUME;
	private int currButton = 2;
	private SpriteSheet crowSheet, miniCrowSheet;
	private Animation crowAnimation, miniCrowAnimation;
	private float CrowMovement = 0.3f;
	private float miniCrowMovement = 0.2f;
	Music mainMusic;
	
	
	public LoginState(int num) {
		this.ID = num; 
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		BGs = new Image("/OfficialGame/Sprites/Background/Background.png");
		LOGO = new Image("/OfficialGame/Sprites/Logo/ZombieTown_Logo.png");
		CURSOR = new Image("/OfficialGame/Sprites/cursor.png");
		gc.setMouseCursor(CURSOR, 0, 0);
		
	    mainMusic = new Music("/OfficialGame/music/GameMusic.ogg");
	    if(!mainMusic.playing()){
	      mainMusic.loop();
	      mainMusic.setVolume((float) 0.4);
	    }
		
		VOLUME = new Image("/OfficialGame/Sprites/Volume.png");
		LOGIN = new Image("/OfficialGame/Sprites/buttonImages/login.png");
		LOGIN_HOVER = new Image("/OfficialGame/Sprites/buttonImages/login_hover.png");
		REGISTER = new Image("/OfficialGame/Sprites/buttonImages/register.png");
		REGISTER_HOVER = new Image("/OfficialGame/Sprites/buttonImages/register_hover.png");
		QUIT = new Image("/OfficialGame/Sprites/buttonImages/quit.png");
		QUIT_HOVER = new Image("/OfficialGame/Sprites/buttonImages/quit_hover.png");
		
		crowSheet = new SpriteSheet(new Image("/OfficialGame/Sprites/crow-sprite.png"), 108, 70);
		miniCrowSheet = new SpriteSheet(new Image("/OfficialGame/Sprites/mini-crows.png"), 50, 25);
		
		crowAnimation = new Animation(crowSheet, 160);
		miniCrowAnimation = new Animation(miniCrowSheet, 300);
		
		try {
	        InputStream inputStream = ResourceLoader.getResourceAsStream("/OfficialGame/Font/Pixeled.ttf");

	        Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	        awtFont2 = awtFont2.deriveFont(24f);
	        new TrueTypeFont(awtFont2, false);
		} catch (Exception e) {
	        e.printStackTrace();
	    } 
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		g.drawImage(BGs, -300, -200);
		g.drawImage(LOGO, 165, 10); 
		g.drawImage(VOLUME, 950, 10);
		
		crowAnimation.draw(-100 + CrowMovement, 60);
		miniCrowAnimation.draw(-120 + miniCrowMovement, 409);
		miniCrowAnimation.draw(-23 + miniCrowMovement, 400);
		miniCrowAnimation.draw(-555 + miniCrowMovement, 420);
		miniCrowAnimation.draw(-223 + miniCrowMovement, 390);
		miniCrowAnimation.draw(-90 + miniCrowMovement, 410);
		miniCrowAnimation.draw(-123 + miniCrowMovement, 395);
		miniCrowAnimation.draw(-40 + miniCrowMovement, 398);
		//miniCrowAnimation.draw(-80 + count, 0);
		CrowMovement += 0.6f;
		if (CrowMovement > 1500) {
			CrowMovement = 0.6f;
		}
		miniCrowMovement += 0.2f;
		if (CrowMovement > 1500) {
			CrowMovement = 0.2f;
		}
	
		g.drawImage(LOGIN, 390, 380);
		g.drawImage(REGISTER, 390, 480);
		g.drawImage(QUIT, 390, 580);

		if (currButton == 0) {
			g.drawImage(LOGIN_HOVER, 390, 380);
		} else if (currButton == 1) {
			g.drawImage(REGISTER_HOVER, 390, 480);
		} else if (currButton == 2) {
			g.drawImage(QUIT_HOVER, 390, 580);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {	
		Input input = gc.getInput();
		input.enableKeyRepeat();
		// ----------------------------------------------------------------------
		// DETECT WHICH BUTTON WILL BE PRESSED WHEN USING KEYBOARD CONTROL
		// ----------------------------------------------------------------------
		if (input.isKeyPressed(Input.KEY_UP)) {
			if (currButton > 0) {
				currButton = currButton - 1;
			}
		}
		if (input.isKeyPressed(Keyboard.KEY_DOWN)) {
			if (currButton < 2) {
				currButton = currButton + 1;
			}
		}
		if (input.isKeyPressed(Keyboard.KEY_RETURN)) {
			// If LOGIN is pressed.
			if (currButton == 0) {
				// GO TO STATE 1 -> LOGIN STATE
			    //sbg.getState(1).init(gc, sbg);
				sbg.enterState(1);
			}
			// If REGISTER is pressed.
			else if (currButton == 1) {
				// GO TO STATE 2 -> REGISTER STATE
			    //sbg.getState(2).init(gc, sbg);
				sbg.enterState(2);
			}
			// If EXIT GAME is pressed.
			else if (currButton == 2) {
				// EXIT THE GAME
				System.exit(0);
			}
		}
		// ----------------------------------------------------------------------
		// DETECT WHICH BUTTON WILL BE PRESSED WHEN USING MOUSE CONTROL
		// ----------------------------------------------------------------------
		int PosX = Mouse.getX();
		int PosY = Mouse.getY();
		// IF HOVERING LOGIN
		if ((PosX >= 380 && PosX <= 680) && (PosY >= 280 && PosY <= 348)) {
			currButton = 0;	
			// If LOGIN is pressed.
			if (Mouse.isButtonDown(0)) {
				// GO TO STATE 1 -> LOGIN STATE
			    //sbg.getState(1).init(gc, sbg);
				sbg.enterState(1);
			}
		}
		// IF HOVERING REGISTER
		else if ((PosX >= 380 && PosX <= 680) && (PosY >= 180 && PosY <= 248)) {
			currButton = 1;	
			// If REGISTER is pressed.
			if (Mouse.isButtonDown(0)) {
				// GO TO STATE 2 -> REGISTER STATE
			    //sbg.getState(2).init(gc, sbg);
				sbg.enterState(2);
			}
		}		
		// IF HOVERING EXIT GAME
		else if ((PosX >= 380 && PosX <= 680) && (PosY >= 80 && PosY <= 148)) {
			currButton = 2;	
			// If EXIT GAME is pressed.
			if (Mouse.isButtonDown(0)) {
				System.exit(0);
			}
		} 
		else if ((PosX >= 960 && PosX <= 990) && (PosY >= 675 && PosY <= 715)) { 
          // If EXIT GAME is pressed.
          if (gc.getInput().isMousePressed(0)) {
            if (mainMusic.playing()){
              mainMusic.stop();
            }else{
              mainMusic.play();
              mainMusic.setVolume(0.1f);
            }
          }
        }else {
			currButton = -1;
		}
		// ----------------------------------------------------------------------
		// ----------------------------------------------------------------------
		
	}


	@Override
	public int getID() {
		return this.ID;
	}

}
