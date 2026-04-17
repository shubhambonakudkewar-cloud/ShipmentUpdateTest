package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import pages.*;

public class DestinationHubChangeTest extends BaseTest {

    @Test
    public void verifyDestinationHubChange() {

        LoginPage login = new LoginPage(page);

        login.login(
                ConfigReader.get("app.url"),
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        new DestinationHubPage(page)
                .changeHub(ConfigReader.get("shipment.id"),
                        ConfigReader.get("new.hub"));

        Page page2 = context.newPage();

        new LoginPage(page2)
                .login(ConfigReader.get("hub.url"),
                        ConfigReader.get("username"),
                        ConfigReader.get("password"));

        String actual =
                new HubVerificationPage(page2)
                        .getFinalHub(ConfigReader.get("shipment.id"));

        Assert.assertEquals(actual,
                ConfigReader.get("expected.hub"));
    }
}
