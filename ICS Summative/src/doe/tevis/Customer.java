package doe.tevis;

public class Customer {

	private String fName;
	private String lName;
	private String city;
	private String pCode;
	private long ccn;
	private int id;

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
