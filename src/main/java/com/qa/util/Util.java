package com.qa.util;





import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.util.Timeout;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.testbase.TestBase;

public class Util extends TestBase{
	
	
	
	public static String ErrorMessage="";

	//private static WebDriver driver;
	
	public Util(WebDriver driver) {

		this.driver = driver;

		//this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//setobj_dashboard((new dashboard()));
		
	}


	public static void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	//Alert Handle 
	
	public static boolean isAlertPresent() {
		
		boolean presentflag=false;
		
		try {
			Alert alert= driver.switchTo().alert();
			
			String AlertText=alert.getText();
			
			System.out.println("Print Alert Text is"+ AlertText);
			
			alert.accept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return presentflag;
	}
	
	//Explixit wait 
	
	  public static void explicitwait(String Xpath) {
		  
		  WebDriverWait wait = new WebDriverWait(driver, 2);
		  
		  WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
		  element.click();
	  }
	
	
   // Test data extract from Testdata.xls
	public static String TEST_DATA_FILE_PATH ="C:\\Users\\LENOVO\\eclipse-workspace\\SampleQA.TestProject\\src\\main\\java\\com\\qa\\testdata\\TestData.xls";
    static Workbook book;
	static Sheet sheet;
	
	public static Object[][]getTestData(String SheetName) throws EncryptedDocumentException, IOException{
		
		FileInputStream inputfile= new FileInputStream(TEST_DATA_FILE_PATH); 
		
		book=WorkbookFactory.create(inputfile);
		
		sheet=book.getSheet(SheetName);
		
		Object[][]data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			
  			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				
				System.out.println(data[i][j]);
				
				
			}
			
			
		}
		return data;
		
		
	}

	
    //Take Screenshot
	
	
	public static void TakeScreenshot() {
		
    
		String ScreenshotLocation="C:\\Users\\LENOVO\\eclipse-workspace\\SampleQA.TestProject\\ScreenShot\\element_screenshot.png";
		
		try {
			File TakeScreehsotFile= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(TakeScreehsotFile, new File(currentDir+"/ScreenShot/"+System.currentTimeMillis()+".png"));
			
			System.out.println("Screen shot capture on the Path "+TakeScreehsotFile);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block   
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		  
		
	}
	
	//Handle Error Messege
	
	public static boolean ErrorMessageHandeler() {
		
		
		
		boolean flag = false;
		
		String ErrorMessageXpath ="//div[@class='alert alert-danger alert-dismissible']";
		
		String ErroemssageCssSelector=".alert.alert-danger.alert-dismissible";
		
		
		
		String errorMessageONeString =driver.findElement(By.xpath(ErroemssageCssSelector)).getText();
		//String ErrorElement = driver.findElement(By.xpath(ErrorMessageXpath)).getText();
		
		//String ErrorElement1= driver.findElement(By.cssSelector(ErroemssageCssSelector)).getText();
		//System.out.println("Print Error Message"+ErrorElement);
		
		
		System.out.println("Print Error Message is ! : "+errorMessageONeString);
		if(!errorMessageONeString.contains("Warning")) {
		
			
			
		System.out.println("Test cases has been Sussecc"+errorMessageONeString);
		
		  TakeScreenshot();
			
		}else if(errorMessageONeString.contains("Warning")) {
			
			System.out.println("Print Error Message" +"TestCase Status fails : Status is ! "   +errorMessageONeString);
			
			TakeScreenshot();
			
		}
		
		return flag;
	}
	




}
