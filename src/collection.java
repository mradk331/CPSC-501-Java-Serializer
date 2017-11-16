import java.util.*;
import java.io.*;

public class collection implements Serializable{

	
	int first;
	int second;
	int third;
	ArrayList <Integer> arrayList;
	
	public collection(int first, int second, int third)
	{
		
		this.first = first;
		this.second = second;
		this.third = third;
		arrayList = new ArrayList<Integer>();
		
		arrayList.add(first);
		arrayList.add(second);
		arrayList.add(third);
		
		
	}
	
	
	
	public void print() {
		
		System.out.println("First integer: " + arrayList.get(0));
		System.out.println("Second integer: " + arrayList.get(1));
		System.out.println("Third integer: " + arrayList.get(2) + '\n');
	}
	
	
	
	public ArrayList getcontents()
	{
		return arrayList;

	}
}
