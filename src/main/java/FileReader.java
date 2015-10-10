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
				.distinct()
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
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Point) {
				Point temp = (Point) obj;
		        if (new Double(this.x).equals(temp.x) && new Double(this.y).equals(this.y)) {
		        	return true;
		        }
		    }
		    return false;
		}
		
		@Override
		public int hashCode() {
		    return (new Double(this.x).hashCode() + new Double(this.y).hashCode());        
		}
	}
}
