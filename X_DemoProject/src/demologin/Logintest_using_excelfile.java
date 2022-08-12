package demologin;

import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Logintest_using_excelfile {
	
	WebDriver driver;
	
	String [][] data= null;
	
	@DataProvider(name = "inputsfromexcel")
	public String[][] providelogindata() throws BiffException, IOException {
		data = getExcelData();
		return data;
	}
	
	public String[][] getExcelData() throws BiffException, IOException {
		
		FileInputStream excel = new FileInputStream(
				"D:\\API learning 2022 latest\\automation in eclipse\\Login_inputs.xls");
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);	
		int rowcount = sheet.getRows();
		int columncount = sheet.getColumns();
		
		String testdata[][] = new String[rowcount-1][columncount];
		for (int i = 1; i < rowcount; i++) {
			for (int j = 0; j < columncount; j++) {							
				testdata[i-1][j] = sheet.getCell(j, i).getContents();			
		}			
		}	
		return testdata;
	}
	
	@BeforeTest
	public void launchbrowser() {
		System.setProperty("webdriver.chrome.driver", 
				"D:\\API learning 2022 latest\\automation in eclipse\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");		
	}
	
	@AfterTest
	public void closebrowser() {
		driver.quit();	
	}
		
	@Test(dataProvider = "inputsfromexcel")
	public void login(String Uname, String Upassword ) {
			
		WebElement Username = driver.findElement(By.id("txtUsername"));
		Username.sendKeys(Uname);
		
		WebElement Password = driver.findElement(By.id("txtPassword"));
		Password.sendKeys(Upassword);
		
		driver.findElement(By.id("btnLogin")).click();
		
		WebElement spanMessage = driver.findElement(By.id("spanMessage"));
		String errormsg = spanMessage.getText();
		System.out.println("username is (" + Uname + ") & password is (" +Upassword +") = "+ "'" +errormsg+ "'");		
		}	
}
