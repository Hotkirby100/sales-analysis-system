package doe.tevis;

import java.util.*;
import java.io.*;

public class Driver {
	
	private int id;
	
	public static void main(String[] args) {
		
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<PostalCode> postalCodes = new ArrayList<>();
		loadCodes(postalCodes);
		
		Scanner in = new Scanner(System.in);
		// A string is used instead of an integer here, since it's easier
		// to handle if a user doesn't enter a number.
		String uchoice = "0";
		//This is the main menu, which will keep repeating until the user wishes to exit 
		while (!uchoice.equals("5")) {
			System.out.println(
					"Sales Analysis System\n1. Input Customer Info\n2. Generate Customer Data File\n3. Sales Report\n4. Check for Fraud\n5. Quit");
			System.out.println("Enter your option now:");
			uchoice = in.nextLine();

			if (uchoice.equals("1")) {
				
				newCustomer(customers, postalCodes);
				
			} else if (uchoice.equals("2")) {

			} else if (uchoice.equals("4")) {

			} else if (uchoice.equals("5")) {
				
				System.out.println("Program exiting...");
				
			} else {

				System.out.println("Invalid Input, please try again.");

			}
		}

	}


	private static void loadCodes(ArrayList<PostalCode> postalCodes) {
		
		File pFile = new File("postal_codes.csv");
		
		try {
			
			Scanner in = new Scanner(pFile);
			String data[];
			String hold;
			
			in.nextLine();
			
			while (in.hasNextLine()) {

				hold = in.nextLine();
				data = hold.split("|");
				postalCodes.add(new PostalCode(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4])));

			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		
	}


	private static void newCustomer(ArrayList<Customer> customers, ArrayList<PostalCode> postalCodes) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the customer's first name: ");
		String fName = in.nextLine();
		while(fName.contains(" ")) {
			
			System.out.println("Please input the customer's first name only. Try again.");
			fName = in.nextLine();
			
		}
		System.out.println("Enter the customer's last name: ");
		String lName = in.nextLine();
		while(lName.contains(" ")) {
			
			System.out.println("Please input the customer's last name only. Try again.");
			lName = in.nextLine();
			
		}
		System.out.println("Enter the customer's postal code.");
		String pCode = in.nextLine();
		while(!validatePostal(pCode, postalCodes)) {
			
			System.out.println("Invalid postal code, please try again.");
			pCode = in.nextLine();
			
		}
		
		
	}


	private static boolean validatePostal(String pCode, ArrayList<PostalCode> postalCodes) {
		
		if(pCode.length() < 3) {
			
			for (int i = 0 ; i < postalCodes.size() ; i++ ) {
				
				if (pCode.equals(postalCodes.get(i).getCode())) {
					
					return true;
					
				}
				
			}
			
		}
		
		
		return false;
	}

}
