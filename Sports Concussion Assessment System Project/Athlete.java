import java.util.LinkedList;
import java.util.Scanner;

public class Athlete {
    private String name;
    private LinkedList<int[]> symptomEntries; // Stores up the 5 most recent symptom entries
    private LinkedList<String> feedbackHistory; // Stores the feedback for each entry

    public Athlete(String name) {
        this.name = name;
        this.symptomEntries = new LinkedList<>(); // Initialize as LinkedList to handle the 5 entries
        this.feedbackHistory = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void addSymptomEntry(Scanner scanner) {
        System.out.printf("Please complete the symptom evaluation form for %s:\n", name);

        int[] newEntry = new int[22];
        for (int i = 0; i < newEntry.length; i++) {
            int score = -1;
            while (score < 0 || score > 6) {
                System.out.printf("%s: ", getSymptomName(i));
                try {
                    score = scanner.nextInt();
                    if (score < 0 || score > 6) {
                        System.out.println("Please enter a valid score between 0 and 6.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please try again, enter a number between 0 and 6.");
                    scanner.next();
                }
            }
            newEntry[i] = score;
        }

        if (symptomEntries.size() >= 5) {
            symptomEntries.removeFirst(); // Remove the oldest entry if @ max capacity
            feedbackHistory.removeFirst(); // Remove the corresponding feedback
        }
        symptomEntries.add(newEntry);
        feedbackHistory.add(""); // Add an empty feedback entry for this entry part

        System.out.println("Form completed for " + name + "!");
    }

    public void displaySymptomHistory() {
        if (symptomEntries.isEmpty()) {
            System.out.println("No entries were found for " + name + ".");
            return;
        }

        System.out.println("Symptom History for " + name + ":");
        int entryNumber = 1;
        for (int[] entry : symptomEntries) {
            System.out.printf("Entry %d: %s\n", entryNumber, getSummary(entry));
            entryNumber++;
        }
    }

    public void displayFeedbackHistory() {
        if (feedbackHistory.isEmpty()) {
            System.out.println("No feedback entries were found for " + name + ".");
            return;
        }

        System.out.println("Feedback History for " + name + ":");
        int entryNumber = 1;
        for (String feedback : feedbackHistory) {
            System.out.printf("Entry %d Feedback: %s\n", entryNumber, feedback);
            entryNumber++;
        }
    }

    public void setFeedback(int index, String feedback) {
        if (index < 1 || index > feedbackHistory.size()) {
            System.out.println("Invalid entry number for feedback. Please try again.");
            return;
        }
        feedbackHistory.set(index - 1, feedback);
    }

    public int[] getEntryByIndex(int index) {
        if (index < 1 || index > symptomEntries.size()) {
            System.out.println("Invalid number. Please try again.");
            return null;
        }
        return symptomEntries.get(index - 1);
    }

    public int calculateTotalSymptoms(int[] entry) {
        int totalSymptoms = 0;
        for (int symptom : entry) {
            if (symptom > 0) {
                totalSymptoms++;
            }
        }
        return totalSymptoms;
    }

    public int calculateSeverityScore(int[] entry) {
        int severityScore = 0;
        for (int symptom : entry) {
            severityScore += symptom;
        }
        return severityScore;
    }

    private String getSymptomName(int index) {
        switch (index) {
            case 0: return "Please enter your headache score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 1: return "Please enter your pressure in head score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 2: return "Please enter your neck pain score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 3: return "Please enter your nausea or vomiting score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 4: return "Please enter your dizziness score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 5: return "Please enter your blurred vision score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 6: return "Please enter your balance problems score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 7: return "Please enter your sensitivity to light score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 8: return "Please enter your sensitivity to noise score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 9: return "Please enter your feeling slowed down score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 10: return "Please enter your feeling like \"in a fog\" score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 11: return "Please enter your  \"don't feel right\" score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 12: return "Please enter your difficulty concentrating score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 13: return "Please enter your difficulty remembering score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 14: return "Please enter your fatigue or low energy score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 15: return "Please enter your confusion score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 16: return "Please enter your drowsiness score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 17: return "Please enter your trouble falling asleep score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 18: return "Please enter your more emotional score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 19: return "Please enter your irritability score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 20: return "Please enter your sadness score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            case 21: return "Please enter your nervous or anxious score (none (0), mild (1-2), moderate (3-4), & severe (5-6))";
            default: return "Unknown Symptom. Please try again";
        }
    }

    private String getSummary(int[] symptoms) {
        StringBuilder summary = new StringBuilder();
        for (int score : symptoms) {
            summary.append(score).append(" ");
        }
        return summary.toString().trim();
    }

    public void athleteMenu(Scanner scanner, MedicalPractitioner practitioner) {
        while (true) {
            System.out.println("\n--- Athlete Menu ---");
            System.out.println("1. Symptom Evaluation Form");
            System.out.println("2. View Symptom History");
            System.out.println("3. View Feedback History");
            System.out.println("4. Back");
            System.out.print("Choose an option (1-4): ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addSymptomEntry(scanner);
                    break;
                case 2:
                    displaySymptomHistory();
                    break;
                case 3:
                    displayFeedbackHistory();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
