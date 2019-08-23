package appium.android;

import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class Utils {

	public static HashMap<String, String> firstNItemsFromSearch(int N, AndroidDriver<AndroidElement> driver){
		HashMap<String, String> item_price_list = new HashMap<String, String>();
		Dimension dimensions = driver.manage().window().getSize();
        int height = dimensions.getHeight();
        int width = dimensions.getWidth();
        int startx=width/2;
        int endx=width/2;
        int starty=(int)(height*.55);
        int endy=(int)(height*.10);
		while(N!=0) {
			driver.swipe(startx, endx,starty,endy,1000);
        	AndroidElement item_title = driver.findElementByXPath("//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_title']"); 
            AndroidElement item_price = driver.findElementByXPath("//android.widget.RelativeLayout/android.widget.LinearLayout[@index='3']/android.widget.RelativeLayout/android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_price']");
            //System.out.println(item_title.getText()+" = "+item_price.getText());
			item_price_list.put(item_title.getText(), item_price.getText());
			if(item_price_list.size()==N)
				break;
		}
		
		
		return item_price_list;
	}
	
	public static void searchAndSortProduct(AndroidDriver<AndroidElement> driver, String productName) {
		 driver.findElementByXPath("//android.widget.TextView[@text='Search for anything']").click();
	        driver.findElementByXPath("//android.widget.EditText[@text='Search for anything']").sendKeys(productName);
	        driver.pressKeyCode(AndroidKeyCode.ENTER); 
	        WebElement popUp = driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@resource-id='com.ebay.mobile:id/text_slot_1']");
	        popUp.click();
	        driver.findElementByXPath("//android.widget.Button[@text='SORT']").click();
	        driver.findElementByXPath("//*[@text='Lowest Price + Shipping']").click();
	        
	}
}
