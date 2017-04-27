package tsp;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class RoadmapReader {

	protected ArrayList<Float> floats = new ArrayList<>();

	public RoadmapReader(String pathToFile) throws Exception {

		try (Scanner scanner = new Scanner(new File(pathToFile))) {
			scanner.useLocale(Locale.US);
			// scanner.useDelimiter(System.lineSeparator());
			while (scanner.hasNextFloat()) {
				floats.add(scanner.nextFloat());
			}
		} catch (Exception ex) {
			throw new Exception(ex); // be brutal
		}
	}

	public Roadmap getRoadmap() {
		int size = (int) (floats.get(0) + 0.1f);
		int source = (int) (floats.get(1) + 0.1f);
		
		float[] xs = new float[size];
		float[] ys = new float[size];
		
		int cursor = 0;
		for (int i = 2; i < floats.size(); i += 2) {
			xs[cursor] = floats.get(i);
			ys[cursor] = floats.get(i + 1);
			cursor++;
		}
		
		return new Roadmap(xs, ys, source);
	}

}
