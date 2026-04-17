package factory;

import com.microsoft.playwright.*;
import config.ConfigReader;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;

    public static BrowserContext initBrowser() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(ConfigReader.getBoolean("headless"))
        );

        return browser.newContext();
    }

    public static void tearDown() {
        browser.close();
        playwright.close();
    }
}
