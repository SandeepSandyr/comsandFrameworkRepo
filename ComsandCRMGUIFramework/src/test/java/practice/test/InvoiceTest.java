package practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.Baseclass;

public class InvoiceTest extends Baseclass {
	
	@Test
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		String actTiltle = driver.getTitle();
		Assert.assertEquals(actTiltle, "Login");
		System.out.println("1");
		System.out.println("2");
		System.out.println("3");
		System.out.println("4");
		
	}
	
	@Test
	public void createInvoiceTestWithContactTest() {
		System.out.println("execute createInvoiceTestWithContactTest");
		System.out.println("1");
		System.out.println("2");
		System.out.println("3");
		System.out.println("4");
		
	}

}
