import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SportsConcussionAssessmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Athlete> athletes = new ArrayList<>();
        MedicalPractitioner practitioner = new MedicalPractitioner();

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Register Athlete");
            System.out.println("2. Athlete Menu");
            System.out.println("3. Medical Practitioner Menu");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Please enter the athlete's name: ");
                        String name = scanner.nextLine();
                        athletes.add(new Athlete(name));
                        System.out.println("Athlete " + name + " has been registered.");
                        //System.out.println("Please choose option \"2. Athlete Menu\" for the evaluation form.");
                        break;

                    case 2:
                        System.out.print("Please enter the athlete's name: ");
                        name = scanner.nextLine();
                        Athlete athlete = findAthlete(athletes, name);
                        if (athlete != null) {
                            athlete.athleteMenu(scanner, practitioner);
                        } else {
                            System.out.println("Athlete cannot found.");
                        }
                        break;

                    case 3:
                        System.out.print("Please enter the athlete's name for evaluation: ");
                        name = scanner.nextLine();
                        athlete = findAthlete(athletes, name);
                        if (athlete != null) {
                            practitioner.evaluateAthleteSymptoms(athlete, scanner);
                        } else {
                            System.out.println("Athlete not found.");
                        }
                        break;

                    case 4:
                        System.out.println("Exiting the system.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private static Athlete findAthlete(ArrayList<Athlete> athletes, String name) {
        for (Athlete athlete : athletes) {
            if (athlete.getName().equalsIgnoreCase(name)) {
                return athlete;
            }
        }
        return null; // Nothing found
    }
}
