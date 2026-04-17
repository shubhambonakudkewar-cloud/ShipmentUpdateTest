package com.test.PlaywrightDestinationPage;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class HubopsLogin {

    public static void main(String[] args) {

        //Create Playwright instance
        Playwright playwright = Playwright.create();

        //Launch browser (headed mode so you can see it)
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );
        
        BrowserContext context2 = browser.newContext();
        Page page2 = context2.newPage();

        // Login to Verification Portal
        page2.navigate("https://stage-huboperationapp.xbees.in");

        page2.fill("//*[@id=\"username\"]", "shubham.bonakudkewar@xpressbees.com");
        page2.fill("//*[@id=\"password\"]", "Dodo@123456789");
        page2.click("//*[@id=\"kc-login\"]");
        page2.waitForTimeout(5000);

        // Search shipment
        page2.locator("div.nav-link.nav-dropdown-toggle:has-text('Report')").click();
        page2.waitForSelector("a[href='#/BagShipmentQuery']");
        page2.click("text=Bag / Shipment Query");
        page2.fill("//*[@id=\"shippingID\"]", "125581575806");
        
        page2.waitForSelector("button:has-text('Show Shipment Log')");
        page2.locator("button:has-text('Show Shipment Log')").click();

     // Wait until table loads
        page2.waitForSelector("table tbody tr");

     // Expected destination hub
        String expectedDestinationHub = "DummyHUB(516)";

        // Locate first row (or you can locate by shipment ID – shown below)
        Locator finalDestinationCell = page2
                .locator("table tbody tr")
                .first()
                .locator("td")
                .nth(10); // 11th column → Final Destination

        // Read actual value
        String actualDestinationHub = finalDestinationCell.innerText().trim();

        // Validation
        if (actualDestinationHub.equals(expectedDestinationHub)) {
            System.out.println("✅ Final Destination updated successfully: " + actualDestinationHub);
        } else {
            System.out.println("❌ Final Destination NOT updated. Expected: "
                    + expectedDestinationHub + ", but found: " + actualDestinationHub);
        }

        browser.close();
        playwright.close();
    }
       
   }
