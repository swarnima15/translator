package translator;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class test {
	static String[] a=null;
	static String[] b=null;
	
	@BeforeTest
	public void get()
	{
	
	 a=Trans.readText();
     try {
		b=token.getText();
	} catch (IOException e) {
		
		e.printStackTrace();
	} catch (ParseException e) {
		
		e.printStackTrace();
	}	
     }
	
	@DataProvider(name="objects")
	public static Object[][] getObjects(){
		
		Object[][] objects= new Object[a.length][2];
		for(int i=0;i<a.length;i++){
			objects[i][0]= a[i];
			objects[i][1]=b[i];
		}
		return objects;
	}
	
@Test(dataProvider="objects")
public void check(String first,String second)
{
	System.out.println(first);
	System.out.println(second);
	Assert.assertEquals(first,second);
}
}
