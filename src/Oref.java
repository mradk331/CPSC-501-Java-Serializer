

import java.io.*;

public class Oref implements Serializable{

	int first;
	int second;
	int third;
	primitive prim;
	
	
	public Oref(int first, int second, int third)
	{
		this.first = first;
		this.second = second;
		this.third = third;
		prim = new primitive(first, second, third);
		
		
	}
	
	public void print()
	{
		prim.print();
		System.out.println();
	}
}
