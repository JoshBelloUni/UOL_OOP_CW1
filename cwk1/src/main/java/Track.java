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

  public void readFile(String filename) throws IOException {
    try {
        File csvFile = new File(filename);
        Scanner lines = new Scanner(csvFile);
        int lineNumber = 0;

        while (lines.hasNextLine()) {
          //creates a string of each line  
          String line = lines.nextLine();
            
            //checking number of values on the line
            String[] elements = line.split(",");
            if (elements.length != 4) {
                System.out.println("Error: Line " + lineNumber + " has less than 4 elements");
                continue;
            }
            //increments the line number
            lineNumber++;

            // checks the line number
            // if lineNumber is greater than 1 means counting from line 1 (disregards headers)
            // also checks if the line is not empty
            if (lineNumber > 1 && !line.isEmpty()) { 
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                // parse at each comma, then create variable for each element
                ZonedDateTime readTime = ZonedDateTime.parse(lineScanner.next());
                double readLon = Double.parseDouble(lineScanner.next());
                double readLat = Double.parseDouble(lineScanner.next());
                double readEla = Double.parseDouble(lineScanner.next());

                // create the point variable for this line then add to the List
                Point readPoint = new Point(readTime, readLon, readLat, readEla);
                listOfPoints.add(readPoint);
                lineScanner.close();
            }
        }
        lines.close();
    //exception for invalid file
    } catch (IOException e) {
        System.out.println("Invalid File");
        throw e;
    }
}
  

  public void add(Point point) {
    listOfPoints.add(point);
  }

  public Point get(int index) {
    try {
        return listOfPoints.get(index);
    } catch (IndexOutOfBoundsException e) {
        throw new GPSException("Error: " + e.getMessage());
    }
}

  public int size() {
    int sizeOfTrack = listOfPoints.size();
    return sizeOfTrack;
    
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
