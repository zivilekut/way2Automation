import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;
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
        Point initialLocation = draggableElement.getLocation();
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(draggableElement, 100, 100).build().perform();
        Point finalLocation = draggableElement.getLocation();

        Assert.assertNotEquals(finalLocation, initialLocation);
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

        WebElement resultText = driver.findElement(By.xpath("/html/body/div[2]/p"));
        Assert.assertEquals(resultText.getText(), "Dropped!");

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
        Point initialLocation = resizableElement.getLocation();

        Actions actions = new Actions(driver);
        actions.clickAndHold(resizableElement).build().perform();
        actions.moveByOffset(100, 100).build().perform();
        actions.release().build().perform();

        Point finalLocation = resizableElement.getLocation();

        Assert.assertNotEquals(finalLocation, initialLocation);
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

    @Test //write part of the text and select the first option
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

    @Test //select date from a calendar
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

    @Test //hover over an item and check the text
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

    @Test //open new tab
    public void framesAndWindows1() {
        driver.get("https://www.way2automation.com/way2auto_jquery/frames-and-windows.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe")));

        driver.findElement(By.xpath("/html/body/div/p/a")).click(); //click on "New Browser Tab"

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //assert 1 - check number of tabs
        Set<String> windowHandlesAfterClick = driver.getWindowHandles(); // Get all window handles after clicking the link
        Assert.assertEquals(windowHandlesAfterClick.size(), 2);

        //assert 2 - check text in the opened tab
        WebElement resultText = driver.findElement(By.xpath("/html/body/div/p/a"));
        Assert.assertEquals(resultText.getText(), "New Browser Tab");
        driver.quit();
    }

    @Test //open separate new window
    public void framesAndWindows2() {
        driver.get("https://www.way2automation.com/way2auto_jquery/frames-and-windows.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[1]/ul/li[2]/a")).click(); //open separate new window tab
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[2]/div/iframe")));

        String mainWindowHandle = driver.getWindowHandle();

        driver.findElement(By.xpath("/html/body/div/p/a")).click(); //click on "Open New Seprate Window"
        driver.switchTo().defaultContent();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Set<String> windowHandlesAfterClick = driver.getWindowHandles(); // Get all window handles after clicking the link

        String newWindowHandle = null;
        for (String windowHandle : windowHandlesAfterClick) {
            if (!windowHandle.equals(mainWindowHandle)) {
                newWindowHandle = windowHandle;
                break;
            }
        }

        Assert.assertNotNull(newWindowHandle);// Assert that a new window has been opened

        driver.switchTo().window(newWindowHandle);// Switch to the new window

        WebElement resultText = driver.findElement(By.xpath("/html/body/div/p/a"));
        Assert.assertEquals(resultText.getText(), "Open New Seprate Window");
        driver.close();// Close the new window

        driver.switchTo().window(mainWindowHandle);// Switch back to the main window
        driver.quit();

    }
}
