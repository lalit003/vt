package appium.android;


import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map.Entry;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class App extends BaseClass
{
	
	public static AndroidDriver<AndroidElement> driver;

	
	@BeforeMethod
	public static void initialize() throws MalformedURLException {
		driver=init();
	}
	
	@Test
    public static void firstNElements() throws MalformedURLException, InterruptedException
    {
		Utils.searchAndSortProduct(driver, "laptop");
        HashMap<String, String> hm=Utils.firstNItemsFromSearch(10, driver);
        System.out.println("---------------first n items from search-------------------");
        for(Entry<String, String> set: hm.entrySet()) {
        	System.out.println(set.getKey()+" = "+set.getValue());
        }
    }			
	
	@Test
    public static void signInPageValidation() throws MalformedURLException, InterruptedException
    {  
		Utils.searchAndSortProduct(driver, "laptop");
            
        driver.findElementByXPath("//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_title']").click();
        
        //try-catch implemented because under product view i was getting "WATCHLIST and somethime "ADD TO WATCH LIST" button
        try {
        	driver.findElementByXPath("//*[@resource-id='com.ebay.mobile:id/button_add_to_watchlist']").click();
        }catch(Exception e) {
        	driver.findElementByXPath("//*[@resource-id='com.ebay.mobile:id/button_watch']").click();	
        }
        
        String actual = driver.findElementByXPath("//*[@resource-id='com.ebay.mobile:id/title']").getText();
        String expected = "Sign in";
        Assert.assertEquals(actual, expected);
        
    }	
	
	@AfterMethod
	public static void tearDown() throws InterruptedException {
		driver.closeApp();
	}
     

    
}
