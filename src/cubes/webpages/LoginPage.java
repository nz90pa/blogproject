package cubes.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	private WebDriver driver;
	private static final String PAGE_URL = "http://testblog.kurs-qa.cubes.edu.rs/login";
	
	//WebElements
	@FindBy(name="email")
	private WebElement weEmail;
	@FindBy(name="password")
	private WebElement wePassword;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement weButton;
	@FindBy(id = "email-error")
	private WebElement weEmailError;
	@FindBy(id = "description-error")
	private WebElement wePasswordError;
	@FindBy(xpath = "//div[@class='invalid-feedback']//child::strong")
	private WebElement weInvalidError;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver,this);
	}
	
	public void loginSuccess() {
		inputStringInEmail("kursqa@cubes.edu.rs");
		inputStringInPassword("cubesqa");
		clickOnSignIn();
	}
	
	public void login(String email,String password) {
		inputStringInEmail(email);
		inputStringInPassword(password);
		clickOnSignIn();	
	}
	
	public void openPage() {
		driver.get(PAGE_URL);
	}
	
	public void inputStringInEmail(String email) {
		weEmail.clear();
		weEmail.sendKeys(email);
	}
	
	public void inputStringInPassword(String password) {
		wePassword.clear();
		wePassword.sendKeys(password);
	}
	
	public void clickOnSignIn() {
		weButton.click();
	}
	
	public String getEmailInputError() {
		return weEmailError.getText();
	}
	
	public String getPasswordInputError() {
		return wePasswordError.getText();
	}
	
	public String getInvalidError() {
		return weInvalidError.getText();
	}

}
