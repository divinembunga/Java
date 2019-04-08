import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
//-------------------------------------------------------------------------
/**
*  Test class for Arith Class
*
*  @author  Divine Mbunga
*  @version 1/12/15 13:03:48
*/

@RunWith(JUnit4.class)
public class ArithTest {

	 //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Arith();
    }
    
    //~ Public Methods ........................................................
	
	@Test
	public void testValidatePrefixOrder(){
		String[] prefixOne= new String[5];
		String[] prefixTwo  = new String[5];
		String[] prefixThree    = new String[5];
		String[] prefixFour   = new String[5];
		String[] prefixFive =new String[1];
		
		prefixOne[0] = "-";
		prefixOne[1] = "+";
		prefixOne[2] = "8";
		prefixOne[3] = "1";
		prefixOne[4] = "5";
		
		prefixTwo[0] = "*";
		prefixTwo[1] = "3";
		prefixTwo[2] = "-";
		prefixTwo[3] = "1";
		prefixTwo[4] = "2";
		
		prefixThree[0] = "1";
		prefixThree[1] = "-";
		prefixThree[2] = "a";
		prefixThree[3] = "2";
		prefixThree[4] = "3";
		
		prefixFour[0] = "+";
		prefixFour[1] = "2";
		prefixFour[2] = "3";
		prefixFour[3] = "-";
		prefixFour[4] = "4";
		
		prefixFive[0] = "+";
		
		assertTrue("Validating prefix format",Arith.validatePrefixOrder(prefixOne));
		assertFalse("Proving this prefix is invalid",Arith.validatePrefixOrder(prefixThree));
		assertTrue("Validating prefix format",Arith.validatePrefixOrder(prefixTwo));
		assertFalse("Proving this prefix is invalid",Arith.validatePrefixOrder(prefixFour));
		assertFalse("Proving this prefix is invalid",Arith.validatePrefixOrder(prefixFive));
	}
	
	@Test
	public void testValidatePostfixOrder(){
		String[] postfixOne= new String[5];
		String[] postfixTwo  = new String[5];
		String[] postfixThree    = new String[5];
		
		postfixOne[0] = "-";
		postfixOne[1] = "+";
		postfixOne[2] = "8";
		postfixOne[3] = "1";
		postfixOne[4] = "5";
		
		postfixTwo[0] = "1";
		postfixTwo[1] = "2";
		postfixTwo[2] = "a";
		postfixTwo[3] = "-";
		postfixTwo[4] = "*";
		
		postfixThree[0] = "1";
		postfixThree[1] = "2";
		postfixThree[2] = "3";
		postfixThree[3] = "-";
		postfixThree[4] = "*";
		
		assertFalse("Proving this prefix is invalid",Arith.validatePostfixOrder(postfixOne));
		assertFalse("Proving this prefix is invalid",Arith.validatePostfixOrder(postfixTwo));
		assertTrue("Validating prefix format",Arith.validatePostfixOrder(postfixThree));
	}
	
	@Test
	public void testEvaluatePrefixOrder(){
		String[] p = new String[5];
		String[] p1 = new String[5];
		p1[0] = "/";
		p1[1] = "+";
		p1[2] = "1";
		p1[3] = "2";
		p1[4] = "3";
		
		p[0] = "*";
		p[1] = "-";
		p[2] = "1";
		p[3] = "2";
		p[4] = "3";
		
		
		
		assertEquals("Evaluating expression *-123 = -3",Arith.evaluatePrefixOrder(p),-3);
		assertEquals("Evaluating expression /+123 = 1",Arith.evaluatePrefixOrder(p1),1);
	}
	
	@Test
	public void testEvaluatePostfixOrder(){
		String[] p = new String[5];
		String[] p1 = new String[5];
		p1[0] = "3";
		p1[1] = "1";
		p1[2] = "2";
		p1[3] = "+";
		p1[4] = "/";
		
		p[0] = "3";
		p[1] = "1";
		p[2] = "2";
		p[3] = "-";
		p[4] = "*";
		
		assertEquals("Evaluating expression 312-* = -3",Arith.evaluatePostfixOrder(p),-3);
		assertEquals("Evaluating expression 312+/ = 1",Arith.evaluatePostfixOrder(p1),1);
	}
	
	@Test
	public void testConvertPrefixToPostfix(){
		String[]prefix = new String[5];
		String[]expectedPostfix = new String[5];
		
		prefix[0] = "*";
		prefix[1] = "3";
		prefix[2] = "-";
		prefix[3] = "1";
		prefix[4] = "2";
		
		expectedPostfix[0] = "3";
	    expectedPostfix[1] = "1";
	    expectedPostfix[2] = "2";
	    expectedPostfix[3] = "-";
	    expectedPostfix[4] = "*";
		
	    assertEquals(expectedPostfix,Arith.convertPrefixToPostfix(prefix));
	}
	
	@Test
	public void testConvertPostfixToPrefix(){
		String[]postfix = new String[5];
		String[]expectedPrefix = new String[5];
		
		postfix[0] = "3";
		postfix[1] = "1";
		postfix[2] = "2";
		postfix[3] = "-";
		postfix[4] = "*";
		
		expectedPrefix[0] = "*";
	    expectedPrefix[1] = "3";
	    expectedPrefix[2] = "-";
	    expectedPrefix[3] = "1";
	    expectedPrefix[4] = "2";
		
	    assertEquals(expectedPrefix,Arith.convertPostfixToPrefix(postfix));
	}
	
	@Test
	public void textConvertPostfixToInfix(){
		String[] postfix = new String[5];
		String[] infix = new String[9];
		
		postfix[0] = "3";
		postfix[1] = "1";
		postfix[2] = "2";
		postfix[3] = "-";
		postfix[4] = "*";
		
		infix[0] ="(";
		infix[1] ="3";
		infix[2] ="*";
		infix[3] ="(";
		infix[4] ="1";
		infix[5] ="-";
		infix[6] ="2";
		infix[7] =")";
		infix[8] =")";
		
		assertEquals(infix,Arith.convertPostfixToInfix(postfix));
	} 
	
	@Test
	public void textConvertPrefixToInfix(){
		String[] prefix = new String[5];
		String[] infix = new String[9];
		
		prefix[0] = "*";
		prefix[1] = "-";
		prefix[2] = "1";
		prefix[3] = "2";
		prefix[4] = "3";
		
		infix[0] ="(";
		infix[1] ="(";
		infix[2] ="1";
		infix[3] ="-";
		infix[4] ="2";
		infix[5] =")";
		infix[6] ="*";
		infix[7] ="3";
		infix[8] =")";
		
		assertEquals(infix,Arith.convertPrefixToInfix(prefix));
	} 

}
