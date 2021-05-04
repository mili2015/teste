package geral;

import com.sun.jna.Native;

public class DLLSintegra
{
	static  
	{  
		Native.register("DllInscE32");  
	}   
	public static native int ConsisteInscricaoEstadual(String ie, String uf); 
}
