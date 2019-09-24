package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class Zombie implements Actor {

	private int healthVal;
	private int x, y;
	private Image zombie;
	private float speed = 4.1f;
	private Grid grid;
	private Circle A;
	private boolean isDead;

	public Zombie(float myX, float myY, boolean isBoss, Grid grid) {
		if (isBoss) {
			this.healthVal = 30000;
			// this.leftSprite = new Image("PathToImage");
			// this.rightSprite = new Image("PathToImage");
			// this.upSprite = new Image("PathToImage");
			// this.downSprite = new Image("PathToImage");
		} else {
			this.healthVal = 200;
			// this.leftSprite = new Image("PathToImage");
			// this.rightSprite = new Image("PathToImage");
			// this.upSprite = new Image("PathToImage");
			// this.downSprite = new Image("PathToImage");
		}
		this.setX(myX);
		this.setY(myY);
		this.grid = grid;
		this.isDead = false;
	}

	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		zombie = new Image("/OfficialGame/Sprites/Zombie/zoimbie1_hold.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Color.green);
		A = new Circle(x + 16, y + 16, 16, 16);
		g.draw(A);

		g.drawImage(zombie, x, y);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int num)
			throws SlickException {
		int rand = (int) (Math.ceil((Math.random() * 4)));
		System.out.println("Zombie's x: " + x + ", y: " + y);
		if (rand == 1) {
			x -= Math.round(num / speed);
			/*if (grid.grid[(int) Math.round((x - 48 + 437) / 64 * -1)][(int) Math
					.round((y - 48 + 617) / 64 * -1)][0] == 0
					|| grid.grid[(int) Math.round((x - 48 + 437) / 64 * -1)][(int) Math
							.round((y - 16 + 617) / 64 * -1)][0] == 0) {
				x += Math.round(num / speed);
			}*/
		} else if (rand == 2) {
			x += Math.round(num / speed);
			/*if (grid.grid[(int) Math.round((x - 16 + 437) / 64 * -1)][(int) Math
					.round((y - 16 + 617) / 64 * -1)][0] == 0
					|| grid.grid[(int) Math.round((x - 16 + 437) / 64 * -1)][(int) Math
							.round((y - 48 + 617) / 64 * -1)][0] == 0) {
				x -= Math.round(num / speed);
			}*/
		} else if (rand == 3) {
			y += Math.round(num / speed);
			/*if (grid.grid[(int) Math.round((x - 16 + 437) / 64 * -1)][(int) Math
					.round((y - 16 + 617) / 64 * -1)][0] == 0
					|| grid.grid[(int) Math.round((x - 48 + 437) / 64 * -1)][(int) Math
							.round((y - 16 + 617) / 64 * -1)][0] == 0) {
				y -= Math.round(num / speed);
			}*/
		} else if (rand == 4) {
			y -= Math.round(num / speed);
			/*if (grid.grid[(int) Math.round((x - 48 + 437) / 64 * -1)][(int) Math
					.round((y - 48 + 617) / 64 * -1)][0] == 0
					|| grid.grid[(int) Math.round((x - 16 + 437) / 64 * -1)][(int) Math
							.round((y - 48 + 617) / 64 * -1)][0] == 0) {
				y += Math.round(num / speed);
			}*/
		}
	}

	@Override
	public int getHealthValue() {
		// TODO Auto-generated method stub
		return this.healthVal;
	}

	@Override
	public void setHealthValue(int value) {
		// TODO Auto-generated method stub
		this.healthVal = value;
	}
	
	public void takeDamage(float damage) {
		if (this.healthVal <= damage) {
			this.isDead = true;
		}
		this.healthVal -= damage;
	}

	public int getY() {
		return y;
	}
	
	public boolean isDead() {
		return isDead;
	}

	public void setY(float y) {
		this.y = (int) y;
	}

	public int getX() {
		return x;
	}

	public void setX(float myX) {
		this.x = (int) myX;
	}
	
	public Circle getA() {
		return A;
	}
}
