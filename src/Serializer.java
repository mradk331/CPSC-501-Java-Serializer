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
	static int read = 0;
	
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
	if(read == 0)
	{
		rootelement.setAttribute(new Attribute("Class",obj.getClass().getSimpleName()));
		rootelement.setAttribute(new Attribute("ID", hash));
		read =1;
	}	
		
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
				Attribute type = new Attribute ("type", fields[i].getType().getComponentType().getName());
				System.out.print(String.valueOf(fields[i].getType().getComponentType().getName()));
				
				arrayObj.setAttribute(decclass);
				
				arrayObj.setAttribute(name);
				arrayObj.setAttribute(type);
				
				
				
				Object iArray = fields[i].get(obj);
				int arraylength = Array.getLength(iArray);
				//System.out.print(arraylength);
				
				
				Attribute arraylen = new Attribute("length" , String.valueOf(arraylength));
				arrayObj.setAttribute(arraylen);
						System.out.println("array length is " + arraylength);
						
						
						
						
						for(int j = 0; j < arraylength; j++)
						{
							
							Element value = new Element("value");
							Attribute index = new Attribute("index", String.valueOf(j));
							
							
							value.addContent(String.valueOf(Array.get(fields[i].get(obj), j)));
							value.setAttribute(index);
							//System.out.println(String.valueOf(Array.get(fields[i].get(obj), j)));
							arrayObj.addContent(value);
							
							
						}
						//System.out.print("kill me");
						object = arrayObj;
			}
			
			
				
				if(fields[i].getType().isPrimitive() || Collection.class.isAssignableFrom(fields[i].getType()))
				
			{   
				System.out.print("please can i die");
				
				newfield = new Element("Field");
				
				Attribute decclass = new Attribute("declaringclass", fields[i].getDeclaringClass().getName());		
				Attribute name = new Attribute("name", String.valueOf(fields[i].getName()));
				Attribute type = new Attribute ("type", fields[i].getType().getName());
				//System.out.print(String.valueOf(fields[i].getName()));
				
				//System.out.print(fields[i].getType().getName());
				
				
				newfield.setAttribute(name);
				newfield.setAttribute(decclass);
				newfield.setAttribute(type);
				
				Element value = new Element("value");
				value.addContent((fields[i].get(obj).toString()));
				newfield.addContent(value);
				//field addContent, or setArrribute
				
				object.addContent(newfield);
				//rootelement.addContent(object);
							//System.out.println("three teim");	
							//System.out.println("primitive");
			}
				
			
			//is object reference
			
				else if(!fields[i].getType().isPrimitive())
			{
				System.out.println("sfgsfgsfgsdfgsfg");
				newfield = new Element("Field");
				Attribute decclass = new Attribute("declaringclass", fields[i].getDeclaringClass().getName());		
				Attribute name = new Attribute("name", String.valueOf(fields[i].getName()));
				Attribute type = new Attribute ("type", fields[i].getType().getName());
				
				newfield.setAttribute(name);
				newfield.setAttribute(decclass);
				newfield.setAttribute(type);
				
				Object oref = fields[i].get(obj);
				System.out.println(fields.length + oref.getClass().getName());
				object.addContent(newfield);
				rootelement.addContent(object);
				this.serializer(oref);
				
				
				
				
				
				
			}
			
			
			
			document.getRootElement().addContent(object);
			
			
		}
		System.out.print("please get here");
		XMLOutputter out = new XMLOutputter();
		out.output(rootelement, new FileWriter("src//test.xml"));
		
		return null;
	}
	
	
	
	
	
}
