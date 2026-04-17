package com.test.PlaywrightDestinationPage;

import com.microsoft.playwright.*;

public class BrowserLaunch {

    public static void main(String[] args) {

        // Create Playwright instance
        try (Playwright playwright = Playwright.create()) {

            // =========================
            // Launch Chrome (Chromium)
            // =========================
            Browser chrome = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false) // set true for headless
                            .setChannel("chrome") // launches installed Chrome
            );

            Page chromePage = chrome.newPage();
            chromePage.navigate("https://www.google.com");
            System.out.println("Chrome Title: " + chromePage.title());

            // =========================
            // Launch Firefox
            // =========================
            Browser firefox = playwright.firefox().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
            );

            Page firefoxPage = firefox.newPage();
            firefoxPage.navigate("https://www.google.com");
            System.out.println("Firefox Title: " + firefoxPage.title());

            // Wait 5 seconds so you can see browsers
            chromePage.waitForTimeout(5000);
            firefoxPage.waitForTimeout(10000);

            // Close browsers
            chrome.close();
            firefox.close();
        }
    }
}