import java.util.Scanner;

public class test {
    // Assuming the input line contains four comma-separated elements
    public static void main(String[] args) {  
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(",");
        String element1 = scanner.next().trim();
        int element2 = Integer.parseInt(scanner.next().trim());
        String element3 = scanner.next().trim();
        double element4 = Double.parseDouble(scanner.next().trim());


        // Use the parsed elements as needed
        System.out.println("Element 1: " + element1);
        System.out.println("Element 2: " + element2);
        System.out.println("Element 3: " + element3);
        System.out.println("Element 4: " + element4);
        scanner.close();
    }
}