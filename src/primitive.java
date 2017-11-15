
import java.io.*;

public class primitive implements Serializable{

	int first;
	int second;
	int third;

	
	
	
	
	
	public primitive( int first, int second, int third)
	
	{
		
		this.first = first;
		this.second = second;
		this.third = third;
		
	}
	
	
	public void print()
	{
		
		
		System.out.println("First integer = " + first);
		System.out.println("Second integer = " + second);
		System.out.println("Third integer = " + third + '\n');
		
	}
	
}
