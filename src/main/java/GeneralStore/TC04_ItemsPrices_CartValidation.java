package GeneralStore;

import GeneralSource_Util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static GeneralSource_Util.Util.convertCurrencyStringToDouble;
import static GeneralSource_Util.Util.driver;

public class TC04_ItemsPrices_CartValidation {


    @Test
    public void Test01() throws Exception {

        double totalproductscost = 0;

        String arr[] = {"Air Jordan 4 Retro", "Air Jordan 1 Mid SE"};

        Util.launchApp();
        Util.fillUserDetails(driver, "Bubbly", "Antarctica", "female");
        Util.scrollAndAddItemsToCart(driver, arr);
        //Clicking on CartIcon
        driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.androidsample.generalstore:id/appbar_btn_cart']")).click();
        List<WebElement> prices = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']"));
        int noofproducts = prices.size();
        System.out.println("Number of Products in the Cart : " + noofproducts);
        for (int i = 0; i < prices.size(); i++) {
            WebElement eleprice = prices.get(i);
            String price = eleprice.getText();
            double productprice = convertCurrencyStringToDouble(price);
            totalproductscost += productprice;
        }


        String cartamount = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/totalAmountLbl\"]\n")).getText();
        double totalcartamount = Util.convertCurrencyStringToDouble(cartamount);
        Assert.assertEquals(totalproductscost, totalcartamount, "Total product cost matches the total cart amount.");
        System.out.println("Validation Successful");
    }
}
