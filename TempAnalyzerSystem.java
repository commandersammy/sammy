// Name: Samrat KC
// Date: 10/12/2024
// Description: This program collects and analyzes temperature data for a number of days, calculates daily and overall averages, and determines the highest and lowest temperature days.
import java.util.Scanner;

public class TempAnalyzerSystem {


    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        // First, we need to ask the user how many days they want to analyze. The number has to be between 2 and 366.
        int daysToAnalyze = getValidDays(inputScanner);

        // We’re going to store the temperature data (lows and highs) in these arrays.
        double[] lowTempsArray = new double[daysToAnalyze];
        double[] highTempsArray = new double[daysToAnalyze];

        // Now, let's loop through each day and collect the temperature values from the user.
        inputTemperatures(inputScanner, lowTempsArray, highTempsArray);

        // Once we’ve got all the data, it’s time to crunch some numbers and display the results.
        displayTemperatureAnalysis(lowTempsArray, highTempsArray);

        // Done! Let’s close the scanner to free up resources.
        inputScanner.close();
    }

    // This method asks the user for the number of days they want to analyze and makes sure it’s a valid number.
    private static int getValidDays(Scanner scanner) {
        int days = 0;
        do {
            System.out.print("Enter the number of days to analyze (between 2 and 366): ");
            if (scanner.hasNextInt()) {
                days = scanner.nextInt();
            } else {
                // Oops! That wasn’t a number. Let’s ask again.
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();// Clear the invalid input
            }
        } while (days < 2 || days > 366);// Keep asking until the user gives a valid number.

        return days;// Finally, we have the right number of days!
    }

    // Here, we loop through each day and ask for the low and high temperatures.
    // We also make sure the temperatures are valid and that the high temperature isn’t lower than the low temperature.
    private static void inputTemperatures(Scanner scanner, double[] lows, double[] highs) {
        for (int i = 0; i < lows.length; i++) {
            System.out.println("\nDay " + (i + 1) + ":");

            // First, get the low temperature. It has to be between -100 and 100.
            lows[i] = getValidTemperature(scanner, "low", -100, 100);

            // Now, get the high temperature. Same range, but it also has to be higher than the low temperature.
            do {
                highs[i] = getValidTemperature(scanner, "high", -100, 100);
                if (highs[i] < lows[i]) {
                    // Uh-oh, the high can’t be lower than the low! Let’s re-prompt.
                    System.out.println("High temperature must be greater than or equal to the low temperature.");
                }
            } while (highs[i] < lows[i]); // Keep asking until we get a valid high temperature.
        }
    }

    // This method asks for a valid temperature (either low or high).
    // The temperature has to be a number within a specific range (between min and max).
    private static double getValidTemperature(Scanner scanner, String tempType, double min, double max) {
        double temp;
        do {
            System.out.printf("Enter the %s temperature (%.1f to %.1f): ", tempType, min, max);
            if (scanner.hasNextDouble()) {
                temp = scanner.nextDouble();
                if (temp < min || temp > max) {
                    System.out.printf("Invalid input. The %s temperature must be between %.1f and %.1f.%n", tempType, min, max);
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clearing invalid input
                temp = min - 1; // Forcing another loop iteration
            }
        } while (temp < min || temp > max);

        return temp;
    }

    // Using function to calculate and display temperature analysis.
    private static void displayTemperatureAnalysis(double[] lows, double[] highs) {
        double overallSum = 0;
        double maxTemp = -100, minTemp = 100;
        int dayOfMaxTemp = 0, dayOfMinTemp = 0;

        // Displaying daily averages and calculate statistics.
        System.out.println("\nDaily Average Temperatures:");
        for (int i = 0; i < lows.length; i++) {
            double dailyAvg = (lows[i] + highs[i]) / 2;
            System.out.printf("Day %d: %.2f%n", (i + 1), dailyAvg);
            overallSum += dailyAvg;

            // Tracking the highest temperature day.
            if (highs[i] > maxTemp) {
                maxTemp = highs[i];
                dayOfMaxTemp = i + 1;
            }

            // Tracking the lowest temperature day.
            if (lows[i] < minTemp) {
                minTemp = lows[i];
                dayOfMinTemp = i + 1;
            }
        }

        // Calculating and display overall average.
        double overallAvg = overallSum / lows.length;
        System.out.printf("\nOverall Average Temperature: %.2f%n", overallAvg);

        // Displaying the days with highest and lowest temperatures.
        System.out.printf("Day with the highest temperature: Day %d (%.2f)%n", dayOfMaxTemp, maxTemp);
        System.out.printf("Day with the lowest temperature: Day %d (%.2f)%n", dayOfMinTemp, minTemp);
    }
}
