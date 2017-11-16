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
		//this.serializer(obj);
	}
	
	public void updateChoice(int choice)
	{
		this.choice = choice;
		
		
	}
	
	public org.jdom.Document serializer(Object obj) throws IllegalArgumentException, IllegalAccessException, IOException, JDOMException, ClassNotFoundException, InstantiationException, InvocationTargetException 
	{
		//System.out.println("Why");
		document.setRootElement(rootelement);
		
		Class toserialize = obj.getClass();
		Element object = new Element("Object");
		
		String hash = String.valueOf(obj.hashCode());
		String classname = obj.getClass().getName();
		Attribute classat = new Attribute("Class", classname);
		Attribute hashat = new Attribute("id" , hash); 
		object.setAttribute(classat);
		object.setAttribute(hashat);
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
			                //System.out.print(fields[i].getType().isArray());
			if(fields[i].getType().isArray() && (fields[i].getType().getComponentType().isPrimitive()))
			{	
				//System.out.println("is array no primitives");
				arrayObj = new Element("Object");
				newfield = new Element("Field");
				Attribute decclass = new Attribute("declaringclass", fields[i].getDeclaringClass().getName());		
				Attribute name = new Attribute("name", String.valueOf(fields[i].getName()));
				Attribute type = new Attribute ("type", String.valueOf(fields[i].getType().getComponentType()));
				//System.out.print(String.valueOf(fields[i].getType().getComponentType()) + "LOLOLOL");
				
				newfield.setAttribute(decclass);
				
				newfield.setAttribute(name);
				newfield.setAttribute(type);
				
				
				
				Object iArray = fields[i].get(obj);
				int arraylength = Array.getLength(iArray);
				//System.out.print(arraylength);
				System.out.println("SHOULD NOT BE HERE!!!");
				
				Attribute arraylen = new Attribute("length" , String.valueOf(arraylength));
				newfield.setAttribute(arraylen);
						//System.out.println("array length is " + arraylength);
						
						
						
						
						for(int j = 0; j < arraylength; j++)
						{
							
							Element value = new Element("value");
							Attribute index = new Attribute("index", String.valueOf(j));
							
							
							//value.addContent(String.valueOf(Array.get(fields[i].get(obj), j)));
							Attribute valueatt = new Attribute("value",String.valueOf(Array.get(fields[i].get(obj), j)));
							value.setAttribute(valueatt);
							value.setAttribute(index);
							
							//System.out.println(String.valueOf(Array.get(fields[i].get(obj), j)));
							newfield.addContent(value);
							
							
						}
						//System.out.print("kill me");
						arrayObj.addContent(newfield);
						object = arrayObj;
			}
			
			
				
			else if(fields[i].getType().isPrimitive() || Collection.class.isAssignableFrom(fields[i].getType()))
				
			{   
				
				
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
				//value.addContent((fields[i].get(obj).toString()));
				Attribute valueatt = new Attribute("value",fields[i].get(obj).toString() );
				value.setAttribute(valueatt);
				newfield.addContent(value);
				
				//field addContent, or setArrribute
				
				object.addContent(newfield);
				//rootelement.addContent(object);
							//System.out.println("three teim");	
							//System.out.println("primitive");
			}
				
			
			//is object reference
			
				else if(!fields[i].getType().isPrimitive())
			{	//System.out.println("not working");
				//System.out.println(fields[i].getType().getComponentType().getName() + "read this");
				newfield = new Element("Field");
				Attribute decclass = new Attribute("declaringclass", fields[i].getDeclaringClass().getName());		
				Attribute name = new Attribute("name", String.valueOf(fields[i].getName()));
				Attribute type = null; 
				
				
				
				//if(!(fields[i].getType().isArray()))
				//{System.out.print("outer loop");
				 //type = new Attribute ("type", fields[i].getType().getName());
				//}
				
				if((fields[i].getType().isArray()))
				{
					
					 type = new Attribute ("type", fields[i].getType().getComponentType().getName());
					//System.out.print("inner loopfgff");
				}
				else{
					
					type = new Attribute ("type", fields[i].getType().getName());
					
				}
				
				//System.out.print("outer loop");
				newfield.setAttribute(name);
				newfield.setAttribute(decclass);
				newfield.setAttribute(type);
				
				Object oref = fields[i].get(obj);
				//System.out.println(fields.length + oref.getClass().getName());
				
				
				
				
				if(fields[i].getType().isArray())
				{
					
					
					int arraylength = Array.getLength(oref);
					//System.out.print(arraylength + " arraylength");
					
					
					Attribute arraylen = new Attribute("length" , String.valueOf(arraylength));
					newfield.setAttribute(arraylen);
					//System.out.print("ARRAY LENGHTHTHTTHTH " + arraylength);
					//object.addContent(newfield);
					rootelement.addContent(object);
					object.addContent(newfield);
					for(int k = 0; k< arraylength ; k++)
					{
						//rootelement.addContent(object)
						//object.addContent(newfield);
						//rootelement.addContent(object);
						Object nextone = Array.get(fields[i].get(obj), k);
						this.serializer(nextone);
					}
					
					
				}
				else {
					//System.out.print("never get here");
					object.addContent(newfield);
					rootelement.addContent(object);
				this.serializer(oref);
				}
				
				
				
				
				
			}
			
			
			
			document.getRootElement().addContent(object);
			
			
		}
		//System.out.print("please get here");
		deserialize();
		return null;
	}
	public void deserialize() throws IOException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, JDOMException, InstantiationException, InvocationTargetException
	{
		
		XMLOutputter out = new XMLOutputter();
		out.output(rootelement, new FileWriter("src//test.xml"));
		
		//testing
		//Deserializer deserializer = new Deserializer();
		//Deserializer.deserialize(document);
		
	}
	
	
	
	
}
