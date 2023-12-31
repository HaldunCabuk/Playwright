package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class _01CreatePlaywright {

    @Test
    public void createPlaywright(){

        // playwright create eedilmeli
        Playwright playwright = Playwright.create();

        // browser olusturulur
        // chromuim(chrome, edge), firefox, webkit
        Browser browser = playwright.chromium().launch();

        // page olusturulur
        Page page = browser.newPage();

        // navigate
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        browser.close();

        playwright.close();



    }
}
