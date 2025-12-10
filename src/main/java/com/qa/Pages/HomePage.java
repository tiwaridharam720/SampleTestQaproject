package com.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.qa.testbase.TestBase;

public class HomePage extends TestBase {

	private String SearchTextFieldXpath ="//input[@placeholder='Search']";
	private String Searchbutton ="//button[@class='btn btn-default btn-lg']";
	private String searchPageheader ="div[id='content'] h1";
	private String USerRagister ="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']";

	public HomePage() {

		PageFactory.initElements(driver, this);
	}

	public static String ValidatePagetiele() {

		return driver.getTitle();

		}

	public String doSearch(String productName) {

        driver.findElement(By.xpath(SearchTextFieldXpath)).clear();
		driver.findElement(By.xpath(SearchTextFieldXpath)).sendKeys(productName);

		driver.findElement(By.xpath(Searchbutton)).click();

		String HeaderXpath= driver.findElement(By.cssSelector(searchPageheader)).getText();

		System.out.println("PageHeader is "+HeaderXpath);


	   return HeaderXpath;
	}

	public RegisterPage NavigateToRigesterPage() {

		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();


		driver.findElement(By.xpath(USerRagister)).click();

		return new RegisterPage();
	}
}
