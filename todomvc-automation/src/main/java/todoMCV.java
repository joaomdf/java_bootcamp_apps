import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import java.util.List;
public class todoMCV {
    private Page page;
    public todoMCV(Page page) {
        this.page = page;
    }

    public void navigate(String link) {
        page.navigate(link);
    }
    public void chooseFramework(String framework){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setExact(true).setName(framework)).click();
    }
    public void addItem(String task){
        Locator textBox = page.getByPlaceholder("What needs to be done?");
        textBox.click();
        textBox.fill(task);
        textBox.press("Enter");
    }
    public void baseSetup(String framework, List<String> listOfStrings){
        navigate("https://todomvc.com");
        chooseFramework(framework);
        for (String item : listOfStrings) {
            addItem(item);
        }
    }

//    public void baseSetup(String framework, String task1, String task2){
//        navigate(framework);
//        addItem(task1);
//        addItem(task2);
//    }
    public void modifyItem(String oldTask, String newTask){
        itemLocator(oldTask).dblclick();
        itemLocator(oldTask).locator("input.edit").fill(newTask);
        if (itemLocator(newTask).isVisible()) {
            itemLocator(newTask).press("Enter");
        } else if (itemLocator(oldTask).isVisible()) {
            itemLocator(oldTask).press("Enter");
        }
    }
    public Locator itemLocator(String text){
        Locator itemlocator = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText(text));
        return itemlocator;
    }
    public void deleteItem(String text){
        itemLocator(text).hover();
        itemLocator(text).locator("button.destroy").click();
    }
    public void checkItem(String text){
        itemLocator(text).locator("input.toggle").setChecked(true);
    }
    public void uncheckItem(String text){
        itemLocator(text).locator("input.toggle").setChecked(false);
    }
    public Locator listLocator(){
        return page.locator("ul.todo-list > li");
    }
    public Locator listCompleteItems(){
        return page.locator("li.completed");
    }
    public Locator itemToggle(String text){
        return itemLocator(text).locator("input.toggle");
    }
    public void modifyItemCancel(String oldTask, String newTask){
        itemLocator(oldTask).dblclick();
        itemLocator(oldTask).locator("input.edit").fill(newTask);
        if (itemLocator(newTask).isVisible()) {
            itemLocator(newTask).press("Escape");
        } else if (itemLocator(oldTask).isVisible()) {
            itemLocator(oldTask).press("Escape");
        }
    }
    public Locator numberItemsStatusBar(){
        return page.locator("span.todo-count");
    }
    public void chooseFilter(String option){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(option)).click();
    }
    public void clickCheckAll(){
        page.locator("label").first().click();
    }
    public Locator footerLocator(){
        return page.locator("footer.footer");
    }
    public Locator clearCompleteButton(){
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setExact(true).setName("Clear completed"));
    }
}

