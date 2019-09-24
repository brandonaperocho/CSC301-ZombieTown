package main;

public class Score {
	private String user;
	private int score;
	
	public Score(String user, int score) {
		this.user = user;
		this.score = score;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public String getUser() {
		return this.user;
	}
}
