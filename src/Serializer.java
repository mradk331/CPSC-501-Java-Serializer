import java.beans.*;
import java.io.*;
import java.lang.*;

import org.jdom.*;
import org.jdom.output.XMLOutputter;

import java.lang.reflect.*;
import java.util.*;
public class Serializer {

	

	static Element element = new Element("Serializer");
	static Document root = new Document(element);
	int choice = 0;
	int roothash = 0;
	public void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException
	{
		
		
		primitive obj = new primitive(1,2,3);
		this.Serializer(obj);
	}
	
	public void updateChoice(int choice)
	{
		this.choice = choice;
		
		
	}
	
	public org.jdom.Document Serializer(Object obj) throws IllegalArgumentException, IllegalAccessException, IOException {
		
		
		System.out.println(obj.getClass().getName() + " class Passed in to Serializer");
		//do this first I think
		
		
		
		root.setRootElement(element);
		
		
		
		
		Element element = new Element ("Object");
		
		String hash =Integer.toString(obj.hashCode());
		
		if(roothash == 0)
		roothash = obj.hashCode();
		
	//name followed by hash	
		element.setAttribute(new Attribute("Class",obj.getClass().getSimpleName()));
		element.setAttribute(new Attribute("ID", hash));
		
		
		switch(choice)
		{
		
		
		// "primitive":
		case 1:
			
		Element field = null;
		Field[] fields = obj.getClass().getDeclaredFields();	
		
		for(int i = 0; i < fields.length; i++) {
			field = new Element("Field");
			Attribute attribute = new Attribute("Name" , fields[i].getName());
			
			field.setAttribute(attribute);
			field.setAttribute("DeclaingClass", fields[i].getDeclaringClass().getSimpleName());
			
			
			
			Element value = new Element("Value");
			fields[i].get(obj);  //vlue	
			//System.out.println(fields[i].get(obj) + "pleasze do somethine");
			value.addContent(fields[i].get(obj).toString());		
			field.addContent(value);	
			element.addContent(field);
			//1root.getRootElement().addContent(element);
				}
		
		
		
		
		
		
			//element.addContent(field);
			//root.getRootElement().addContent(element);
		root.getRootElement().addContent(element);
			//System.out.println(root. " Is there anything here?") ;
			XMLOutputter out= new XMLOutputter();
			out.output(root, new FileWriter("src//test.xml"));

			break;
			
			
		// "Oref":
		case 2:
			
			
			
		// "PrimitiveArray":
		case 3:
		
			
			
		// "Oarray":
		case 4:
			
			
			
		// "collection":
		case 5:
			
		
		}
		
		return root;
		//return null;
		
		
	}
	
	
	
	
	
}
