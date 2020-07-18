package IntroPkg;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SpiceJetPro {
 static WebDriver driver;
 public static void main(String[] args) throws InterruptedException {
  
  System.setProperty("webdriver.chrome.driver", "C:\\JavaSelenium\\chromedriver.exe");
  driver = new ChromeDriver();
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  driver.get("https://www.spicejet.com/");
  driver.manage().window().maximize();
  //Departure From which place
  driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).clear();
  driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).sendKeys("AMD");
  Thread.sleep(1000);
  //Arrival To which place
  driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).clear();
  driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).sendKeys("BOM");
  
  Thread.sleep(1000);
  String departDate = "25 July 2020";
  String departDateArr[] = departDate.split(" ");
  String month_year = departDateArr[1]+" "+departDateArr[2];
  String day = departDateArr[0];
  
  
  
  selectDate(month_year,day);
  
  
        Thread.sleep(5000);
     
        driver.close();
 }
 
private static void selectDate(String month_year,String day) throws InterruptedException{
List<WebElement> elements = driver.findElements(By.xpath("//div[@id='ui-datepicker-div'] //div[@class='ui-datepicker-title']"));
  
  for(int i=0;i<elements.size();i++){
   String elementText = elements.get(i).getText();
   if(elementText.equals(month_year)){
    List<WebElement> days = driver.findElements(By.xpath("//div[@id='ui-datepicker-div'] //div[contains(@class,'ui-datepicker-group')]["+(i+1)+"]/table/tbody/tr/td/a"));
    for(WebElement d : days){
     if(d.getText().equals(day)){
      d.click();
      return;
     }
    }
   }
  }
  
  driver.findElement(By.xpath("//a[@title='Next']")).click();
  Thread.sleep(1000);
  driver.findElement(By.xpath("//a[@title='Next']")).click();
  Thread.sleep(2000);
  selectDate(month_year,day);
 }

}