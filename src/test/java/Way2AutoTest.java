import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

public class Way2AutoTest {
    ChromeDriver driver;
    @BeforeTest
    public void SetupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.way2automation.com/way2auto_jquery/automation-practice-site.html");
    }
}
