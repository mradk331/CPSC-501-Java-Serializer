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
		
		Element field = null;
		Field[] fields = null;
		
		switch(choice)
		{
		
		
		// "primitive":
		case 1:
			
		field = null;
		fields = obj.getClass().getDeclaredFields();	
		
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
			 field = new Element("Field");
			 Element primfield = new Element ("Field"); //maybe
			  fields = obj.getClass().getDeclaredFields();
			
			  for(int i = 0; i <fields.length; i++)
			  {
				 if(!fields[i].getType().isPrimitive()) 
				 { 
					 field = new Element("Field");
					 String hashcode = String.valueOf(fields[i].hashCode());
					 Attribute name = new Attribute("Name", fields[i].getName());
					 Attribute decclass = new Attribute("DeclaringClass", fields[i].getDeclaringClass().getName());
					 field.setAttribute(name);
					 field.setAttribute(decclass);
					 System.out.println(decclass.getName());
					 Element ref = new Element("Reference");
					 ref.addContent(hashcode);
					 field.addContent(ref);
					// element.addContent(field);
					 
				 }
				 
				 else
				 {
					 
					 //same as first primitive
					 
					    primfield = new Element("Field");
						Attribute attribute = new Attribute("Name" , fields[i].getName());
						
						primfield.setAttribute(attribute);
						primfield.setAttribute("DeclaingClass", fields[i].getDeclaringClass().getSimpleName());
						
						
						
						Element value = new Element("Value");
						fields[i].get(obj);  //vlue	
						//System.out.println(fields[i].get(obj) + "pleasze do somethine");
						value.addContent(fields[i].get(obj).toString());		
						System.out.print(fields[i].get(obj).toString());
						primfield.addContent(value);	
						
						element.addContent(primfield);
					 
				 }
				 
				 //same as first
				  
				  
			  }
			   root.getRootElement().addContent(field);
			   root.getRootElement().addContent(element);
			   XMLOutputter refout= new XMLOutputter();
				refout.output(root, new FileWriter("src//test.xml"));

				break;
			
			
			
			
			
			
		// "PrimitiveArray":except that an additional length attribute is used, and each element of the array will be 
				//stored as content to a value or reference element, depending on the component type. For example:


		case 3:
			 field = new Element("Field");
			 Element arrayO = new Element ("Object");
			 ///Element arrayfield = new Element ("Field"); //maybe
			  fields = obj.getClass().getDeclaredFields();
		
			for(int i = 0; i< fields.length; i++)
			{
				System.out.print("Please get hrer");
				if(fields[i].getType().isArray());
				{
					System.out.print("Please get hrer");
					arrayO = new Element("Object");
					arrayO.setAttribute("class", fields[i].getDeclaringClass().getName());
					String hashe = String.valueOf(fields[i].hashCode());
					arrayO.setAttribute("id", String.valueOf(hashe));
					
					
				Object iArray = fields[i].get(obj);
					
					int length = Array.getLength(iArray);
					arrayO.setAttribute("length", String.valueOf(length));
					
					for (int j = 0 ; j < length; j++)
					{
						Element value = new Element ("value");
						value.addContent(String.valueOf(Array.get(fields[i].get(obj), j)));
						Element ref = new Element ("reference");
						ref.addContent(hashe);
						arrayO.addContent(value);
						arrayO.addContent(ref);
						
					}
					
				}
				
				
				
				
			}
			root.getRootElement().addContent(arrayO);
			XMLOutputter arrayout= new XMLOutputter();
			arrayout.output(root, new FileWriter("src//test.xml"));

		// "Oarray":
		case 4:
			
			
			
		// "collection":
		case 5:
			
		
		}
		
		return root;
		//return null;
		
		
	}
	
	
	
	
	
}
