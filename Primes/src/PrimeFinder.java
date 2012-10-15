//Primefinder.java Author: Adam Wilczek
//This is a basic program to find 25 primes between two
//numbers. it uses the basis that a prime is a number that
//can only be divided by itself and 1. the way we check for
//this is by looking for numbers divisible by some number 
//between 2 and the number's square root. it then takes
//any number that ends up not divisible by a number in this
//range and puts it in a hashmap. once the range has been 
//thouroughly searched the program randomly picks 25 primes
//from the hashmap and outputs them.
//example output from console for this program
//
//Please insert a Minimum number
//1000000
//please insert a Maximum number
//2000000
//prime #1 : 1736191.0
//prime #2 : 1069499.0
//prime #3 : 1534289.0
//prime #4 : 1821377.0
//prime #5 : 1030951.0
//prime #6 : 1727969.0
//prime #7 : 1045307.0
//prime #8 : 1022719.0
//prime #9 : 1269427.0
//prime #10 : 1458319.0
//prime #11 : 1575731.0
//prime #12 : 1456691.0
//prime #13 : 1246057.0
//prime #14 : 1655201.0
//prime #15 : 1108321.0
//prime #16 : 1535803.0
//prime #17 : 1350467.0
//prime #18 : 1633249.0
//prime #19 : 1649689.0
//prime #20 : 1671101.0
//prime #21 : 1526069.0
//prime #22 : 1007827.0
//prime #23 : 1688387.0
//prime #24 : 1845791.0
//prime #25 : 1671493.0

import java.util.*;
public class PrimeFinder {

	//creates the hashmap we store the primes in.
	HashMap listOfPrimes = new HashMap();

	public PrimeFinder()
	{
		//places a placeholder at 0 because i wanted a count
		// ofprimes
		//   	listOfPrimes.put(0, "ignore me");
	}
	public void finder(int min, int max)
	{
		//sets the index of the hashmap.
		int index = 0;
		//the initialization of the remainder vairable
		double remainder = 0;
		//random number generator for numbers 
		Random gen = new Random();
		//this is a boolean place holder so we know if a number
		//is prime
		boolean prime = false;

		//here we iterate through all the numbers between the
		//minimum and the maximum given
		for(double j=min; j <= max; j++)
		{
			//			System.out.print(j + " % ");

			//since we only need to check up to the square root
			//of a number we take the number and square root it
			double limit = Math.sqrt((double)j);

			//here we itterate through the whole list of
			//numbers starting from 2 because all numbers are
			//divisible by 1.
			for(double i= 2; i <= limit; i = i + 1)
			{
				//				System.out.print(i);
				//gets the remainder of the division to accertain
				//if it can be divided evenly by any number
				remainder = j % i;
				//				System.out.print(" = " + remainder + "\n");
				//checks to see if the remainder is 0
				if (remainder == 0)
				{
					//if the item can be devided by anything other
					//than one and itself marks the prime as false
					//and breaks out of the loop
					prime = false;
					break;
				}
				else 
				{
					prime = true;
				}
			}
			//Checks if the boolean prime is still true.
			//if so adds it to the hashmap at whatever index
			//it is on starting with 1

			if (prime == true)
			{
				listOfPrimes.put(index, j);
				index = index + 1;
			}


		}
		//Outputs 25 random primes in the span, the limit is
		//currently set for the number between 1000000 and 
		//2000000
		for(int i=1; i <= 25; i++)
		{
			int r = gen.nextInt(68901);
			System.out.println("prime #" + i + " : " +listOfPrimes.get(r));
		}
	}


}



