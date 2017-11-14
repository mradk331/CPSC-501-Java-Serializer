

import java.io.*;

public class Oref implements Serializable{

	
	primitive prim;
	
	
	public Oref(int first, int second, int third)
	{
		
		prim = new primitive(first, second, third);
		
		
	}
	
	public void print()
	{
		prim.print();
		System.out.println();
	}
}
