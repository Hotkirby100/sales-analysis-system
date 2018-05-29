package doe.tevis;

public class Customer {
	//Strings, long, and int to hold customer data.
	private String fName;
	private String lName;
	private String city;
	private String pCode;
	private long ccn;
	private int id;
	/**
	 * Constructor method for the Customer object.
	 * @param first First name of the customer.
	 * @param last Last name of the customer.
	 * @param place City of the customer.
	 * @param postal Postal code of the customer.
	 * @param credit Credit card number of the customer.
	 * @param num id of the customer.
	 */
	public Customer(String first, String last, String place, String postal, long credit, int num) {

		fName = first;
		lName = last;
		city = place;
		pCode = postal;
		ccn = credit;
		id = num;

	}

	public String getName() {

		return fName + " " + lName;

	}
	
	public String getCity() {
		
		return city;
		
	}
	
	public String getCode() {
		
		return pCode;
		
	}
	
	public long getCredit() {
		
		return ccn;
		
	}
	
	public int getID() {
		
		return id;
		
	}

}
