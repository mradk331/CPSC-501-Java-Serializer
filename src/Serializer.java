import java.beans.*;
import java.io.*;
import java.lang.*;

import org.jdom2.*;


import java.util.*;
public class Serializer {

	
	Document root;
	Element element;
	int choice = 0;
	public void main(String[] args)
	{
		
		
		primitive obj = new primitive(1,2,3);
		this.Serializer(obj);
	}
	
	public void updateChoice(int choice)
	{
		this.choice = choice;
		
		
	}
	
	public org.jdom2.Document Serializer(Object obj) {
		
		
		System.out.println(obj.getClass().getName());
		
		switch(choice)
		{
		
		
		// "primitive":
		case 1:
			
			
			
		// "Oref":
		case 2:
			
			
			
		// "PrimitiveArray":
		case 3:
		
			
			
		// "Oarray":
		case 4:
			
			
			
		// "collection":
		case 5:
			
		
		}
		
		
		return null;
		
		
	}
	
	
	
	
	
}
