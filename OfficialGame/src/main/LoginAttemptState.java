package main;

import java.awt.Font;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class LoginAttemptState extends BasicGameState {

  private final int ID;
  private Image BG, LOGIN, LOGIN_HOVER, LOGO, BACK, BACK_HOVER;
  private TrueTypeFont pixel;
  private TextField username, password;
  private int currButton = 2;
  private int currText = 0;
  private boolean incorrectLogin = false;
  protected static String user;
  
  public LoginAttemptState(int num) {
    this.ID = num;
  }

  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	  BG = new Image("/OfficialGame/Sprites/Background/Background.png");	
	  LOGO = new Image("/OfficialGame/Sprites/Logo/ZombieTown_Logo.png");
	  LOGIN = new Image("/OfficialGame/Sprites/buttonImages/login.png");		
	  LOGIN_HOVER = new Image("/OfficialGame/Sprites/buttonImages/login_hover.png");		
	  BACK = new Image("/OfficialGame/Sprites/buttonImages/back.png");		
	  BACK_HOVER = new Image("/OfficialGame/Sprites/buttonImages/back_hover.png");
		
	  try {
		  InputStream inputStream = ResourceLoader.getResourceAsStream("/OfficialGame/Font/Pixeled.ttf");
		  Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream); 
		  awtFont2 = awtFont2.deriveFont(12f); 
		  pixel = new TrueTypeFont(awtFont2, false);
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
		
	  username = new TextField(gc, gc.getDefaultFont(), 390, 370, 300, 25);
	  username.setBorderColor(Color.white);
	  username.setMaxLength(30);
	  
	  password = new PasswordTextField(gc, gc.getDefaultFont(), 390, 445, 300, 25);
	  password.setBorderColor(Color.white);
	  password.setMaxLength(16);
  }

  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	 g.drawImage(BG, -300, -200);
	 g.drawImage(LOGO, 165, 10);
	 g.setFont(pixel);
	 g.setColor(Color.white);
	 username.render(gc, g);
	 password.render(gc, g);
	 g.setColor(Color.white);
	 g.drawString("USERNAME", 390, 340);
	 g.drawString("PASSWORD", 390, 415);
    
	 g.setColor(Color.white);
	 g.drawImage(LOGIN, 390, 495);    
	 g.drawImage(BACK, 390, 590);
    
	 if (currButton == 0) {
		 g.drawImage(LOGIN_HOVER, 390, 495);
	 } else if (currButton == 1) {
    	g.drawImage(BACK_HOVER, 390, 590);
	 }
    
	 if (incorrectLogin) {
		 g.setColor(Color.red);
		 g.drawString("INCORRECT USERNAME OR PASSWORD", 380, 670);
	 }
  }

  public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {
    Input input = gc.getInput();
    input.enableKeyRepeat();

    // ----------------------------------------------------------------------
    // DETECT WHICH BUTTON WILL BE PRESSED WHEN USING KEYBOARD CONTROL
    // ----------------------------------------------------------------------
    if (input.isKeyPressed(Input.KEY_TAB)) {
      if (currText == 0) {
        currText = 1;
        username.setFocus(true);
      } else {
        currText = 0;
        password.setFocus(true);
      }
    }
    if (input.isKeyPressed(Input.KEY_LEFT)) {
      if (currButton == 1 || currButton == 0) {
        currButton = 0;
      }
    }
    if (input.isKeyPressed(Keyboard.KEY_RIGHT)) {
      if (currButton == 1 || currButton == 0) {
        currButton = 1;
      }
    }
    if (input.isKeyPressed(Keyboard.KEY_RETURN)) {
        // ATTEMPT TO LOGIN
        Connection dbconn = (Connection) Database.setupConn();
        Statement stmt;
        String hashedPass = Md5Hash.hashPass(password.getText());
        boolean authenticated = false;
        try {
          String query = "select * from accounts where user=\'" + username.getText()
              + "\' and pass=\'" + hashedPass + "\'";
          stmt = (Statement) dbconn.createStatement();
          ResultSet rs = (ResultSet) stmt.executeQuery(query);
          if(rs.next()){
            authenticated = rs.getString(1).length() > 0;
          }
          Database.closeConn(dbconn);
        } catch (SQLException e) {
          e.printStackTrace();
        }
        // CHECK CORRECT CREDENTIALS
        if (authenticated) {
          // ACTUALLY LOGIN
          user = username.getText();
          // GO TO STATE 4 -> USER LOGGED IN MAIN MENU
          incorrectLogin = false;
          sbg.enterState(3);
        } else {
          // PROVIDE FEEDBACK INDICATING WRONG USERNAME OR PASSWORD
          incorrectLogin = true;
        }
    }
    // ----------------------------------------------------------------------
    // DETECT WHICH BUTTON WILL BE PRESSED WHEN USING MOUSE CONTROL
    // ----------------------------------------------------------------------
    int PosX = Mouse.getX();
    int PosY = Mouse.getY();
    // IF HOVERING LOGIN
    if ((PosX >= 380 && PosX <= 680) && (PosY >= 160 && PosY <= 228)) {
      currButton = 0;
      // IF LOGIN is pressed.
      if (Mouse.isButtonDown(0)) {
        // ATTEMPT TO LOGIN
    	Connection dbconn = (Connection) Database.setupConn();
        Statement stmt;
        String hashedPass = Md5Hash.hashPass(password.getText());
        boolean authenticated = false;
        try {
          String query = "select * from accounts where user=\'" + username.getText()
              + "\' and pass=\'" + hashedPass + "\'";
          stmt = (Statement) dbconn.createStatement();
          ResultSet rs = (ResultSet) stmt.executeQuery(query);
          if(rs.next()){
            authenticated = rs.getString(1).length() > 0;
          }
          Database.closeConn(dbconn);
        } catch (SQLException e) {
          e.printStackTrace();
        }
        // CHECK CORRECT CREDENTIALS
        if (authenticated) {
          // ACTUALLY LOGIN
          user = username.getText();
          // GO TO STATE 4 -> USER LOGGED IN MAIN MENU
          incorrectLogin = false;
          Mouse.setCursorPosition(450, 500);
          sbg.enterState(3);
        } else {
          // PROVIDE FEEDBACK INDICATING WRONG USERNAME OR PASSWORD
          incorrectLogin = true;
        }
      }
    }
    // IF HOVERING RETURN TO MAIN MENU
    else if ((PosX >= 380 && PosX <= 680) && (PosY >= 50 && PosY <= 118)) {
      currButton = 1;
      // If REGISTER is pressed.
      if (Mouse.isButtonDown(0)) {
    	 // GO TO STATE 0 -> MAIN MENU
         //sbg.getState(0).init(gc, sbg);
          Mouse.setCursorPosition(450, 500);

    	 sbg.enterState(0);
      }
    } else {
    	currButton = -1;
    }

    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------

  }

  @Override
  public int getID() {
    // TODO Auto-generated method stub
    return this.ID;
  }

}
