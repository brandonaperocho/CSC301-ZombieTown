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
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class RegisterState extends BasicGameState {

  private final int ID;
  private Image BG, REGISTER, REGISTER_HOVER, BACK, BACK_HOVER;
  private TextField username, password, password2, year, month, day, firstname, lastname, CCN;
  private TrueTypeFont pixel;
  private TextField[] inputs;
  private int currButton = 2;
  private int currText = 0;
  private int registrationError = 0;

  public RegisterState(int num) {
    this.ID = num;
  }

  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    BG = new Image("/OfficialGame/Sprites/Background/Background.png");
    REGISTER = new Image("/OfficialGame/Sprites/buttonImages/register.png");
    REGISTER_HOVER = new Image("/OfficialGame/Sprites/buttonImages/register_hover.png");
    BACK = new Image("/OfficialGame/Sprites/buttonImages/back.png");
    BACK_HOVER = new Image("/OfficialGame/Sprites/buttonImages/back_hover.png");
    try {
        InputStream inputStream = ResourceLoader.getResourceAsStream("/OfficialGame/Font/Pixeled.ttf");

        Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        awtFont2 = awtFont2.deriveFont(10f);
        pixel = new TrueTypeFont(awtFont2, false);
	} catch (Exception e) {
        e.printStackTrace();
    } 
    inputs = new TextField[9];

    username = new TextField(gc, gc.getDefaultFont(), 390, 160, 300, 25);
    username.setBorderColor(Color.black);
    username.setMaxLength(16);
    inputs[0] = username;

    password = new PasswordTextField(gc, gc.getDefaultFont(), 390, 205, 300, 25);
    password.setBorderColor(Color.black);
    password.setMaxLength(16);
    inputs[1] = password;

    password2 = new PasswordTextField(gc, gc.getDefaultFont(), 390, 250, 300, 25);
    password2.setBorderColor(Color.black);
    password2.setMaxLength(16);
    inputs[2] = password2;

    year = new TextField(gc, gc.getDefaultFont(), 390, 295, 45, 25);
    year.setBorderColor(Color.black);
    year.setMaxLength(4);
    inputs[3] = year;

    month = new TextField(gc, gc.getDefaultFont(), 440, 295, 25, 25);
    month.setBorderColor(Color.black);
    month.setMaxLength(2);
    inputs[4] = month;

    day = new TextField(gc, gc.getDefaultFont(), 470, 295, 25, 25);
    day.setBorderColor(Color.black);
    day.setMaxLength(2);
    inputs[5] = day;


    firstname = new TextField(gc, gc.getDefaultFont(), 390, 350, 300, 25);
    firstname.setBorderColor(Color.black);
    firstname.setMaxLength(16);
    inputs[6] = firstname;

    lastname = new TextField(gc, gc.getDefaultFont(), 390, 400, 300, 25);
    lastname.setBorderColor(Color.black);
    lastname.setMaxLength(16);
    inputs[7] = lastname;

    CCN = new TextField(gc, gc.getDefaultFont(), 390, 450, 300, 25);
    CCN.setBorderColor(Color.black);
    CCN.setMaxLength(19);
    inputs[8] = CCN;
  }

  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    // DRAW THE BACKGROUND AND HEADER
	g.drawImage(BG, -300, -200);
    g.setColor(Color.white);
    g.drawImage(REGISTER, 210, 550);
    g.drawImage(BACK, 560, 550);
    g.setFont(pixel);
    username.render(gc, g);
    password.render(gc, g);
    password2.render(gc, g);
    year.render(gc, g);
    month.render(gc, g);
    day.render(gc, g);
    firstname.render(gc, g);
    lastname.render(gc, g);
    CCN.render(gc, g);
    g.setColor(Color.white);
    g.drawString("USERNAME  |  Max 16 chars:", 390, 140);
    g.drawString("PASSWORD  |  Max 16 chars:", 390, 185);
    g.drawString("CONFIRM PASSWORD  |  Max 16 chars:", 390, 230);
    g.drawString("DATE OF BIRTH  |  YYYYMMDD:", 390, 275);
    g.drawString("FIRST NAME  |  Max 16 chars:", 390, 330);
    g.drawString("LAST NAME  |  Max 16 chars:", 390, 380);
    g.drawString("Credit Card Number  |  XXXX-XXXX-XXXX-XXXX:", 390, 430);
    if (currButton == 0) {
      g.drawImage(REGISTER_HOVER, 210, 550);
    } else if (currButton == 1) {
      g.drawImage(BACK_HOVER, 560, 550);
    }
    
    if (registrationError == 1) {
      g.setColor(Color.red);
      g.drawString("That username is currently in use.", 390, 100);
    } else if (registrationError == 2) {
      g.setColor(Color.red);
      g.drawString("The passwords do not match.", 390, 100);
    } else if (registrationError == 3) {
      g.setColor(Color.red);
      g.drawString("Please fill in all the fields.", 390, 100);
    } else if(registrationError == 4){
      g.setColor(Color.red);
      g.drawString("You need to be older then 13 to play.", 390, 100);
    }

  }

  public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {
    Input input = gc.getInput();
    input.enableKeyRepeat();

    // ----------------------------------------------------------------------
    // DETECT WHICH BUTTON WILL BE PRESSED WHEN USING KEYBOARD CONTROL
    // ----------------------------------------------------------------------
    if (input.isKeyPressed(Input.KEY_TAB)) {
      if (currText == 8) {
        currText = 0;
        inputs[currText].setFocus(true);
      } else {
        currText += 1;
        inputs[currText].setFocus(true);
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
      // If REGISTER is pressed.
      if (currButton == 0) {
        // ATTEMPT TO REGISTER
        // IF USERNAME IS TAKEN
        if (checkUser()) {
          // CHECK THE USERNAME IS AVAILABLE AND CHANGE THE IF STATEMENT
          registrationError = 1;
          // IF PASSWORDS DO NOT MATCH
        } else if (!password.getText().equals(password2.getText())) {
          registrationError = 2;
          // IF ALL FIELDS ARE NOT PROVIDED
        } else if(2017-Integer.valueOf(year.getText()) <=13){
          registrationError=4;
        }else if (username.getText().length() == 0 || password.getText().length() == 0
            || password2.getText().length() == 0 || year.getText().length() != 4
            || month.getText().length() != 2 || day.getText().length() != 2
            || firstname.getText().length() == 0 || lastname.getText().length() == 0
            || CCN.getText().length() != 19) {
          registrationError = 3;
          // OTHERWISE REGISTER THE USER
        } else {
          registrationError = 0;
          // PUT IN CODE TO REGISTER THE USER HERE
          register();
          Mouse.setCursorPosition(450, 500);
          // GO TO STATE 0 -> MAIN MENU
          sbg.enterState(0);
        }
      }
      // If RETURN TO MAIN MENU is pressed.
      if (currButton == 1) {
        // GO TO STATE 0 -> MAIN MENU
        sbg.enterState(0);
      }
    }
    // ----------------------------------------------------------------------
    // DETECT WHICH BUTTON WILL BE PRESSED WHEN USING MOUSE CONTROL
    // ----------------------------------------------------------------------
    int PosX = Mouse.getX();
    int PosY = Mouse.getY();
    // IF HOVERING REGISTER
    if ((PosX >= 200 && PosX <= 500) && (PosY >= 110 && PosY <= 180)) {
    	currButton = 0;
      // IF REGISTER is pressed.
      if (Mouse.isButtonDown(0)) {
        // ATTEMPT TO REGISTER
        // IF USERNAME IS TAKEN
        if (checkUser()) {
          // CHECK THE USERNAME IS AVAILABLE AND CHANGE THE IF STATEMENT
          registrationError = 1;
          // IF PASSWORDS DO NOT MATCH
        } else if (!password.getText().equals(password2.getText())) {
          registrationError = 2;
          // IF ALL FIELDS ARE NOT PROVIDED
        }else if (username.getText().length() == 0 || password.getText().length() == 0
            || password2.getText().length() == 0 || year.getText().length() != 4
            || month.getText().length() != 2 || day.getText().length() != 2
            || firstname.getText().length() == 0 || lastname.getText().length() == 0
            || CCN.getText().length() != 19) {
          registrationError = 3;
          // OTHERWISE REGISTER THE USER
        } else if(2017-Integer.valueOf(year.getText()) <=13){
          registrationError=4;
        }else {
          registrationError = 0;
          // PUT IN CODE TO REGISTER THE USER HERE
          register();
          Mouse.setCursorPosition(450, 500);
          // GO TO STATE 0 -> MAIN MENU
          sbg.enterState(0);
        }
      }
    }
    // IF HOVERING RETURN TO MAIN MENU
    else if ((PosX >= 550 && PosX <= 850) && (PosY >= 110 && PosY <= 180)) {
      currButton = 1;
      // If RETURN TO MAIN MENU is pressed.
      if (Mouse.isButtonDown(0)) {
        // GO TO STATE 0 -> MAIN MENU
        Mouse.setCursorPosition(450, 500);
        sbg.enterState(0);
      }
    } else {
    	currButton = -1;
    }

  }

  @Override
  public int getID() {
    return this.ID;
  }

  private boolean register() {
    Connection dbconn = (Connection) Database.setupConn();
    Statement stmt;
    String hashedPass = Md5Hash.hashPass(password.getText());
    try {
      String query = "insert into accounts (user,pass,first,last,day,month,year,ccn)" + "values (\'"
          + username.getText() + "\',\'" + hashedPass + "\',\'" + firstname.getText() + "\',\'"
          + lastname.getText() + "\',\'" + day.getText() + "\',\'" + month.getText() + "\',\'"
          + year.getText() + "\',\'" + CCN.getText() + "\')";
      stmt = (Statement) dbconn.createStatement();
      stmt.executeUpdate(query);
      Database.closeConn(dbconn);
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  private boolean checkUser(){
    Connection dbconn = (Connection) Database.setupConn();
    Statement stmt;
    boolean result = false;
    try {
      String query = "select * from accounts where user=\'" + username.getText()+"\'";
      stmt = (Statement) dbconn.createStatement();
      ResultSet rs = (ResultSet) stmt.executeQuery(query);
      if(rs.next()) result=true;
      Database.closeConn(dbconn);
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return result;
  }

}
