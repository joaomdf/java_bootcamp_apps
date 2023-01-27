import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import java.nio.file.Paths;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class TodoTest {
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

    // in TodoTest.java

    @Test
    void shouldLoadHomepage() {
        page.navigate("https://todomvc.com");
        Locator githubButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("View on GitHub"));
        System.out.println(githubButton.textContent());
        Locator javascriptTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("JavaScript"));
        System.out.println(javascriptTab.textContent());
    }

    @Test
    void shouldGoOnReactPage(){
        page.navigate("https://todomvc.com");
        Locator reactLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setExact(true).setName("React"));
        reactLink.click();
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("react.png")));
        System.out.println(page.title());
    }

    @Test
    void checkActualFunctionality(){
        page.navigate("https://todomvc.com/examples/react/#/");
        assertThat(page).hasTitle("React • TodoMVC");
        Locator todoInput = page.getByPlaceholder("What needs to be done?");

        todoInput.fill("Buy some milk");
        todoInput.press("Enter");
        Locator milk = page.locator("ul.todo-list > li").nth(0);

        todoInput.fill("Buy some eggs");
        todoInput.press("Enter");
        Locator eggs = page.locator("ul.todo-list > li").nth(1);

        assertThat(milk).hasText("Buy some milk");
        assertThat(eggs).hasText("Buy some eggs");
        assertThat(page.locator("span.todo-count")).hasText("2 items left");

        milk.locator("input.toggle").check();
        assertThat(page.locator("span.todo-count")).hasText("1 item left");

        eggs.locator("input.toggle").check();
        assertThat(page.locator("span.todo-count")).hasText("0 items left");
    }
}