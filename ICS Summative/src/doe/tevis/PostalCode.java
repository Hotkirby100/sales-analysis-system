package doe.tevis;

public class PostalCode {

	private String pCode;
	private String place;
	private String city;
	private double latitude;
	private double longitude;

	public PostalCode(String c, String p, String ci, double la, double lo) {

		pCode = c;
		place = p;
		city = ci;
		latitude = la;
		longitude = lo;

	}
	
	public String getCode() {
		
		return pCode;
		
	}

}
