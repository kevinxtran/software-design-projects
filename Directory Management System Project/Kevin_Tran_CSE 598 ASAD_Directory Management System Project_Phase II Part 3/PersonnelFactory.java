// Phase 2 Part 1
// Use Factory Design Pattern 
public class PersonnelFactory {

    public Person createPersonnel(String type, String last, String first, String middle, int id, double sal) {
        switch (type) {
            case "executive":
                return new Executives(last, first, middle, id, sal);
            case "security":
                return new Security(last, first, middle, id, sal);
            case "volunteer":
                return new Volunteers(last, first, middle, id, sal);
            default:
                System.out.println("Invalid, please try again.");
                return null; //Return null if type is invalid
        }
    }
} 
