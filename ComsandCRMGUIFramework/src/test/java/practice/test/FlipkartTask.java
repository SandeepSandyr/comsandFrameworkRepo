package practice.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartTask {

	public static void main(String[] args) throws IOException, Throwable {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
		WebElement emailTF = driver.findElement(By.name("email"));
		wait.until(ExpectedConditions.elementToBeClickable(emailTF));
		emailTF.sendKeys("sandeepkiran001@gmail.com");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("standaraba");
		driver.findElement(By.id("signInSubmit")).click();
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone15pro", Keys.ENTER);
		driver.findElement(By.xpath("//span[text()='Apple iPhone 15 Pro (128 GB) - Natural Titanium']")).click();
		
		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus12", Keys.ENTER);
		driver.findElement(By.xpath("//span[text()='OnePlus 12 (Flowy Emerald, 16GB RAM, 512GB Storage)']")).click();
		
		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("pixel8", Keys.ENTER);
		driver.findElement(By.xpath("//span[text()='Pixel 8 5G (Obsidian, 128 GB) (8 GB RAM)']")).click();
		
		driver.close();
		Set<String> allWin = driver.getWindowHandles();
		
		for (String win : allWin) {
			driver.switchTo().window(win);
			String pro = driver.findElement(By.id("title")).getText();
			System.out.println(pro);
			driver.findElement(By.xpath("//span[text()=' In stock ']/../../../..//input[@id='add-to-cart-button']")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("attach-close_sideSheet-link")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("nav-cart")).click();
			WebElement proImg = driver.findElement(By.xpath("//img[contains(@alt,'"+pro+"')]"));
			File src = proImg.getScreenshotAs(OutputType.FILE);
			File dest = new File("./screenshot/pro_"+pro+"_.png");
			FileUtils.copyFile(src, dest);	
		}
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File tempss = ts.getScreenshotAs(OutputType.FILE);
		File permss = new File("./screenshot/page.png");
		FileUtils.copyFile(tempss, permss);
		
		driver.quit();

	}
	}
