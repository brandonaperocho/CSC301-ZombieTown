package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.Font;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lwjgl.input.*;

public class MenuState extends BasicGameState {

    private final int ID;
    private Image BG, CLOUDS, BAR, BAR_HOVER;
    private SpriteSheet miniCrowSheet;
    private Animation miniCrowAnimation;
    private TrueTypeFont pixel;
    private int currButton = 5; // 0 - NG, 1 - LB, 2 - PS, 3 - ST, 4 - EG
    private float move = 0.8f;
    private float miniCrowMovement = 0.2f;
    protected Double score;

    public MenuState(int num) {
        this.ID = num; 
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	CLOUDS = new Image("/OfficialGame/Sprites/dark-clouds.png");
        BG = new Image("/OfficialGame/Sprites/Background/Background2.png");
        miniCrowSheet = new SpriteSheet(new Image("/OfficialGame/Sprites/mini-crows.png"), 50, 25);
		miniCrowAnimation = new Animation(miniCrowSheet, 300);
        
        BAR = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
        BAR_HOVER = new Image("/OfficialGame/Sprites/buttonImages/Menubar_hover.png");
        
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("/OfficialGame/Font/Pixeled.ttf");

            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(17f);
            pixel = new TrueTypeFont(awtFont2, false);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        score=null; 
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {    
        // DRAW THE BACKGROUND AND HEADER
        g.drawImage(BG, -300, -200);
        g.drawImage(CLOUDS, 1+move, 1);
        g.drawImage(BAR, 200, 430);
        g.drawImage(BAR, 200, 530);
        g.drawImage(BAR, 600, 430);
        g.drawImage(BAR, 600, 530);
        g.drawImage(BAR, 400, 630);
        move += 0.1f;
        
        if (move > 1500) {
            move = 0.8f;
        }
        
        miniCrowAnimation.draw(-120 + miniCrowMovement, 409);
		miniCrowAnimation.draw(-23 + miniCrowMovement, 400);
		miniCrowAnimation.draw(-555 + miniCrowMovement, 420);
		miniCrowAnimation.draw(-223 + miniCrowMovement, 390);
		miniCrowAnimation.draw(-90 + miniCrowMovement, 410);
		miniCrowAnimation.draw(-123 + miniCrowMovement, 395);
		miniCrowAnimation.draw(-40 + miniCrowMovement, 398);
		//miniCrowAnimation.draw(-80 + count, 0);
		miniCrowMovement += 0.2f;
		if (miniCrowMovement > 1500) {
			miniCrowMovement = 0.2f;
		}
        
        if (currButton == 0) {
            g.drawImage(BAR_HOVER, 200, 430);
        } else if (currButton == 1) {
            g.drawImage(BAR_HOVER, 200, 530);
        } else if (currButton == 2) {
            g.drawImage(BAR_HOVER, 600, 430);
        } else if (currButton == 3) {
            g.drawImage(BAR_HOVER, 600, 530);
        } else if (currButton == 4) {
            g.drawImage(BAR_HOVER, 400, 630);
        }

        if(score==null){
          Connection dbconn = (Connection) Database.setupConn();
          Statement stmt;
          try {
            String query = "select score from accounts where user=\'" + LoginAttemptState.user + "\'";
            stmt = (Statement) dbconn.createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery(query);
            rs.next();
            score =rs.getDouble(1);
            Database.closeConn(dbconn);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
        g.setColor(Color.black);
        g.setFont(pixel);
        g.drawString("SINGLE PLAYER", 250, 448);
        g.drawString("LEADERBOARDS", 250, 548);
        g.drawString("VIEW STATS", 670, 448);
        g.drawString("STORE", 710, 548);
        g.drawString("LOGOUT", 505, 648);
        g.setColor(Color.white);
        g.drawString("WELCOME TO ZOMBIE TOWN, " + LoginAttemptState.user, 30, 325);
        g.drawString("YOUR HIGH SCORE IS: " + score, 30, 350);
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
            if (currButton < 5) {
                currButton = currButton + 1;
            }
        }
        if (input.isKeyPressed(Keyboard.KEY_RETURN)) {
            // If START NEW SINGLEPLAYER GAME is pressed.
            if (currButton == 0) {
                // GO TO STATE 7 -> GAME STATE
                sbg.enterState(7);
            }
            // If VIEW LEADERBOARDS is pressed.
            if (currButton == 1) {
                // GO TO STATE 5 -> LEADERBOARDS STATE
                sbg.getState(5).init(gc, sbg);
                sbg.enterState(5);
            }
            // If VIEW PERSONAL STATS is pressed.
            if (currButton == 2) {
                // GO TO STATE 6 -> STATS STATE
                sbg.enterState(6);
            }
            // If STORE is pressed.
            if (currButton == 3) {
                // GO TO STATE 5 -> STORE STATE
                sbg.enterState(4);
            }
            // If EXIT GAME is pressed.
            if (currButton == 4) {
                // EXIT THE GAME
                sbg.getState(1).init(gc, sbg);
            	sbg.enterState(1);
            }
        }
        // ----------------------------------------------------------------------
        // DETECT WHICH BUTTON WILL BE PRESSED WHEN USING MOUSE CONTROL
        // ----------------------------------------------------------------------
        int PosX = Mouse.getX();
        int PosY = Mouse.getY();

        // IF HOVERING START NEW SINGLEPLAYER GAME
        if ((PosX >= 190 && PosX <= 490) && (PosY >= 230 && PosY <= 290)) {
            currButton = 0; 
            // If START NEW SINGLEPLAYER GAME is pressed.
            if (Mouse.isButtonDown(0)) {
                // GO TO STATE 7 -> GAME STATE
                sbg.getState(7).init(gc, sbg);
                sbg.enterState(7);
            }
        }
        // IF HOVERING VIEW LEADERBOARDS
        else if ((PosX >= 190 && PosX <= 490) && (PosY >= 130 && PosY <= 190)) {
            currButton = 1; 
            // If VIEW LEADERBOARDS is pressed.
            if (Mouse.isButtonDown(0)) {
                sbg.getState(5).init(gc, sbg);
                // GO TO STATE 5 -> LEADERBOARDS STATE
                sbg.enterState(5);
            }
        }
        // IF HOVERING VIEW PERSONAL STATS
        else if ((PosX >= 590 && PosX <= 890) && (PosY >= 230 && PosY <= 290)) {
            currButton = 2; 
            // If VIEW PERSONAL STATS is pressed.
            if (Mouse.isButtonDown(0)) {
                sbg.getState(6).init(gc, sbg);
                // GO TO STATE 6 -> STATS STATE
                sbg.enterState(6);
            }
        }       
        // IF HOVERING STORE
        else if ((PosX >= 590 && PosX <= 890) && (PosY >= 130 && PosY <= 190)) {
            currButton = 3; 
            // If STORE is pressed.
            if (Mouse.isButtonDown(0)) {
                // GO TO STATE 5 -> STORE STATE
                sbg.enterState(4);
            }
        }
        // IF HOVERING EXIT GAME
        else if ((PosX >= 400 && PosX <= 700) && (PosY >= 30 && PosY <= 95)) {
            currButton = 4; 
            // If EXIT GAME is pressed.
            if (Mouse.isButtonDown(0)) {
            	sbg.enterState(1);
            }
        } else {
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
