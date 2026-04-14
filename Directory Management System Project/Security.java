// Phase 2 Part 1
public class Security extends Person{
	private int secID;
	private double secSalary;


	public Security(String last, String first, String middle, int id, double sal) {
		super(last, first, middle);
		this.secID = id;
		this.secSalary = sal;

	}

	public int getexecID()
	{
		return secID;

	}
	
	public double getSecSalary() 
	{
        return secSalary;
    }
	

}
