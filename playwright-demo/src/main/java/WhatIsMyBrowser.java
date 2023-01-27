import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WhatIsMyBrowser {
    public static void main(String[] args){

        Playwright playwright = Playwright.create();

        List<BrowserType> browsersToTest = Arrays.asList(playwright.firefox(),playwright.webkit());

        for (BrowserType browserType: browsersToTest){
            try (Browser browser = browserType.launch()) {
                System.out.println("Running in " + browserType.name() + "...");
                Page page = browser.newPage();
                page.navigate("https://www.whatismybrowser.com/");
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot-" + browserType.name() + ".png")));
            }
        }
        playwright.close();
    }
}
