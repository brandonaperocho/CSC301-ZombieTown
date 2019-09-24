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

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.input.*;

public class StoreState extends BasicGameState {

	private final int ID;
	private Image BG, LAYOUT, CLOUDS;
	private Image H1, H2, H3, H4, H5, H6, H7, BH, BBH;
	private Image B1, B2, B3, B4, B5, B6, B7;
	private Image O1, O2, O3, O4, O5, O6, O7;
	private Image R1, R2, R3, R4, R5, R6, R7;
	private Image S1, S2, S3, S4, S5, S6, S7;
	private Image U1, U2, U3, U4, U5, U6, U7;
	private Image W1, W2, W3, W4, W5, W6, W7;
	private Image Z1, Z2, Z3, Z4, Z5, Z6, Z7;
	private Image M1, M2, M3, M4, M5, M6, M7;
	private TrueTypeFont pixel;
	private Image[][] Sprites;
	private int equippedSprite = 0;
	private int currButton = -1;
	private float move = 0.8f;

	public StoreState(int num) {
		this.ID = num; 
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		BG = new Image("/OfficialGame/Sprites/Background/Background2.png");
		CLOUDS = new Image("/OfficialGame/Sprites/dark-clouds.png");
		LAYOUT = new Image("/OfficialGame/Sprites/Store-Layout.png");
		
		BH = new Image("/OfficialGame/Sprites/buttonImages/MiniMenuBar.png");
		BBH = new Image("/OfficialGame/Sprites/buttonImages/MiniMenuBar_hover.png");
		
		H1 = new Image("/OfficialGame/Sprites/Hitman/Hitman_gun.png");
		H2 = new Image("/OfficialGame/Sprites/Hitman/Hitman_gun_walksheet.png");
		H3 = new Image("/OfficialGame/Sprites/Hitman/Hitman_machine.png");
		H4 = new Image("/OfficialGame/Sprites/Hitman/Hitman_machine_walksheet.png");
		H5 = new Image("/OfficialGame/Sprites/Hitman/Hitman_reload.png");
		H6 = new Image("/OfficialGame/Sprites/Hitman/Hitman_silencer.png");
		H7 = new Image("/OfficialGame/Sprites/Hitman/Hitman_silencer_walksheet.png");
		
		B1 = new Image("/OfficialGame/Sprites/ManBlue/ManBlue_gun.png");
		B2 = new Image("/OfficialGame/Sprites/ManBlue/ManBlue_gun_walksheet.png");
		B3 = new Image("/OfficialGame/Sprites/ManBlue/ManBlue_machine.png");
		B4 = new Image("/OfficialGame/Sprites/ManBlue/ManBlue_machine_walksheet.png");
		B5 = new Image("/OfficialGame/Sprites/ManBlue/ManBlue_reload.png");
		B6 = new Image("/OfficialGame/Sprites/ManBlue/ManBlue_silencer.png");
		B7 = new Image("/OfficialGame/Sprites/ManBlue/ManBlue_silencer_walksheet.png");
		
		O1 = new Image("/OfficialGame/Sprites/ManOld/ManOld_gun.png");
		O2 = new Image("/OfficialGame/Sprites/ManOld/ManOld_gun_walksheet.png");
		O3 = new Image("/OfficialGame/Sprites/ManOld/ManOld_machine.png");
		O4 = new Image("/OfficialGame/Sprites/ManOld/ManOld_machine_walksheet.png");
		O5 = new Image("/OfficialGame/Sprites/ManOld/ManOld_reload.png");
		O6 = new Image("/OfficialGame/Sprites/ManOld/ManOld_silencer.png");
		O7 = new Image("/OfficialGame/Sprites/ManOld/ManOld_silencer_walksheet.png");
		
		R1 = new Image("/OfficialGame/Sprites/Robot/Robot_gun.png");
		R2 = new Image("/OfficialGame/Sprites/Robot/Robot_gun_walksheet.png");
		R3 = new Image("/OfficialGame/Sprites/Robot/Robot_machine.png");
		R4 = new Image("/OfficialGame/Sprites/Robot/Robot_machine_walksheet.png");
		R5 = new Image("/OfficialGame/Sprites/Robot/Robot_reload.png");
		R6 = new Image("/OfficialGame/Sprites/Robot/Robot_silencer.png");
		R7 = new Image("/OfficialGame/Sprites/Robot/Robot_silencer_walksheet.png");
		
		S1 = new Image("/OfficialGame/Sprites/Soldier/Soldier_gun.png");
		S2 = new Image("/OfficialGame/Sprites/Soldier/Soldier_gun_walksheet.png");
		S3 = new Image("/OfficialGame/Sprites/Soldier/Soldier_machine.png");
		S4 = new Image("/OfficialGame/Sprites/Soldier/Soldier_machine_walksheet.png");
		S5 = new Image("/OfficialGame/Sprites/Soldier/Soldier_reload.png");
		S6 = new Image("/OfficialGame/Sprites/Soldier/Soldier_silencer.png");
		S7 = new Image("/OfficialGame/Sprites/Soldier/Soldier_silencer_walksheet.png");
		
		U1 = new Image("/OfficialGame/Sprites/Survivor/Survivor_gun.png");
		U2 = new Image("/OfficialGame/Sprites/Survivor/Survivor_gun_walksheet.png");
		U3 = new Image("/OfficialGame/Sprites/Survivor/Survivor_machine.png");
		U4 = new Image("/OfficialGame/Sprites/Survivor/Survivor_machine_walksheet.png");
		U5 = new Image("/OfficialGame/Sprites/Survivor/Survivor_reload.png");
		U6 = new Image("/OfficialGame/Sprites/Survivor/Survivor_silencer.png");
		U7 = new Image("/OfficialGame/Sprites/Survivor/Survivor_silencer_walksheet.png");
		
		W1 = new Image("/OfficialGame/Sprites/WomanGreen/WomanGreen_gun.png");
		W2 = new Image("/OfficialGame/Sprites/WomanGreen/WomanGreen_gun_walksheet.png");
		W3 = new Image("/OfficialGame/Sprites/WomanGreen/WomanGreen_machine.png");
		W4 = new Image("/OfficialGame/Sprites/WomanGreen/WomanGreen_machine_walksheet.png");
		W5 = new Image("/OfficialGame/Sprites/WomanGreen/WomanGreen_reload.png");
		W6 = new Image("/OfficialGame/Sprites/WomanGreen/WomanGreen_silencer.png");
		W7 = new Image("/OfficialGame/Sprites/WomanGreen/WomanGreen_silencer_walksheet.png");
		
		Z1 = new Image("/OfficialGame/Sprites/Zombie/Zombie_gun.png");
		Z2 = new Image("/OfficialGame/Sprites/Zombie/Zombie_gun_walksheet.png");
		Z3 = new Image("/OfficialGame/Sprites/Zombie/Zombie_machine.png");
		Z4 = new Image("/OfficialGame/Sprites/Zombie/Zombie_machine_walksheet.png");
		Z5 = new Image("/OfficialGame/Sprites/Zombie/Zombie_reload.png");
		Z6 = new Image("/OfficialGame/Sprites/Zombie/Zombie_silencer.png");
		Z7 = new Image("/OfficialGame/Sprites/Zombie/Zombie_silencer_walksheet.png");
		
		M1 = new Image("/OfficialGame/Sprites/ManBrown/ManBrown_gun.png");
		M2 = new Image("/OfficialGame/Sprites/ManBrown/ManBrown_gun_walksheet.png");
		M3 = new Image("/OfficialGame/Sprites/ManBrown/ManBrown_machine.png");
		M4 = new Image("/OfficialGame/Sprites/ManBrown/ManBrown_machine_walksheet.png");
		M5 = new Image("/OfficialGame/Sprites/ManBrown/ManBrown_reload.png");
		M6 = new Image("/OfficialGame/Sprites/ManBrown/ManBrown_silencer.png");
		M7 = new Image("/OfficialGame/Sprites/ManBrown/ManBrown_silencer_walksheet.png");
		
		Sprites = new Image[9][7];
		Sprites[0][0] = M1;
		Sprites[0][1] = M2;
		Sprites[0][2] = M3;
		Sprites[0][3] = M4;
		Sprites[0][4] = M5;
		Sprites[0][5] = M6;
		Sprites[0][6] = M7;
		Sprites[1][0] = Z1;
		Sprites[1][1] = Z2;
		Sprites[1][2] = Z3;
		Sprites[1][3] = Z4;
		Sprites[1][4] = Z5;
		Sprites[1][5] = Z6;
		Sprites[1][6] = Z7;
		Sprites[2][0] = W1;
		Sprites[2][1] = W2;
		Sprites[2][2] = W3;
		Sprites[2][3] = W4;
		Sprites[2][4] = W5;
		Sprites[2][5] = W6;
		Sprites[2][6] = W7;
		Sprites[3][0] = U1;
		Sprites[3][1] = U2;
		Sprites[3][2] = U3;
		Sprites[3][3] = U4;
		Sprites[3][4] = U5;
		Sprites[3][5] = U6;
		Sprites[3][6] = U7;
		Sprites[4][0] = S1;
		Sprites[4][1] = S2;
		Sprites[4][2] = S3;
		Sprites[4][3] = S4;
		Sprites[4][4] = S5;
		Sprites[4][5] = S6;
		Sprites[4][6] = S7;
		Sprites[5][0] = R1;
		Sprites[5][1] = R2;
		Sprites[5][2] = R3;
		Sprites[5][3] = R4;
		Sprites[5][4] = R5;
		Sprites[5][5] = R6;
		Sprites[5][6] = R7;
		Sprites[6][0] = O1;
		Sprites[6][1] = O2;
		Sprites[6][2] = O3;
		Sprites[6][3] = O4;
		Sprites[6][4] = O5;
		Sprites[6][5] = O6;
		Sprites[6][6] = O7;
		Sprites[7][0] = B1;
		Sprites[7][1] = B2;
		Sprites[7][2] = B3;
		Sprites[7][3] = B4;
		Sprites[7][4] = B5;
		Sprites[7][5] = B6;
		Sprites[7][6] = B7;
		Sprites[8][0] = H1;
		Sprites[8][1] = H2;
		Sprites[8][2] = H3;
		Sprites[8][3] = H4;
		Sprites[8][4] = H5;
		Sprites[8][5] = H6;
		Sprites[8][6] = H7;
		
		
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
		move += 0.1f;
		
		if (move > 1500) {
			move = 0.8f;
		}
		g.setFont(pixel);
		g.setColor(Color.white);
		g.drawString("BACK", 900, 20);
		
		g.setColor(Color.black);
		g.drawString("HITMAN SPRITE PACK", 150, 125);
		g.drawRect(150, 150, 73, 73);
		g.drawImage(H1, 153, 153);
		g.drawImage(H3, 188, 153);
		g.drawImage(H4, 153, 188);
		g.drawImage(H5, 188, 188);
		g.drawImage(BH, 240, 153); // currButton = 8
		
		g.drawString("BLUE MAN SPRITE PACK", 150, 275);
		g.setColor(Color.black);
		g.drawRect(150, 300, 73, 73);
		g.drawImage(B1, 153, 303);
		g.drawImage(B3, 188, 303);
		g.drawImage(B4, 153, 338);
		g.drawImage(B5, 188, 338);
		g.drawImage(BH, 240, 303); // currButton = 7
		
		g.drawString("OLD MAN SPRITE PACK", 150, 425);
		g.setColor(Color.black);
		g.drawRect(150, 450, 73, 73);
		g.drawImage(O1, 153, 453);
		g.drawImage(O3, 188, 453);
		g.drawImage(O4, 153, 488);
		g.drawImage(O5, 188, 488);
		g.drawImage(BH, 240, 453); // currButton = 6
		
		g.drawString("ROBOT SPRITE PACK", 150, 575);
		g.setColor(Color.black);
		g.drawRect(150, 600, 73, 73);
		g.drawImage(R1, 153, 603);
		g.drawImage(R3, 188, 603);
		g.drawImage(R4, 153, 638);
		g.drawImage(R5, 188, 638);
		g.drawImage(BH, 240, 603); // currButton = 5
		
		g.setColor(Color.black);
		g.drawString("SOLDIER SPRITE PACK", 450, 125);
		g.drawRect(450, 150, 73, 73);
		g.drawImage(S1, 453, 153);
		g.drawImage(S3, 488, 153);
		g.drawImage(S4, 453, 188);
		g.drawImage(S5, 488, 188);
		g.drawImage(BH, 540, 153); // currButton = 4
		
		g.drawString("SURVIVOR SPRITE PACK", 450, 275);
		g.setColor(Color.black);
		g.drawRect(450, 300, 73, 73);
		g.drawImage(U1, 453, 303);
		g.drawImage(U3, 488, 303);
		g.drawImage(U4, 453, 338);
		g.drawImage(U5, 488, 338);
		g.drawImage(BH, 540, 303); // currButton = 3
		
		g.drawString("WOMAN SPRITE PACK", 450, 425);
		g.setColor(Color.black);
		g.drawRect(450, 450, 73, 73);
		g.drawImage(W1, 453, 453);
		g.drawImage(W3, 488, 453);
		g.drawImage(W4, 453, 488);
		g.drawImage(W5, 488, 488);
		g.drawImage(BH, 540, 453); // currButton = 2
		
		g.drawString("ZOMBIE SPRITE PACK", 450, 575);
		g.setColor(Color.black);
		g.drawRect(450, 600, 73, 73);
		g.drawImage(Z1, 453, 603);
		g.drawImage(Z3, 488, 603);
		g.drawImage(Z4, 453, 638);
		g.drawImage(Z5, 488, 638);
		g.drawImage(BH, 540, 603); // currButton = 1
		
		g.setColor(Color.black);
		g.drawString("BROWN MAN SPRITE PACK", 750, 125);
		g.drawRect(750, 150, 73, 73);
		g.drawImage(M1, 753, 153);
		g.drawImage(M3, 788, 153);
		g.drawImage(M4, 753, 188);
		g.drawImage(M5, 788, 188);
		g.drawImage(BH, 840, 153); // currButton = 0 :: Default
		
		
		g.drawRect(730, 430, 245, 200);
		g.drawString("CURRENTLY EQUIPPED", 750, 450);
		g.drawString("SPRITE PACK", 790, 475);
		g.drawRect(813, 520, 73, 73);
		g.drawImage(Sprites[equippedSprite][0], 816, 523);
		g.drawImage(Sprites[equippedSprite][2], 851, 523);
		g.drawImage(Sprites[equippedSprite][4], 816, 558);
		g.drawImage(Sprites[equippedSprite][5], 851, 558);
		
		
		if (currButton == 0) {
			g.drawImage(BBH, 840, 153);
		} else if (currButton == 1) {
			g.drawImage(BBH, 540, 603);
		} else if (currButton == 2) {
			g.drawImage(BBH, 540, 453);
		} else if (currButton == 3) {
			g.drawImage(BBH, 540, 303);
		} else if (currButton == 4) {
			g.drawImage(BBH, 540, 153);
		} else if (currButton == 5) {
			g.drawImage(BBH, 240, 603);
		} else if (currButton == 6) {
			g.drawImage(BBH, 240, 453);
		} else if (currButton == 7) {
			g.drawImage(BBH, 240, 303);
		} else if (currButton == 8) {
			g.drawImage(BBH, 240, 153);
		} 
		
		g.drawString("EQUIP", 887, 175);
		g.drawString("EQUIP", 587, 625);
		g.drawString("EQUIP", 587, 475);
		g.drawString("EQUIP", 587, 325);
		g.drawString("EQUIP", 587, 175);
		g.drawString("EQUIP", 287, 625);
		g.drawString("EQUIP", 287, 475);
		g.drawString("EQUIP", 287, 325);
		g.drawString("EQUIP", 287, 175);
		
	}
	
