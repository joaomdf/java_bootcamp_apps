import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;
public class GovUkTest {
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
    void checkGovUk(){
        GovUk searchPage = new GovUk(page);
        searchPage.navigate();
        assertThat(page).hasURL("https://www.gov.uk/");
        searchPage.removeCookies();

        searchPage.searchFor("car tax");
        assertThat(searchPage.topResult()).containsText("Tax your vehicle");

        searchPage.clickTopResult("Tax your vehicle");
        assertThat(page).hasURL("https://www.gov.uk/vehicle-tax");
    }

    @Test
    void checkDropdown(){
        GovUk searchPage = new GovUk(page);
        searchPage.navigate();
        searchPage.removeCookies();

        searchPage.searchFor("car tax");
        searchPage.changeDropDownBox("updated-oldest");
        assertThat(searchPage.dropDownBox()).hasValue("updated-oldest");
        assertThat(searchPage.topResult()).containsText("Bangladesh: tax treaties");
    }
}