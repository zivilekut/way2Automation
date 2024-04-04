import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Way2AutoTest {
    ChromeDriver driver;
    @BeforeTest
    public void SetupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        //driver.get("https://www.way2automation.com/way2auto_jquery/automation-practice-site.html");
    }

    @Test //drag and drop
    public void droppable(){
        driver.get("https://www.way2automation.com/way2auto_jquery/droppable.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe")));

        WebElement draggableElement = driver.findElement(By.id("draggable"));
        WebElement droppableElement = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggableElement, droppableElement).build().perform();
        driver.close();
    }
}
