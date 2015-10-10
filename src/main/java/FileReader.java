package main.java;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
	
	public static List<Point> getPointsFromFile() {
		try (Stream<String> stream = Files.lines(Paths.get("src/dots.txt"), Charset.defaultCharset())) {
			return stream
				.map(l -> new Point(Double.valueOf(l.split(" ")[0]), Double.valueOf(l.split(" ")[1])))
				.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<Point>();
	}
	
	public static class Point {
		public double x;
		public double y;
		
		Point (double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
}
