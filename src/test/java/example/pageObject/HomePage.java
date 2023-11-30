package example.pageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import example.base.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage extends BaseTest {

    Locator username = page.locator("//input[@name='user-name']");
    Locator password = page.locator("//input[@name='password']");
    Locator login = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
    Locator productTitle = page.locator("//span[@class='title' and text()='Products']");

    public void login(String un, String pwd){

        username.fill(un);
        password.fill(pwd);
        login.click();
    }

    public void navigateToHp(String url){

        page.navigate(url);
    }

    public ProductPage textIsVisible(String text){

        Locator locator = page.getByText(text);
        assertThat(locator).isVisible();
        return new ProductPage(page);
    }
}
