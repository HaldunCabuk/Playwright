package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.MouseButton;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static utils.Utils.sleep;

public class _04Actions {

    Playwright playwright;
    Browser browser;

    Page page;

    @BeforeTest
    public void beforeTest() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setChannel("chrome")
                        .setHeadless(false)
                        .setSlowMo(1000)
        );

        page = browser.newPage();

    }


    @AfterTest
    public void afterTest() {
        sleep(3);
        page.close();
        browser.close();
        playwright.close();
    }


    @Test
    public void dragDrop() {

        page.navigate("https://demoqa.com/droppable");
        sleep(2);

        String source = "#draggable";
        String target = "#droppable";
        page.dragAndDrop(source, target);
        sleep(3);

        page.reload();
        page.dragAndDrop(source, target,
                new Page.DragAndDropOptions()
                        .setTargetPosition(70, 10)
        );
        sleep(3);

        page.reload();
        page.dragAndDrop(source, target,
                new Page.DragAndDropOptions()
                        .setTargetPosition(70, 100)
        );
        sleep(3);


    }

    @Test
    public void mouseAction() {

        page.navigate("https://demoqa.com/buttons");
        sleep(2);
        Locator doubleClick = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Double Click Me"));

        doubleClick.dblclick();
        sleep(3);

        Locator rightClick = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Right Click Me"));
        rightClick.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        sleep(3);

        Locator clickMe = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Click Me").setExact(true));
        sleep(3);
    }

    @Test
    public void iframe() {
        page.navigate("https://the-internet.herokuapp.com/iframe");
        Locator editor = page.frameLocator("iframe").locator("#tinymce");
       // editor.fill("Java");

        editor.focus();

        page.keyboard().type("Java");
    }

    @Test
    public void windows() {

        BrowserContext context = browser.newContext();
        Page page1 = context.newPage();
        page1.navigate("https://the-internet.herokuapp.com/windows");

        Locator locator = page1.locator(".example > a");

        Page page2 = context.waitForPage(() -> {

                    locator.click(); // acilan sayfayi page2 ye atadik

                }
        );

        page2.waitForLoadState(); // Sayfanin yuklenmesini bekliyoruz

        assertThat(page2.locator("//h3[text()='New Window']")).isVisible();

        sleep(2);
        page2.close();
        sleep(2);
        page1.close();
        //page.bringToFront();

    }
}