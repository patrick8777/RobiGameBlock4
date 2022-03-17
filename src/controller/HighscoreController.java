package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Highscore;

public class HighscoreController {

	static String path = "highscore.csv";
	ArrayList<Highscore> highscores = new ArrayList<Highscore>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HighscoreController controller = new HighscoreController();

		controller.readHighscores();
		for (Highscore hs: controller.highscores) {
		System.out.println(hs);
		}
		controller.addHighscore(new Highscore("Patrick", 150));
		controller.addHighscore(new Highscore("Aleks", 190));
		controller.addHighscore(new Highscore("Danilo", 120));
		controller.addHighscore(new Highscore("Sven", 100));
		controller.addHighscore(new Highscore("Ramon", 120));

		controller.writeHighscores();



		}
	public void addHighscore(Highscore hs) {
		highscores.add(hs);

	}

	public void writeHighscores() {
		PrintWriter prnt = null;
		try {
			prnt = new PrintWriter(new File(path));

			highscores.sort((a, b) -> {
				return b.getScore() - a.getScore();
			});

			// für jedes Highscore-Objekt die zu speichernde Zeile basteln
			for (Highscore hs : highscores) {
				String line = hs.getName() + ";" + hs.getScore() + System.lineSeparator();
				prnt.print(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Datei nicht vorhanden");
			e.printStackTrace();
		} finally {
			prnt.flush(); // Puffer nur am Schluss schreiben
			prnt.close();
		}
	}

	public String readFile() {
		String result = "";
		File file = new File(path);
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buff = new BufferedReader(reader);
			while (buff.ready()) {
				result += buff.readLine() + " - ";
			}
			buff.close();
		} catch (Exception ex) {
			System.out.println("OMG, alles kaabbuutt");
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * liest und parst highscores-datei und erstellt Highscore-Array
	 */
	public void readHighscores() {
		File f = new File(path);

		highscores = new ArrayList<>();
		try {
			f.createNewFile();
			FileReader reader = new FileReader(f);
			BufferedReader buff = new BufferedReader(reader);

			while (buff.ready()) {
				String line = buff.readLine(); // Zeile einlesen
				String[] parts = line.split(";"); // Zeile zerlegen
				String name = parts[0];
				int score = Integer.parseInt(parts[1]);
				Highscore hs = new Highscore(name, score);
				highscores.add(hs);
			}
			buff.close();

		} catch (Exception ex) {
			System.out.println("Fehler beim Einlesen: ");
			ex.printStackTrace();
		}
	}

}