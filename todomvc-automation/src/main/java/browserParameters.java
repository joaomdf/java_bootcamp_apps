import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

import java.util.Arrays;
import java.util.List;


public class browserParameters {
    public Browser browser;
    public BrowserContext browserContext;
    public Page page;
    public browserParameters(Browser browser) {
        this.browser = browser;
        this.browserContext = browser.newContext();
        this.page = browserContext.newPage();
    }
}
