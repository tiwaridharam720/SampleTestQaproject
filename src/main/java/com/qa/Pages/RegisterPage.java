package com.qa.Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.testbase.TestBase;
import com.qa.util.Util;

public class RegisterPage extends TestBase{

	
	
	
	@FindBy(css="#input-firstname")
	WebElement firstname;

	@FindBy(xpath ="//input[@id='input-lastname']")
	WebElement lastname;

	@FindBy(xpath ="//input[@id='input-email']")
	WebElement email;

	@FindBy(xpath ="//input[@id='input-telephone']")
	WebElement telephone;

	@FindBy(xpath ="//input[@id='input-password']")
	WebElement password;

	@FindBy(xpath ="//input[@id='input-confirm']")
	WebElement confirm;


	@FindBy(xpath ="//label[normalize-space()='Yes']")
	WebElement Yes;


	@FindBy(xpath ="//input[@value='0']")
	WebElement No;

	@FindBy(xpath="//input[@name='agree']")
	WebElement Policy;

	String ContinueButtonXPath ="//input[@value='Continue']";
	
	






	public RegisterPage() {

		PageFactory.initElements(driver, this);
    }


	public String ValidateRigsterPageTitle() {

		String RigesterPageTitel = driver.getTitle();

		System.out.println(RigesterPageTitel);


		return RigesterPageTitel;
	}

	
     

	public LoginPage doRigester(String Fname, String LName, String Email,String Telephone, String Password, String Confirmpassword ) throws IOException {
        
		firstname.clear();
		firstname.sendKeys(Fname);
		lastname.clear();
		lastname.sendKeys(LName);
		email.clear();
		email.sendKeys(Email);
		telephone.clear();
		telephone.sendKeys(Telephone);
		password.clear();
		password.sendKeys(Password);
		confirm.clear();
		confirm.sendKeys(Confirmpassword);

		String Radiobutton = driver.findElement(By.xpath("//label[normalize-space()='Yes']")).getText();

		System.out.println("Print Test of Radiobutton"+Radiobutton);

	   if(Radiobutton.contains("Yes")) {
          
		   Yes.click();
		   
	   }else {
           No.click();
	   }

	   Policy.click();
        // Util.isAlertPresent();
	   
	   
	   driver.findElement(By.xpath(ContinueButtonXPath)).click();
	   
	   String ErrMessage ="";
	   
	   
//        if(ErrMessage.contains(" ")){
//          Util.ErrorMessageHandeler();  
//          System.out.println("Test Cases has been "+"Pass");
//      // Util.TakeScreenshot();
//        }else {
//        	
//        	Util.ErrorMessageHandeler();  
//            System.out.println("Test Cases has been "+"Fail");
//          	
//        }

		return new LoginPage();
	}

}
