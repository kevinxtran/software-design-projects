import java.util.Scanner;

public class MedicalPractitioner {
    private String riskLvl = "Green";

    public void evaluateAthleteSymptoms(Athlete athlete, Scanner scanner) {
        athlete.displaySymptomHistory();
        
        System.out.print("Enter the entry number you would like to review: ");
        int entryNumber = scanner.nextInt();
        int[] selectedEntry = athlete.getEntryByIndex(entryNumber);

        if (selectedEntry == null) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }

        int totalSymptoms = athlete.calculateTotalSymptoms(selectedEntry);
        int severityScore = athlete.calculateSeverityScore(selectedEntry);

        System.out.println("Athlete's Symptom Summary for Entry " + entryNumber + ":");
        System.out.println("Total Symptoms: " + totalSymptoms);
        System.out.println("Severity Score: " + severityScore);

        System.out.print("Do the symptoms get worse with physical activity (y/n)? ");
        boolean physicalAct = scanner.next().equalsIgnoreCase("y");

        System.out.print("Do the symptoms get worse with mental activity (y/n)? ");
        boolean mentalAct = scanner.next().equalsIgnoreCase("y");

        String overallRating = "Unsure";
        if (totalSymptoms < 3 && severityScore < 10) {
            overallRating = "No Difference";
        } else if (totalSymptoms < 3 && severityScore >= 10) {
            overallRating = "Unsure";
        } else if (totalSymptoms >= 3 && severityScore >= 15) {
            overallRating = "Very Different";
        }
        
        if (overallRating.equals("Very Different") && severityScore >= 15 || physicalAct || mentalAct) {
            riskLvl = "\u001B[41mRed\u001B[0m"; // Red for high risk
        } else if (overallRating.equals("Unsure") && severityScore >= 10 || physicalAct || mentalAct) {
            riskLvl = "\u001B[43mYellow\u001B[0m"; // Yellow for moderate risk
        } else if (overallRating.equals("No Difference") && totalSymptoms < 3 && severityScore < 10) {
            riskLvl = "\u001B[42mGreen\u001B[0m"; // Green for low risk
        }
        
        // For medical practitioner to see
        System.out.println("Overall Rating: " + overallRating);
        System.out.println("Risk level for the athlete: " + riskLvl);

        // For athlete to see
        String feedbackMessage = "\nOverall Rating: " + overallRating + "\nRisk Level: " + riskLvl;
        athlete.setFeedback(entryNumber, feedbackMessage); // Store combined feedback
    }

    public void evaluateRiskLevel(Athlete athlete) {
        System.out.println("Risk Status: " + riskLvl);
        switch (riskLvl) {
            case "Green":
                System.out.println("Low risk");
                break;
            case "Yellow":
                System.out.println("Moderate risk");
                break;
            case "Red":
                System.out.println("High risk !!!");
                break;
            default:
                System.out.println("Assessment unavailable");
        }
    }
}
