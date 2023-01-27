import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
public class browserParameters2 {
    public Browser browser;
    public BrowserContext browserContext;
    public Page page;
    public String framework;
    public browserParameters2(Browser browser, String framework) {
        this.browser = browser;
        this.browserContext = browser.newContext();
        this.page = browserContext.newPage();
        this.framework = framework;
    }
}