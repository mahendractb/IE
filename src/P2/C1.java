package P2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class C1 {
	WebDriver driver;
	@Test
	public void T() throws InterruptedException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		System.setProperty("webdriver.ie.driver","C:\\selenium\\IEDriverServer1.exe");
		driver=new InternetExplorerDriver(capabilities);
		driver.get("http://kbes.eticketing.my");
		 
		 driver.findElement(By.name("txtUID")).sendKeys("pje-03");
			driver.findElement(By.name("txtUPass")).sendKeys("Ctb2890768");

driver.findElement(By.xpath("//table[@class='BlackText']/tbody/tr[7]/td/input[1]")).click();
System.out.println("1");
Thread.sleep(500);
System.out.println("2");
//Robot r=new 
driver.switchTo().alert().accept();
/*Alert alt=driver.switchTo().alert();
System.out.println("3");
alt.accept();
System.out.println("4");
alt.accept();
	*/	
	}
}
