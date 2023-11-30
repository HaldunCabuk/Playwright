package example.base;

import com.microsoft.playwright.*;
import example.pageObject.ProductPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseTest {
  protected  Playwright playwright;
  protected Browser browser;
  protected Page page;

  public BaseTest(){
      playwright = Playwright.create();
      browser = playwright.chromium().launch(
              new BrowserType.LaunchOptions()
                      .setHeadless(false)
                      .setChannel("chrome")
                      .setSlowMo(250)
      );
      page = browser.newPage();
  }



}
