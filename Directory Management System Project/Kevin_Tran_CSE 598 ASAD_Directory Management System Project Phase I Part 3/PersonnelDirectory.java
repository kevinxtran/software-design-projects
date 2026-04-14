import java.util.Scanner;
public class PersonnelDirectory
{



   public static void main(String[] args)
   {
	Personnel per = new Personnel();
	totalObjects total = new totalObjects();
	Scanner scan = new Scanner(System.in);
	

	int choice = -1;


      do{
		  displayMenu(); 			// Seperated to call and display the menu
		  choice = scan.nextInt();
          scan.nextLine();

          switch(choice)
          {
			  case 1:		// Add Personnel
				addPersonnel(scan, per, total);
			  	break;

			  case 2:		// Find Personnel

				findPersonnel(scan, per, total);
              	break;

			  case 3:		// Print Name
				
				printNames(scan, per);
				break;

			  case 4:		// Display Total Entries
			  	System.out.println("Total Entries : " + total.getTotalObjects());

               	break;
			
			// Adding an exit feature
			case 5:
				System.out.println("Exiting...");
				scan.close();
				return;
			
			default:
				System.out.println("Invalid option! Please try again.");
		  }

		 } while(true);


  }

  private static void displayMenu()
  {
	System.out.println("Welcome to the Personnel Directory Management System");
	System.out.println("====================================================");

	System.out.println("\n\n\t 1. Add Personel");
	System.out.println("\n\t 2. Find Personel");
	System.out.println("\n\t 3. Print Names");
	System.out.println("\n\t 4. Number of Entries in the Directory");

	System.out.println("\n\t 5. Exit");

	System.out.println("\n\t Select one of the options above (1, 2, 3, 4)");
  }

  private static void addPersonnel(Scanner scan, Personnel per, totalObjects total) {
	String firstN, lastN, middleN;
	int empID;
	double salary;
	
	System.out.println("Enter first name: ");
	firstN = scan.nextLine();
	System.out.println("Enter last name: ");
	lastN = scan.nextLine();
	System.out.println("Enter middle name: ");
	middleN = scan.nextLine();

	System.out.println("Enter empploy id : ");
	empID = scan.nextInt();
	System.out.println("Enter base salaey" );
	salary = scan.nextDouble();
	scan.nextLine();

	Employee e1  = new Employee(lastN, firstN, middleN, empID, salary);


	per.addPersonnel(e1);
	total.objectAdded();

  }

  private static void findPersonnel(Scanner scan, Personnel per, totalObjects total) {
	String firstN, lastN;
	System.out.println("Enter firts name : ");
	firstN = scan.nextLine();

	System.out.println("Enter last name : ");
	lastN = scan.nextLine();


	boolean found = false;
	int loc =-1;
	
	// Search for the person in the personnel list
	for(int i =0; i <per.getPersonList().size(); i++)		// Use getPersonList method from Personnel file
	{
		// Use the getter files from Person.java
		if(per.getPersonList().get(i).getFirst().equals(firstN) && per.getPersonList().get(i).getLast().equals(lastN))
		{
			found = true;
			loc = i;
			break;		// Break the loop if person found
		}
	}

	if(found)
	{
		System.out.println("Found");
		per.getPersonList().get(loc).printFirstMidLast();

	}else
	{
		System.out.println("not found");
		Person p1  = new Person(lastN, firstN, " ");
		per.getPersonList().add(p1);
		total.objectAdded();
	}
  }


  private static void printNames(Scanner scan, Personnel per) {
	System.out.println("Enter the order 0: first, middle,  last, 1: first, last, middle, 2: last, first , middle ");
	int order = scan.nextInt();
	for(int i=0; i<per.getPersonList().size(); i++)	
	{
		switch (order) {
			case 0:
				per.getPersonList().get(i).printFirstMidLast(); // Assuming you implement this method
				break;
			case 1:
				per.getPersonList().get(i).printFirstLastMid(); // Assuming you implement this method
				break;
			case 2:
				per.getPersonList().get(i).printLastFirstMid(); // Assuming you implement this method
				break;
			default:
				System.out.println("Invalid order specified.");
		}
	}
  }
	
}