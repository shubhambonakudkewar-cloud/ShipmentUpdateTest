package pages;

import com.microsoft.playwright.Page;

public class DestinationHubPage {

    private Page page;

    public DestinationHubPage(Page page) {
        this.page = page;
    }

    public void changeHub(String shipmentId, String hub) {

        page.click("text=Change Destination Hub");

        page.fill("input[placeholder='Enter Shipment ID/AWB No.']", shipmentId);

        page.locator(".react-select__control").click();
        page.keyboard().type(hub);
        page.keyboard().press("Enter");

        page.fill("textarea", "Automation Test");
        page.click("button[type='submit']");
    }
}
