package example.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Driver {

    public static Playwright playwright;
    public static Browser browser;
    public static Page page;


    private static void initialize(){
        if (playwright==null)
            playwright = Playwright.create();

        if (browser==null){
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setChannel("chrome")
                            .setSlowMo(500)
            );
        }

        if (page==null)
            page = browser.newPage();
    }

    public static Page getPage(){
        initialize();
        return page;
    }


    public static Browser getBrowser(){
        initialize();
        return browser;
    }
}
