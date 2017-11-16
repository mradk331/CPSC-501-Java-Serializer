
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jdom.*;
//import org.jdom2.*;
import org.jdom.input.SAXBuilder;


public class Deserializer {

	
	
	public static void deserialize(Document doc)throws IllegalArgumentException, IllegalAccessException, IOException, JDOMException {
	System.out.println();
	Document open = new Document();
	SAXBuilder builder = new SAXBuilder();
	doc = builder.build("src//test.xml");
	//File xmlFile = new File("xmlfile.xml"); 
	Element root = doc.getRootElement();
	//System.out.println(root + "root");
	List <Element> children = root.getChildren("Object");
	String rootClass = root.getAttributeValue("Class");
	String rootID = root.getAttributeValue("ID");
	
	
	//System.out.println(rootID);
	
	List primvalues = new ArrayList();
	List primtypes = new ArrayList();
	List declaringclass = new ArrayList();
	
	//List <Element> childrens = children.get(i).getChildren("Field");
	//System.out.println(root.getName());
		//System.out.println("children size" + children.size() + children.get(1));
	System.out.println("contents of Object: " + rootClass);
	for(int i = 0; i < children.size(); i++)
	{
		
		//System.out.print("nottest");
		List <Element> childrens = children.get(i).getChildren("Field");
		List <Element> childrenvalues = childrens.get(0).getChildren("value");
	//System.out.println("length is children " + childrenvalues.size() );
		//System.out.println(childrens.get(i));
		
		//List <Attribute> att = childrens.get(i).getAttributes();
		System.out.print(childrens.get(0).getAttribute("name").getValue() + " is type ");
		if(childrenvalues.size() == 1)
		{ System.out.print(childrens.get(0).getAttribute("type").getValue() + " has value " + childrenvalues.get(0).getAttribute("value").getValue());
			
		
			primvalues.add(childrenvalues.get(0).getAttribute("value").getValue());
		
		//if(childrens.get(0).getAttribute("declaringclass").getValue() != rootClass)
		System.out.println(" Declared in " + childrens.get(0).getAttribute("declaringclass").getValue());
		
		declaringclass.add(childrens.get(0).getAttribute("declaringclass").getValue());
		}
		else
		{
			
			System.out.println(childrens.get(0).getAttribute("type").getValue() + " and has values ");
			for (int k = 0 ; k< childrenvalues.size(); k++)
			{
				
				System.out.println("index " + k + " has value " + childrenvalues.get(k).getAttribute("value").getValue());
				primvalues.add(childrenvalues.get(k).getAttribute("value").getValue());
				declaringclass.add(childrens.get(0).getAttribute("declaringclass").getValue());
			}
			
		}
		
		
		
		
		//List <Element> childrenvalues = childrens.get(0).getChildren();
		//System.out.println(childrenvalues.get(0).getAttribute("value").getValue());
		//if(false)
        // primtypes.add(childrens.get(0).getAttribute("type").getValue());
		//System.out.println(att.get(i).getValue()+ "asdasdasd");
		//List test = att.getChildren();
		//for(int j = 0; j<att.size(); j++)
	System.out.println();
	
		
		//String childClass = attributes.get(0).getValue();
		//System.out.println(childClass);
	}
	
	List arguments = new ArrayList();
	
     for(int n = 0; n < primvalues.size(); n++)
     {
	     
	     
	     if((String.valueOf(declaringclass.get(n)).equals(rootClass)) )
	     
    	 arguments.add(primvalues.get(n));
	
   
     }
     
     System.out.println(arguments);
     System.out.println(primvalues);
     System.out.println(declaringclass);
	{
	
	
	
	
	}
     
     
	}
	

}