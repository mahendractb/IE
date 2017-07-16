package P1;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class RangeExcel {

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
		 String k[]=ExcelDate(5);
		 
		 driver.get(k[0]);
		 
		 driver.findElement(By.name("txtUID")).sendKeys(k[1]);
			driver.findElement(By.name("txtUPass")).sendKeys(k[2]);
		 //driver.get("http://perdana.eticketing.my/Main/Login.asp");
		 //driver.findElement(By.name("txtUID")).sendKeys("catchthatbus");
			//driver.findElement(By.name("txtUPass")).sendKeys("ctb7382");
			driver.findElement(By.xpath("//table[@class='BlackText']/tbody/tr[7]/td/input[1]")).click();


			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			
			Alert alt=driver.switchTo().alert();
			alt.accept();
			
			Thread.sleep(2000);
			String hand="";
			for(String handle:driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
				try{
					driver.switchTo().frame("frameCalender");
					hand=handle;
				}
				catch(Exception e)
				{
					driver.close();
				}
			}
			driver.switchTo().window(hand);
			System.out.println("title ="+driver.getTitle());
	}
  @Test
  public void DateMethod1() throws IOException, InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, ParseException {
	 System.out.println("11");
	  String k[]=ExcelDate(1);
	  System.out.println("12");
	  fw1=new FileWriter(new File("code1.txt"));
	  System.out.println("13");
	  u1=1;
	  int uu1=Integer.parseInt(k[3]);
	  int uu2=Integer.parseInt(k[6]);
	  f4(uu1,uu2,u1);
	  
  }
  @Test
  public void DateMethod2() throws IOException, InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, ParseException {
	  String k[]=ExcelDate(2);
	  fw2=new FileWriter(new File("code2.txt"));
	  u2=2;;
	  int uu1=Integer.parseInt(k[3]);
	  int uu2=Integer.parseInt(k[6]);
	  f4(uu1,uu2,u2);
  }
  @Test
  public void DateMethod3() throws IOException, InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, ParseException {
	  String k[]=ExcelDate(3);
	  	  fw3=new FileWriter(new File("code3.txt"));
	  u3=3;
	  int uu1=Integer.parseInt(k[3]);
	  int uu2=Integer.parseInt(k[6]);
	  f4(uu1,uu2,u3);
  }
  //Excel DateReading related Method-------------------------------------------------->
   
 public String[] ExcelDate(int y1) throws IOException, InterruptedException{
	 System.out.println("1");
	 String[] dd=new String[10];
	 
	File f=new File("C:\\Users\\CTBCS\\Desktop\\5.xlsx");//please provide the excel path here-------------->
	 FileInputStream objfile = new FileInputStream(f);
	 XSSFWorkbook wb= new XSSFWorkbook(objfile);
	 XSSFSheet xs =wb.getSheet("sheet1");
	 for(int i=1;i<xs.getLastRowNum();i=i+4){
		 Row r=xs.getRow(i);
		 Cell cp=r.getCell(3);
		 String ty=cp.getStringCellValue();
		 System.out.println("ty=="+ty);
		 if(ty.equalsIgnoreCase("Y")){
			 Cell cp1=r.getCell(0);
			 String ty1=cp1.getStringCellValue();
			 Cell cp2=r.getCell(1);
			 String ty2=cp2.getStringCellValue();
			 Cell cp3=r.getCell(2);
			 String ty3=" ";
			 try{
			  ty3=cp3.getStringCellValue();
			 }
			 catch(Exception e){
				 int password=(int) cp3.getNumericCellValue();
				 
				 ty3=String.valueOf(password);
			 }
			 System.out.println("ty1=="+ty1+"ty2==="+ty2+"ty3=="+ty3);
			 dd[0]=ty1;
			 dd[1]=ty2;
			 dd[2]=ty3;
			if(y1!=5){
			 int yu=i+y1;
			 System.out.println("i="+yu);
			 Row r1=xs.getRow(yu);
			 Cell c1=r1.getCell(0);
			 Date s1=c1.getDateCellValue();
			 Cell c2=r1.getCell(2);
			 int s2=(int) c2.getNumericCellValue();
			 SimpleDateFormat dt1 = new SimpleDateFormat("dd");
			 String D=dt1.format(s1);
			 System.out.println("day"+D);//starting day of the jounary 
			 SimpleDateFormat dt2 = new SimpleDateFormat("MM");
			 String M=dt2.format(s1);//starting month of the jounary
			 SimpleDateFormat dt3 = new SimpleDateFormat("YYYY");
			 String y=dt3.format(s1);
			 dd[3]=D;
			 dd[4]=M;
			 dd[5]=y;
			 dd[6]=String.valueOf(s2);
			}
			else
			{
				dd[3]="";
				 dd[4]="";
				 dd[5]="";
				 dd[6]="";
			}
			break; 		 
		 }
	 }
	 
	 /*SimpleDateFormat dt1 = new SimpleDateFormat("dd");
	
	 int D=Integer.parseInt(dt1.format(s1));
	 System.out.println("day"+D);//starting day of the jounary 
	 SimpleDateFormat dt2 = new SimpleDateFormat("MM");
	 String M=dt2.format(s1);//starting month of the jounary
	 SimpleDateFormat dt3 = new SimpleDateFormat("YYYY");
	 String y=dt3.format(s1);//starting year of the jounary
	 */
	 if(y1!=5){
		 Thread.sleep(3000);
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
	 if(year1.equals(dd[5])&&Month1.equals(dd[4]))
	 {
System.out.println("DAter");

		 break;
	 }
	 else{
		
		 driver.findElement(By.xpath("//table[@class='BlackText']/tbody/tr/td[6]/img")).click();
	 }
	 }
	 }
	  return dd;
 }
 
 
 //Source & destination station related methods
 public void f4(int s,int enddate,int ll) throws IOException, InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, ParseException {
	 	
		
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
		String first=l.get(0).getText();
		String last=l.get(l.size()-1).getText();
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
			//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frameDestination");
			//WebDriverWait w1 = new WebDriverWait(driver,10);
			//w1.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("\\td[@class='tableheader']"),":: Destination ::"));
		/*String tr=" ";
			try{
				tr=driver.findElement(By.xpath(".//form[@name='form1']/table/tbody/tr[1]/td")).getText();
				System.out.println("Test1");
		}
			catch(Exception e){
				Thread.sleep(10000);
				tr=driver.findElement(By.xpath(".//form[@name='form1']/table/tbody/tr[1]/td")).getText();
			
			}
			*/
			Thread.sleep(3000);
			if(ll==1&&Source.equalsIgnoreCase(first)){
				fw1.write("Jounary date starting=="+JoDate);
				
			}
			else if(ll==1&&Source.equalsIgnoreCase(last))
			{
				fw1.write("Jounary date Ending=="+JoDate);
			
			}
			
			if(ll==2&&Source.equalsIgnoreCase(first)){
				fw2.write("Jounary date starting=="+JoDate);
				
			}
			else if(ll==2&&Source.equalsIgnoreCase(last))
			{
				fw2.write("Jounary date Ending=="+JoDate);	
				
			}
			
			
			if(ll==3&&Source.equalsIgnoreCase(first)){
				fw3.write("Jounary date starting=="+JoDate);
			}
			else if(ll==3&&Source.equalsIgnoreCase(last))
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
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
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

double price=Double.parseDouble(driver.findElement(By.name("txtSAPric")).getAttribute("value"));
double cprice=Double.parseDouble(driver.findElement(By.name("txtSCPric")).getAttribute("value"));
double sprice=Double.parseDouble(driver.findElement(By.name("txtSSPric")).getAttribute("value"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame("frameSeat");

							List<WebElement> seat=driver.findElements(By.className("Button2"));	
							int size=seat.size();
							
							String operator=driver.getTitle();
							String oper1=operator.substring(0,15);
							String dt=JoDate+" "+btime;
							 DateFormat readFormat = new SimpleDateFormat( "dd/MM/yyyy hh:mm aaa");

							    DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
							    Date date = null;
							    try {
							       date = readFormat.parse(dt);
							    } catch ( ParseException e ) {
							        e.printStackTrace();
							    }

							    String formattedDate = "";
							    if( date != null ) {
							    formattedDate = writeFormat.format( date );
							    }
							    
							    //hash code related code
							    String m5=Source+Destination+btime+r5+price;
						        MessageDigest md = MessageDigest.getInstance("MD5");
						        byte[] messageDigest = md.digest(m5.getBytes());
						        BigInteger number = new BigInteger(1, messageDigest);
						        String hashtext = number.toString(16);

							//db.insertBookingDetails(oper1, Source, Destination, JoDate,r5,Route,price,btime, size);
						        db.insertBookingDetails(oper1, Source, Destination,hashtext,r5,Route,price,formattedDate, size,cprice,sprice);
							
							System.out.println("operator"+operator+"Jounary date="+JoDate+"source="+Source+"Destinations="+Destination+"Time="+btime+"seats ="+seat.size()+"price=="+price+"Route="+Route+"trip"+r5);
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
			if(ll==1){
				fw1.close();
			}
			if(ll==2){
				fw2.close();
			}
			if(ll==3){
				fw3.close();
			}
		}
}
  
  
  
  @AfterMethod
  public void Me(){
  driver.close();
  }
}

/*public class RangeExcel {

}
*/
