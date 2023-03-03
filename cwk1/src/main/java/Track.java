/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author YOUR NAME
 */

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;

public class Track {
  
  private List<Point> listOfPoints;
  
  public Track(){
    this.listOfPoints = new ArrayList<Point>();
  }

  public Track(String file) {
    this.listOfPoints = new ArrayList<Point>();
    try {
      readFile(file);
    } catch (IOException e) {
    }
  } 

  public void readFile(String filename) throws IOException {
    try (Scanner lines = new Scanner(new File(filename))) {
        int lineNumber = 0;

        // clears data
        listOfPoints.clear();

        while (lines.hasNextLine()) {

          // creates a string of each line  
          String line = lines.nextLine();
            
            // checking number of values on the line
            String[] elements = line.split(",");
            if (elements.length != 4) {
                throw new GPSException("Error: Line " + lineNumber + " has less than 4 elements");
            }
            // increments the line number
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
    // checking if track is empty
    if (listOfPoints.size() == 0) {
      throw new GPSException("Empty Track");
    }
    // creating Array for elvations
    double[] arrayOfElv = new double[listOfPoints.size()];
    // initialising the array
    for (int i = 0; i < listOfPoints.size(); i++) {
      arrayOfElv[i] = listOfPoints.get(i).getElevation();
    }
    // checking the the lowest point
    int minIndex = 0;
    for (int i = 1; i < arrayOfElv.length; i++) {
        if (arrayOfElv[i] < arrayOfElv[minIndex]) {
            minIndex = i;
        }
    }
    // return the point with the lowest elevation
    return listOfPoints.get(minIndex);
  }

  public Point highestPoint() {
    if (listOfPoints.size() == 0) {
      throw new GPSException("Empty Track");
    }
    // creating Array for elvations
    double[] arrayOfElv = new double[listOfPoints.size()];
    // initialising the array
    for (int i = 0; i < listOfPoints.size(); i++) {
      arrayOfElv[i] = listOfPoints.get(i).getElevation();
    }
    // checking the the highest point
    int maxIndex = 0;
    for (int i = 1; i < arrayOfElv.length; i++) {
        if (arrayOfElv[i] > arrayOfElv[maxIndex]) {
            maxIndex = i;
        }
    }
    // return the point with the lowest elevation
    return listOfPoints.get(maxIndex);
  }

  public double totalDistance() {
    // check if the size of track is greater than 2
    if (listOfPoints.size() < 2) {
      throw new GPSException("Number of points must be greater than 2");
    }
    // compute distances from point i to i+1
    // 'i < listOfPoints.size()-1' because we dont want to check the very last array element
    double distance = 0.0;
    for (int i = 0; i < listOfPoints.size()-1; i++) {
      distance = distance + Point.greatCircleDistance(listOfPoints.get(i), listOfPoints.get(i+1));
    }
    return distance;
  }

  public double averageSpeed() {
    // check if the size of track is greater than 2
    if (listOfPoints.size() < 2) {
      throw new GPSException("Number of points must be greater than 2");
    }
    // creating Array for times
    ZonedDateTime[] arrayOfTimes = new ZonedDateTime[listOfPoints.size()];

    // initialising the array
    for (int i = 0; i < listOfPoints.size(); i++) {
      arrayOfTimes[i] = listOfPoints.get(i).getTime();
    }

    // getting earliest and latest times
    int earlyIndex = 0;
    int lateIndex = 0;
    
    // earliest
    for (int i = 1; i < arrayOfTimes.length; i++) {
      if (arrayOfTimes[i].compareTo(arrayOfTimes[earlyIndex]) < 0) {
          earlyIndex = i;
      }
    }
    // latest
    for (int i = 1; i < arrayOfTimes.length; i++) {
      if (arrayOfTimes[i].compareTo(arrayOfTimes[earlyIndex]) > 0) {
          lateIndex = i;
      }
    }
    // calculating seconds between earliest and latest time
    double timeTaken = ChronoUnit.SECONDS.between(arrayOfTimes[earlyIndex], arrayOfTimes[lateIndex]);
    return this.totalDistance() / timeTaken; 
  }
}
