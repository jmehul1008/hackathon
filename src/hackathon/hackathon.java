package hackathon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class hackathon {


	WebDriver driver = null;
	WebDriverWait wait = null;
	List <WebElement> products;

	@BeforeMethod
	public void OpenBrowser() {
       String browser = "chrome";
        
		if(browser.equalsIgnoreCase("chrome")) {
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Downloads\\Eclipse\\hackathon\\Drivers\\chromedriver.exe");
	       ChromeOptions co = new ChromeOptions();
	       co.addArguments("--disable-infobars");
	       co.addArguments("--disable-notifications");
	       driver = new ChromeDriver(co);
	       driver.manage().window().maximize();
		}
	       else {
	    	   System.setProperty("webdriver.gecko.driver", "C:\\Users\\USER\\Downloads\\Eclipse\\hackathon\\Drivers\\geckodriver.exe");
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--disable-infobars");
	            fo.addPreference("dom.webnotifications.enabled", false);
			    fo.addPreference("geo.enabled", false);
			    fo.addPreference("geo.prompt.testing", false);
		        fo.addPreference("geo.prompt.testing.allow", false);
			    driver = new FirefoxDriver(fo);
			    driver.manage().window().maximize();
	       }
	}
	
	@AfterMethod
	public void quitBrowser() {
		  
		driver.quit();
	}

	//to set the price slider
	public void price() {
		  Actions action = new Actions(driver);
	       wait = new WebDriverWait(driver,30);
		   wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//body/div[2]/div[2]/div[3]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
	       WebElement price = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/ul[1]/li[1]"));
	       action.moveToElement(price).perform();
	       WebElement Slider = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/ul[1]/li[1]/div[2]/div[1]/div[1]/ul[1]/li[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]"));
		   action.dragAndDropBy(Slider, -209,0).build().perform();
	}
	
	//storage type
	public void storage() {
		 Actions action = new Actions(driver);
		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		WebElement storageType = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/div[1]"));
     	action.moveToElement(storageType).perform();
  		WebElement open = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[1]/label[1]"));
      	action.click(open).build().perform();
     }
	
	//remove out of stock things
	public void stock() {
		wait = new WebDriverWait(driver,30);
  		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[contains(text(),'Exclude Out Of Stock')]")));
     	driver.findElement(By.xpath("//label[contains(text(),'Exclude Out Of Stock')]")).click(); 
	}
	
	@Test
	public void urbanLadder() {
		String URL = "https://www.urbanladder.com/";     
        driver.get(URL);
        
        String pagename = "Furniture Online: Buy Home Wooden Furniture Online In India At Best Price - Urban Ladder - Urban Ladder";
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle ,pagename);
       
        //selecting bookshevels
        wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//body/div[1]/div[2]/div[2]/div[3]/div[1]/div[3]/a[4]")));
        driver.findElement(By.xpath("//body/div[1]/div[2]/div[2]/div[3]/div[1]/div[3]/a[4]")).click();
         
       //price
        price();
     
       
       //storage
	    storage();
     	
     	//stock
     	stock();
     	
        //productDetails();
        products  = driver.findElements(By.xpath("//span[@class='name']"));
          for(int i=1;i<=3;i++) {
			String product = products.get(i).getText();
	 System.out.println(product); 
		} 
     	
          /*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	 
     	 String productname=driver.findElement(By.xpath("//*[@id='content']/div[4]/ul/li[1]/div/div[3]/a/div[1]/span")).getText();
    	 System.out.println(productname);
     	 String productprice=driver.findElement(By.xpath("//*[@id='content']/div[4]/ul/li[1]/div/div[3]/a/div[2]/span")).getText();
     	 String value= productprice.substring(1);
     	 System.out.println(value);
     	 
     	String productname1=driver.findElement(By.xpath("//*[@id='content']/div[4]/ul/li[2]/div/div[3]/a/div[1]/span")).getText();
   	    System.out.println(productname1);
    	String productprice1=driver.findElement(By.xpath("//*[@id='content']/div[4]/ul/li[2]/div/div[3]/a/div[2]/span")).getText();
    	String value2= productprice1.substring(1);
    	System.out.println(value2);
    	
    	
    	String productname2=driver.findElement(By.xpath("//*[@id='content']/div[4]/ul/li[3]/div/div[3]/a/div[1]/span")).getText();
   	    System.out.println(productname2);
    	String productprice2=driver.findElement(By.xpath("//*[@id='content']/div[4]/ul/li[3]/div/div[3]/a/div[2]/span")).getText();
    	String value3= productprice2.substring(1);
    	System.out.println(value3);*/
    	
     	 
     	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
