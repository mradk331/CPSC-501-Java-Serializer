import java.io.*;


public class Oarray implements Serializable{

	primitive[] primArray;
	
	
	public Oarray (int first, int second, int third)
	{
		
		primArray = new primitive[2];
		primArray[0] = new primitive(first, second, third);
		primArray[1] = new primitive(first, second, third);
		
	}
	
	public void print()
	{
		
		System.out.println("first object contents: ");
		primArray[0].print();
		System.out.println("second object contents: ");
		primArray[1].print();
		System.out.println();
	}
}
