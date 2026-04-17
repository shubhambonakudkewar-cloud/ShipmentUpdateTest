package com.test.PlaywrightDestinationPage;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ServiceTypeChange {

    public static void main(String[] args) {

        // 1️⃣ Create Playwright instance
        Playwright playwright = Playwright.create();

        // 2️⃣ Launch browser (headed mode so you can see it)
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );

        // 3️⃣ Create browser context (clean session)
        BrowserContext context = browser.newContext();

        // 4️⃣ Open a new page (tab)
        Page page = context.newPage();

        // 5️⃣ Navigate to application URL
        page.navigate("https://app-newstaging-shipmentupdates.xbees.in/");

        // 6️⃣ Login actions (UPDATE LOCATORS)
        page.fill("//*[@id=\"username\"]", "shubham.bonakudkewar@xpressbees.com");
        page.fill("//*[@id=\"password\"]", "Dodoshubham@2495");
        page.click("//*[@id=\"kc-login\"]");
        
        // Change Service type of a shipment
        page.click("//*[@id=\"root\"]/div/div/div[1]/nav/div/div/ul/li[2]/a/span[2]");
        page.fill("//*[@id=\"shippingId\"]", "125581575806");
        page.click("//*[@id=\"root\"]/div/div/div[2]/div/div[1]/div[3]/button");
        page.selectOption("//*[@id=\"ServiceType\"]", "SD");
        page.fill("//*[@id=\"comments\"]", "This is the test shipment on stage, i want to change its service type");
        page.click("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div[2]/div/button");

        page.waitForTimeout(5000);
        
        //CONTEXT 2 – VERIFICATION
   
     BrowserContext context2 = browser.newContext();
     Page page2 = context2.newPage();

     // Login to Verification Portal
     page2.navigate("https://stage-huboperationapp.xbees.in");

     page2.fill("//*[@id=\"username\"]", "shubham.bonakudkewar@xpressbees.com");
     page2.fill("//*[@id=\"password\"]", "Dodoshubham@2495");
     page2.click("//*[@id=\"kc-login\"]");
     page.waitForTimeout(5000);

     // Search shipment
     page2.locator("div.nav-link.nav-dropdown-toggle:has-text('Report')").click();
     page2.waitForSelector("a[href='#/BagShipmentQuery']");
     page2.click("text=Bag / Shipment Query");
     page2.fill("//*[@id=\"shippingID\"]", "125581575806");
     
     page2.waitForSelector("button:has-text('Show Shipment Log')");
     page2.locator("button:has-text('Show Shipment Log')").click();

  // Wait until table loads
     page2.waitForSelector("table tbody tr");

  // Locate Service Type cell expecting SDD
     Locator serviceTypeSDD = page2.locator(
         "table tbody tr td",
         new Page.LocatorOptions().setHasText("SDD")
     );

     // Give table time to load (safe wait)
     page2.waitForTimeout(2000);

     // Assertion using count()
     if (serviceTypeSDD.count() > 0) {
         System.out.println("✅ Service Type successfully updated");
     } else {
         System.out.println("❌ Service Type NOT updated");
     }


     browser.close();
     playwright.close();
 }
    
}


