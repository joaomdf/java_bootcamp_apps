import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import java.util.regex.Pattern;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;
public class MakersChallengeTwoTest {
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
    void makersPageTest(){
        playwright.selectors().setTestIdAttribute("id");
        page.navigate("https://makers.tech");
        page.pause();
        assertThat(page).hasTitle(Pattern.compile("Change Your Life"));
        Locator codeOfConduct = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Code of Conduct"));
        assertThat(codeOfConduct).isVisible();
        codeOfConduct.click();
        assertThat(page).hasURL("https://makers.tech/code-of-conduct/");
        assertThat(page).hasTitle(Pattern.compile("Code of Conduct"));
        page.goBack();
        assertThat(page).hasURL("https://makers.tech/");
        page.getByTestId("menu-item-1195").click();
        assertThat(page).hasURL("https://faq.makers.tech/en/knowledge");
        Locator searchBox = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search for answers"));
        searchBox.fill("badger");
        searchBox.press("Enter");
        assertThat(page.locator("h1.kb-search-results__heading")).containsText("No results for badger");
    }
}