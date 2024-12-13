import java.io.*;
import java.util.Date;

public class FileHandler {

    // Method to write patient details to a file
    public static boolean writeToFile(File file, Patient patient) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write the header of the document
            writer.write("**Patient Registration Document**\n");
            // Write patient details: Patient Name
            writer.write("Patient Name: " + patient.getPatientName() + "\n");
            // Write patient details: Owner Name
            writer.write("Owner Name: " + patient.getOwnerName() + "\n");
            // Write patient details: Email Address
            writer.write("Email Address: " + patient.getEmail() + "\n");
            // Write patient details: Selected Veterinarian
            writer.write("Veterinarian: " + patient.getVet() + "\n");
            // Write the current date of registration
            writer.write("Date: " + patient.getRegistrationDate() + "\n");
            return true; // Return true if writing to file was successful
        } catch (IOException e) {
            // Catch any IO exception during file writing
            return false; // Return false if an error occurred while writing
        }
    }
}
