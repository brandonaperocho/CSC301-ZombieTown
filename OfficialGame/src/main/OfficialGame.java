package main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;



public class OfficialGame extends StateBasedGame {

	public static TrueTypeFont pixel;
	public static final int LoginState = 0;
	public static final int LoginAttemptState = 1;
	public static final int RegisterState = 2;
	public static final int MenuState = 3;
	public static final int StoreState = 4;
	public static final int LeaderboardState = 5;
	public static final int StatsState = 6;
	public static final int GameState = 7;
	public static final String GameName = "Zombie Town";
	
	
	public OfficialGame() {
		super(GameName);
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new LoginState(LoginState));
		this.addState(new LoginAttemptState(LoginAttemptState));
		this.addState(new RegisterState(RegisterState));
		this.addState(new MenuState(MenuState));
		this.addState(new StoreState(StoreState));
		this.addState(new LeaderboardState(LeaderboardState));
		this.addState(new StatsState(StatsState));
		this.addState(new GameState(GameState));
		this.enterState(LoginState);
	}
	
	
	public static void main(String[] args) {
		try {
			AppGameContainer Window = new AppGameContainer(new ScalableGame(new OfficialGame(), 1080, 720));
			Window.setDisplayMode(1080, 720, false);
			Window.setAlwaysRender(true);
			Window.setVSync(true);
			Window.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
