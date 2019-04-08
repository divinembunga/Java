// -------------------------------------------------------------------------
/**
 *  Utility class containing validation/evaluation/conversion operations
 *  for prefix and postfix arithmetic expressions.
 *
 *  @author  Divine Mbunga
 *  @version 1/12/15 13:03:48
 */
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Stack; //Data structure used.

public class Arith 
{
  //~ Research ................................................................
  /**
   * Data Structure used: STACK
   * Methods called:
   *  - stack.pop()
   *    Has a running time of Θ(1)- constant time as operation does not contain any loops and 
   *    executes once when it is called.
   *  - stack.push();
   *    Has a running time of Θ(1)- constant time as operation does not contain any loops and 
   *    executes once when it is called.
   */

  //~ Optimality ................................................................
   /**
    * I think that my implementation is optimal as I used a simple data structure API; Stack
    * which contains methods that run in constant time giving an overall low running time and 
    * memory. For the stack the worst case memory usage would be n the number of strings being 
    * pushed into the stack.My implementations contains for loops which all run Θ(n) time and
    * using n memory also. Overall my implementation is mainly based on the size of the input 
    * and that is why I think it is somewhat optimal.
    */

  //~ Validation methods ..........................................................


  /**
   * Validation method for prefix notation.
   *
   * @param prefixLiterals : an array containing the string literals hopefully in prefix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return true if the parameter is indeed in prefix notation, and false otherwise.
   **/
	
	 /**
     * Worst-case asymptotic running time cost: Θ(N)
     *
     * Justification:The function contains majority constant run times but there is a 
     * for loop which would run N times - number of strings as worst case.
     *   
     */	
  public static boolean validatePrefixOrder(String prefixLiterals[])
  {
      int counter = 0;
	  if(prefixLiterals.length <2){
		  return false;
	  }
	  for(int i=0; i<prefixLiterals.length-1; i++){
		  String x = prefixLiterals[i];
		  if(isOperator(x)){
			  counter++;
		  }else if(isNumeric(x)){
			  counter--;
		  }
		  if(counter <0){  //if negative prefix is invalid
			  return false;
		  }
	  }if(counter == 0){
		  return true;
	  }else{
		  return false;
	  }
	  
  }
  
  /**
   * 
   * @param String str
   * @return Whether or not the string is numeric i.e a number
   * 	 
     * Worst-case asymptotic running time cost: Θ(1)
     *
     * Justification: This method only exceutes once, when it is called
     *   
     */
   
  public static boolean isNumeric(String str)
  {
    NumberFormat formatter = NumberFormat.getInstance();
    ParsePosition pos = new ParsePosition(0);
    formatter.parse(str, pos);
    return str.length() == pos.getIndex();
  }



  /**
   * Validation method for postfix notation.
   *
   * @param postfixLiterals : an array containing the string literals hopefully in postfix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return true if the parameter is indeed in postfix notation, and false otherwise.
   **/
  
   /**
   * Worst-case asymptotic running time cost: Θ(N)
   *
   * Justification:The function contains majority constant run times but there is a 
   * for loop which would run N times - number of strings as worst case.
   *   
   */	
  public static boolean validatePostfixOrder(String postfixLiterals[])
  {
      int counter = -1;
	  
	  for(int i=0; i<postfixLiterals.length; i++){
		  String x = postfixLiterals[i];
		  if(isOperator(x)){
			  counter--;
		  }else if(isNumeric(x)){
			  counter++;
		  }
		  if(counter < 0){  //if negative postfix is invalid
			  return false;
		  }
	  }if(counter == 0){
		  return true;
	  }else{
		  return false;
	  }
	  
    
  }


  //~ Evaluation  methods ..........................................................


  /**
   * Evaluation method for prefix notation.
   *
   * @param prefixLiterals : an array containing the string literals in prefix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the integer result of evaluating the expression
   **/
  
   /**
   * Worst-case asymptotic running time cost: Θ(N)
   *
   * Justification:The for loop at worst case will have to go through each string from the
   * array therefore N times, N being the number of strings contained in the array
   *   
   */	
  public static int evaluatePrefixOrder(String prefixLiterals[])
  {
	  Stack<Integer> stack = new Stack<Integer>();
	  int result = 0;
	  for(int i=prefixLiterals.length-1; i>=0; i--){
		  String x = prefixLiterals[i];
		  if(isOperator(x)){
			  //pop two numbers off the stack
			  int b = (int) stack.pop();
			  int a = (int) stack.pop();
			  
			  //carrying desired arithmetic expression
			  switch(x){
			  case "+": result =(a + b);break;
			  case "-": result =(b - a);break;
			  case "*": result =(a * b);break;
			  case "/": result =(b / a);break;
			   
			  }
			  //push result back into the stack
			  stack.push(result);
		  }else{
			  int strToInt = Integer.parseInt(x);
			  stack.push(strToInt);
		  }
	  }
	  return result;
  }

  /**
   * 
   * @param String str
   * @return Whether or not the string is a valid operator "+,-,*,/"
   */
  
