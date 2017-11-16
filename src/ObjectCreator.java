
import java.util.*;



public class ObjectCreator {

	
	
	
	public static void main(String [] args)
	{
		Scanner scanner = new Scanner(System.in);
		Serializer serialize;
		
		
		System.out.println("What would you like do do?");
		System.out.println("1: Simple Object ");
		System.out.println("2: Object with Object references");
		System.out.println("3: Object with array of primitives");
		System.out.println("4: Object with an array of objects");
		System.out.println("5: Object with Collection classes " + '\n');
		
		String first;
		int argument;
		int argument1;
		int argument2;
		
		while(true)
		{
			//first = scanner.nextLine();
			try {
				first = scanner.nextLine();	
				
				
			
			int choice = Integer.parseInt(first);
			
			if(choice == 1)
			{
				System.out.println("What value would you like to set?, provide 3 integers");
				argument = scanner.nextInt();	
				argument1 = scanner.nextInt();
				argument2 = scanner.nextInt();
				primitive primitive = new primitive(argument, argument1, argument2);
				primitive.print();
				serialize = new Serializer();
				serialize.updateChoice(choice);
				serialize.serializer(primitive);

				break;
			}
			
			if(choice == 2)
			{
				System.out.println("What value would you like to set?, provide 3 integers");
				argument = scanner.nextInt();	
				argument1 = scanner.nextInt();
				argument2 = scanner.nextInt();
				
				Oref objectReference = new Oref(argument, argument1, argument2);
				objectReference.print();
				serialize = new Serializer();
				serialize.updateChoice(choice);
				serialize.serializer(objectReference);
				break;
			}
			

			if(choice == 3)
			{
				System.out.println("What value would you like to set?, provide 3 integers");
				argument = scanner.nextInt();	
				argument1 = scanner.nextInt();
				argument2 = scanner.nextInt();
				PrimitiveArray primitiveArray = new PrimitiveArray(argument, argument1, argument2);
				primitiveArray.print();
				serialize = new Serializer();
				serialize.updateChoice(choice);
				serialize.serializer(primitiveArray);
				break;
			}
			
			
			if(choice == 4)
			{
				System.out.println("What value would you like to set?, provide an integer");
				argument = scanner.nextInt();	
				argument1 = scanner.nextInt();
				argument2 = scanner.nextInt();
				
				Oarray objectArray = new Oarray(argument, argument1, argument2);
				objectArray.print();
				serialize = new Serializer();
				serialize.updateChoice(choice);
				serialize.serializer(objectArray);
				
				break;
				
			}
			
			
			
			if(choice == 5)
			{
				System.out.println("What value would you like to set?, provide an integer");
				argument = scanner.nextInt();	
				argument1 = scanner.nextInt();
				argument2 = scanner.nextInt();
				
				collection Collection = new collection(argument, argument1, argument2);
				Collection.print();
				serialize = new Serializer();
				serialize.updateChoice(choice);
				serialize.serializer(Collection);
				
				break;
				
			}
			}
			
			
			catch(Exception e)
			{
				System.out.println("Please choose a valid argument");
			}
			//System.out.println("Please choose a valid argument");
			break;
			
		}
			scanner.close();
		
		
		
	}
	
	
}
