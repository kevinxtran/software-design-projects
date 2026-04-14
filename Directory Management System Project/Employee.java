public class Employee extends Person{
	private int empID;
	private double baseSalary;


	public Employee(String last, String first, String middle, int id, double sal) {
		super(last, first, middle);
		this.empID = id;
		this.baseSalary = sal;

	}

	public int getID()
	{
		return empID;

	}
	
	public double getBaseSalary() 
	{
        return baseSalary;
    }
	
}
