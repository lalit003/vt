package appium.android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {

	public static AndroidDriver<AndroidElement> init() throws MalformedURLException {
	   	File f = new File("src\\main\\java\\");
    	File fs = new File(f, "eBay.apk");
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel2");
        capability.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
        capability.setCapability("fullReset","false");
        //capability.setCapability("appPackage", "com.google.android.youtube");
       /* capability.setCapability("appPackage", "com.my_package_name"); 
        capability.setCapability("appActivity", "my_package_name");*/
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capability);
        //driver.close(); 
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        return driver;
	}
}
