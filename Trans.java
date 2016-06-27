package translator;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class Trans {
	static WebDriver driver=new FirefoxDriver();
	static String url= "https://www.bing.com/translator";
	static String csvFile = "/home/swarnimagupta/Desktop/demo.csv";
	static String line = "";
	static String[] country;
	static String[] res=new String[2];
	
	public static String text() throws InterruptedException                     //UI performing the translation
	{
		country = line.split(",");
	    driver.findElements(By.className("dropdownArrow")).get(0).click();
	    List<WebElement> list1 = driver.findElements(By.tagName("td")); 
	    int p=0;	
	    while(!(list1.get(p).getText().equals(country[0]))){
				p+=1;}
		Thread.sleep(2000);
		list1.get(p).click();
	    driver.findElement(By.id("srcText")).sendKeys(country[2]);
	    Thread.sleep(1000);
	    driver.findElements(By.className("dropdownArrow")).get(1).click();
	    List<WebElement> list = driver.findElements(By.tagName("td")); int j=0;	
	   while(!(list.get(j).getText().equals(country[1]))){
			j+=1;
		}
		Thread.sleep(2000);
		list.get(j).click();
		Thread.sleep(2000);
		    String s1=driver.findElement(By.id("destText")).getText();
	    return(s1);
	}
	
	public static String[] readText()                             //reading from CSV file
	{
        int i=0,o=0;;
        
		BufferedReader br = null;
		
		driver.get(url);
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
			if(i>=1)	
			{
				res[o]=text();
				o++;
			}
			i++;
		
			 clear(driver);
			}
			driver.quit();
			
			}
		catch(Exception e){}
		return res;
	}
public static void clear(WebDriver d)                                               //clear function to reset text field
{
	d.findElement(By.id("srcText")).clear();
}
	
}
