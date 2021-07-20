package operation;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Keywords {

	WebDriver driver;
	public Keywords(WebDriver driver){
		this.driver = driver;
	}
	public void perform(Properties p,String operation,String objectName,String objectType,String value) throws Exception{
		System.out.println("");
		if(operation.equals("CLICK"))
		{
			try
			{
				Thread.sleep(5000);
				driver.findElement(this.getObject(p,objectName,objectType)).click();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(operation.equals("ENTERDATA"))
		{
			try
			{
				driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
		else if(operation.equals("OPENAPPLICATION"))
		{
			try
			{
				System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
				driver.get(p.getProperty(value));
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(operation.equals("CLOSEAPPLICATION"))
		{
			try
			{
				driver.quit();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(operation.equals("GETTEXT"))
		{
			try
		{
				driver.findElement(this.getObject(p,objectName,objectType)).getText();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		}
		else if(operation.equals("MAXIMISE"))
		{
		try
		{
				driver.manage().window().maximize();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		}
		else if(operation.equals("GETTITLE"))
		{
		try
		{
				System.out.println("page title is-->"+driver.getTitle());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		}
		
		else if(operation.equals("IMPLICITWAIT"))
		{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		}
		else if(operation.equals("WAIT"))
		{
		try
		{
				Thread.sleep(5000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		}
		
	}
	private By getObject(Properties p,String objectName,String objectType) throws Exception{
		//Find by xpath
		if(objectType.equalsIgnoreCase("XPATH")){
			
			return By.xpath(p.getProperty(objectName));
		}
		//find by class
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			
			return By.className(p.getProperty(objectName));
			
		}
		//find by name
		else if(objectType.equalsIgnoreCase("NAME")){
			
			return By.name(p.getProperty(objectName));
			
		}
		//Find by css
		else if(objectType.equalsIgnoreCase("CSS")){
			
			return By.cssSelector(p.getProperty(objectName));
			
		}
		//find by link
		else if(objectType.equalsIgnoreCase("LINK")){
			System.out.println("in the link");
			return By.linkText(p.getProperty(objectName));
			
		}
		//find by partial link
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			
			return By.partialLinkText(p.getProperty(objectName));
			
		}else
		{
			throw new Exception("Wrong object type");
		}
	}
}
