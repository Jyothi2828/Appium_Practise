package GeneralSource_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Util {

    public static WebDriver driver;

    public static void launchApp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        // Predefined capabilities
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Mytest"); // Update with your device/emulator name
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5554"); // Update with your UDID
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.androidsample.generalstore");
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.androidsample.generalstore.SplashActivity");
        // Initialize the AndroidDriver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4725/"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    // Scroll to the product and return the "Add to Cart" button WebElement
    public static WebElement scroll_To_Shoe_And_AddToCart(WebDriver driver, String productName) {
        String scrollableCommand = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + productName + "\"));";
        ((AndroidDriver) driver).findElementByAndroidUIAutomator(scrollableCommand);

        String xpathForAddToCartButton = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName' and @text='" + productName + "']" +
                "/following-sibling::android.widget.LinearLayout/android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']";

        WebElement addtocartbtn=driver.findElement(By.xpath(xpathForAddToCartButton));
        addtocartbtn.click();
        return addtocartbtn;
    }

    // Single reusable method to remove $ and convert to double
    public static double convertCurrencyStringToDouble(String str) {
        double result = 0.0;
        try {
            // Remove the dollar sign and trim spaces
            str = str.replace("$", "").trim();
            // Convert the cleaned string to double
            result = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + str + " cannot be converted to a double.");
            // Handle the exception or return a default value, here it's 0.0
        }
        return result;
    }

    // Scroll to an element using its text
    public static WebElement scrollbyUsingtext(WebDriver driver, String text) {
        String sr = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + text + "\"));";
        return ((AndroidDriver) driver).findElementByAndroidUIAutomator(sr);
    }

    // Scroll through an array of product names and add each to the cart
    public static void scrollAndAddItemsToCart(WebDriver driver, String[] productNames) {
        for (String productName : productNames) {
            String scrollableCommand = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + productName + "\"));";
            ((AndroidDriver) driver).findElementByAndroidUIAutomator(scrollableCommand);

            String xpathForAddToCartButton = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName' and contains(@text, '" + productName + "')]" +
                    "/following-sibling::android.widget.LinearLayout/android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']";

            WebElement addToCartButton = driver.findElement(By.xpath(xpathForAddToCartButton));
            addToCartButton.click();

            System.out.println("Added " + productName + " to cart.");
        }
    }

    // Method to fill user details
    public static void fillUserDetails(WebDriver driver, String name, String country, String gender) {
        // Click on the country dropdown and select the specified country
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]")).click();
        scrollbyUsingtext(driver, country).click();

        // Enter the name
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]"))
                .sendKeys(name);

        // Select the gender
        if (gender.equalsIgnoreCase("male")) {
            driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioMale\"]")).click();
        } else if (gender.equalsIgnoreCase("female")) {
            driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioFemale\"]")).click();
        } else {
            System.out.println("Invalid gender specified: " + gender);
        }

        // Click on the "Lets Shop" button
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]")).click();
    }

    public static String getTextByXPath(WebDriver driver, String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            return element.getText(); // Return the text of the element
        } catch (Exception e) {
            System.out.println("Element not found or unable to get text. Error: " + e.getMessage());
            return ""; // Return an empty string or handle as needed
        }
    }

}
