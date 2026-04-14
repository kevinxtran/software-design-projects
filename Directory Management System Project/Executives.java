// Phase 2 Part 1
public class Executives extends Person{
	private int execID;
	private double bigSalary;


	public Executives(String last, String first, String middle, int id, double sal) {
		super(last, first, middle);
		this.execID = id;
		this.bigSalary = sal;

	}

	public int getexecID()
	{
		return execID;

	}
	
	public double getBigSalary() 
	{
        return bigSalary;
    }
	
}
