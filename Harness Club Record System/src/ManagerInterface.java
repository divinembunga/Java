/* SELF ASSESSMENT
Harness Class: Member variables (8 marks)
All my data members are declared, private and the ones that don't change are marked as private. I also have a constant for the maximum number of uses of a harness.
Comment: 8/8 All my variables that do not change i.e the make and model number are private member variables and I also have a constant maximum number of times 
         a harness can be used.

Harness Class: Harness constructor 1 & constructor 2 (6 marks)
I initialise all the variables using the parameters given and set the other members to reasonable default values.
Comment: 6/6 My first constructor takes in the make, model number, number of times the harness was used,instructors name, the status of isLoaned,and borrowers name and set the values. 
         The second constructor is passed the make, model number and the instructors name and all the member variables are set.

Harness Class: checkHarness method (5 marks)
My method takes an instructor's name as a parameter, and if the harness is not on loan sets the instructor member variable to the given parameter value (assuming the instructor's name is not null/empty). It also resets the number of times the harness was used.
Comment: 5/5 If the instructors name is not null and the harness is not on loan, then the variable instuctorName is set to equal the name passed as the parameter. And the variable timesUsed is reset to zero.

Harness Class: isHarnessOnLoan method (2 marks) 
My method has no parameters and returns the value of the loan status variable.
Comment: 2/2 This method has to parameters passed to it and returns whatever boolean value the variable isLoaned contains.

Harness Class: canHarnessBeLoaned method (4 marks)
My method has no parameters and returns true if the harness is not on loan and if the number of times it was used is less than the maximum allowed number of times.
Comment: 4/4 This method has no parameters,it checks if the harness is on loan using the isHarnessOnLoan() method and if so checks that the number of times the harness was used 
         doesn't exceed the max number and if so returns true, otherwise false.

Harness Class: loanHarness method (6 marks)
My method has a member name as a parameter and it checks if harness can be loaned by using the canHarnessBeLoaned method. If true, it sets the club member value to the parameter value, sets the on loan status to true and increments the number of times used variable.
Comment: 6/6 This method checks that the member name is not null and them ensures that the harness can be loaned using the canHarnessBeLoaned() method. If so it sets the borroweeName value to the member name and sets the isLoan variable to true. And increments the 
         timesUsed variable.
 
Harness Class: returnHarness method (5 marks)
My method has no parameters, checks if the harness is on loan, and if so, changes its on-loan status to false, and resets the club member value.
Comment: 5/5 This method is not passed anything but it checks if the harness is on loan. If it is then it sets the isLoan boolean to false-state that it has been returned and sets the borrowerName to null.

Harness Class: toString method (3 marks)
My method returns a String representation of all the member variables.
Comment: 3/3 The toString method prints the make and model number of the harness.Then it checks if it is on loan,if so, it prints who it is loaned to. It also prints the amount of
         times the harness was used and the name of the instructor that last checked it.

HarnessRecords Class: member variables (3 marks)
I declare the member variable as a private collection of Harnesses 
Comment: 3/3 I create an array list of harnesses and gave it the protected setting in order to avoid receiving the null pointer exception every time I try to add a harness into the
         array list.

HarnessRecords Class: HarnessRecords constructor 1 & 2 (9 marks)
In the first constructor, I set the member variable to null [1 mark]. In the second constructor, I use the Java I/O to read data from a text file I created containing sets of Harness characteristics. I use these set of characteristics to create Harness objects and add them to the collection. 
Comment: 7/9 For the first constructor I initialised the array list but did not set it to null as in my case it kept giving me a null pointer exception. The second constructor was passed a fileReader and read the harness text file and created a collection of harnesses.

HarnessRecords Class: isEmpty method (1 marks)
I return true if the collection is null/empty and, false otherwise.
Comment: 1/1 Yes I return true if the collection is equal to null and false otherwise.

HarnessRecords Class: addHarness method (5 marks)
My method takes a Harness object as a parameter and adds the harness to the collection.
Comment: 5/5 This method does take in a Harness object and adds that object to the collection.

HarnessRecords Class: findHarness method (6 marks)
My method takes a make and model number as parameters. It checks if a harness with such properties exists and if it does it returns harness object, otherwise returns null.
Comment: 6/6 This method does take in the harness make and model number. It loops through the collection until a harness contains the same make and model number and returns that harness, otherwise null.

HarnessRecords Class: checkHarness method (6 marks)
must take instructor name, make and model number as parameters and return a Harness. If a harness with the make and model number exists by using the findHarness method and is not on loan, the Harness method checkHarness is called with the instructor name as a parameter and the updated Harness object is returned. If the harness is not available returns null.
Comment: 6/6 This method takes in the instructor name, make, model number and returns a harness.If a harness with the make and model number exists-findHarness method and is not on loan, the Harness checkHarness method is called with the instructor name as a parameter and the updated harness is returned. If the harness is not available it returns null.

HarnessRecords Class: loanHarness method (7 marks)
My method takes a club member name as a parameter and looks for an available harness by calling the method canHarnessBeLoaned be loaned. If an available harness is found it is loaned by using the Harness method loanHarness with the club member as a parameter, returning the harness. If there's no available harness null is returned.
Comment: 7/7 This method takes in a club member and loops through the collection for a harness that can be loaned (canBeLoaned() method), if found then the harness loanHarness() method is called and that harness is returned, otherwise null is returned.

HarnessRecords Class: returnHarness method (7 marks)
My method takes a make and model number as parameters. It checks if a harness with those properties exists by using the findHarness method. If the found harness is not null, it returns the harness object by using Harness method returnHarness, otherwise returns null.
Comment: 7/7 This method is passed the make and model number of a harness. It finds that harness- findHarness()method, and if the return is not null it calls the Harness returnHarness() method and returns that harness, otherwise returns null.

HarnessRecords Class: removeHarness method (8 marks)
My method takes a make and model number as parameters and check the collection for a harness with those properties and removes it. It returns the harness object if it is found, otherwise returns null.
Comment: 8/8 This method takes the model number and make of a harness.It loops through the collection of harnesses and if one matches with the make and model number it removes that harness and returns it, otherwise it returns null.

GUI (Java main line) (5 marks)
My test class has a menu which implements at least the five points specified using the HarnessRecords class methods.
Comment: 1/5 My addHarness-to record a new purchase of a harness, and Quit-to exit the loop of queries works. However the loaning a harness, checking a harness,returning a harness and removing a harness do not go into their methods after the required input is given, i.e instructors name, harness make, harness model number.
         I tried to debug it but couldn't find the cause of this.
         
TOTAL: 90/100
*/


