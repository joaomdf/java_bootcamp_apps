import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class MakersChallengeRecordTest {
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
        context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos")));
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void makersCheck() {
        page.navigate("https://makers.tech/");
        assertThat(page).hasTitle(Pattern.compile("Change Your Life"));
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Code of Conduct"))).isVisible();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Code of Conduct")).click();
        assertThat(page).hasURL("https://makers.tech/code-of-conduct/");
        assertThat(page).hasTitle(Pattern.compile("Code of Conduct"));
        page.goBack();
        assertThat(page).hasURL("https://makers.tech/");
        page.locator("#menu-item-1195").getByText("FAQs").click();
        assertThat(page).hasURL("https://faq.makers.tech/en/knowledge");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search for answers")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search for answers")).fill("badger");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search for answers")).press("Enter");
        assertThat(page.locator("h1.kb-search-results__heading")).containsText("No results for badger");
    }
}