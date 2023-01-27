import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class GovUk {
    private Page page;
    private Locator searchBox;
    private Locator searchGo;
    private Locator topResult;
    private Locator dropDown;

    public GovUk (Page page){
        this.page = page;
        this.searchBox = page.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setExact(true).setName("Search"));
        this.searchGo = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setExact(true).setName("Search GOV.UK"));
        this.topResult = page.locator("ul.gem-c-document-list>li").first();
//        this.topResult = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions()).nth(2).first();
        this.dropDown = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sort by"));

    }

    public void navigate(){
        page.navigate("https://www.gov.uk");
    }
    public void searchFor(String searchTerm){
        searchBox.fill(searchTerm);
        searchGo.click();
    }
    public Locator topResult(){
        return topResult;
    }
    public void clickTopResult(String top){
        topResult = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setExact(true).setName(top));
        topResult.click();
    }
    public void changeDropDownBox(String option){
        this.dropDown.selectOption(option);
    }
    public Locator dropDownBox(){
        return this.dropDown;
    }
    public void removeCookies(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setExact(true).setName("Reject additional cookies")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setExact(true).setName("Hide this message")).click();
    }
}