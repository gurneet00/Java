import java.util.Scanner;

class Patient {
    private String name;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;

    public Patient(String name, String dateOfBirth, String address, String phoneNumber) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void displayInformation() {
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);

    }
}

//LOGIC BUILDING CODE - DESIGN BASED: FOR ADULT
class Adult extends Patient {
    private String insuranceProvider;
    private String medicalHistory;

    public Adult(String name, String dateOfBirth, String address, String phoneNumber, String insuranceProvider, String medicalHistory) {

        super(name, dateOfBirth, address, phoneNumber);

        this.insuranceProvider = insuranceProvider;
        this.medicalHistory = medicalHistory;
    }


    @Override
    public void displayInformation() {
        super.displayInformation();
        System.out.println("Insurance Provider: " + insuranceProvider);
        System.out.println("Medical History: " + medicalHistory);
    }
}

//LOGIC BUILDING CODE - DESIGN BASED: FOR CHILD
class Child extends Patient {
    private String guardianName;
    private String pediatrician;
    private String immunizationRecords;

    public Child(String name, String dateOfBirth, String address, String phoneNumber, String guardianName, String pediatrician, String immunizationRecords) {

        super(name, dateOfBirth, address, phoneNumber);

        this.guardianName = guardianName;
        this.pediatrician = pediatrician;
        this.immunizationRecords = immunizationRecords;
    }


    @Override
    public void displayInformation() {
        super.displayInformation();
        System.out.println("Guardian Name: " + guardianName);
        System.out.println("Pediatrician: " + pediatrician);
        System.out.println("Immunization Records: " + immunizationRecords);
    }
}

//LOGIC BUILDING CODE - DESIGN BASED: FOR SENIOR
class Senior extends Patient {
    private String medicareNumber;
    private String chronicConditions;

    public Senior(String name, String dateOfBirth, String address, String phoneNumber, String medicareNumber, String chronicConditions) {

        super(name, dateOfBirth, address, phoneNumber);

        this.medicareNumber = medicareNumber;
        this.chronicConditions = chronicConditions;
    }

    @Override
    public void displayInformation() {
        super.displayInformation();
        System.out.println("Medicare Number: " + medicareNumber);
        System.out.println("Chronic Conditions: " + chronicConditions);
    }
}


//DRIVER CODE
public class T1 {

    private static void gatherAndDisplayAdultPatientInfo(Scanner scanner) {
        System.out.println("Enter Adult Patient Information:");
        System.out.print("Name: ");
        String adultName = scanner.nextLine();
        System.out.print("Date of Birth: ");
        String adultDOB = scanner.nextLine();
        System.out.print("Address: ");
        String adultAddress = scanner.nextLine();
        System.out.print("Phone Number: ");
        String adultPhoneNumber = scanner.nextLine();
        System.out.print("Insurance Provider: ");
        String insuranceProvider = scanner.nextLine();
        System.out.print("Medical History: ");
        String medicalHistory = scanner.nextLine();

        Adult adultPatient = new Adult(adultName, adultDOB, adultAddress, adultPhoneNumber, insuranceProvider, medicalHistory);

        System.out.println("\nAdult Patient Information:");
        adultPatient.displayInformation();


    }

    private static void gatherAndDisplayChildPatientInfo(Scanner scanner) {
        System.out.println("\nEnter Child Patient Information:");
        System.out.print("Name: ");
        String childName = scanner.nextLine();
        System.out.print("Date of Birth: ");
        String childDOB = scanner.nextLine();
        System.out.print("Address: ");
        String childAddress = scanner.nextLine();
        System.out.print("Phone Number: ");
        String childPhoneNumber = scanner.nextLine();
        System.out.print("Guardian Name: ");
        String guardianName = scanner.nextLine();
        System.out.print("Pediatrician: ");
        String pediatrician = scanner.nextLine();
        System.out.print("Immunization Records: ");
        String immunizationRecords = scanner.nextLine();

        Child childPatient = new Child(childName, childDOB, childAddress, childPhoneNumber, guardianName, pediatrician, immunizationRecords);

        System.out.println("\nChild Patient Information:");
        childPatient.displayInformation();


    }

    private static void gatherAndDisplaySeniorPatientInfo(Scanner scanner) {
        System.out.println("\nEnter Senior Patient Information:");
        System.out.print("Name: ");
        String seniorName = scanner.nextLine();
        System.out.print("Date of Birth: ");
        String seniorDOB = scanner.nextLine();
        System.out.print("Address: ");
        String seniorAddress = scanner.nextLine();
        System.out.print("Phone Number: ");
        String seniorPhoneNumber = scanner.nextLine();
        System.out.print("Medicare Number: ");
        String medicareNumber = scanner.nextLine();
        System.out.print("Chronic Conditions: ");
        String chronicConditions = scanner.nextLine();

        Senior seniorPatient = new Senior(seniorName, seniorDOB, seniorAddress, seniorPhoneNumber, medicareNumber, chronicConditions);

        System.out.println("\nSenior Patient Information:");
        seniorPatient.displayInformation();

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Patient Type:");
        System.out.println("1. Adult");
        System.out.println("2. Child");
        System.out.println("3. Senior");
        System.out.print("> ");

        int patientTypeChoice = scanner.nextInt();
        scanner.nextLine();

        switch (patientTypeChoice) {
            case 1:
                gatherAndDisplayAdultPatientInfo(scanner);
                break;
            case 2:
                gatherAndDisplayChildPatientInfo(scanner);
                break;
            case 3:
                gatherAndDisplaySeniorPatientInfo(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please select a valid patient type (1, 2, or 3).");
        }

        scanner.close();

    }

}
