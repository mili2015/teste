package codeChallenge;

import java.util.Scanner;

public class Test1
{

	  public static int FirstFactorial(int num) { 
		  
//	       System.out.println(num);
	       if (num < 1 || num > 18) {
	        return 1;
	       }
	       
	    return num * FirstFactorial(--num);
	    
	  } 
	  
	  public static void main (String[] args) {  
	    // keep this function call here     
	    Scanner s = new Scanner(System.in);
	    String st = "";
	    
	    System.out.print(FirstFactorial(Integer.parseInt(s.nextLine()))); 
	  }  
	

}
