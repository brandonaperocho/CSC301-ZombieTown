package main;

import java.awt.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

public class Bullet {

	private float x, y;
	private float dx, dy;
	private float acc = 25.0f;
	private int ttl = 500;
	private Circle A;

	public Bullet(float x, float y, double rotation) {
		this.x = x;
		this.y = y;
		this.dx = (float) Math.cos(Math.toRadians(rotation));
		this.dy = (float) -Math.sin(Math.toRadians(rotation));
	}

	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
	}

	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		if (ttl > 0) {
				A = new Circle(x+16, y+16, 4, 4);
				g.setColor(org.newdawn.slick.Color.black);
				g.draw(A);
				g.fill(A);
		}
	}

	public void update() throws SlickException {
		x += dx * acc;
		y += dy * acc;
		ttl -= acc;
	}
	
	public Circle getA() {
		return A;
	}

	public void incrementX(int x) {
		// TODO Auto-generated method stub
		this.x += x;
	}

	public void incrementY(int y) {
		// TODO Auto-generated method stub
		this.y += y;
	}

}
