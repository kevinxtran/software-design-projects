import java.util.*;
public class Personnel {

	private ArrayList<Person> personList; // Changed to private

	public Personnel() {
	   personList = new ArrayList<Person>();
	}

	public void addPersonnel(Person p)
	{
		personList.add(p);
	}

	public List<Person> getPersonList() 
	{
        return new ArrayList<>(personList); // Return copy for encapsulation
    }
}