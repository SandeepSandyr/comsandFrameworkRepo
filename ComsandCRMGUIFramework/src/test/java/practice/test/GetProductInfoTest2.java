package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;


public class GetProductInfoTest2 {
	
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName , String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']")).sendKeys(brandName,Keys.ENTER);
		String x = "//div[text()='"+productName+"']/../../div[2]/div[1]/div[1]/div[1]";
		String price =driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable{
		ExcelUtility eu = new ExcelUtility();
		int rowcount = eu.getRowCount("product");
		
		Object[][] objArr = new Object[rowcount][2];
		
		for(int i=0; i<rowcount; i++) {
			objArr[i][0]=eu.getDataFromExcel("product", i+1, 0);
			objArr[i][1]=eu.getDataFromExcel("product", i+1, 1);
		}
		
		
		return objArr;

}
}
