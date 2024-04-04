import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
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
    }

    @Test //drag object anywhere
    public void draggable() {
        driver.get("https://www.way2automation.com/way2auto_jquery/draggable.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe")));

        WebElement draggableElement = driver.findElement(By.id("draggable"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(draggableElement, 100, 100).build().perform();
        driver.close();
    }

    @Test //drag and drop to a specific zone
    public void droppable() {
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

    @Test //resize window
    public void resizable() {
        driver.get("https://www.way2automation.com/way2auto_jquery/resizable.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe")));
        WebElement resizableElement = driver.findElement(By.xpath("/html/body/div/div[3]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(resizableElement).build().perform();
        actions.moveByOffset(100, 100).build().perform();
        actions.release().build().perform();
        driver.close();
    }

    @Test //text is visible after clicking on section
    public void accordion() {
        driver.get("https://www.way2automation.com/way2auto_jquery/accordion.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe")));
        driver.findElement(By.id("ui-id-3")).click(); //section 2
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement resultText = driver.findElement(By.xpath("/html/body/div/div[2]/p"));
        Assert.assertEquals(resultText.getText(), "Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna.");
        driver.close();
    }

    @Test
    public void autocomplete() {
        driver.get("https://www.way2automation.com/way2auto_jquery/autocomplete.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe")));
        WebElement autocompleteField = driver.findElement(By.id("tags"));
        autocompleteField.sendKeys("ja");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/ul/li[1]")).click(); //select autocomplete suggestion

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String fieldValue = autocompleteField.getAttribute("value");
        Assert.assertEquals(fieldValue, "Java");
        driver.close();
    }

    @Test
    public void datePicker() {
        driver.get("https://www.way2automation.com/way2auto_jquery/datepicker.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe")));
        driver.findElement(By.id("datepicker")).click(); //click on date field to get a calendar
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/div/div/a[1]/span")).click(); //go back by 1 month
        driver.findElement(By.xpath("/html/body/div/div/a[1]/span")).click(); //go back by 1 month
        driver.findElement(By.xpath("/html/body/div/div/a[1]/span")).click(); //go back by 1 month
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]/a")).click(); //select January 1

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement resultText = driver.findElement(By.id("datepicker"));
        String fieldValue = resultText.getAttribute("value");
        Assert.assertEquals(fieldValue, "01/01/2024");
        driver.close();
    }

    @Test
    public void tooltip() {
        driver.get("https://www.way2automation.com/way2auto_jquery/tooltip.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe")));

        WebElement elementToHover1 = driver.findElement(By.xpath("/html/body/p[1]/a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToHover1).perform();
        String tooltipText1 = elementToHover1.getAttribute("title");
        Assert.assertEquals(tooltipText1, "That's what this widget is");

        WebElement elementToHover2 = driver.findElement(By.xpath("/html/body/p[2]/a"));
        actions.moveToElement(elementToHover2).perform();
        String tooltipText2 = elementToHover2.getAttribute("title");
        Assert.assertEquals(tooltipText2, "ThemeRoller: jQuery UI's theme builder application");

        WebElement elementToHover3 = driver.findElement(By.id("age"));
        actions.moveToElement(elementToHover3).perform();
        String tooltipText3 = elementToHover3.getAttribute("title");
        Assert.assertEquals(tooltipText3, "We ask for your age only for statistical purposes.");

        driver.close();
    }
}
