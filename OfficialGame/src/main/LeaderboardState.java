package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
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
import java.util.ArrayList;

import org.lwjgl.input.*;

public class LeaderboardState extends BasicGameState {

	private final int ID;
	private Image BAR, BAR_HOVER, BG, CLOUDS, LAYOUT;
	private TrueTypeFont pixel;
	private int currButton = 1;
	private ArrayList<Score> high_scores;
	private float move = 0.8f;
	
	public LeaderboardState(int num) {
		this.ID = num; 
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		BG = new Image("/OfficialGame/Sprites/Background/Background2.png");
		CLOUDS = new Image("/OfficialGame/Sprites/dark-clouds.png");
		
		BAR = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
		BAR_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
		
		LAYOUT = new Image("/OfficialGame/Sprites/Leaderboards-Layout.png");
		
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("/OfficialGame/Font/Pixeled.ttf");
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream); 
			awtFont2 = awtFont2.deriveFont(12f); 
			pixel = new TrueTypeFont(awtFont2, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		high_scores = new ArrayList<Score>();
		getScores();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		g.drawImage(BG, -300, -200);
		g.drawImage(CLOUDS, 1+move, 1);
		g.drawImage(LAYOUT, 20, -100);
		move += 0.1f;
		
		if (move > 1500) {
			move = 0.8f;
		}
		g.setFont(pixel);
		g.setColor(Color.white);
		
		for (int i = 0; i < 10; i++) {
			Score score = high_scores.get(i);
			g.drawString(Integer.toString(i+1), 110, 195+53*i);
			g.drawString(score.getUser(), 450, 195+53*i);
			g.drawString(Integer.toString(score.getScore()), 820, 195+53*i);
		}
		
		g.drawString("BACK", 900, 20);
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
	}
	
	private void getScores() {
		Connection dbconn = (Connection) Database.setupConn();
        Statement stmt;
        try {
          String query = "select * from accounts order by score desc";
          stmt = (Statement) dbconn.createStatement();
          ResultSet rs = (ResultSet) stmt.executeQuery(query);
          while(rs.next()) {
        	  String user = rs.getString("user");
        	  int score = rs.getInt("score");
        	  high_scores.add(new Score(user, score));
          }
          Database.closeConn(dbconn);
        } catch (SQLException e) {
          e.printStackTrace();
        }
	}

	@Override
	public int getID() {
		return this.ID;
	}

}
