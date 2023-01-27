import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

public class MakersTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void shouldPrintPageTitle() {
        page.navigate("https://makers.tech");
        System.out.println(page.title());
    }

    @Test
    void shouldCreatePrint(){
        page.navigate("https://makers.tech");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("makers-test.png")));
    }
}
