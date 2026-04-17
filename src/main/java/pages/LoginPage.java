package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String url, String user, String pass) {
        page.navigate(url);
        page.fill("#username", user);
        page.fill("#password", pass);
        page.click("#kc-login");
    }
}
