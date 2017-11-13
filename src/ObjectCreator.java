
import java.util.*;



public class ObjectCreator {

	
	
	
	public static void main(String [] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		
		
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
			first = scanner.nextLine();
			try {
				
				
				
			
			int choice = Integer.parseInt(first);
			
			if(choice == 1)
			{
				System.out.println("What value would you like to set?, provide 3 integers");
				argument = scanner.nextInt();	
				argument1 = scanner.nextInt();
				argument2 = scanner.nextInt();
				primitive primitive = new primitive(argument, argument1, argument2)
;				break;
			}
			
			if(choice == 2)
			{
				System.out.println("What value would you like to set?, provide 3 integers");
				argument = scanner.nextInt();	
				argument1 = scanner.nextInt();
				argument2 = scanner.nextInt();
				
				Oref oref = new Oref(argument, argument1, argument2);
				break;
			}
			

			if(choice == 3)
			{
				System.out.println("What value would you like to set?, provide 3 integers");
				argument = scanner.nextInt();	
				argument1 = scanner.nextInt();
				argument2 = scanner.nextInt();
				PrimitiveArray primitivearray = new PrimitiveArray(argument, argument1, argument2);
				
				break;
			}
			
			
			if(choice == 4)
			{
				System.out.println("What value would you like to set?, provide an integer");
				argument = scanner.nextInt();	
				
				
				
				break;
				
			}
			
			
			
			if(choice == 5)
			{
				System.out.println("What value would you like to set?, provide an integer");
				argument = scanner.nextInt();	
				
				
				
				break;
				
			}
			}
			
			catch(Exception e)
			{
			
			}
			System.out.println("Please choose a valid argument");
			
			
		}
			
		
		
		
		
	}
	
	
}
