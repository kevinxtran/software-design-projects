public class Person {
	private String last;     // Changed to private
    private String first;    // Changed to private
    private String middle;    // Changed to private


	public Person(String last, String first, String middle) {
		this.last = last;
		this.first = first;
		this.middle = middle;

	}


	public void printFirstMidLast() {
        System.out.println(first + " " + middle + " " + last);
    }

    public void printFirstLastMid() {
        System.out.println(first + ", " + last + " " + middle);
    }

    public void printLastFirstMid() {
        System.out.println(last + ", " + first + " " + middle);
    }
	

	// Getter for 'first' name
	public String getFirst() {
		return first;
	 }

	 // Getter for 'middle' name
	public String getMid() {
		return middle;
	 }
  
	 // Getter for 'last' name
	 public String getLast() {
		return last;
	 }

}