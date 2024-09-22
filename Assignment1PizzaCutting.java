//Name: Samrat KC
//Student ID: 100937353
//Date: 9/21/2024
//Description: This Java program calculates the number of pizza slices and slice area
//based on the pizza’s diameter, ensuring valid input and handling errors.
import java.util.Scanner;

public class Assignment1PizzaCutting {

    public static void main(String[] args) {
        // Launch Scanner for input
        Scanner size = new Scanner(System.in);

        // Ask the user for the pizza diameter
        System.out.print("Please enter the diameter of your pizza: ");

        // Ensure the input is a valid number
        if (size.hasNextDouble()) {
            // Input the diameter as a double
            double diameter = size.nextDouble();

            // Check the diameter range
            if (diameter >= 6 && diameter <= 24) {
                int slices; // Variable to store the number of slices

                // Determine the number of slices based on the diameter
                if (diameter < 8) {
                    slices = 4;
                } else if (diameter < 12) {
                    slices = 6;
                } else if (diameter < 14) {
                    slices = 8;
                } else if (diameter < 16) {
                    slices = 10;
                } else if (diameter < 20) {
                    slices = 12;
                } else {
                    slices = 16;
                }

                // Calculate the area of the pizza
                double radius = diameter / 2;
                double pizzaArea = Math.PI * radius * radius;  // Area formula: πr^2

                // Calculate the area per slice
                double sliceArea = pizzaArea / slices;

                // Output the result
                System.out.println("\nA " + diameter + "\" pizza will yield " + slices + " slices.");
                System.out.printf("Each slice will have an area of %.2f square inches.\n", sliceArea);
            } else {
                // Output error message if the diameter is out of range
                System.out.println("Error: Pizza must have a diameter in the range of 6\" to 24\" inclusive! Please try again.");
            }
        } else {
            // Handle invalid input (non-numeric values)
            System.out.println("Error: Invalid input. Please enter a numeric value for the diameter.");
        }

        // Close the size
        size.close();
    }
}
