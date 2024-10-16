package GeneralStore;

import GeneralSource_Util.Util;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static GeneralSource_Util.Util.driver;

public class TC02_Add_Single_Item_To_Cart {

    @Test
    public static void addtocrt()throws Exception{
        Util.launchApp();
        Util.fillUserDetails(driver,"JP","Antarctica","female");
        Util.scroll_To_Shoe_And_AddToCart(driver,"Air Jordan 1 Mid SE");
    }
}