import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;



public class ManagerInterface {

	public static void main(String[] args)throws IOException{
		// TODO Auto-generated method stub
		FileReader harnessRecords =new FileReader("harness");
		HarnessRecords harnessRecordsFile= new HarnessRecords(harnessRecords);
        boolean finished=false;
        Scanner input= new Scanner(System.in);
        while(!finished){
        	System.out.println("Please enter the number corresponding to what you like to do: "
        			+"\n 1-To record a purchased harness."
        			+"\n 2-To remove a harness from the club"
        			+"\n 3-To record a harness is being checked by an instructor"
        			+"\n 4-To loan a harness to a club member if there is one available"
        			+"\n 5-To return a harness from a club member"
        			+"\n 'Quit' to exit the program");
        	if(input.hasNext("1")){
        		input.nextLine();
        		System.out.println("Enter the harness make");
        		String make=input.nextLine();
        		System.out.println("Enter the harness model number");
        		int modelNumber=input.nextInt();
        		input.nextLine();
        		System.out.println("Enter the instructors name");
        		String instructorName=input.nextLine();
        		Harness purchasedHarness= new Harness(make,modelNumber,instructorName);
        		purchasedHarness=harnessRecordsFile.addHarness(purchasedHarness);
        		if(purchasedHarness != null){
        			System.out.println("Successfully added harness"
        					+"\n "+purchasedHarness.toString());
        		}else{
        			System.out.println("Unable to record the harness");
        		}
        	}
        	else if(input.hasNext("2")){
        		Harness harness=null;
        		input.nextLine();        		
        		System.out.println("Enter the harness make");
        		String make=input.nextLine();
        		System.out.println("Enter the harness model number");
        		int modelNumber=input.nextInt();
        		System.out.println(modelNumber);
        		harness=harnessRecordsFile.removeHarness(make,modelNumber);
        		if(harness!=null){
        			System.out.println("Successfully removed harness"
        					+"\n Make:"+make+"\n Model Number:"+modelNumber);
        		}else{
        			System.out.println("Unable to remove the harness");
        		}
        	}
        	else if(input.hasNext("3")){
        		Harness harness=null;
        		input.nextLine();
        		System.out.println("Enter the harness make");
        		String make=input.nextLine();
        		System.out.println("Enter the harness model number");
        		int modelNumber=input.nextInt();
        		input.nextLine();
        		System.out.println("Enter the instructors name");
        		String instructorName=input.nextLine();
        		harness=harnessRecordsFile.checkHarness(instructorName,make,modelNumber);
        		if(harness!=null){
        			System.out.println("Successfully checked harness"
        					+"\n Make:"+make+"\n Model Number:"+modelNumber);
        					
        		}else{
        			System.out.println("Unable to check the harness");
        		}
        	}
        	else if(input.hasNext("4")){
        		Harness harness=null;
        		input.nextLine();
        		System.out.println("Enter the club member's name");
        		String memberName=input.nextLine();
        		harness=harnessRecordsFile.loanHarness(memberName);
        		if(harness!=null){
        			System.out.println("Successfully loaned the harness to"+memberName);
        					
        		}else{
        			System.out.println("Unable to loan the harness. There are no harness available");
        		}
        	}
        	else if(input.hasNext("5")){
        		input.hasNextLine();
        		System.out.println("Enter the harness make");
        		String make=input.nextLine();
        		System.out.println("Enter the harness model number");
        		int modelNumber=input.nextInt();
        		if(harnessRecordsFile.returnHarness(make,modelNumber)!=null){
        			System.out.println("Successfully returned harness"
        					+"\n Make:"+make+"\n Model Number:"+modelNumber);
        		}else{
        			System.out.println("Unable to return the harness");
        		}
        	}
        	else if(input.hasNext("Quit") || input.hasNext("quit")){
        		System.out.println("Thank you for using the service.Have a nice day");
        		finished=true;
        	}
        	else{
        		System.out.print("Invalid input! Please retry entering the correct instruction.\n");
				System.out.print("Please enter either '1','2','3','4','5' or 'Quit'.");

				input.nextInt();
        	}
        }
        input.close();
	}

}
