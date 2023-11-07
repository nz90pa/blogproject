package cubes.main;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestLoginPage {

	public static void main(String[] args) throws InterruptedException {
		
		String urlLoginPage = "http://testblog.kurs-qa.cubes.edu.rs/login";

		System.setProperty("webdriver.http.factory", "jdk-http-client");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get(urlLoginPage);
		driver.manage().window().maximize();
		
		WebElement weEmail = driver.findElement(By.name("email"));
		WebElement wePassword = driver.findElement(By.name("password"));
		WebElement weButton = driver.findElement(By.xpath("//button[@type='submit']"));
		
		weEmail.sendKeys("kursqa@cubes.edu.rs");
		wePassword.sendKeys("cubesqa");
		weButton.click();
		
		WebElement weStarterPage = driver.findElement(By.xpath("//h1[@class='m-0 text-dark']"));
		
		if(weStarterPage.getText().equalsIgnoreCase("Starter Page")) {
			System.out.println("Uspesno ste se ulogovali!");
		}
		else {
			System.out.println("Niste se ulogovali! Proverite email i password!");
		}
		
		driver.navigate().to("https://testblog.kurs-qa.cubes.edu.rs/admin/tags/add");
		
		
		WebElement weTagName = driver.findElement(By.name("name"));
		
		Random random = new Random();
		
		String tagName = "Tag name "+random.nextInt(100);
		
		weTagName.sendKeys(tagName);
		
		WebElement weButtonSaveTag = driver.findElement(By.xpath("//button[@type='submit']"));
		weButtonSaveTag.click();
		
		WebElement weDeleteButton = driver.findElement(By.xpath("//strong[text()='"+tagName+"']//ancestor::tr//td[5]//button"));
		weDeleteButton.click();
		
		//1. nacin cekanja
		//Thread.sleep(30000);
		//2. implicitni nacin cekanja
		//driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
		//3. eklicitni nacin cekanja
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='btn btn-danger']"))));
		
		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@class='toast-message']"))));
		WebElement weProfile = driver.findElement(By.xpath("//i[@class='far fa-user']"));
		weProfile.click();
		
		WebElement weLogout = driver.findElement(By.xpath("//a[@href='https://testblog.kurs-qa.cubes.edu.rs/logout']"));
		weLogout.click();
		
		driver.close();
		
	}


}
