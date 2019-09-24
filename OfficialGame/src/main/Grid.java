package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Grid {

	protected int[][][] grid;

	public Grid() {

	}

	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		grid = new int[71][71][2];
		for (int i = 0; i < 71; i++) {
			for (int j = 0; j < 71; j++) {
				grid[i][j][0] = 1;
				grid[i][j][1] = 1;
			}
		}
		for (int i = 0; i < 71; i++) {
			grid[i][70][0] = 0;
			grid[70][i][0] = 0;
		}

		// grid[x][y][0] = WALKABLE 1 = Yes, 0 = No
		// grid[x][y][1] = CAN SHOOT THROUGH 1 = Yes, 0 = No
		// -------------------------- Above River
		grid[0][3][0] = 0;
		grid[3][3][0] = 0;
		grid[6][3][0] = 0;
		grid[9][3][0] = 0;
		grid[12][3][0] = 0;
		grid[15][3][0] = 0;
		grid[18][3][0] = 0;
		grid[21][3][0] = 0;
		grid[24][3][0] = 0;
		grid[27][3][0] = 0;
		grid[30][3][0] = 0;
		grid[33][3][0] = 0;
		grid[36][3][0] = 0;
		grid[39][3][0] = 0;
		grid[55][3][0] = 0;
		grid[58][3][0] = 0;
		grid[61][3][0] = 0;
		grid[64][3][0] = 0;
		grid[67][3][0] = 0;
		// -------------------------- Above River
		grid[1][0][0] = 0;
		grid[2][0][0] = 0;
		grid[4][0][0] = 0;
		grid[5][0][0] = 0;
		grid[7][0][0] = 0;
		grid[8][0][0] = 0;
		grid[10][0][0] = 0;
		grid[11][0][0] = 0;
		grid[13][0][0] = 0;
		grid[14][0][0] = 0;
		grid[16][0][0] = 0;
		grid[17][0][0] = 0;
		grid[19][0][0] = 0;
		grid[20][0][0] = 0;
		grid[22][0][0] = 0;
		grid[23][0][0] = 0;
		grid[25][0][0] = 0;
		grid[26][0][0] = 0;
		grid[28][0][0] = 0;
		grid[29][0][0] = 0;
		grid[31][0][0] = 0;
		grid[32][0][0] = 0;
		grid[34][0][0] = 0;
		grid[35][0][0] = 0;
		grid[37][0][0] = 0;
		grid[38][0][0] = 0;
		grid[40][0][0] = 0;
		grid[41][0][0] = 0;
		grid[53][0][0] = 0;
		grid[54][0][0] = 0;
		grid[56][0][0] = 0;
		grid[57][0][0] = 0;
		grid[59][0][0] = 0;
		grid[60][0][0] = 0;
		grid[62][0][0] = 0;
		grid[63][0][0] = 0;
		grid[65][0][0] = 0;
		grid[66][0][0] = 0;
		grid[68][0][0] = 0;
		grid[69][0][0] = 0;
		// -------------------------- Bridge
		grid[52][3][0] = 0;
		grid[42][3][0] = 0;
		grid[52][11][0] = 0;
		grid[42][11][0] = 0;
		// -------------------------- Water
		for (int i = 0; i < 43; i++) {
			for (int j = 4; j < 11; j++) {
				grid[i][j][0] = 0;
			}
		}
		// -------------------------- Water
		for (int i = 52; i < 70; i++) {
			for (int j = 4; j < 11; j++) {
				grid[i][j][0] = 0;
			}
		}
		// -------------------------- Factory Building
		for (int i = 20; i < 40; i++) {
			for (int j = 12; j < 21; j++) {
				grid[i][j][0] = 0;
			}
		}
		for (int i = 20; i < 32; i++) {
			grid[i][21][0] = 0;
		}
		for (int i = 20; i < 29; i++) {
			grid[i][25][0] = 0;
		}
		for (int i = 20; i < 29; i++) {
			grid[i][29][0] = 0;
		}
		for (int i = 25; i < 34; i++) {
			grid[20][i][0] = 0;
		}
		for (int i = 21; i < 40; i++) {
			grid[i][33][0] = 0;
		}
		for (int i = 38; i < 40; i++) {
			for (int j = 21; j < 33; j++) {
				grid[i][j][0] = 0;
			}
		}
		for (int i = 31; i < 33; i++) {
			for (int j = 24; j < 33; j++) {
				grid[i][j][0] = 0;
			}
		}
		for (int i = 33; i < 38; i++) {
			grid[i][32][0] = 0;
		}
		for (int i = 34; i < 37; i = i + 2) {
			for (int j = 26; j < 31; j++) {
				grid[i][j][0] = 0;
			}
		}
		grid[35][26][0] = 0;
		grid[33][24][0] = 0;
		grid[34][24][0] = 0;
		grid[36][24][0] = 0;
		grid[37][24][0] = 0;
		// -------------------------- Factory Building Garden Stuff
		for (int i = 16; i < 18; i++) {
			for (int j = 14; j < 16; j++)
				grid[i][j][0] = 0;
			for (int j = 17; j < 19; j++)
				grid[i][j][0] = 0;
			for (int j = 20; j < 22; j++)
				grid[i][j][0] = 0;
			for (int j = 25; j < 27; j++)
				grid[i][j][0] = 0;
			for (int j = 28; j < 30; j++)
				grid[i][j][0] = 0;
			for (int j = 31; j < 33; j++)
				grid[i][j][0] = 0;
		}
		for (int i = 0; i < 16; i++) {
			grid[i][36][0] = 0;
			if (i == 4) {
				i = 9;
			}
		}
		grid[4][35][0] = 0;
		grid[10][35][0] = 0;
		// -------------------------- Right Houses on Top
		for (int i = 14; i < 35; i++) {
			grid[52][i][0] = 0;
			if (i == 14 || i == 23 || i == 32) {
				i++;
			}
		}
		int tempY = 12;
		for (int u = 0; u < 3; u++) {
			for (int i = 54; i < 64; i++)
				grid[i][tempY][0] = 0;
			for (int i = 54; i < 59; i++) {
				grid[i][tempY + 1][0] = 0;
				if (i == 54) {
					i = i + 2;
				}
			}
			for (int i = 54; i < 64; i++) {
				grid[i][tempY + 2][0] = 0;
				if (i == 55) {
					i = i + 5;
				}
			}
			for (int i = 61; i < 64; i++)
				grid[i][tempY + 3][0] = 0;
			for (int i = 54; i < 64; i++) {
				grid[i][tempY + 4][0] = 0;
				if (i == 58) {
					i = i + 2;
				}
			}
			for (int i = 54; i < 64; i++)
				grid[i][tempY + 5][0] = 0;

			grid[65][tempY + 1][0] = 0;
			grid[66][tempY + 1][0] = 0;
			grid[65][tempY + 2][0] = 0;
			grid[66][tempY + 2][0] = 0;
			grid[67][tempY + 2][0] = 0;

			grid[65][tempY + 4][0] = 0;
			grid[66][tempY + 4][0] = 0;
			grid[65][tempY + 5][0] = 0;
			grid[66][tempY + 5][0] = 0;
			grid[67][tempY + 4][0] = 0;

			tempY += 9;
		}
		for (int i = 54; i < 59; i++) {
			grid[i][18][0] = 0;
			grid[i][20][0] = 0;
			grid[i][27][0] = 0;
			grid[i][29][0] = 0;
		}

		// -------------------------- Right Houses on Bottom
		tempY = 50;
		for (int u = 0; u < 2; u++) {
			// Trees to the right of the driveway
			for (int i = 64; i < 68; i++) {
				for (int j = tempY - 3; j < tempY + 1; j++) {
					grid[i][j][0] = 0;
				}
			}
			// Trees and shrubs under driveway
			grid[54][tempY][0] = 0;
			grid[55][tempY][0] = 0;
			grid[54][tempY + 1][0] = 0;
			grid[55][tempY + 1][0] = 0;
			grid[52][tempY + 2][0] = 0;
			grid[52][tempY + 3][0] = 0;
			grid[52][tempY + 6][0] = 0;
			grid[52][tempY + 7][0] = 0;

			// House
			for (int i = 53; i < 67; i++)
				grid[i][tempY + 7][0] = 0;

			for (int i = 53; i < 67; i++) {
				grid[i][tempY + 6][0] = 0;
				if (i == 58)
					i = i + 4;
			}
			for (int i = 53; i < 67; i++) {
				grid[i][tempY + 4][0] = 0;
				grid[i][tempY + 5][0] = 0;
				if (i == 53)
					i = i + 10;
			}
			for (int i = 53; i < 67; i++) {
				grid[i][tempY + 3][0] = 0;
				if (i == 56)
					i = i + 2;
				if (i == 60)
					i = i + 4;
			}
			for (int i = 53; i < 67; i++) {
				grid[i][tempY + 2][0] = 0;
				if (i == 56)
					i = i + 2;
				if (i == 60)
					i = i + 1;
			}
			grid[56][tempY][0] = 0;
			grid[59][tempY][0] = 0;
			grid[56][tempY + 1][0] = 0;
			grid[59][tempY + 1][0] = 0;

			tempY += 12;
		}
		// Collision for the bottom left corner - more right house
		tempY = 49;
		for (int i = 25; i < 40; i++) {
			grid[i][tempY][0] = 0;
			if (i == 27)
				i = i + 2;
		}
		for (int i = 25; i < 40; i++) {
			grid[i][tempY+1][0] = 0;
			if (i == 27)
				i = i + 2;
			if (i == 33)
				i = i + 3;
		}
		for (int i = 25; i < 40; i++) {
			grid[i][tempY+2][0] = 0;
			if (i == 26)
				i = 38;
		}
		for (int i = 25; i < 40; i++) {
			grid[i][tempY+3][0] = 0;
			grid[i][tempY+4][0] = 0;
			if (i == 25)
				i = i + 2;
			if (i == 36)
				i = i + 2;
		}
		for (int i = 25; i < 40; i++) {
			grid[i][tempY+5][0] = 0;
			grid[i][tempY+6][0] = 0;
			grid[i][tempY+15][0] = 0;
			grid[i][tempY+16][0] = 0;
			grid[i][tempY+17][0] = 0;
			if (i == 25)
				i = 38;
		}		
		for (int i = 25; i < 40; i++) {
			grid[i][tempY+7][0] = 0;
			grid[i][tempY+8][0] = 0;
			grid[i][tempY+11][0] = 0;
			grid[i][tempY+12][0] = 0;
			if (i == 33)
				i = i + 2;
			if (i == 37)
				i = i + 1;
		}
		for (int i = 36; i < 40; i++) {
			grid[i][tempY+9][0] = 0;
			grid[i][tempY+10][0] = 0;
			if (i == 37)
				i = i + 1;
		}
		for (int i = 25; i < 40; i++) {
			grid[i][tempY+13][0] = 0;
			grid[i][tempY+14][0] = 0;
			if (i == 33)
				i = i + 5;
		}
		for (int i = 25; i < 40; i++) {
			grid[i][tempY+18][0] = 0;
			if (i == 37)
				i = i + 1;
		}
		for (int i = 25; i < 40; i++) {
			grid[i][tempY+19][0] = 0;
		}
		
		// Collision for the bottom left corner - more left house
		
		
		
		tempY = 52;
		for (int i = 9; i < 22; i++) {
			grid[i][tempY][0] = 0;
		}
		for (int i = 9; i < 22; i++) {
			grid[i][tempY+1][0] = 0;
			if (i == 11)
				i = 17; 
		}
		for (int i = 9; i < 22; i++) {
			grid[i][tempY+2][0] = 0;
			if (i == 11 || i == 16 | i == 19)
				i = i + 1; 
		}
		for (int i = 9; i < 22; i++) {
			grid[i][tempY+3][0] = 0;
			if (i == 9)
				i = 12;
			if (i == 16 | i == 19)
				i = i + 1; 
		}
		for (int i = 9; i < 22; i++) {
			grid[i][tempY+4][0] = 0;
			grid[i][tempY+5][0] = 0;
			grid[i][tempY+6][0] = 0;
			if (i == 9)
				i = 20; 
		}
		for (int i = 9; i < 22; i++) {
			grid[i][tempY+7][0] = 0;
			grid[i][tempY+8][0] = 0;
			if (i == 9)
				i = 11; 
			if (i == 17)
				i = 20;
		}
		for (int i = 17; i < 22; i++) {
			grid[i][tempY+9][0] = 0;
			grid[i][tempY+10][0] = 0;
			grid[i][tempY+12][0] = 0;
			grid[i][tempY+13][0] = 0;
			if (i == 17)
				i = 20; 
		}
		for (int i = 17; i < 22; i++) {
			grid[i][tempY+11][0] = 0;
			if (i == 17)
				i = 18; 
			if (i == 19)
				i = 20;
		}
		for (int i = 17; i < 22; i++) {
			grid[i][tempY+14][0] = 0;
		}
		
		grid[13][62][0] = 0;
		grid[13][63][0] = 0;
		grid[14][62][0] = 0;
		grid[14][63][0] = 0;
		grid[14][64][0] = 0;
		
		for (int i = 11; i < 22; i=i+3) {
			for (int j = 49; j < 51; j++) {
				grid[i][j][0] = 0;
				grid[i+1][j][0] = 0;
			}
		}

	}

	public boolean walkable(int playerX, int playerY) {
		return true;
	}

}
