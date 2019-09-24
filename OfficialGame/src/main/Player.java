package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

public class Player implements Actor {

	private int healthVal;
	private int armorVal;
	protected boolean isDead = false;
	private int weaponID;
	protected int maxAmmoP, ammoP, clipSizeP; // weaponID = 0
	protected int maxAmmoS, ammoS, clipSizeS; // weaponID = 2
	protected int maxAmmoM, ammoM, clipSizeM; // weaponID = 5
	protected boolean pistol,shotgun,machineGun;
	private int score;
	private String state;
	private Image[][] sprites;
	private Image player;
	private Image playerReload;
	private SpriteSheet walkSheet;
	private Animation walk;

	public Player() {
		this.healthVal = 100;
		this.armorVal = 100;
		this.state = "idle";
		
		this.weaponID = 0;

		this.pistol=true;
		this.maxAmmoP = 50;
		this.clipSizeP = 10;
		this.ammoP = this.clipSizeP;
		
		this.shotgun = false;
		this.maxAmmoS = 20;
		this.clipSizeS = 2;
		this.ammoS = this.clipSizeS;
		this.machineGun = false;
		this.maxAmmoM = 400;
		this.clipSizeM = 50;
		this.ammoM = this.clipSizeM;
	}

	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {

		sprites = ((StoreState) arg1.getState(4)).getSpriteList();
		player = sprites[((StoreState) arg1.getState(4)).getSprite()][0];
		playerReload = sprites[((StoreState) arg1.getState(4)).getSprite()][4];
		walkSheet = new SpriteSheet(
				sprites[((StoreState) arg1.getState(4)).getSprite()][weaponID + 1],
				32, 32);
		walk = new Animation(walkSheet, 400);
	}

	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {

		if (state == "walking") {
			walk.draw(gc.getWidth() / 2, gc.getHeight() / 2);
		} else if (state == "reloading") {
			g.drawImage(playerReload, gc.getWidth() / 2, gc.getHeight() / 2);
		} else {
			g.drawImage(player, gc.getWidth() / 2, gc.getHeight() / 2);
		}

		g.setColor(Color.black);
		g.fillRoundRect(800, 20, 255, 35, 5);
		g.fillRoundRect(800, 60, 255, 35, 5);
		g.setColor(Color.white);
		g.drawString("ARMOR:", 740, 25);
		g.drawString("HEALTH:", 733, 65);
		g.setColor(Color.black);
		g.fillRect(800, 20, 250, 30);
	    g.setColor(Color.cyan); 
	    g.fillRect(800, 20, (this.armorVal * 2) + 50, 30); 
	    g.setColor(Color.red);
	    g.fillRect(800, 60, 250, 30);
	    g.setColor(Color.green);
	    g.fillRect(800, 60, (this.healthVal * 2) + 50, 30);
	 


		g.setColor(Color.white);
		if (weaponID == 0) {
			g.drawString("Pistol Ammo: " + this.ammoP + "/" + this.maxAmmoP,
					gc.getWidth() - 200, gc.getHeight() - 100);
		} else if (weaponID == 2) {
			g.drawString("Shotgun Ammo: " + this.ammoS + "/" + this.maxAmmoS,
					gc.getWidth() - 200, gc.getHeight() - 100);
		} else if (weaponID == 5) {
			g.drawString("Machine Gun Ammo: " + this.ammoM + "/"
					+ this.maxAmmoM, gc.getWidth() - 200, gc.getHeight() - 100);
		}
	}

	public void update() throws SlickException {

	}

	public void setState(String state) {
		this.state = state;
	}

	public void takeDamage(float damage) {
		this.armorVal -= damage;
		if (this.armorVal < 0) {
			this.healthVal += this.armorVal;
			this.armorVal = 0;
		}
		if (this.healthVal <= damage) {
			this.isDead = true;
		}
	}

