/**
 * 
 */
package document.comparator.utils;

/**
 * @author Mircea Sirghi
 *
 */
public class Comparison {
	public static int compareTwoNumbers(int a, int b)
	{
		int retVal = 2;
		
		if(a == b)
			retVal = 0;	
		else if(a < b)
			retVal = -1;
		else if(a > b)
			retVal = 1;
		
		return retVal;
	}
}
