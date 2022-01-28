package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static Platform instance;
    private static final String
            APPIUM_URL = "http://127.0.0.1:4723/wd/hub",
            ENV_PLATFORM_NAME = "PLATFORM_NAME";
    public static final String
            ANDROID_PLATFORM = "android",
            IOS_PLATFORM = "ios",
            MOBILE_WEB_PLATFORM = "mobile_web";

    private Platform() {}

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }

        return instance;
    }

    public RemoteWebDriver getDriverByPlatformFromEnv() throws Exception {
        final String platformNameFromEnv = getPlatformNameFromEnv();
        RemoteWebDriver driver;

        switch (platformNameFromEnv) {
            case ANDROID_PLATFORM:
                driver = getAndroidDriver();
                break;
            case IOS_PLATFORM:
                driver = getIOSDriver();
                break;
            case MOBILE_WEB_PLATFORM:
                driver = getMobileWebChromeDriver();
                break;
            default:
                throw new IllegalArgumentException("Из переменной окружения '" + ENV_PLATFORM_NAME + "' не удалось определить тестируемую платформу: '" +
                        platformNameFromEnv + "'");
        }

        return driver;
    }

    private AndroidDriver<AndroidElement> getAndroidDriver() throws Exception {
        return new AndroidDriver<>(new URL(APPIUM_URL), getAndroidCapabilities());
    }

    private IOSDriver<IOSElement> getIOSDriver() throws Exception {
        return new IOSDriver<>(new URL(APPIUM_URL), getIOSCapabilities());
    }

    private ChromeDriver getMobileWebChromeDriver() {
        return new ChromeDriver(getMobileWebChromeOptions());
    }

    private DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "and80");
        capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.wikipedia");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".main.MainActivity");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/admin/IdeaProjects/MobileAppAutomator/MobileAppAutomator-Ex17/apks/org.wikipedia.apk");

        return capabilities;
    }

    private DesiredCapabilities getIOSCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");
        capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/admin/IdeaProjects/MobileAppAutomator/MobileAppAutomator-Ex17/apks/wikipedia.app");

        return capabilities;
    }

    private ChromeOptions getMobileWebChromeOptions() {
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone SE");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        return chromeOptions;
    }

    public boolean isAndroid() {
        return isPlatform(ANDROID_PLATFORM);
    }

    public boolean isIOS() {
        return isPlatform(IOS_PLATFORM);
    }

    public boolean isWebMobile() {
        return isPlatform(MOBILE_WEB_PLATFORM);
    }

    private boolean isPlatform(String platformName) {
        String platformNameFromEnv = getPlatformNameFromEnv();
        return platformName.equals(platformNameFromEnv);
    }

    public String getPlatformNameFromEnv() {
        return System.getenv(ENV_PLATFORM_NAME);
    }
}
