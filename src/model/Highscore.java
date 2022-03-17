package model;

public class Highscore {

	private String name;
	private int score;

	public Highscore(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {//Die Funktion gibt ein Zeichenfolgenobjekt zurück, das den Namen des angegebenen Dateiobjekts enthält.
		return name;

	}

	public int getScore() {
		return score;
	}
}