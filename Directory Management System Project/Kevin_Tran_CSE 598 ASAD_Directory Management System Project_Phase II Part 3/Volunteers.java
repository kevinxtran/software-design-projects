// Phase 2 Part 1
public class Volunteers extends Person{
	private int volID;
	private double volSalary;


	public Volunteers(String last, String first, String middle, int id, double sal) {
		super(last, first, middle);
		this.volID = id;
		this.volSalary = sal;

	}

	public int getvolID()
	{
		return volID;

	}
	
	public double getVolSalary() 
	{
        return volSalary;
    }
	
}
