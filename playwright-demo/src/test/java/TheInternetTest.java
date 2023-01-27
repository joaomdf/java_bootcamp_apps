import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TheInternetTest {
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
    void downloadFile() throws IOException {
        page.navigate("https://the-internet.herokuapp.com");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setExact(true).setName("File Download")).click();
        Download myFile = page.waitForDownload(() -> page.getByText("some-file.txt").click());
        myFile.saveAs(Paths.get("/Users/joaofernandes/Downloads/my-file.txt"));

        File file = new File("/Users/joaofernandes/Downloads/my-file.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
    }
}