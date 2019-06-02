package seleniumWebDriver;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

public class ModificarUsuario {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
		// Descargar Firefox driver (Gecko Driver) de https://github.com/mozilla/geckodriver/releases y copiar en carpeta drivers
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver-v0.24.0-win64/geckodriver.exe");
		// Descargar Chrome driver de https://sites.google.com/a/chromium.org/chromedriver/downloads y copiar en carpeta drivers
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_win32/chromedriver.exe");
		// System.setProperty("webdriver.opera.driver", "/path/to/operadriver");

		// Descargar IE driver from https://www.seleniumhq.org/download/ y copiar en carpeta drivers
		// System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriverServer.exe");

		// System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe");
		// System.setProperty("phantomjs.binary.path", "/path/to/phantomjs");

		// driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		 driver = new HtmlUnitDriver();
	    baseUrl = "https://www.katalon.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

  @Test
  public void testModificarUsuario() throws Exception {
    driver.get("https://loginhmis1svo585.azurewebsites.net/Identity/Account/Login");
    driver.findElement(By.id("Input_Email")).click();
    driver.findElement(By.id("Input_Email")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=Input_Email | ]]
    driver.findElement(By.id("Input_Email")).clear();
    driver.findElement(By.id("Input_Email")).sendKeys("usuario@gmail.com");
    driver.findElement(By.id("Input_Password")).click();
    driver.findElement(By.id("Input_Password")).clear();
    driver.findElement(By.id("Input_Password")).sendKeys("Abc123!");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
    driver.findElement(By.linkText("Hello usuario@gmail.com!")).click();
    driver.findElement(By.id("Input_PhoneNumber")).click();
    driver.findElement(By.id("Input_PhoneNumber")).clear();
    driver.findElement(By.id("Input_PhoneNumber")).sendKeys("76567678");
    driver.findElement(By.id("update-profile-button")).click();
    assertTrue(isElementPresent(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Profile'])[2]/following::div[1]")));
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Hello usuario@gmail.com!'])[1]/following::button[1]")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
