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
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Track {
  
  private List<Point> listOfPoints;
  
  public Track(){
    this.listOfPoints = new ArrayList<Point>();
  }

  public void readFile(String filename) throws IOException{
    try {
      File csvFile = new File(filename);
      Scanner lines = new Scanner(csvFile);
      
      while (lines.hasNextLine()) {
        String line = lines.nextLine();

        if (!line.isEmpty()) {
          Scanner lineScanner = new Scanner(line);
          lineScanner.useDelimiter(",");

          ZonedDateTime readTime = ZonedDateTime.parse(lineScanner.next());
          double readLon = Double.parseDouble(lineScanner.next());
          double readLat = Double.parseDouble(lineScanner.next());
          double readEla = Double.parseDouble(lineScanner.next());

          Point readPoint = new Point(readTime, readLon, readLat, readEla);
          listOfPoints.add(readPoint);
          lineScanner.close();
        }

      }
      lines.close();

    } catch (FileNotFoundException e) {
      throw new IOException("Error: could not access file", e);
    }
  }

  public void add(Point point) {
    listOfPoints.add(point);
  }

  public Point get(int index) {
    try {
        return listOfPoints.get(index);
    } catch  {
        System.out.println("Error: " + e.getMessage());
        return null;
    }
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
