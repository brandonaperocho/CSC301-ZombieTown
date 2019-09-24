package main;

import java.awt.Font;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;
import org.omg.PortableServer.ThreadPolicyOperations;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class UI {

  private TrueTypeFont pixel, pixel2;
  private double score = 100, time = 0.0;
  private int hours = 0, mins = 0, currButton = -1, selectedControls = 1, kills = 0;
  private float scale;
  private String minsS = "", hoursS = "";
  int x, y;
  private Image QUIT, QUIT_HOVER, BACK, BACK_HOVER, HOWTOPLAY, HOWTOPLAY_HOVER, CHANGECONTROLS,
      CHANGECONTROLS_HOVER, SHOP, SHOP_HOVER;
  private Image C1, C2, C1_HOVER, C2_HOVER;
  private Image BUY_MG_HOVER, BUY_PISTOL_HOVER, BUY_SHOTGUN_HOVER;
  private Image BUY_MG, BUY_PISTOL, BUY_SHOTGUN;
  private Image BUY_MG_X150_HOVER, BUY_PISTOL_X75_HOVER, BUY_SHOTGUN_X25_HOVER;
  private Image BUY_MG_X150, BUY_PISTOL_X75, BUY_SHOTGUN_X25;
  private Image BUY_MG_X600_HOVER, BUY_PISTOL_X300_HOVER, BUY_SHOTGUN_X100_HOVER;
  private Image BUY_MG_X600, BUY_PISTOL_X300, BUY_SHOTGUN_X100;
  private boolean paused = false;
  private boolean changeControls = false, howToPlay = false, shop = false;

  public UI(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
    try {
      InputStream inputStream = ResourceLoader.getResourceAsStream("/OfficialGame/Font/Pixeled.ttf");
      Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
      awtFont2 = awtFont2.deriveFont(14f);
      pixel = new TrueTypeFont(awtFont2, false);
      InputStream inputStream2 = ResourceLoader.getResourceAsStream("/OfficialGame/Font/Pixeled.ttf");
      Font awtFont3 = Font.createFont(Font.TRUETYPE_FONT, inputStream2);
      awtFont3 = awtFont3.deriveFont(17f);
      pixel2 = new TrueTypeFont(awtFont3, false);
    } catch (Exception e) {
      e.printStackTrace();
    }

    QUIT = new Image("/OfficialGame/Sprites/buttonImages/quit.png");
    QUIT_HOVER = new Image("/OfficialGame/Sprites/buttonImages/quit_hover.png");
    BACK = new Image("/OfficialGame/Sprites/buttonImages/back.png");
    BACK_HOVER = new Image("/OfficialGame/Sprites/buttonImages/back_hover.png");

    HOWTOPLAY = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
    HOWTOPLAY_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
    CHANGECONTROLS = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
    CHANGECONTROLS_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
    SHOP = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
    SHOP_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");

    C1 = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
    C1_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
    C2 = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
    C2_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
    
    
    BUY_MG_X150 = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
	BUY_PISTOL_X75 = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
	BUY_SHOTGUN_X25 = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
	
	BUY_MG_X150_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
	BUY_PISTOL_X75_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
	BUY_SHOTGUN_X25_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
	
	BUY_MG_X600 = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
	BUY_PISTOL_X300 = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
	BUY_SHOTGUN_X100 = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
	
	BUY_MG_X600_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
	BUY_PISTOL_X300_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
	BUY_SHOTGUN_X100_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
	
	BUY_MG = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
	BUY_PISTOL = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
	BUY_SHOTGUN = new Image("/OfficialGame/Sprites/buttonImages/MenuBar.png");
	
	BUY_MG_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
	BUY_PISTOL_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
	BUY_SHOTGUN_HOVER = new Image("/OfficialGame/Sprites/buttonImages/MenuBar_hover.png");
    
  }

  public void updateXY(int X, int Y) {
    this.x = X;
    this.y = Y;
  }

  public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
    g.setFont(pixel);
    g.setColor(Color.white);
    g.drawString("Score: " + Double.toString(Math.round(score)), 25, 50);
    
    if (mins < 10)
      minsS = "0" + mins;
    else
      minsS = "" + mins;
    if (hours < 10)
      hoursS = "0" + hours;
    else
      hoursS = "" + hours;
    g.drawString("Time:  " + (hoursS) + " : " + (minsS), 25, 100);


    if (paused) {
      Color trans = new Color(0f, 0f, 0f, 0.7f);
      g.setColor(trans);
      g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
      if (howToPlay) { // In the HowToPlayMenu
        g.setColor(Color.black);
        g.fillRect(20, 20, gc.getWidth() - 40, gc.getHeight() - 40);
        g.setColor(Color.darkGray);
        g.fillRect(25, 25, gc.getWidth() - 50, gc.getHeight() - 50);
        g.drawImage(BACK, 390, 630);
        if (currButton == 1) {
          g.drawImage(BACK_HOVER, 390, 630);
        }
        g.setColor(Color.black);
        g.resetFont();
        g.drawString("Welcome to Zombie Town!", 50, 50);
        g.drawString("This is a sandbox zombie survival game developed to test your skills.", 50, 100);
        g.drawString("The map is vast and large enough to keep you entertained for ages.", 50, 150);
        g.drawString("Your score increases steadily as the game progress and increases much faster", 50, 200);
        g.drawString("as you kill zombies. You can use your score to buy new weapons and ammo", 50, 250);
        g.drawString("for your guns from the ingame store that can be accessed with the ESC key.", 50, 300);
        g.drawString("See how long you can survive and compete with others by trying to achieve", 50, 350);
        g.drawString("a highscore on the global leaderboards!", 50, 400);



      } else if (changeControls) { // In the ControlsMenu
        g.setColor(Color.white);
        g.drawString("CURRENT CONTROLS:     CONTROL SET " + selectedControls, 300, 50);

        g.drawImage(C1, 140, 150);
        g.drawImage(C2, 640, 150);

        g.drawImage(BACK, 390, 630);
        if (currButton == 1) {
          g.drawImage(BACK_HOVER, 390, 630);
        } else if (currButton == 5) {
          g.drawImage(C1_HOVER, 140, 150);
        } else if (currButton == 6) {
          g.drawImage(C2_HOVER, 640, 150);
        }
        g.setFont(pixel2);
        g.setColor(Color.black);
        g.drawString("CONTROL SET 1", 187, 166);
        g.drawString("CONTROL SET 2", 687, 166);

        g.setFont(pixel);
        g.setColor(Color.white);
        g.drawString("W - MOVE NORTH", 187, 250);
        g.drawString("A - MOVE WEST", 187, 300);
        g.drawString("S - MOVE SOUTH", 187, 350);
        g.drawString("D - MOVE EAST", 187, 400);
        g.drawString("Q - CHANGE WEAPONS", 187, 450);
        g.drawString("R - RELOAD WEAPON", 187, 500);
        g.drawString("ESC - PAUSE MENU", 187, 550);

        g.drawString("UP - MOVE NORTH", 687, 250);
        g.drawString("LEFT - MOVE WEST", 687, 300);
        g.drawString("DOWN - MOVE SOUTH", 687, 350);
        g.drawString("RIGHT - MOVE EAST", 687, 400);
        g.drawString("SHIFT - CHANGE WEAPONS", 687, 450);
        g.drawString("CTRL - RELOAD WEAPON", 687, 500);
        g.drawString("ESC - PAUSE MENU", 687, 550);


      } else if (shop) { // In the ShopMenu
			g.drawImage(BUY_MG, 40, 100);
			g.drawImage(BUY_PISTOL, 390, 100);
			g.drawImage(BUY_SHOTGUN, 740, 100);
			
			g.drawImage(BUY_MG_X150, 40, 300);
			g.drawImage(BUY_PISTOL_X75, 390, 300);
			g.drawImage(BUY_SHOTGUN_X25, 740, 300);
			
			g.drawImage(BUY_MG_X600, 40, 500);
			g.drawImage(BUY_PISTOL_X300, 390, 500);
			g.drawImage(BUY_SHOTGUN_X100, 740, 500);

			if (currButton == 7) {
				g.drawImage(BUY_MG_HOVER, 40, 100);
			} else if (currButton == 8) {
				g.drawImage(BUY_PISTOL_HOVER, 390, 100);
			} else if (currButton == 9) {
				g.drawImage(BUY_SHOTGUN_HOVER, 740, 100);
			} else if (currButton == 10) {
				g.drawImage(BUY_MG_X150_HOVER, 40, 300);
			} else if (currButton == 11) {
				g.drawImage(BUY_PISTOL_X75_HOVER, 390, 300);
			} else if (currButton == 12) {
				g.drawImage(BUY_SHOTGUN_X25_HOVER, 740, 300);
			} else if (currButton == 13) {
				g.drawImage(BUY_MG_X600_HOVER, 40, 500);
			} else if (currButton == 14) {
				g.drawImage(BUY_PISTOL_X300_HOVER, 390, 500);
			} else if (currButton == 15) {
				g.drawImage(BUY_SHOTGUN_X100_HOVER, 740, 500);
			}
			
			g.setColor(Color.black);
			
			g.drawString("BUY MACHINE GUN", 90, 119);
			g.drawString("BUY PISTOL", 480, 119);
			g.drawString("BUY SHOTGUN", 815, 119);
			
			g.drawString("MACHINE GUN AMMO x150", 55, 319);
			g.drawString("PISTOL AMMO x75", 440, 319);
			g.drawString("SHOTGUN AMMO x25", 780, 319);
			
			g.drawString("MACHINE GUN AMMO x600", 55, 519);
			g.drawString("PISTOL AMMO x300", 435, 519);
			g.drawString("SHOTGUN AMMO x100", 777, 519);
			
			
			g.setColor(Color.white);
			
			g.drawString("- 50 SCORE", 130, 69);
			g.drawString("- 10 SCORE", 480, 69);
			g.drawString("- 30 SCORE", 820, 69);
			
			g.drawString("- 10 SCORE", 130, 269);
			g.drawString("- 10 SCORE", 480, 269);
			g.drawString("- 10 SCORE", 820, 269);
			
			g.drawString("- 30 SCORE", 130, 469);
			g.drawString("- 30 SCORE", 480, 469);
			g.drawString("- 30 SCORE", 820, 469);
			
			g.drawImage(BACK, 390, 630);
			if (currButton == 1) {
				g.drawImage(BACK_HOVER, 390, 630);
			}
      } else {
        g.drawImage(QUIT, 390, 150);
        g.drawImage(HOWTOPLAY, 390, 350);
        g.drawImage(BACK, 390, 550);
        g.drawImage(CHANGECONTROLS, 390, 450);
        g.drawImage(SHOP, 390, 250);
        if (currButton == 0) {
          g.drawImage(QUIT_HOVER, 390, 150);
        } else if (currButton == 1) {
          g.drawImage(BACK_HOVER, 390, 550);
        } else if (currButton == 2) {
          g.drawImage(HOWTOPLAY_HOVER, 390, 350);
        } else if (currButton == 3) {
          g.drawImage(CHANGECONTROLS_HOVER, 390, 450);
        } else if (currButton == 4) {
          g.drawImage(SHOP_HOVER, 390, 250);
        }
        g.setFont(pixel2);
        g.setColor(Color.black);
        g.drawString("HOW TO PLAY", 455, 366);
        g.drawString("CHANGE CONTROLS", 415, 466);
        g.drawString("WEAPONS AND AMMO", 410, 266);
      }
    } else {
      score -= 0.001;
      scale = (mins / (300.0f));
      if (getHour() == 19) {
        g.setColor(new Color(0f, 0, 0f, 0.0f + scale));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
      }
      if (getHour() == 20) {
        g.setColor(new Color(0f, 0, 0f, 0.2f + scale));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
      }
      if (getHour() == 21) {
        g.setColor(new Color(0f, 0, 0f, 0.4f + scale));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
      }
      if (getHour() == 22) {
        g.setColor(new Color(0f, 0, 0f, 0.6f + scale));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
      }
      if (getHour() == 23 || getHour() <= 3) {
        g.setColor(new Color(0f, 0, 0f, 0.8f));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
      }
      if (getHour() == 4) {
        g.setColor(new Color(0f, 0, 0f, 0.8f - scale));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
      }
      if (getHour() == 5) {
        g.setColor(new Color(0f, 0, 0f, 0.6f - scale));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
      }
      if (getHour() == 6) {
        g.setColor(new Color(0f, 0, 0f, 0.4f - scale));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
      }
      if (getHour() == 7) {
        g.setColor(new Color(0f, 0, 0f, 0.2f - scale));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
      }
    }
  }

  public void update() throws SlickException {

  }

  public void setTime(int T) {
    time += T;
    mins = ((int) time / 500) % 60;
    hours = (18 + (int) Math.floor((double) (time / 500 / 60))) % 24;
  }

  public int getMins(int T) {
    return mins;
  }

  public int getHour() {
    return hours;
  }
  
  public double getScore() {
	  return score;
  }

  public void subtractScore(double sub) {
	  score = score - sub;
  }
  
  public void endGame() {

    Connection dbconn = (Connection) Database.setupConn();
    Statement stmt;
    try {
      String query = "UPDATE accounts SET score=\'" + Math.round(score) + "\' where user=\'"
          + LoginAttemptState.user + "\' and score<\'" + Math.round(score) + "\'";
      stmt = (Statement) dbconn.createStatement();
      int rs = stmt.executeUpdate(query);
      if (rs == 1) {
        System.out.println(query);
        System.out.println("update score success");
      }
      Database.closeConn(dbconn);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    updateStats();

  }
  
  private void updateStats(){
    Connection dbconn = (Connection) Database.setupConn();
    Statement stmt;
    try {
      String query = "select * from accounts where user=\'" + LoginAttemptState.user
          + "\'";
      stmt = (Statement) dbconn.createStatement();
      ResultSet rs = (ResultSet) stmt.executeQuery(query);
      rs.next();
      double totalTime = Math.round((GameState.totalTime + rs.getInt("timePlayed"))/1000);
      int totalKills = kills+ rs.getInt("kills");
      
      String query1 = "UPDATE accounts SET kills=\'" + totalKills
          + "\', timePlayed=\'" + totalTime +  "\' where user=\'"
          + LoginAttemptState.user +"\'";
      System.out.println(query1);
      stmt = (Statement) dbconn.createStatement();
      int rs1 = stmt.executeUpdate(query1);
      if (rs1 == 1) {
        System.out.println("update score success");
      }
      Database.closeConn(dbconn);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  
  public void setControls(int cVal) {
    selectedControls = cVal;
  }

  public void enterControls() {
    changeControls = true;
  }

  public void leaveControls() {
    changeControls = false;
  }

  public void enterHelp() {
    howToPlay = true;
  }

  public void leaveHelp() {
    howToPlay = false;
  }

  public void enterShop() {
    shop = true;
  }

  public void leaveShop() {
    shop = false;
  }

  public void renderPause() {
    paused = true;
  }

  public void unPause() {
    paused = false;
  }

  public void setcurrButton(int i) {
    currButton = i;

  }

}