	private void selectSprite(int Val) {
		equippedSprite = Val;
	}
	
	public Image[][] getSpriteList() {
		return Sprites;
	}
	
	public int getSprite() {
		return equippedSprite;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {	
		int PosX = Mouse.getX();
		int PosY = Mouse.getY();
		// GO BACK TO THE MENU
		if ((PosX >= 900 && PosX <= 948) && (PosY >= 686 && PosY <= 706)) {
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(3);
			}
		}
		if ((PosX >= 840 && PosX <= 975) && (PosY >= 510 && PosY <= 565)) {
			currButton = 0;
			if (Mouse.isButtonDown(0))
				selectSprite(0);
		} else if ((PosX >= 540 && PosX <= 675) && (PosY >= 60 && PosY <= 115)) {
			currButton = 1;
			if (Mouse.isButtonDown(0))
				selectSprite(1);
		} else if ((PosX >= 540 && PosX <= 675) && (PosY >= 210 && PosY <= 265)) {
			currButton = 2;
			if (Mouse.isButtonDown(0))
				selectSprite(2);
		} else if ((PosX >= 540 && PosX <= 675) && (PosY >= 360 && PosY <= 415)) {
			currButton = 3;
			if (Mouse.isButtonDown(0))
				selectSprite(3);
		} else if ((PosX >= 540 && PosX <= 675) && (PosY >= 510 && PosY <= 565)) {
			currButton = 4;
			if (Mouse.isButtonDown(0))
				selectSprite(4);
		} else if ((PosX >= 240 && PosX <= 375) && (PosY >= 60 && PosY <= 115)) {
			currButton = 5;
			if (Mouse.isButtonDown(0))
				selectSprite(5);
		} else if ((PosX >= 240 && PosX <= 375) && (PosY >= 210 && PosY <= 265)) {
			currButton = 6;
			if (Mouse.isButtonDown(0))
				selectSprite(6);
		} else if ((PosX >= 240 && PosX <= 375) && (PosY >= 360 && PosY <= 415)) {
			currButton = 7;
			if (Mouse.isButtonDown(0))
				selectSprite(7);
		} else if ((PosX >= 240 && PosX <= 375) && (PosY >= 510 && PosY <= 565)) {
			currButton = 8;
			if (Mouse.isButtonDown(0))
				selectSprite(8);
		} else {
			currButton = -1;
		}
		
	}

	@Override
	public int getID() {
		return this.ID;
	}

}
