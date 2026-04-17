package base;

import com.microsoft.playwright.*;
import factory.PlaywrightFactory;
import org.testng.annotations.*;

public class BaseTest {

    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        context = PlaywrightFactory.initBrowser();
        page = context.newPage();
    }

    @AfterMethod
    public void tearDown() {
        PlaywrightFactory.tearDown();
    }
}
