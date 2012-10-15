import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
	PrimeFinder a = new PrimeFinder();
	int min;
	int max;
	System.out.println("Please insert a Minimum number");
	min = s.nextInt();
	System.out.println("please insert a Maximum number");
	max = s.nextInt();
	
	a.finder(min,max);
	}
}
