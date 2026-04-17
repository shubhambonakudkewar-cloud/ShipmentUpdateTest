package pages;

import com.microsoft.playwright.Page;

public class HubVerificationPage {

    private Page page;

    public HubVerificationPage(Page page) {
        this.page = page;
    }

    public String getFinalHub(String shipmentId) {

        page.fill("#shippingID", shipmentId);
        page.click("text=Show Shipment Log");

        page.waitForSelector("table tbody tr");

        return page.locator("table tbody tr")
                .first()
                .locator("td")
                .nth(10)
                .innerText()
                .trim();
    }
}
