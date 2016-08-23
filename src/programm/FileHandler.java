package programm;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


public class FileHandler {

	private Controller controller = Controller.getInstance();
	private final String path = "bla.txt";
	
	public void writeFile(Collection<Plant> plants, String filePath) {
		
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
			
			for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
				Plant plant = (Plant) iterator.next();
				bw.write(plant.toString());
				bw.newLine();
			}
			controller.getMw().getStatusBar().setMessageAndClear("Speichern erfolgreich");
		} catch (FileNotFoundException e) {
			controller.getMw().getStatusBar().setMessageAndClear("Datei nicht gefunden");
		} catch (IOException e) {
			controller.getMw().getStatusBar().setMessageAndClear("Fehler beim Schreiben");
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Collection<Plant> readFile(String filePath) {
		
		BufferedReader br = null;
		String line = "";
		Collection<Plant> plants = new ArrayList<Plant>();
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			line = br.readLine();
			while(line != null) {
				plants.add(parsePlant(line));
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return plants;
	}
	
	private Plant parsePlant(String line) {
		
		String[] splittedLine = line.split(";");
		
		try {
			return new Plant(splittedLine[0],
					splittedLine[1],
					splittedLine[2],
					new Color(Integer.parseInt(splittedLine[3]),Integer.parseInt(splittedLine[4]),Integer.parseInt(splittedLine[5])),
					splittedLine[6],
					splittedLine[7],
					splittedLine[8],
					controller.df.parse(splittedLine[9]),
					controller.df.parse(splittedLine[10]),
					splittedLine[11],
					new Rectangle(Integer.parseInt(splittedLine[12]),Integer.parseInt(splittedLine[13]),Integer.parseInt(splittedLine[14]),Integer.parseInt(splittedLine[15])),
					Boolean.parseBoolean(splittedLine[16]),
					Integer.parseInt(splittedLine[17]),
					Integer.parseInt(splittedLine[18]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Plant();
	}
	
	/**
	 * main method for test cases
	 * @param args
	 */
	public static void main (String [] args) {
		System.out.println(new Date().getTime());
	}
}