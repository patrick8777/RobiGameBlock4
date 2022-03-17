package model;

public class Highscore {

	private String name;
	private int score;

	public Highscore(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {//Die Funktion gibt ein Zeichenfolgenobjekt zur�ck, das den Namen des angegebenen Dateiobjekts enth�lt.
		return name;

	}

	public int getScore() {
		return score;
	}
}