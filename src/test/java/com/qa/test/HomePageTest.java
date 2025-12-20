package com.qa.test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Pages.HomePage;
import com.qa.testbase.TestBase;
import com.qa.util.Util;
import org.apache.log4j.Logger;
public class HomePageTest extends TestBase {

	HomePage homepage;
	
	String SheetName="HomePage";

    Logger log = Logger.getLogger(HomePageTest.class);   
	
	public HomePageTest() {

		super();
	}

	@BeforeMethod
	public void Setup() {

		
		log.info("****************************Starting the Test Case execustion***********");
		Initilization();

		homepage = new HomePage();
	}   

	@Test(priority =1, enabled = false)

	public void ValidateThePageTitle() {
		log.info("****************************** starting test case *****************************************");
		log.info("****************************** Your Store *****************************************");
	
		
		String ActualPageTitle = HomePage.ValidatePagetiele();

		System.out.println("Prinnt PageTitle"+ActualPageTitle);

		Assert.assertEquals(ActualPageTitle, "Your Store");
		log.info("****************************** ending test case *****************************************");
		log.info("****************************** Your Store *****************************************");



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



	@Test(priority =2 , dataProvider="HomePageTestData", enabled =true)
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
    	log.info("******************Info Search Product********************");
    	log.debug("*********************Search Product********************");
    	log.fatal("*********************Fatal Search Product**************");
    	log.warn("**********************Waring Search Product*************");

	}

	@AfterMethod
	public void Teardown() throws Exception {


		if(driver !=null) {

			try {
				//driver.close();
				Thread.sleep(2000);
				driver.quit();
				Thread.sleep(2000);
				
				log.info("****************************** Browser is closed *****************************************");

			} catch (InterruptedException e) {
				System.out.println("Print Error"+e.getMessage());			
		}	
	}
	}

}
