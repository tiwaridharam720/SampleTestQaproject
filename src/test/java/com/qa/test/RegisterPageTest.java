



package com.qa.test;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Pages.HomePage;
import com.qa.Pages.LoginPage;
import com.qa.Pages.RegisterPage;
import com.qa.testbase.TestBase;
import com.qa.util.Util;

/**
 *    Author Name Dharam 
 */
public class RegisterPageTest extends TestBase {

	HomePage Homepage;
	LoginPage loginP;
	RegisterPage registerPage;
	String SheetName ="RegisterData";
	
	Logger log = Logger.getLogger(RegisterPageTest.class);	
	
	@FindBy(how=How.XPATH, using ="//*[@id='account']/div/label")
	private List<WebElement> PageLabelLIST;
	
	
	


	public RegisterPageTest() {

		super();
	}

	@BeforeMethod
	public void Setup() {
		log.info("******* Browser Opening *****");
		Initilization();
		log.debug(eventlistner);;
		Homepage = new HomePage();
		
		
		//Util.switchToFrame();
		registerPage =Homepage.NavigateToRigesterPage();
		log.info("******* Register user Details********");
		log.warn("Error is coming ");
		log.fatal("Fatal Error message");
	}
		@Test(priority =1,enabled=true)
	public void ValidateRigesterPageTitle() {


		String RegisterPageTitel = registerPage.ValidateRigsterPageTitle();

		System.out.println("Ragister Page titel" +RegisterPageTitel);

		Assert.assertEquals(RegisterPageTitel, "Register Account");
		log.debug(RegisterPageTitel);

	}

//	@DataProvider(name="RegisterData")
//	public Object[][] getRegisteration(){
//
//		return new Object[][] {
//
//			{"Fname",  "LName",  "tiwaridharam720@gmail.com", "9354984729",  "Dms@test1",  "Dms@test1"},
//			{"Fname",  "LName",  "tiwaridharam57@gmail.com", "7701815659",  "Dms@test1",  "Dms@test1"}
//		};
//	}

		@Test(priority=2)
		 public void validateAllfieldLabel() {
			    
			    
			List <WebElement> LabelList = driver.findElements(By.xpath("//div[@id='account-register']//a[@class='list-group-item']"));		
			int Total_Labelcnt;
				try {
					Total_Labelcnt = LabelList.size();
					System.out.println("Totatl Number of Element are ........" +Total_Labelcnt);
					
					 for(int i=0;i<Total_Labelcnt;i++) {
						   
						   String LabelName_Of_RegisterPage=LabelList.get(i).getText();
						   
						   System.out.println("Print Register Page label Name is : ...." +LabelName_Of_RegisterPage);
						   
						   if(LabelName_Of_RegisterPage.contains("Login")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Login");
						   }else if(LabelName_Of_RegisterPage.contains("Register")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Register");
						   }else if(LabelName_Of_RegisterPage.contains("Forgotten")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Forgotten Password");
						   }else if(LabelName_Of_RegisterPage.contains("Account")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "My Account");
						   }else if(LabelName_Of_RegisterPage.contains("Address")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Address Book");
						   }else if(LabelName_Of_RegisterPage.contains("Wish")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Wish List");
						   }else if(LabelName_Of_RegisterPage.contains("Order")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Order History");
						   }else if(LabelName_Of_RegisterPage.contains("Downloads")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Downloads");
						   }else if(LabelName_Of_RegisterPage.contains("Recurring")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Recurring payments");
						   }else if(LabelName_Of_RegisterPage.contains("Reward")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Reward Points");
						   }else if(LabelName_Of_RegisterPage.contains("Returns")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Returns");
						   }else if(LabelName_Of_RegisterPage.contains("Transactions")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Transactions");
						   }else if(LabelName_Of_RegisterPage.contains("Newsletter")) {
							   Assert.assertEquals(LabelName_Of_RegisterPage, "Newsletter");
						   }else {
							   System.out.println("List Has been not found");
						   }
					   }
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			    log.debug(LabelList+ "Validation");
			    
			   
			  
			  
			  
			  
			  
		  }
	
	@DataProvider
	public Object[][] getRegisterTestdata() throws EncryptedDocumentException, IOException{
		
		Object[][] data= Util.getTestData(SheetName);
	
		return data;
	}

	@Test(priority=3, dataProvider="getRegisterTestdata", enabled=true)

	public void doRegisterTest(String Fisrtname, String Lastname, String Emaild,String PhoneNUmber, String Password, String ConfirmPassword) {

        
		
		
		try {
			loginP=registerPage.doRigester(Fisrtname, Lastname, Emaild,PhoneNUmber, Password, ConfirmPassword);
			
			Assert.assertEquals(Util.ErrorMessageHandeler(), "Warning: E-Mail Address is already registered!");
			log.info("RegisterTest info");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        

	}

	
		
	@AfterMethod
	public void teardown() {
		
		
		driver.quit();
	}
}
