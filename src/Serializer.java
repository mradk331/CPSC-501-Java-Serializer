import java.beans.*;
import java.io.*;
import java.lang.*;

import org.jdom.*;
import org.jdom.output.XMLOutputter;

import java.lang.reflect.*;
import java.util.*;
public class Serializer {

	

	static Element rootelement = new Element("serialized");
	static Document document = new Document(rootelement);
	
	int choice = 0;
	int roothash = 0;
	public void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException
	{
		
		
		primitive obj = new primitive(1,2,3);
		this.serializer(obj);
	}
	
	public void updateChoice(int choice)
	{
		this.choice = choice;
		
		
	}
	
	public org.jdom.Document serializer(Object obj) throws IllegalArgumentException, IllegalAccessException, IOException {
		document.setRootElement(rootelement);
		
		Class toserialize = obj.getClass();
		Element object = new Element("Object");
		
		String hash = String.valueOf(obj.hashCode());
		String classname = obj.getClass().getName();
		Attribute classat = new Attribute("Class", classname);
		Attribute hashat = new Attribute("id" , hash); 
		//object.setAttribute(classat);
		//object.setAttribute(hashat);
	
		rootelement.setAttribute(new Attribute("Class",obj.getClass().getSimpleName()));
		rootelement.setAttribute(new Attribute("ID", hash));
		
		
		if (obj == null) 
			return document;
		
		
		//Class toserialize = obj.getClass();
		
		//System.out.println(obj);
		
		Field[] fields = null;
		 fields =  obj.getClass().getDeclaredFields();
		 Element newfield = null;
		Element field = new Element("Field");
	
		
		
		for (int i = 0; i < fields.length; i++)
		{
			Element arrayObj = null;
			object = new Element("Object");
			//System.out.print("three times");
			fields[i].setAccessible(true);
			
			if(fields[i].getType().isArray())
			{	
				
				arrayObj = new Element("Object");
				Attribute decclass = new Attribute("declaringclass", fields[i].getDeclaringClass().getName());		
				Attribute name = new Attribute("name", String.valueOf(fields[i].getName()));
				
				arrayObj.setAttribute(decclass);
				
				arrayObj.setAttribute(name);
				
				
				
				
				Object iArray = fields[i].get(obj);
				int arraylength = Array.getLength(iArray);
				
				
				
				Attribute arraylen = new Attribute("length" , String.valueOf(arraylength));
				arrayObj.setAttribute(arraylen);
						System.out.println("array length is " + arraylength);
						//System.out.print("kill me");
			}
			
			
			
			
			
			if(fields[i].getType().isPrimitive() || Collection.class.isAssignableFrom(fields[i].getType()))
			{   
				System.out.print(fields[i].getName());
				
				newfield = new Element("Field");
				
				Attribute decclass = new Attribute("declaringclass", fields[i].getDeclaringClass().getName());		
				Attribute name = new Attribute("name", String.valueOf(fields[i].getName()));
				//System.out.print(String.valueOf(fields[i].getName()));
				
				
				
				newfield.setAttribute(name);
				newfield.setAttribute(decclass);
				
				
				Element value = new Element("value");
				value.addContent((fields[i].get(obj).toString()));
				newfield.addContent(value);
				//field addContent, or setArrribute
				
				object.addContent(newfield);
				//rootelement.addContent(object);
							//System.out.println("three teim");	
							
			}
			
			
			
			
			else
			{
				
				
				
				
				
				
				
			}
			
			
			
			document.getRootElement().addContent(object);
			
			
		}
		System.out.print("please get here");
		XMLOutputter out = new XMLOutputter();
		out.output(rootelement, new FileWriter("src//test.xml"));
		
		return null;
	}
	
	
	
	
	
}