	public boolean canShoot() {
		if (weaponID == 0) {
			if (this.ammoP > 0) {
				return true;
			}
		} else if (weaponID == 2) {
			if (this.ammoS > 0) {
				return true;
			}
		} else if (weaponID == 5) {
			if (this.ammoM > 0) {
				return true;
			}
		}
		return false;
	}

	public void shoot() {
		if (weaponID == 0) {
			this.ammoP--;
		} else if (weaponID == 2) {
			this.ammoS--;
		} else if (weaponID == 5) {
			this.ammoM--;
		}
	}

	public void rotate(float X, float Y) {
		player.setRotation((float) (Math.toDegrees(Math.atan2(X, Y))));
		playerReload.setRotation((float) (Math.toDegrees(Math.atan2(X, Y))));
		walk.getCurrentFrame().setRotation(
				(float) (Math.toDegrees(Math.atan2(X, Y))));
	}

	public void reload() {
		if (weaponID == 0) {
			if (this.maxAmmoP == 0) {
				// do nothing
			} else if (this.ammoP < this.clipSizeP) {
				if (this.maxAmmoP < this.clipSizeP
						&& this.maxAmmoP + this.ammoP < this.clipSizeP) {
					this.ammoP += this.maxAmmoP;
					this.maxAmmoP = 0;
				} else {
					int diff = this.clipSizeP - this.ammoP;
					this.ammoP = this.clipSizeP;
					this.maxAmmoP -= diff;
				}
			}
		} else if (weaponID == 2) {
			if (this.maxAmmoS == 0) {
				// do nothing
			} else if (this.ammoS < this.clipSizeS) {
				if (this.maxAmmoS < this.clipSizeS
						&& this.maxAmmoS + this.ammoS < this.clipSizeS) {
					this.ammoS += this.maxAmmoS;
					this.maxAmmoS = 0;
				} else {
					int diff = this.clipSizeS - this.ammoS;
					this.ammoS = this.clipSizeS;
					this.maxAmmoS -= diff;
				}
			}
		} else if (weaponID == 5) {
			if (this.maxAmmoM == 0) {
				// do nothing
			} else if (this.ammoM < this.clipSizeM) {
				if (this.maxAmmoM < this.clipSizeM
						&& this.maxAmmoM + this.ammoM < this.clipSizeM) {
					this.ammoM += this.maxAmmoM;
					this.maxAmmoM = 0;
				} else {
					int diff = this.clipSizeM - this.ammoM;
					this.ammoM = this.clipSizeM;
					this.maxAmmoM -= diff;
				}
			}
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

	public int getArmorValue() {
		// TODO Auto-generated method stub
		return this.armorVal;
	}

	public void setArmorValue(int value) {
		// TODO Auto-generated method stub
		this.armorVal = value;
	}

	public int getScore(int value) {
		// TODO Auto-generated method stub
		return this.score;
	}

	public void setScore(int value) {
		// TODO Auto-generated method stub
		this.score = value;
	}

	public void addAmmo(int value, int id) {
		if (id == 0) {
			this.maxAmmoP += value;
		} else if (id == 2) {
			this.maxAmmoS += value;
		} else if (id == 5) {
			this.maxAmmoM += value;
		}
	}
	
	public void unlock(int id){
		if(id == 0){
			this.pistol = true;
		} else if (id == 2) {
			this.shotgun = true;			
		} else if (id == 5) {
			this.machineGun = true;
		}
	}
	
	public boolean isUnlocked(int id){
		if(id == 0){
			return pistol;
		} else if (id == 2) {
			return shotgun;			
		} else if (id == 5) {
			return machineGun;
		}
		return false;
	}
	
	public int currentGun(){
		return weaponID;
	}

	public void switchGun(int i) {
		// TODO Auto-generated method stub
		weaponID = i;
		
	}
	
	public float damage() {
		float dmgDealt = 0.0f;
		if (currentGun() == 0) {
			dmgDealt = 90.0f;
		} else if (currentGun() == 2) {
			dmgDealt = 125.0f;
		} else if (currentGun() == 5) {
			dmgDealt = 50.0f;
		}
		return dmgDealt;
	}

}
