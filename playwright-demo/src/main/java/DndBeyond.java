import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class DndBeyond {
    public static void main (String[] args){
        Playwright playwright = Playwright.create();

        Browser firefox = playwright.firefox().launch();

        Page page = firefox.newPage();

        page.navigate("https://www.dndbeyond.com");

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("dnd.png")));

        String title_string = page.title();
        String[] split_title = title_string.split(" ");

        System.out.println(split_title[0]);

        playwright.close();
    }
}
