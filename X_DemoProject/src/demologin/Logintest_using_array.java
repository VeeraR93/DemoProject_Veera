package demologin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Logintest_using_array {
	
	// try the next to provide inputs from excel and using before & after test methods
	
	String [][] data= {
			{"Admin","admin"},			
			{"Admin1","admin123"},
			{"Admin1","admin"},
			{"",""}
	};
	
	@DataProvider(name = "matrixlogindata")
	public String[][] providelogindata() {
		return data;
	}
	
	
	@Test(dataProvider = "matrixlogindata")
	public void launchbrowser(String Uname, String Upassword ) {
		System.setProperty("webdriver.chrome.driver", 
				"D:\\API learning 2022 latest\\automation in eclipse\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		WebElement Username = driver.findElement(By.id("txtUsername"));
		Username.sendKeys(Uname);
		
		WebElement Password = driver.findElement(By.id("txtPassword"));
		Password.sendKeys(Upassword);
		
		driver.findElement(By.id("btnLogin")).click();
		
		//WebElement spanMessage = driver.findElement(By.id("spanMessage"));
		WebElement spanMessage = driver.findElement(By.xpath("//span[@id='spanMessage']"));
		String errormsg = spanMessage.getText();
		System.out.println(errormsg);
		driver.quit();
		}

}
