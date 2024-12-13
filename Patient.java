import java.util.Date;

public class Patient {
    // Private fields for storing patient information
    private String patientName;
    private String ownerName;
    private String email;
    private String vet;
    private Date registrationDate;

    // Constructor to initialize the Patient object with details and capture the current date
    public Patient(String patientName, String ownerName, String email, String vet) {
        this.patientName = patientName; // Set the patient name
        this.ownerName = ownerName; // Set the owner's name
        this.email = email; // Set the owner's email address
        this.vet = vet; // Set the selected veterinarian
        this.registrationDate = new Date();  // Capture the current date and time as the registration date
    }

    // Getter method for patient name
    public String getPatientName() {
        return patientName; // Return the patient's name
    }

    // Getter method for owner's name
    public String getOwnerName() {
        return ownerName; // Return the owner's name
    }

    // Getter method for owner's email address
    public String getEmail() {
        return email; // Return the owner's email address
    }

    // Getter method for selected veterinarian
    public String getVet() {
        return vet; // Return the selected veterinarian's name
    }

    // Getter method for registration date
    public Date getRegistrationDate() {
        return registrationDate; // Return the registration date and time
    }
}
