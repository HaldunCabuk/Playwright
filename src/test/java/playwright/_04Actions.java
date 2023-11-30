package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.MouseButton;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static utils.Utils.sleep;

public class _04Actions {

    Playwright playwright;
    Browser browser;

    Page page;

    @BeforeTest
    public void beforeTest(){
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
    public void afterTest(){
        sleep(3);
        page.close();
        browser.close();
        playwright.close();
    }


    @Test
    public void dragDrop(){

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
    public void mouseAction(){

        page.navigate("https://demoqa.com/buttons");
        sleep(2);
        Locator doubleClick = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Double Click Me"));

        doubleClick.dblclick();
        sleep(3);

        Locator rightClick = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Right Click Me"));
        rightClick.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        sleep(3);

        Locator clickMe = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Click Me").setExact(true));
        sleep(3);
    }
}