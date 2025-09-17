import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PatientDAO dao = new PatientDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n**** Patient Management System ****");
            System.out.println("1. Add Patient");
            System.out.println("2. Update Patient");
            System.out.println("3. Delete Patient");
            System.out.println("4. Show All Patients");
            System.out.println("5. Search Patient by Name");
            System.out.println("6. Sort Patients by Age");
            System.out.println("7. Show Statistics");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Patient ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Gender (Male/Female/Other): ");
                    String gender = sc.nextLine();
                    System.out.println("Enter Phone Number (10 digits): ");
                    String phone = sc.nextLine();
                    Patient p = new Patient(id, name, age, gender, phone);
                    if (dao.addPatient(p)) System.out.println("Patient added!");
                    else System.out.println("Failed to add patient.");
                    break;

                case 2:
                    System.out.println("Enter Patient ID to Update: ");
                    id = sc.nextLine();
                    System.out.println("Enter New Name: ");
                    name = sc.nextLine();
                    System.out.println("Enter New Age: ");
                    age = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter New Gender: ");
                    gender = sc.nextLine();
                    System.out.println("Enter New Phone: ");
                    phone = sc.nextLine();
                    p = new Patient(id, name, age, gender, phone);
                    if (dao.updatePatient(p)) System.out.println("Patient updated!");
                    else System.out.println("Failed to update patient.");
                    break;

                case 3:
                    System.out.println("Enter Patient ID to Delete: ");
                    id = sc.nextLine();
                    if (dao.deletePatient(id)) System.out.println("Patient deleted!");
                    else System.out.println("Failed to delete patient.");
                    break;

                case 4:
                    List<Patient> patients = dao.getAllPatients();
                    System.out.printf("%-10s %-15s %-5s %-10s %-10s\n", "ID", "Name", "Age", "Gender", "Phone");
                    for (Patient pat : patients) {
                        System.out.println(pat);
                    }
                    break;

                case 5:
                    System.out.println("Enter Name to Search: ");
                    name = sc.nextLine();
                    patients = dao.searchByName(name);
                    for (Patient pat : patients) {
                        System.out.println(pat);
                    }
                    break;

                case 6:
                    patients = dao.sortByAge();
                    for (Patient pat : patients) {
                        System.out.println(pat);
                    }
                    break;

                case 7:
                    dao.showStatistics();
                    break;

                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}


