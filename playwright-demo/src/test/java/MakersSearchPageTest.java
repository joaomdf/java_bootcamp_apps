import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class MakersSearchPageTest {
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
    void shouldFindSearchResultsForJava() {
        MakersSearchPage searchPage = new MakersSearchPage(page);
        searchPage.navigate();
        searchPage.searchFor("java");
        assertThat(searchPage.getSearchResultsHeading()).containsText("Results for java");
    }

    @Test
    void shouldNotFindSearchResultsForBadger() {
        MakersSearchPage searchPage = new MakersSearchPage(page);
        searchPage.navigate();
        searchPage.searchFor("badger");
        assertThat(searchPage.getSearchResultsHeading()).containsText("No results for badger");
    }
}