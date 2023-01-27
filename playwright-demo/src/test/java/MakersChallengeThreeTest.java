import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MakersChallengeThreeTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
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
    void navigateToPageCodeOfConduct(){
        page.navigate("https://makers.tech");
        assert(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Code of Conduct"))).isVisible();
    }

    @Test
    void mobileResolutionCodeOfConduct(){
        page.setViewportSize(390, 840);
        page.navigate("https://makers.tech");
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Code of Conduct"))).isHidden();
    }
}

