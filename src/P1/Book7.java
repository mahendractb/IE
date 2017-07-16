package P1;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Book7 {
	WebDriver driver;
	BookDB db=null;
	FileWriter fw1,fw2,fw3;
	int u1=0,u2=0,u3=0;
	@BeforeMethod
	public void Test1() throws IOException, InterruptedException, AWTException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		System.setProperty("webdriver.ie.driver", "C:\\selenium\\IEDriverServer1.exe");
		 driver=new InternetExplorerDriver(capabilities);
		 //fw=new FileWriter(new File("code.txt"));
		 driver.get("http://kbes.eticketing.my");
		 
		 driver.findElement(By.name("txtUID")).sendKeys("pje-03");
			driver.findElement(By.name("txtUPass")).sendKeys("Ctb2890768");
		 //driver.get("http://perdana.eticketing.my/Main/Login.asp");
		 //driver.findElement(By.name("txtUID")).sendKeys("catchthatbus");
			//driver.findElement(By.name("txtUPass")).sendKeys("ctb7382");
			driver.findElement(By.xpath("//table[@class='BlackText']/tbody/tr[7]/td/input[1]")).click();
Robot r=new Robot();
//r.keyPress(KeyEvent.VK_ENTER);
			//WebDriverWait wait = new WebDriverWait(driver,10);
			//wait.until(ExpectedConditions.alertIsPresent());
			Thread.sleep(10000);
			//r.keyPress(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_ENTER);
			//Alert alt=driver.switchTo().alert();
			//alt.accept();
			
			Thread.sleep(700);
			for(String handle:driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
			}
			System.out.println("title ="+driver.getTitle());
			Thread.sleep(500);
	}
  @Test
  public void DateMethod1() throws IOException, InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	  int k[]=ExcelDate(1);
	  fw1=new FileWriter(new File("code1.txt"));
	  u1++;
	  f4(k[0],k[1]);
	  
  }
  @Test
  public void DateMethod2() throws IOException, InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	  int k[]=ExcelDate(2);
	  fw2=new FileWriter(new File("code2.txt"));
	  u2++;
	  f4(k[0],k[1]);
  }
  @Test
  public void DateMethod3() throws IOException, InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	  int k[]=ExcelDate(3);
	  	  fw3=new FileWriter(new File("code3.txt"));
	  u3++;
	  f4(k[0],k[1]);
  }
  //Excel DateReading related Method-------------------------------------------------->
   
 public int[] ExcelDate(int y1) throws IOException, InterruptedException{
	 
	 File f=new File("C:\\Users\\CTBCS\\Desktop\\4.xlsx");//please provide the excel path here-------------->
	 FileInputStream objfile = new FileInputStream(f);
	 XSSFWorkbook wb= new XSSFWorkbook(objfile);
	 XSSFSheet xs =wb.getSheet("sheet1");
	 Row r=xs.getRow(y1);
	 Cell c1=r.getCell(0);
	 Date s1=c1.getDateCellValue();
	 Cell c2=r.getCell(2);
	 int s2=(int) c2.getNumericCellValue();
	 
	 
	 SimpleDateFormat dt1 = new SimpleDateFormat("dd");
	
	 int D=Integer.parseInt(dt1.format(s1));
	 System.out.println("day"+D);//starting day of the jounary 
	 SimpleDateFormat dt2 = new SimpleDateFormat("MM");
	 String M=dt2.format(s1);//starting month of the jounary
	 SimpleDateFormat dt3 = new SimpleDateFormat("YYYY");
	 String y=dt3.format(s1);//starting year of the jounary
	 driver.switchTo().defaultContent();
		driver.switchTo().frame("frameCalender");
	 String year1=driver.findElement(By.name("txtYear")).getAttribute("value");
	 String Month1=driver.findElement(By.name("txtMonth")).getAttribute("value");
	
	if(Month1.length()<=1)
	{
		Month1="0"+Month1;
		
	}
	
	  for(;;){
		  
year1=driver.findElement(By.name("txtYear")).getAttribute("value");
	 Month1=driver.findElement(By.name("txtMonth")).getAttribute("value");
	 if(Month1.length()<=1)
		{
		
			Month1="0"+Month1;
		}
	 if(year1.equals(y)&&Month1.equals(M))
	 {
System.out.println("DAter");

		 break;
	 }
	 else{
		
		 driver.findElement(By.xpath("//table[@class='BlackText']/tbody/tr/td[6]/img")).click();
	 }
	 }
	 int[] l=new int[3];
	 l[0]=D;
	 l[1]=s2;
	 
	  return l;
 }
 
 
 //Source & destination station related methods
 public void f4(int s,int enddate) throws IOException, InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	 	
		
	 int f1=0;
		db=new BookDB();
		//String mvalue=driver.findElement(By.className("DisBox")).getAttribute("value");
		//int mvalue1=Integer.parseInt(mvalue);	
		try{
			int count=0;
		
		List<WebElement> datecount = driver.findElements(By.xpath(".//input[starts-with(@id,'idCal')]"));//date list
		while(count<enddate)
		{
		for(WebElement d:datecount)	
				{
					driver.switchTo().defaultContent();
					driver.switchTo().frame("frameCalender");
					
					int t=Integer.parseInt(d.getAttribute("value"));
				//	System.out.println("t____----"+t+"======s"+s+"*******");,
					if(t==s){
						f1=f1+1;
					}
				
				if(d.isEnabled()&&count<enddate&&f1>0){
				
				d.click();
d.click();		
				count++;
}
else
{
continue;
}
				
				int in=0;
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frameInfo");
		String JoDate=driver.findElement(By.name("txtSDate")).getAttribute("value");
		Select counter =new Select(driver.findElement(By.className("InputBox")));
		List<WebElement> l =counter.getOptions();//Source cities names are storing in this list
		System.out.println("Source Size:"+l.size());
		int m=0;
		for(WebElement sou:l)
		{
			
			//System.out.println("*******************************************************************");
			//fw.write("\n*******************************************************************");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frameInfo");

			//fw.write("\nSource: "+sou.getText());
			
			//counter.selectByVisibleText(sou.getText());
			counter.selectByIndex(in);
			in++;
			String Source=sou.getText();
			System.out.println("Source: "+sou.getText());
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frameDestination");
			//WebDriverWait w1 = new WebDriverWait(driver,10);
			//w1.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("\\td[@class='tableheader']"),":: Destination ::"));
		String tr=" ";
			try{
				tr=driver.findElement(By.xpath(".//form[@name='form1']/table/tbody/tr[1]/td")).getText();
				System.out.println("Test1");
		}
			catch(Exception e){
				Thread.sleep(10000);
				tr=driver.findElement(By.xpath(".//form[@name='form1']/table/tbody/tr[1]/td")).getText();
				System.out.println("Test2");
			}
			if(u1>0&&Source.equalsIgnoreCase("AIR HITAM")){
				fw1.write("Jounary date starting=="+JoDate);
				System.out.println("Source++++++++:"+Source);
			}
			else if(u1>0&&Source.equalsIgnoreCase("YONGPENG"))
			{
				fw1.write("Jounary date Ending=="+JoDate);
				System.out.println("close");
			}
			
			if(u2>0&&Source.equalsIgnoreCase("AIR HITAM")){
				fw2.write("Jounary date starting=="+JoDate);
				
			}
			else if(u2>0&&Source.equalsIgnoreCase("YONGPENG"))
			{
				fw2.write("Jounary date Ending=="+JoDate);	
				
			}
			
			
			if(u3>0&&Source.equalsIgnoreCase("AIR HITAM")){
				fw3.write("Jounary date starting=="+JoDate);
			}
			else if(u3>0&&Source.equalsIgnoreCase("YONGPENG"))
			{
				fw3.write("Jounary date Ending=="+JoDate);	
				
			}
			
			List<WebElement> dest=driver.findElements(By.className("DestButton"));//destination cities are storing in this list
			int destCount=dest.size();
			//System.out.println("no.of Destinations:"+destCount);
			//fw.write("\nno.of Destinations:"+destCount);
			
			if(destCount==0){//Destination is zero 

				continue;
			}
			
			
			int i=0,j,b;
			while(i<destCount) //destination cities are available
			{
				
				for(WebElement ds:dest)
				{
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("frameDestination");
					
					System.out.println("===============================================");
					//fw.write("\n=================================================");
					String Destination= ds.getAttribute("value");
					//System.out.println("Destination name:"+ds.getAttribute("value"));
					//fw.write("\nDestination name:"+ds.getAttribute("value"));
					
					ds.click();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("frameTime");
					List<WebElement> time=driver.findElements(By.className("DestButton2"));
					int timeCount=time.size();
					
					j=0;
					while(j<timeCount)//timings 
					{
						b=1;
						
						for(WebElement ti:time)
						{
							
							
							driver.switchTo().defaultContent();
							driver.switchTo().frame("frameTime");
							ti.click();
							String Route=driver.findElement(By.name("txtRnam")).getAttribute("value");
							int r7=b%2==0?5:2;
							int r8=b%2==0?b-1:b;
						
							
							String r1="//table[@class='blacktext']//table[@class='blacktext']//tr["+r8+"]/td["+r7+"]";
							String r5=driver.findElement(By.xpath(r1)).getText();
							r5=r5.split("\\[")[1].split("\\]")[0];
							System.out.println("r5==="+r5);
							b++;
String btime=ti.getAttribute("value");
							
driver.switchTo().defaultContent();
driver.switchTo().frame("frameInfo");

String price=driver.findElement(By.name("txtSAPric")).getAttribute("value");
							driver.switchTo().defaultContent();
							driver.switchTo().frame("frameSeat");

							List<WebElement> seat=driver.findElements(By.className("Button2"));	
							int size=seat.size();
							
							String operator="kbEksprwes";
							//db.insertBookingDetails(operator, Source, Destination, JoDate,r5,Route,price,btime, size);
							
							System.out.println("Jounary date="+JoDate+"source="+Source+"Destinations="+Destination+"Time="+btime+"seats ="+seat.size()+"price=="+price+"Route="+Route+"trip"+r5);
							//fw.write("Jounary date=="+JoDate+"source=="+Source+"Destinations=="+Destination+"Time=="+btime+"seats=="+seat.size()+"price==="+price+"Route=="+Route+"trip==="+r5);
							//fw.write("----------------------------------------------------------------------");
							j++;
							
						}

						if(j%8==0)//when we have more buses and pagination is available 
						{
							driver.switchTo().defaultContent();
							driver.switchTo().frame("frameTime");
							List<WebElement> nxt=driver.findElements(By.xpath("//input[@value=' >> ']"));
							if(nxt.size()==0)//checking pagination available or not
							{
								break;
							}
							else
							{
								nxt.get(nxt.size()-1).click();
							}

							time=driver.findElements(By.className("DestButton2"));
							timeCount=timeCount+time.size();
							//System.out.println("Time Count: "+timeCount);
							//System.out.println("no.of Destinations(updated):"+destCount);
						//fw.write("\nno.of Destinations(updated):"+destCount);
						}

					}

					
					i++;
				}

					if(i==15)//when we have more destinations and pagination is available
					{
						driver.switchTo().defaultContent();
						driver.switchTo().frame("frameDestination");
						List<WebElement> nxt=driver.findElements(By.xpath("//input[@value=' >> ']"));
						if(nxt.size()==0)//checking pagination available or not
						{
							break;
						}
						else
						{
							nxt.get(0).click();
						}
	
						dest=driver.findElements(By.className("DestButton"));
						destCount=destCount+dest.size();
						//System.out.println("no.of Destinations(updated):"+destCount);
						//fw.write("\nno.of Destinations(updated):"+destCount);
					}
					
				}
				}
			
				}
			
			
				
				}
		if(count<enddate)
		{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frameCalender");
			driver.findElement(By.xpath("//table[@class='BlackText']/tbody/tr/td[6]/img")).click();
			 datecount = driver.findElements(By.xpath(".//input[starts-with(@id,'idCal')]"));
		}

		}
				finally{
			if(u1>0){
				fw1.close();
			}
			if(u2>0){
				fw2.close();
			}
			if(u3>0){
				fw3.close();
			}
		}
}
  
  
  
  @AfterMethod
  public void Me(){
  driver.close();
  }
}
	
