package com.qa.test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Pages.HomePage;
import com.qa.testbase.TestBase;
import com.qa.util.Util;

public class HomePageTest extends TestBase {

	HomePage homepage;
	String SheetName="HomePage";



	public HomePageTest() {

		super();
	}

	@BeforeMethod
	public void Setup() {

		Initilization();

		homepage = new HomePage();
	}   

	@Test(priority =1, enabled = true)

	public void ValidateThePageTitle() {

		String ActualPageTitle = HomePage.ValidatePagetiele();

		System.out.println("Prinnt PageTitle"+ActualPageTitle);

		Assert.assertEquals(ActualPageTitle, "Your Store");


	}


//@DataProvider (name="getProduct")
//public Object[][] getProduct(){
//
//	return new Object[][] {
//
//			{"Mackbook"},
//			{"Laptop"},
//		{"Mobile"}
//
//	};
//}


	@DataProvider
	public Object[][]HomePageTestData() throws EncryptedDocumentException, IOException{

		Object[][]data=Util.getTestData(SheetName);

		return data;
	}



	@Test(priority =2 , dataProvider="HomePageTestData")
	 public void doSearchTest(String ProductName) {

		String ActaulHeaderAfterSearch = homepage.doSearch(ProductName);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(ActaulHeaderAfterSearch);

		//Assert.assertEquals(ActaulHeaderAfterSearch, "Search - " +ProductName);
    	Assert.assertEquals(ActaulHeaderAfterSearch, "Search - " + ProductName);

	}

	@AfterMethod
	public void Teardown() throws Exception {


		if(driver !=null) {

			try {
				//driver.close();
				Thread.sleep(2000);
				driver.quit();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Print Error"+e.getMessage());			
		}	
	}
	}

}