  /**
   * Worst-case asymptotic running time cost:Θ(1)
   *
   * Justification:The function will only have one input, therefore be exceuted 
   * once for every time it is called.
   *   
   */
  public static boolean isOperator(String str){
	  
	  if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")){
		  return true;
	  }else{
		  return false;
	  }
  }

  /**
   * Evaluation method for postfix notation.
   *
   * @param postfixLiterals : an array containing the string literals in postfix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the integer result of evaluating the expression
   **/
  
  /**
   * Worst-case asymptotic running time cost: Θ(N)
   *
   * Justification:The for loop at worst case will have to go through each string from the
   * array therefore N times, N being the number of strings contained in the array
   *   
   */
  public static int evaluatePostfixOrder(String postfixLiterals[])
  {
	  Stack<Integer> stack = new Stack<Integer>();
	  int result = 0;
	  for(int i=0; i<postfixLiterals.length; i++){
		  String x = postfixLiterals[i];
		  if(isOperator(x)){
			  //pop two numbers off the stack
			  int b = (int) stack.pop();
			  int a = (int) stack.pop();
			  
			  //carrying desired arithmetic expression
			  switch(x){
			  case "+": result =(a + b);break;
			  case "-": result =(a - b);break;
			  case "*": result =(a * b);break;
			  case "/": result =(a / b);break;
			   
			  }
			  //push result back into the stack
			  stack.push(result);
		  }else {
			  int strToInt = Integer.parseInt(x);
			  stack.push(strToInt);
		  }
	  }
	  return result;
  }


  //~ Conversion  methods ..........................................................


  /**
   * Converts prefix to postfix.
   *
   * @param prefixLiterals : an array containing the string literals in prefix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the expression in postfix order.
   **/
  
  /**
   * Worst-case asymptotic running time cost: Θ(N)
   *
   * Justification:The for loop at worst case will have to go through each string from the
   * array therefore N times, N being the number of strings contained in the array
   *   
   */
  public static String[] convertPrefixToPostfix(String prefixLiterals[])
  {
	Stack<String> stack = new Stack<String>();
	String tmp = null;
	for(int i=prefixLiterals.length-1; i>=0; i--){
		String x = prefixLiterals[i];
		if(isOperator(x)){
			String a = stack.pop();
			String b = stack.pop();
			//forming a postfix format with the strings
			tmp = a+" "+b+" "+x;
			stack.push(tmp);
		}else{
			stack.push(x);
		}
		
	}
	//converting strings to a string array
	String[]result = tmp.split(" ");
	   
    return result;
  }


  /**
   * Converts postfix to prefix.
   *
   * @param prefixLiterals : an array containing the string literals in postfix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the expression in prefix order.
   **/
  
  /**
   * Worst-case asymptotic running time cost: Θ(N)
   *
   * Justification:The for loop at worst case will have to go through each string from the
   * array therefore N times, N being the number of strings contained in the array
   *   
   */
  public static String[] convertPostfixToPrefix(String postfixLiterals[])
  {
	Stack<String> stack = new Stack<String>();
	String tmp = null;
    for(int i=0; i<postfixLiterals.length; i++){
    	String x = postfixLiterals[i];
    	if(isOperator(x)){
    		String a = stack.pop();
    		String b = stack.pop();
    		//concatenating strings to prefix format
    		tmp = x+" "+b+" "+a;
    		stack.push(tmp);
    	}else{
    		stack.push(x);
    	}
    }
    
    String[]result = tmp.split(" ");
   
    return result;
  }

  /**
   * Converts prefix to infix.
   *
   * @param infixLiterals : an array containing the string literals in prefix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the expression in infix order.
   **/
  
  /**
   * Worst-case asymptotic running time cost: Θ(N)
   *
   * Justification:The for loop at worst case will have to go through each string from the
   * array therefore N times, N being the number of strings contained in the array
   *   
   */
  public static String[] convertPrefixToInfix(String prefixLiterals[])
  {
	 Stack<String> stack = new Stack<String>();
	 String tmp = null;
	 for(int i=prefixLiterals.length-1; i>=0; i--){
		 String x = prefixLiterals[i];
		 if(isOperator(x)){
			 String a = stack.pop();
			 String b = stack.pop();
			 //concatenating strings to form infix
			 tmp = "("+" "+a+" "+x+" "+b+" "+")";
			 stack.push(tmp);
		 }else{
			stack.push(x);
		 }
			
	}
	    //converting string to string array
		String[]result = tmp.split(" ");
		   
	    return result;
  }

  /**
   * Converts postfix to infix.
   *
   * @param infixLiterals : an array containing the string literals in postfix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the expression in infix order.
   **/
  
  /**
   * Worst-case asymptotic running time cost: Θ(N)
   *
   * Justification:The for loop at worst case will have to go through each string from the
   * array therefore N times, N being the number of strings contained in the array
   *   
   */
  public static String[] convertPostfixToInfix(String postfixLiterals[])
  {
	  Stack<String> stack = new Stack<String>();
		String tmp = null;
		int count=0;
	    for(int i=0; i<postfixLiterals.length; i++){
	    	String x = postfixLiterals[i];
	    	if(isOperator(x)){
	    		String a = stack.pop();
	    		String b = stack.pop();
	    		//form the infix format
	    		if(count==0){
	    			tmp = "("+" "+b+" "+x+" "+a+" "+")";
	    		}else{
	    			tmp = "("+" "+b+" "+x+" "+a+" "+")";
	    		}
	    		count++;
	    		stack.push(tmp);
	    		
	    	}else{
	    		stack.push(x);
	    	}
	    }
	    //converting string to string array
	    String[]result = tmp.split(" ");
	   
	    return result;
  }

	
}
