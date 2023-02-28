/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author YOUR NAME
 */

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Track {
  
  private List<Point> listOfPoints;
  
  public Track(String points, List<Point> listOfPoints){
    this.listOfPoints = listOfPoints;

  }

  public static void readFile(String filename) throws IOException{
    try {
      File csvFile = new File(filename);
      Scanner lines = new Scanner(csvFile);
      
      while (lines.hasNextLine()) {
        String line = lines.nextLine();
      }
      lines.close();
    } catch (FileNotFoundException e) {
      throw new IOException("Error: could not access file", e)
    }
  }

  public static void add(Point point) {
    List<Point> pointsList = new ArrayList<Point>();
    pointsList.add(point);
  }

  public Point get(int index) {
    return null;
  }

  public int size() {
    return 0;
  }

  public Point lowestPoint() {
    return null;
  }

  public Point highestPoint() {
    return null;
  }

  public double totalDistance() {
    return 0;
  }

  public double averageSpeed() {
    return 0;
  }
}
