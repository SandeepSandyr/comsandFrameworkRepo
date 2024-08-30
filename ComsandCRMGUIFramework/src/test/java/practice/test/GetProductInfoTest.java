package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class GetProductInfoTest {
	
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName , String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']")).sendKeys(brandName,Keys.ENTER);
		String x = "//div[text()='"+productName+"']/../../div[2]/div[1]/div[1]/div[1]";
		String price =driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] getData(){
		
		Object[][] objArr = new Object[3][2];
		objArr[0][0]="iphone15";
		objArr[0][1]="Apple iPhone 15 (Black, 128 GB)";
		
		objArr[1][0]="iphone15";
		objArr[1][1]="Apple iPhone 15 (Blue, 128 GB)";
		
		objArr[2][0]="iphone15";
		objArr[2][1]="Apple iPhone 15 (Pink, 128 GB)";
		
		return objArr;

}
}
