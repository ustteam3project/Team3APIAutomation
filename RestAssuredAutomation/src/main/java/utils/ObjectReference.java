package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.restassured.RestAssured;

public class ObjectReference {
	
	Properties objpro;
	
	public  ObjectReference() throws IOException
	{
		String path=System.getProperty("user.dir");
		File fi = new File(path+"/inputs/dev.properties");
	    FileInputStream fis = new FileInputStream(fi);	    
	    objpro = new Properties();	    
	    objpro.load(fis);	
	}
	
	public String getBaseURI()
	{
		return objpro.getProperty("ServerURL");
	}	
	

	

}
