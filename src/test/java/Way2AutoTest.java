import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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

        driver.findElement(By.id("ui-id-5")).click(); //section 3
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resultText = driver.findElement(By.xpath("/html/body/div/div[3]/p"));
        Assert.assertEquals(resultText.getText(), "Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.");

        driver.findElement(By.id("ui-id-7")).click(); //section 4
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resultText = driver.findElement(By.xpath("/html/body/div/div[4]/p[1]"));
        Assert.assertEquals(resultText.getText(), "Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est.");

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

    @Test //select from dropdown
    public void dropdown() {
        driver.get("https://www.way2automation.com/way2auto_jquery/dropdown.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe")));

        WebElement dropdown = driver.findElement(By.xpath("/html/body/select"));
        dropdown.click();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/select/option[126]")).click(); //Lithuania
        dropdown.click();

        Select select = new Select(dropdown);
        select.selectByVisibleText("Lithuania");

        WebElement selectedOption = select.getFirstSelectedOption();
        String selectedOptionText = selectedOption.getText();

        Assert.assertEquals(selectedOptionText, "Lithuania");

        driver.close();
    }

    @Test //alert message
    public void alert() {
        driver.get("https://www.way2automation.com/way2auto_jquery/alert.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe")));
        driver.findElement(By.xpath("/html/body/button")).click(); //Click the button to display an alert box

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "I am an alert box!");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alert.accept();
        driver.close();
    }
    @Test //fill in registration form
    public void registration(){
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[1]/p[1]/input")).sendKeys("Test"); //name
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[1]/p[2]/input")).sendKeys("Test"); //last name
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[2]/div/label[1]/input")).click(); //marital status
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[3]/div/label[2]/input")).click(); //hobby
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[5]/div[1]/select")).click(); //month
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[5]/div[1]/select/option[2]")).click(); //1
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[5]/div[2]/select")).click(); //day
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[5]/div[2]/select/option[2]")).click(); //1
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[5]/div[3]/select")).click(); //year
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[5]/div[3]/select/option[2]")).click(); //2014
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[6]/input")).sendKeys("+3706111111"); //phone number
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[7]/input")).sendKeys("Test"); //username
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[8]/input")).sendKeys("Test@test.com"); //email
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[9]/input")).sendKeys("C:\\Users\\zivil\\Downloads\\kekw.png"); //photo
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[10]/textarea")).sendKeys("testing"); //about
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[11]/input")).sendKeys("testing"); //password
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[12]/input")).sendKeys("testing"); //password
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[13]/input")).click(); //submit

        WebElement resultText = driver.findElement(By.xpath("/html/body/section/div[1]/div/div/form/fieldset[1]/p[1]/input"));
        Assert.assertEquals(resultText.getText(), "");
        driver.quit();
    }
}
