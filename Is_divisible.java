import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Is_divisible {
	
	// this function will test if the number set is divisible.
	private boolean test_if_divisible(int[] int_array, int[] attempt, int amount_of_num, int m) {
		Is_divisible obj = new Is_divisible();
		String test_if_divisible = "";
		String[] sum = new String[amount_of_num * 2];
		int total = 0;
		
		test_if_divisible = obj.String_builder(int_array, attempt, amount_of_num);
		sum = test_if_divisible.split(" ");
		
		total = Integer.valueOf(sum[0]);
		for(int i = 0; i < sum.length; i++) {
			
			// an if statement. if sum[i] = + then the total will add on the number in the next slot.
			if(sum[i].equals("+")) {
				total += Integer.valueOf(sum[i+1]);
			}
			else if(sum[i].equals("-")){
				total -= Integer.valueOf(sum[i+1]);
			}
		}
		
		// an if statement to see if the total if divisible.
		if(total % m == 0 && total > 0) {
			System.out.println("Result: " + test_if_divisible + " = " + total);
			return true;
		}
		else {
			total = 0;
		}
		return false;
	}
	
	// String_builder. this function creates Strings to be broken-apart/displayed to the user.
	private String String_builder(int[] int_array, int[] attempt, int amount_of_num) {
		String Stringbuild = "" + int_array[0];
		
		for(int i = 1; i < amount_of_num; i++) {
			if(attempt[i] == 0) {
				Stringbuild += " + " + int_array[i];
			}
			else {
				Stringbuild += " - " + int_array[i];
			}
		}
		
		return Stringbuild;
	}
	
	// array_checker. this function will manage the program.
	private boolean array_checker(int int_array[], int m, int amount_of_num) {
		Is_divisible obj = new Is_divisible();
		Random rand = new Random();
		int attempted_amount = 0, plusorminus = 0, static_testing_plus = 0, static_testing_minus = 0, log_amount = 0;
		int[] attempt = new int[amount_of_num]; 
		boolean is_divisible = false;
		
		// for loop: depending on how many numbers the user has is how much the loop will loop.
		for(int loop = 0; loop < amount_of_num; loop++) {
			
			// while the attempted amount is not 10000 it will loop.
			while(attempted_amount != 10000) {
				
				log_amount++;
				
				// this for loop is for generating a set of numbers to use as + or -. 0 = +, 1 = -. 
				for(int i = 0; i < amount_of_num; i++) {
					plusorminus = rand.nextInt(2);
					attempt[i] = plusorminus;
				}
				
				if(static_testing_plus < 1) {
					
					// this for loop is for static testing. this loop will set all the numbers in attempt to 0.
					for(int i = 0; i < amount_of_num; i++) {
						attempt[i] = 0;
					}
					
					// part 2 of the static testing. this loop will test the numbers e.g 1,0,0,0. 0,1,0,0. 0,0,1,0. to test for easy solutions.
					for(int i = 0; i < amount_of_num; i++) {
						try {
							if(is_divisible == true) {
								break;
							}
							attempt[i+1] = 1;
							attempt[i] = 0;
							is_divisible = obj.test_if_divisible(int_array, attempt, amount_of_num, m);
						}
						catch(ArrayIndexOutOfBoundsException e) {
							
						}
						
					}
					static_testing_plus = 1;
				}
				
				// same as above just in reverse.
				else if(static_testing_minus < 1) {
					for(int i = 0; i < amount_of_num; i++) {
						attempt[i] = 1;
					}
					for(int i = 0; i < amount_of_num; i++) {
						try {
							if(is_divisible == true) {
								break;
							}
							attempt[i+1] = 0;
							attempt[i] = 1;
							is_divisible = obj.test_if_divisible(int_array, attempt, amount_of_num, m);
						}
						catch(ArrayIndexOutOfBoundsException e) {
							
						}
						
					}
					static_testing_minus = 1;
				}
				
				if(is_divisible != true) {
					is_divisible = obj.test_if_divisible(int_array, attempt, amount_of_num, m);
				}
				
				if(is_divisible == true) {
					System.out.println("Attempts: " + log_amount);
					return true;
				}
				
				attempted_amount++;
			}
			attempted_amount = 0;
		}
		
		System.out.println("Attempts: " + log_amount);
		return false;
	}
	
	// This function will create a set of numbers to be checked.
	private int[] create_array(int amount_of_num) {
		Scanner user_input = new Scanner(System.in);
		Random rand = new Random();
		int array_count = 0, num = 0, user_c, num_of_set = 0;
		int[] int_array = new int[amount_of_num];
		
		// a try/catch block to stop user's from inputing non-numbers.
		try {
			System.out.print("Pick a number\n1. Random set of numbers\n2. User set numbers\n==>");
			user_c = user_input.nextInt();
			if(amount_of_num > 1) {
				
				// an if statement to see what choice the user made.
				if(user_c == 1) {
					System.out.println("Creating set of numbers...");
					// Generating random numbers to put into the array
					for(array_count = 0; array_count < int_array.length; array_count++) {
						num = rand.nextInt(10);
						// if statement to add 1 to num if num is equal to 0
						if(num == 0) {
							num++;
						}
						int_array[array_count] = num;
					}
				}
				else {
					// allowing the user to enter the numbers.
					for(int i = 0; i < amount_of_num; i++) {
						num_of_set++;
						System.out.print("Enter a number(" + num_of_set + "): ");
						int_array[i] = user_input.nextInt();
						
					}
				}
			}
			else {
				System.out.println("ERROR: The set of numbers must have at least 2 numbers...");
			}
			user_input.close();
			return int_array;
		}
		catch(InputMismatchException e) {
			System.out.println("ERROR: input is invalid...");
			user_input.close();
			return int_array;
		}
	}
	
	//###-MAIN-###//
	public static void main(String[] args) {
		Is_divisible obj = new Is_divisible();
		Scanner user_input = new Scanner(System.in);
		int user_number = 0, amount_of_num = 0;
		boolean is_divisibl = false;
		
		// a try/catch block to stop user's from inputing non-numbers. 
		try {
			System.out.print("Please enter a number as the divisor: ");
			user_number = user_input.nextInt();
			
			// an if statement to stop user's from entering a number below 1.
			if(user_number > 0) {
				System.out.print("How many numbers in the number set do you want?: ");
				amount_of_num = user_input.nextInt();
				
				// an if statement to stop user's from setting the amount of numbers below 2.
				if(amount_of_num > 1) {
					int[] int_array = new int[amount_of_num];
					
					int_array = obj.create_array(amount_of_num);
					System.out.println("Set of new Numbers: " + Arrays.toString(int_array));
						
					is_divisibl = obj.array_checker(int_array, user_number, amount_of_num);
					System.out.println("Divisible: " + is_divisibl);
					
				}
				else {
					System.out.println("ERROR: The number set must have at least 2 numbers...");
				}
			}
			else {
				System.out.println("ERROR: The divisor must be above 0...");
			}
			user_input.close();
			System.out.println("Shutting down...");
			TimeUnit.SECONDS.sleep(10);
		}
		catch(InputMismatchException | InterruptedException e) {
			System.out.println("ERROR: input is invalid...");
			user_input.close();
		}
		user_input.close();
	}
}
