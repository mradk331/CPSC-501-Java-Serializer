import java.io.Serializable;

public class PrimitiveArray implements Serializable{

	

	
	int[] primArray;
	
	
	
	
	public PrimitiveArray( int first, int second, int third)
	
	{
		
		//this.first = first;
		//this.second = second;
		//this.third = third;
		
		primArray =  new int[] {first, second, third};
		
	}
	
	
	public void print()
	{
		
		
		System.out.println("First integer = " + primArray[0]);
		System.out.println("Second integer = " + primArray[1]);
		System.out.println("Third integer = " + primArray[2] + '\n');
		
	}
	
	public int[] getcontents()
			{
		return primArray;
		
		
			}
	
}
