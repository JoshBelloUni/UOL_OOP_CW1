/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author YOUR NAME HERE
 */
public class TrackInfo {
  public static void main(String[] args) {
    
    // error checking command line argument
    if (args.length < 1) {
      System.out.println("Invalid Arguments");
      System.exit(0);
     }


    Track t1 = new Track(args[0]);


    // print size
    int size = t1.size();
    System.out.println(String.format("%d points on the track", size));

    // print lowest point
    System.out.println(String.format("Lowest Point is %s", t1.lowestPoint().toString()));

    // print highest point
    System.out.println(String.format("Highest Point is %s", t1.highestPoint().toString()));
    
    // print distance
    // distance / 1000 to get in km
    double distance = t1.totalDistance();
    System.out.println(String.format("Total Distance = %.3f km", distance/1000));
    
    // print speed
    double avgSpeed = t1.averageSpeed();
    System.out.println(String.format("Average Speed = %.3f m/s", avgSpeed));
  }
}