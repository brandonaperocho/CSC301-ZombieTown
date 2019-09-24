package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import org.newdawn.slick.gui.*;

import java.awt.Font;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lwjgl.input.*;

public class StatsState extends BasicGameState {

	private final int ID;
	private Image BG, LAYOUT, CLOUDS;
	private int currButton = 1;
	private Rectangle E;
	private float move = 0.8f;
	private TrueTypeFont pixel;
	private int highScore;
	private int kills;
	private String favGun = "";
	private int timePlayed;
	private String username = "";

	public StatsState(int num) {
		this.ID = num; 
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        BG = new Image("/OfficialGame/Sprites/Background/Background2.png");
        CLOUDS = new Image("/OfficialGame/Sprites/dark-clouds.png");
        LAYOUT = new Image("/OfficialGame/Sprites/Stats-Layout.png");
        
        
        try {
          InputStream inputStream = ResourceLoader.getResourceAsStream("/OfficialGame/Font/Pixeled.ttf");
          Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream); 
          awtFont2 = awtFont2.deriveFont(12f); 
          pixel = new TrueTypeFont(awtFont2, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		// DRAW THE BACKGROUND AND HEADER
        g.drawImage(BG, -300, -200);    
        g.drawImage(CLOUDS, 1+move, 1);
        g.drawImage(LAYOUT, 0, 0);
        
        if (move > 1500) {
          move = 0.8f;
        }
        g.setFont(pixel);
        g.setColor(Color.white);
        g.drawString("BACK", 900, 20);
        
		g.setColor(Color.darkGray);
		if (currButton == 0) {
			g.fillRect(1100, 650, 300, 75);
		}
		g.setColor(Color.white);
		g.drawString("USER: " + username, 200, 200);
		
		g.drawString("HIGH SCORE: " + highScore, 300, 300);
		g.drawString("TOTAL KILLS: " + kills, 300, 400);
		g.drawString("TOTAL TIME PLAYED: " + timePlayed +" seconds", 300, 500);
		g.drawString("FAVOURITE GUN: " + favGun, 300, 600);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {	
		int PosX = Mouse.getX();
		int PosY = Mouse.getY();

        if ((PosX >= 900 && PosX <= 948) && (PosY >= 686 && PosY <= 706)) {
          currButton = 0;
          if (Mouse.isButtonDown(0)) {
              currButton = 1;
              sbg.enterState(3);
          }
      }
      if(favGun.equals("")){
        Connection dbconn = (Connection) Database.setupConn();
        Statement stmt;
        try {
          String query = "select * from accounts where user=\'" + LoginAttemptState.user
              + "\'";
          stmt = (Statement) dbconn.createStatement();
          ResultSet rs = (ResultSet) stmt.executeQuery(query);
          rs.next();
          username = rs.getString("user");
          highScore = rs.getInt("score");
          kills = rs.getInt("kills");
          timePlayed = rs.getInt("timePlayed");
          favGun = rs.getString("favGun");
          Database.closeConn(dbconn);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
	}

	@Override
	public int getID() {
		return this.ID;
	}

}
