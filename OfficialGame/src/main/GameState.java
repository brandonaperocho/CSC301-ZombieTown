package main;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class GameState extends BasicGameState {

	private final int ID;
	private TiledMap map;
	private int x, y, selectedControls;
	private float speed = 3.5f;
	private float mouseX, mouseY, myX, myY;
	private boolean PAUSED = false;
	private boolean HELPMENU = false;
	private boolean CONTROLSMENU = false;
	private boolean SHOPMENU = false;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private boolean fireDelay, reloadDelay;
	private long runtime = 0;
	private Player player;
	private UI ui;
	private Grid grid;
	private ZombieGenerator generator;
	private int[][] controlDecider;
	protected static int totalTime;
	private Circle A;
	private int timeDelay;
	private int shotDelay;

	public GameState(int num) {
		this.ID = num;
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		x = -3461;
		y = -3254;

		selectedControls = 0; // Decides which control set to use; Control1 is
								// default
		controlDecider = new int[2][6];
		// The first Set of Controls
		controlDecider[0][0] = Input.KEY_W;
		controlDecider[0][1] = Input.KEY_A;
		controlDecider[0][2] = Input.KEY_S;
		controlDecider[0][3] = Input.KEY_D;
		controlDecider[0][4] = Input.KEY_R;
		controlDecider[0][5] = Input.KEY_Q;
		// The second Set of Controls
		controlDecider[1][0] = Input.KEY_UP;
		controlDecider[1][1] = Input.KEY_LEFT;
		controlDecider[1][2] = Input.KEY_DOWN;
		controlDecider[1][3] = Input.KEY_RIGHT;
		controlDecider[1][4] = Input.KEY_RCONTROL;
		controlDecider[1][5] = Input.KEY_RSHIFT;

		player = new Player();
		player.init(gc, sbg);

		ui = new UI(x, y);
		ui.init(gc, sbg);

		grid = new Grid();
		grid.init(gc, sbg);

		map = new TiledMap("/OfficialGame/maps/NewMap.tmx");

		generator = new ZombieGenerator(grid);
		generator.setplayerX(x);
		generator.setplayerY(y);

		this.myX = (float) gc.getWidth() / 2;
		this.myY = (float) gc.getHeight() / 2;
		
		A = new Circle(this.myX + 16, this.myY + 16, 16, 16);

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.scale(1.0f, 1.0f);
		g.setColor(Color.green);
		g.draw(A);
		map.render(x, y);
		player.render(gc, sbg, g);
		ui.updateXY(x, y);
		ui.render(gc, sbg, g);
		if (bullets.size() > 0) {
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).render(gc, sbg, g);
				if (generator.size() > 0) {
					for (int j = 0; j < generator.size(); j++) {
						if (generator.get(j).getA() != null && bullets.get(i).getA() != null 
								&& generator.get(j).getA().intersects(bullets.get(i).getA()) 
								&& shotDelay == 0) {
							generator.get(j).takeDamage(player.damage());
							shotDelay = 25;
							if (generator.get(j).isDead()) {
								generator.remove(j);
								ui.subtractScore(-10.0);
							}
						}
					}
				}
			}
		}
		// System.out.println("generator's size is:" + generator.size());
		if (generator.size() > 0) {
			for (int i = 0; i < generator.size(); i++) {
				generator.get(i).init(gc, sbg);
				generator.get(i).render(gc, sbg, g);
				if (generator.get(i).getA().intersects(A) && timeDelay == 0) {
					if (ui.getHour() >= 21 || ui.getHour() <= 5)
						player.takeDamage(30.0f); // Take additional damage at night
					player.takeDamage(20.0f);
					timeDelay = 200;
				}
			}
		}

		if (PAUSED) {
			ui.renderPause();
		} else {

		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int num)
			throws SlickException {

		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		runtime += 1;
		if (timeDelay > 0)
			timeDelay--;
		if (shotDelay > 0)
			shotDelay--;
		
		if (runtime % 350 == 0) {
			reloadDelay = false;
			player.setState("idle");
		}

		if (player.isDead) {

			ui.endGame();
			sbg.getState(3).init(gc, sbg);
			sbg.enterState(3);

		}
		// Not paused
		if (!PAUSED) {
			ui.setTime(num);
			totalTime += num;
			if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
				PAUSED = true;
			}

			if (runtime % 350 == 0) {
				generator.create(x, y, false, grid);
			}
			
			if (gc.getInput().isKeyDown(controlDecider[selectedControls][3])
					&& (x > -4883)) {
				player.setState("walking");
				x -= Math.round(num / speed);
				generator.incrementX(-Math.round(num / speed));
				for (Bullet b : bullets) {
					b.incrementX(-Math.round(num / speed));
				}
				if (grid.grid[(int) Math.round((x - 48 + 437) / 64 * -1)][(int) Math
						.round((y - 48 + 617) / 64 * -1)][0] == 0
						|| grid.grid[(int) Math.round((x - 48 + 437) / 64 * -1)][(int) Math
								.round((y - 16 + 617) / 64 * -1)][0] == 0) {
					x += Math.round(num / speed);
					generator.incrementX(Math.round(num / speed));
					for (Bullet b : bullets) {
						b.incrementX(Math.round(num / speed));
					}
				}
			}
			if (gc.getInput().isKeyDown(controlDecider[selectedControls][1])
					&& (x < -437)) {
				player.setState("walking");
				x += Math.round(num / speed);
				generator.incrementX(Math.round(num / speed));
				for (Bullet b : bullets) {
					b.incrementX(Math.round(num / speed));
				}
				if (grid.grid[(int) Math.round((x - 16 + 437) / 64 * -1)][(int) Math
						.round((y - 16 + 617) / 64 * -1)][0] == 0
						|| grid.grid[(int) Math.round((x - 16 + 437) / 64 * -1)][(int) Math
								.round((y - 48 + 617) / 64 * -1)][0] == 0) {
					x -= Math.round(num / speed);
					generator.incrementX(-Math.round(num / speed));
					for (Bullet b : bullets) {
						b.incrementX(-Math.round(num / speed));
					}
				}
			}
			if (gc.getInput().isKeyDown(controlDecider[selectedControls][0])
					&& (y < -617)) {
				player.setState("walking");
				y += Math.round(num / speed);
				generator.incrementY(Math.round(num / speed));
				for (Bullet b : bullets) {
					b.incrementY(Math.round(num / speed));
				}
				if (grid.grid[(int) Math.round((x - 16 + 437) / 64 * -1)][(int) Math
						.round((y - 16 + 617) / 64 * -1)][0] == 0
						|| grid.grid[(int) Math.round((x - 48 + 437) / 64 * -1)][(int) Math
								.round((y - 16 + 617) / 64 * -1)][0] == 0) {
					y -= Math.round(num / speed);
					generator.incrementY(-Math.round(num / speed));
					for (Bullet b : bullets) {
						b.incrementY(-Math.round(num / speed));
					}
				}
			}
			if (gc.getInput().isKeyDown(controlDecider[selectedControls][2])
					&& (y > -5063)) {
				player.setState("walking");
				y -= Math.round(num / speed);
				generator.incrementY(-Math.round(num / speed));
				for (Bullet b : bullets) {
					b.incrementY(-Math.round(num / speed));
				}
				if (grid.grid[(int) Math.round((x - 48 + 437) / 64 * -1)][(int) Math
						.round((y - 48 + 617) / 64 * -1)][0] == 0
						|| grid.grid[(int) Math.round((x - 16 + 437) / 64 * -1)][(int) Math
								.round((y - 48 + 617) / 64 * -1)][0] == 0) {
					y += Math.round(num / speed);
					generator.incrementY(Math.round(num / speed));
					for (Bullet b : bullets) {
						b.incrementY(Math.round(num / speed));
					}
				}
			}

			if (!gc.getInput().isKeyDown(controlDecider[selectedControls][0])
					&& !gc.getInput().isKeyDown(
							controlDecider[selectedControls][1])
					&& !gc.getInput().isKeyDown(
							controlDecider[selectedControls][2])
					&& !gc.getInput().isKeyDown(
							controlDecider[selectedControls][3])) {
				player.setState("idle");
			}

			float dx = mouseX - myX;
			float dy = mouseY - myY;
			if (gc.getInput().isMousePressed(0)) {
				if (player.canShoot() && !fireDelay && !reloadDelay) {
					double rotation = (360 + Math.toDegrees(Math.atan2(dy, dx))) % 360;
					addBullet(gc, sbg, rotation);
					player.shoot();
					fireDelay = true;
				} else if (!player.canShoot() && !fireDelay) {
					player.setState("reloading");
					player.reload();
					reloadDelay = true;
				}
			}

			if (gc.getInput().isKeyDown(controlDecider[selectedControls][4])) {
				player.setState("reloading");
				player.reload();
				reloadDelay = true;
			}

			if (bullets.size() > 0) {
				for (int i = 0; i < bullets.size(); i++) {
					Bullet bullet = bullets.get(i);
					bullet.update();
				}
			}

			if (generator.size() > 0) {
				for (int i = 0; i < generator.size(); i++) {
					generator.get(i).update(gc, sbg, num);
				}
			}
			if (fireDelay && runtime % 30 == 0) {
				fireDelay = false;
			}
			
			if (reloadDelay && runtime % 350 == 0) {
				reloadDelay = false;
				player.setState("idle");
			}
			
			if (gc.getInput().isKeyPressed(controlDecider[selectedControls][5])) {
				if(player.currentGun() == 0 && player.isUnlocked(2)){
					player.switchGun(2);
				} else if(player.currentGun() == 0 && player.isUnlocked(5)){
					player.switchGun(5);
				} else if(player.currentGun() == 2 && player.isUnlocked(5)){
					player.switchGun(5);
				} else if(player.currentGun() == 2 && player.isUnlocked(0)){
					player.switchGun(0);
				} else if(player.currentGun() == 5 && player.isUnlocked(0)){
					player.switchGun(0);
				} else if(player.currentGun() == 5 && player.isUnlocked(2)){
					player.switchGun(2);
				}
			}
			
			
			player.rotate(dx, dy);
		} else { // Game paused
			// Check if mouse hovering over buttons
			if (HELPMENU) {
				if ((mouseX >= 380 && mouseX <= 680)
						&& (mouseY >= 30 && mouseY <= 100)) {
					ui.setcurrButton(1);
					// IF BACK is pressed.
					if (gc.getInput().isMousePressed(0)) {
						ui.setcurrButton(-1);
						ui.leaveHelp();
						HELPMENU = false;
					}
				} else {
					ui.setcurrButton(-1);
				}
				if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
					ui.setcurrButton(-1);
					ui.leaveHelp();
					HELPMENU = false;
				}
			} else if (CONTROLSMENU) {
				if ((mouseX >= 380 && mouseX <= 680)
						&& (mouseY >= 30 && mouseY <= 100)) {
					ui.setcurrButton(1);
					// IF BACK is pressed.
					if (gc.getInput().isMousePressed(0)) {
						ui.setcurrButton(-1);
						ui.leaveControls();
						CONTROLSMENU = false;
					}

				} else if ((mouseX >= 135 && mouseX <= 425)
						&& (mouseY >= 510 && mouseY <= 570)) {
					ui.setcurrButton(5);
					// IF CONTROLS 1 IS PRESSED
					if (gc.getInput().isMousePressed(0)) {
						selectedControls = 0;
						ui.setControls(1);
					}
				} else if ((mouseX >= 635 && mouseX <= 925)
						&& (mouseY >= 510 && mouseY <= 570)) {
					ui.setcurrButton(6);
					// IF CONTROLS 1 IS PRESSED
					if (gc.getInput().isMousePressed(0)) {
						selectedControls = 1;
						ui.setControls(2);
					}
				} else {
					ui.setcurrButton(-1);
				}
				if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
					ui.setcurrButton(-1);
					ui.leaveControls();
					CONTROLSMENU = false;
				}
			} else if (SHOPMENU) {
				if ((mouseX >= 380 && mouseX <= 680)
						&& (mouseY >= 30 && mouseY <= 100)) {
					ui.setcurrButton(1);
					// IF BACK is pressed.
					if (gc.getInput().isMousePressed(0)) {
						ui.setcurrButton(-1);
						ui.leaveShop();
						SHOPMENU = false;
					}
				} else if ((mouseX >= 40 && mouseX <= 325)
						&& (mouseY >= 560 && mouseY <= 620)) {
					ui.setcurrButton(7);
					if (gc.getInput().isMousePressed(0)) {
						if (ui.getScore() >= 50.0) {
							ui.subtractScore(50.0);
							// Give player access to MG
							player.unlock(5);
						}
					}
				} else if ((mouseX >= 390 && mouseX <= 675)
						&& (mouseY >= 560 && mouseY <= 620)) {
					ui.setcurrButton(8);
					if (gc.getInput().isMousePressed(0)) {
						if (ui.getScore() >= 10.0) {
							ui.subtractScore(10.0);
							// Give player access to Pistol
							player.unlock(0);
						}
					}
				} else if ((mouseX >= 740 && mouseX <= 1025)
						&& (mouseY >= 560 && mouseY <= 620)) {
					ui.setcurrButton(9);
					if (gc.getInput().isMousePressed(0)) {
						if (ui.getScore() >= 30.0) {
							ui.subtractScore(30.0);
							// Give player access to Shotgun
							player.unlock(2);
						}
					}
				} else if ((mouseX >= 40 && mouseX <= 325)
						&& (mouseY >= 360 && mouseY <= 420)) {
					ui.setcurrButton(10);
					if (gc.getInput().isMousePressed(0)) {
						if (ui.getScore() >= 10.0 && player.isUnlocked(5)) {
							ui.subtractScore(10.0);
							// Give player 150 MG Rounds
							player.addAmmo(150,5);
						}
					}
				} else if ((mouseX >= 390 && mouseX <= 675)
						&& (mouseY >= 360 && mouseY <= 420)) {
					ui.setcurrButton(11);
					if (gc.getInput().isMousePressed(0)) {
						if (ui.getScore() >= 10.0 && player.isUnlocked(0)) {
							ui.subtractScore(10.0);
							// Give player 75 Pistol Rounds
							player.addAmmo(75,0);
						}
					}
				} else if ((mouseX >= 740 && mouseX <= 1025)
						&& (mouseY >= 360 && mouseY <= 420)) {
					ui.setcurrButton(12);
					if (gc.getInput().isMousePressed(0)) {
						if (ui.getScore() >= 10.0 && player.isUnlocked(2)) {
							ui.subtractScore(10.0);
							// Give player 25 Shotgun Rounds
							player.addAmmo(25,2);
						}
					}
				} else if ((mouseX >= 40 && mouseX <= 325)
						&& (mouseY >= 160 && mouseY <= 220)) {
					ui.setcurrButton(13);
					if (gc.getInput().isMousePressed(0) && player.isUnlocked(5)) {
						if (ui.getScore() >= 30.0) {
							ui.subtractScore(30.0);
							// Give player 600 MG Rounds
							player.addAmmo(600,5);
						}
					}
				} else if ((mouseX >= 390 && mouseX <= 675)
						&& (mouseY >= 160 && mouseY <= 220)) {
					ui.setcurrButton(14);
					if (gc.getInput().isMousePressed(0) && player.isUnlocked(0)) {
						if (ui.getScore() >= 30.0) {
							ui.subtractScore(30.0);
							// Give player 300 Pistol Rounds
							player.addAmmo(300,0);
						}
					}
				} else if ((mouseX >= 740 && mouseX <= 1025)
						&& (mouseY >= 160 && mouseY <= 220)) {
					ui.setcurrButton(15);
					if (gc.getInput().isMousePressed(0) && player.isUnlocked(2)) {
						if (ui.getScore() >= 30.0) {
							ui.subtractScore(30.0);
							// Give player 100 Shotgun Rounds
							player.addAmmo(100,2);
						}
					}
				} else {
					ui.setcurrButton(-1);
				}
				if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
					ui.setcurrButton(-1);
					ui.leaveShop();
					SHOPMENU = false;
				}
			} else {
				if ((mouseX >= 380 && mouseX <= 680)
						&& (mouseY >= 510 && mouseY <= 580)) {
					ui.setcurrButton(0);
					// IF QUIT is pressed.
					if (gc.getInput().isMousePressed(0)) {
						ui.endGame();

						sbg.getState(3).init(gc, sbg);
						sbg.enterState(3);
						ui.unPause();
						PAUSED = false;
					}
				} else if ((mouseX >= 380 && mouseX <= 680)
						&& (mouseY >= 110 && mouseY <= 180)) {
					ui.setcurrButton(1);
					// IF BACK is pressed.
					if (gc.getInput().isMousePressed(0)) {
						ui.unPause();
						PAUSED = false;
					}
				} else if ((mouseX >= 380 && mouseX <= 680)
						&& (mouseY >= 310 && mouseY <= 380)) {
					ui.setcurrButton(2);
					// IF HOWTOPLAY is pressed.
					if (gc.getInput().isMousePressed(0)) {
						ui.enterHelp();
						HELPMENU = true;
					}
				} else if ((mouseX >= 380 && mouseX <= 680)
						&& (mouseY >= 210 && mouseY <= 280)) {
					ui.setcurrButton(3);
					// IF CHANGECONTROLS is pressed.
					if (gc.getInput().isMousePressed(0)) {
						ui.enterControls();
						CONTROLSMENU = true;
					}
				} else if ((mouseX >= 380 && mouseX <= 680)
						&& (mouseY >= 410 && mouseY <= 480)) {
					ui.setcurrButton(4);
					// IF CHANGECONTROLS is pressed.
					if (gc.getInput().isMousePressed(0)) {
						ui.enterShop();
						SHOPMENU = true;
					}
				} else {
					ui.setcurrButton(-1);
				}
				if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
					ui.unPause();
					PAUSED = false;
				}
			}
		}
	}

	public void addBullet(GameContainer gc, StateBasedGame sbg, double rotation)
			throws SlickException {
		Bullet bullet = new Bullet(this.myX, this.myY, rotation);
		bullets.add(bullet);
		bullet.init(gc, sbg);
	}

	public void takeDamage(GameContainer gc, StateBasedGame sbg, int damage)
			throws SlickException {

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

}
