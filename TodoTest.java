package WebDriverDemo;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TodoTest {
    static int counter = 0;

	public static void main(String[] args) {
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\rajan\\chromedriver_win32\\chromedriver.exe");	 
			//CREATING WEBDRIVER INSTANCE
			WebDriver driver=new ChromeDriver();  
			driver.get("http://localhost/qa-exercise-master/index.php");			
			
			//GET TODO TEXTBOX 
			WebElement todoTxt=driver.findElement(By.name("data"));
					
			//TEXTBOX VALIDATIONS
			boolean isTxtPresent=todoTxt.isDisplayed();
			if (isTxtPresent!=true)
			{
				System.out.println("Todo add text box not displayed");
			}			
			boolean isTxtEnabled=todoTxt.isEnabled();			
			if (isTxtEnabled!=true)
			{
				System.out.println("Todo add text box not enebled");
			}
			
			//GENERATE RANDOM TEST DATA
			//todoTxt.click();
			Random r = new Random();
			char c = (char)(r.nextInt(26) + 'a');			
			String x =Character.toString(c);;
			todoTxt.sendKeys(x+counter++);
			
			String txt=todoTxt.getText();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			
			//ASSIGNING CATEGORY			
			WebElement selCat=driver.findElement(By.xpath("//*[@id=\"extra\"]/select[1]"));					
			Select select=new Select(selCat);	
			
			try {	Thread.sleep(1000);
			} catch (InterruptedException e) {	
				e.printStackTrace();
			}			
			select.selectByVisibleText("College");		
			
			//ASSIGNING DATE
			WebElement selDay=driver.findElement(By.name("due_day"));
			Select dueDay=new Select(selDay);
			dueDay.selectByIndex(1);
			WebElement selMon=driver.findElement(By.name("due_month"));
			Select dueMon=new Select(selMon);
			dueMon.selectByIndex(1);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
					
			WebElement addBtn=driver.findElement(By.xpath("/html/body/div[4]/input[2]"));
			//CLICK ON ADD BUTTON
			addBtn.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			System.out.println("after click add");
			
			//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			if(txt.length()>15)
			{
				System.out.println("no of charaters exceeds 15");
			}
			
			//CONFIRM WHEATHER TODO IS ADDED
			List todoLst=driver.findElements(By.xpath("*[@id=\"todos-content\"]/form/ul"));						
			if(todoLst.contains(txt));
			{
				System.out.println("Todo added");
			}
			//METHOD TO MODIFY TODO
			ModifyTodo(driver,todoLst);
			
			//METHOD TO DELETE TODO
			DeleteTodo(driver);
			
	}
	private static void DeleteTodo(WebDriver driver) {
		
		WebElement chkBox=driver.findElement(By.xpath("//*[@id=\"todos-content\"]/form/ul/li[1]/input"));
		
		//CHECK THE CHECKBOX FOR TODO TO BE DELETED
		chkBox.click();
		
		try {	Thread.sleep(2000);
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}	
		
		WebElement remBtn=driver.findElement(By.xpath("/html/body/div[3]/input[1]"));
		//CLICK REMOVE BTN
		remBtn.click();
		System.out.println("after remove btn click");
	}
	public static void ModifyTodo(WebDriver driver, List todoLst)
	{
		WebElement editLink=driver.findElement(By.xpath("//*[@id=\"todos-content\"]/form/ul/li[1]/a"));
		
		//CLICK ON EDIT LINK
		editLink.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebElement updtBtn=driver.findElement(By.xpath("/html/body/form/input[4]"));
		
		//IF UPDATE BUTTON EXISTS ON THE PAGE, ITS IN EDIT MODE
		if(updtBtn.isDisplayed())
		{
			System.out.println("todo opened in edit mode");
		}
		
		//GET UPDATE TEXTBOX AND THE TEXT FROM IT
		WebElement updtTxtBox = driver.findElement(By.xpath("/html/body/form/input[3]"));
		String updatedTxt=updtTxtBox.getText();
		
		//UPDATE THE TEXT
		updatedTxt = updatedTxt+counter++;
		updtTxtBox.sendKeys(updatedTxt);
		try {	Thread.sleep(2000);
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}	
		updtBtn.click();
		System.out.println("after update btn click");
		
		//IF LIST ON HOMEPAGE CONTAINS UPDATED TEXT,TODO IS UPDATED 
		if(todoLst.contains(updatedTxt)) {
			System.out.println("todo updated");
		}
	}

}
