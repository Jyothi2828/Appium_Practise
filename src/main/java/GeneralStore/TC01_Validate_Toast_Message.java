package GeneralStore;

import GeneralSource_Util.Util;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static GeneralSource_Util.Util.driver;

public class TC01_Validate_Toast_Message {

    @Test
    public static void ValidationOfToastmessage() throws Exception {
        Util.launchApp();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]\n")).click();
        String toastmsgtext=driver.findElement(By.xpath("//android.widget.Toast[@text=\"Please enter your name\"]")).getText();
        Assert.assertEquals(toastmsgtext,"Please enter your name");
        System.out.println("Toast Message Validation Successful");
    }


}
