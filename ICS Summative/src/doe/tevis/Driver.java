package doe.tevis;

import java.util.*;

public class Driver {

	public static void main(String[] args) {

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

			} else if (uchoice.equals("2")) {

			} else if (uchoice.equals("4")) {

			} else if (uchoice.equals("5")) {
				
				System.out.println("Program exiting...");
				
			} else {

				System.out.println("Invalid Input, please try again.");

			}
		}

	}

}
