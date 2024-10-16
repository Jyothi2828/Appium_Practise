package GeneralStore;

import GeneralSource_Util.Util;
import org.testng.annotations.Test;

public class TC03_Add_Multiple_Items_To_Cart extends Util {

    @Test
    public void Test01() throws Exception {

        String arr[]={"Converse All Star","Air Jordan 9 Retro"};

        Util.launchApp();
        Util.fillUserDetails(driver,"Bubbly","Canada","male");
        Util.scrollAndAddItemsToCart(driver,arr);

//        ,"Nike Blazer Mid '77"


    }


}
