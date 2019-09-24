package main;

import java.util.ArrayList;

public class ZombieGenerator {

	ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	int playerX, playerY;
	int range = 71;
	private Grid grid;
	private int maxZombies = 150;

	public ZombieGenerator(Grid grid) {
		this.grid = grid;
	}

	public int size() {
		return zombies.size();
	}

	public Zombie get(int i) {
		return zombies.get(i);
	}

	public void setX(int x) {
		this.playerX = x;
	}

	public void setY(int y) {
		this.playerY = y;
	}

	public void setplayerX(int x) {
		// TODO Auto-generated method stub

	}

	public void setplayerY(int y) {
		// TODO Auto-generated method stub

	}

	public void incrementX(int x) {
		// TODO Auto-generated method stub
		for (Zombie z : zombies) {
			z.setX(z.getX() + x);
		}
	}

	public void incrementY(int y) {
		// TODO Auto-generated method stub
		for (Zombie z : zombies) {
			z.setY(z.getY() + y);
		}
	}
	
	public void remove(int y) {
		zombies.remove(zombies.get(y));
	}

	public void create(int x, int y, boolean b, Grid grid) {
		// TODO Auto-generated method stub
		if (zombies.size() > maxZombies) {
			System.out.println("Max Zombies");
		} else {
			playerX = x;
			playerY = y;
			
			int rand = (int)(Math.ceil((Math.random() * 4)));
			
			
			
			if(rand == 1 ){
				int offsetx = (int) (Math.ceil((Math.random() * 700)));
				Zombie zombie = new Zombie(playerX + 3622 +offsetx, playerY + 1000, b, grid);
				zombies.add(zombie);
			} else if (rand == 2){
				int offsety = (int) (Math.ceil((Math.random() * 700)));
				Zombie zombie = new Zombie(playerX + 1000, playerY + 3300 + offsety, b, grid);
				zombies.add(zombie);
			} else if (rand == 3) {
				int offsetx = (int) (Math.ceil((Math.random() * 700)));
				Zombie zombie = new Zombie(playerX + 3622 +offsetx, playerY + 5400, b, grid);
				zombies.add(zombie);
			} else if (rand == 4) {
				int offsety = (int) (Math.ceil((Math.random() * 700)));
				Zombie zombie = new Zombie(playerX + 5400, playerY + 3300 + offsety, b, grid);
				zombies.add(zombie);
			}
		}
		
		
	}
}
