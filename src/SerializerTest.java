import static org.junit.Assert.*;

import org.jdom.JDOMException;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*
;public class SerializerTest {

	
	
	
	
	@Test
	public void tester() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException, IOException, JDOMException {
		
			this.test();
			//this.test1();
			//this.test2();
			
			
		}
	//@Test
	public void test() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException, IOException, JDOMException {
		
		primitive primitive = new primitive(1, 2, 3);
		//primitive.print();
		Serializer serialize = new Serializer();
		serialize.serializer(primitive);
		List list = new ArrayList ();
		list.add("1");
		list.add("2");
		list.add("3");
		Deserializer deserialize = new Deserializer();
		assertEquals(list, Deserializer.deserialize(null, "src//test.xml"));
	    serialize = null;
		
	}

	
	
	
	//@Test
	public void test1() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException, IOException, JDOMException {
		
		Oref Reference = new Oref(1, 2, 3);
		
		Serializer serialize1 = null;
		serialize1 = new Serializer();
		serialize1.serializer(Reference);
		List list = new ArrayList ();
		list.add("1");
		list.add("2");
		list.add("3");
		Deserializer deserialize = new Deserializer();
		assertEquals(list, deserialize.deserialize(null, "src//test.xml"));
	}

	
public void test2() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException, IOException, JDOMException {
		
	PrimitiveArray primitiveArray = new PrimitiveArray(1,2,3);
		
		Serializer serialize1 = null;
		serialize1 = new Serializer();
		serialize1.serializer(primitiveArray);
		List list = new ArrayList ();
		list.add("1");
		list.add("2");
		list.add("3");
		Deserializer deserialize = new Deserializer();
		assertEquals(list, deserialize.deserialize(null, "src//test.xml"));
	}
	
	
}
