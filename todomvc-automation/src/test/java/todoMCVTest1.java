import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class todoMCVTest1 {
    static Playwright playwright;
    static List<Browser> browsersToTest;
    List<browserParameters2> browsersSetParameters = new ArrayList<>();
    public static List<String> frameworks = Arrays.asList("React","Vue.js","Backbone.js");
    public static List<browserParameters2> final_list = new ArrayList<>();;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browsersToTest = Arrays.asList(
                playwright.chromium().launch(),
                playwright.firefox().launch(),
                playwright.webkit().launch());
        for (String currentFramework : frameworks){
            for (Browser currentBrowser : browsersToTest) {
                final_list.add(new browserParameters2(currentBrowser,currentFramework));
            }
        }
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        for (browserParameters2 l : final_list) {
            browsersSetParameters.add(new browserParameters2(l.browser,l.framework));
        }
    }

    @AfterEach
    void closeContext() {
        for (browserParameters2 browser : browsersSetParameters) {
            browser.browserContext.close();
        }
    }
    public static class MyArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(new Object[]{final_list}).map(Arguments::of);
        }
    }
//    @Test
//    void checkNavigationToMainPage() {
//        for (browserParameters currentBrowser : browsersSetParameters) {
//            todoMCV testMCV = new todoMCV(currentBrowser.page);
//            testMCV.navigate("https://todomvc.com");
//            assertThat(currentBrowser.page).hasURL("https://todomvc.com/");
//        }
//    }

//    @Test
//    void checkCanNavigateToReactPage() {
//        for (browserParameters currentBrowser : browsersSetParameters) {
//            todoMCV testMCV = new todoMCV(currentBrowser.page);
//            testMCV.navigate("https://todomvc.com");
//            testMCV.chooseFramework("React");
//            assertThat(currentBrowser.page).hasURL("https://todomvc.com/examples/react/#/");
//        }
//    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void addToDoItem(browserParameters2 p){
        todoMCV testMCV = new todoMCV(p.page);
        testMCV.navigate("https://todomvc.com");
        testMCV.chooseFramework(p.framework);
        testMCV.addItem("Task 1");
        assertThat(testMCV.listLocator()).hasText("Task 1");
    }
//
//    @Test
//    void addSeveralToDoItems() {
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.navigate("https://todomvc.com");
//                testMCV.chooseFramework(currentFramework);
//                testMCV.addItem("Task 1");
//                testMCV.addItem("Task 2");
//                testMCV.addItem("Task 3");
//                assertThat(testMCV.listLocator()).hasText(new String[]{"Task 1", "Task 2", "Task 3"});
//            }
//        }
//    }
//
//    @Test
//    void modifyToDoItem(){
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2"));
//                assertThat(testMCV.itemLocator("Task 1")).isVisible();
//                assertThat(testMCV.itemLocator("Task Z")).isHidden();
//                testMCV.modifyItem("Task 1", "Task Z");
//                assertThat(testMCV.itemLocator("Task 1")).isHidden();
//                assertThat(testMCV.itemLocator("Task Z")).isVisible();
//            }
//        }
//    }
//
//    @Test
//    void tickOffToDoItems() {
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2"));
//                testMCV.checkItem("Task 2");
//                assertThat(testMCV.listCompleteItems()).hasCount(1);
//                assertThat(testMCV.itemToggle("Task 2")).isChecked();
//            }
//        }
//    }
//
//    @Test
//    void deleteIncompleteItem() {
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2"));
//                assertThat(testMCV.listLocator()).hasCount(2);
//                testMCV.deleteItem("Task 2");
//                assertThat(testMCV.listLocator()).hasCount(1);
//                assertThat(testMCV.itemLocator("Task 1")).isVisible();
//                assertThat(testMCV.itemLocator("Task 2")).isHidden();
//            }
//        }
//    }
//    @Test
//    void deleteCompleteItem() {
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2"));
//                testMCV.checkItem("Task 2");
//                assertThat(testMCV.listCompleteItems()).hasCount(1);
//                testMCV.deleteItem("Task 2");
//                assertThat(testMCV.listCompleteItems()).hasCount(0);
//                assertThat(testMCV.itemLocator("Task 1")).isVisible();
//                assertThat(testMCV.itemLocator("Task 2")).isHidden();
//            }
//        }
//    }
//    @Test
//    void untickCompletedItem() {
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2"));
//                testMCV.checkItem("Task 2");
//                assertThat(testMCV.listCompleteItems()).hasCount(1);
//                assertThat(testMCV.itemToggle("Task 2")).isChecked();
//                testMCV.uncheckItem("Task 2");
//                assertThat(testMCV.listCompleteItems()).hasCount(0);
//                assertThat(testMCV.itemToggle("Task 2")).not().isChecked();
//            }
//        }
//    }
//    @Test
//    void addEmptyToDoItem() {
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("", "        "));
//                currentBrowser.page.getByPlaceholder("What needs to be done?").click();
//                currentBrowser.page.getByPlaceholder("What needs to be done?").press("Enter");
//                assertThat(testMCV.listLocator()).hasCount(0);
//            }
//        }
//    }
//
//    @Test
//    void addSingleCharacterToDoItem() {
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                assertThat(testMCV.listLocator()).hasCount(0);
//                testMCV.baseSetup(currentFramework, List.of("A", "1"));
//                assertThat(testMCV.listLocator()).hasCount(2);
//            }
//        }
//    }
//
//    @Test
//    void escapeModifyToDoItem(){
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2"));
//                assertThat(testMCV.itemLocator("Task 1")).isVisible();
//                assertThat(testMCV.itemLocator("Task Z")).isHidden();
//                testMCV.modifyItemCancel("Task 1", "Task Z");
//                assertThat(testMCV.itemLocator("Task 1")).isVisible();
//                assertThat(testMCV.itemLocator("Task Z")).isHidden();
//            }
//        }
//    }
//
//    @Test
//    void statusCount() {
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2", "Task 3"));
//                assertThat(testMCV.numberItemsStatusBar()).hasText("3 items left");
//                testMCV.checkItem("Task 3");
//                assertThat(testMCV.numberItemsStatusBar()).hasText("2 items left");
//                testMCV.checkItem("Task 2");
//                assertThat(testMCV.numberItemsStatusBar()).hasText("1 item left");
//                testMCV.checkItem("Task 1");
//                assertThat(testMCV.numberItemsStatusBar()).hasText("0 items left");
//            }
//        }
//    }
//
//    @Test
//    void testFilteringOption(){
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2", "Task 3", "Task 4", "Task 5"));
//                testMCV.checkItem("Task 3");
//                testMCV.checkItem("Task 5");
//
//                testMCV.chooseFilter("Active");
//                assertThat(currentBrowser.page).hasURL(Pattern.compile(".*#/active$", Pattern.CASE_INSENSITIVE));
//                assertThat(testMCV.listLocator()).hasCount(3);
//
//                testMCV.chooseFilter("Completed");
//                assertThat(currentBrowser.page).hasURL(Pattern.compile(".*#/completed$", Pattern.CASE_INSENSITIVE));
//                assertThat(testMCV.listLocator()).hasCount(2);
//
//                testMCV.chooseFilter("All");
//                assertThat(currentBrowser.page).hasURL(Pattern.compile(".*#/$", Pattern.CASE_INSENSITIVE));
//                assertThat(testMCV.listLocator()).hasCount(5);
//            }
//        }
//    }
//    @Test
//    void checkAllTest(){
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2"));
//                testMCV.clickCheckAll();
//                assertThat(testMCV.listCompleteItems()).hasCount(2);
//                testMCV.clickCheckAll();
//                assertThat(testMCV.listCompleteItems()).hasCount(0);
//            }
//        }
//    }
//    @Test
//    void addItemAccentedChar() {
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("à, è, ì, ò, ù, À, È, Ì, Ò, Ù, á, é, í, ó, ú, ý, Á, É, Í, Ó, Ú, , ñ, õ,  ç, Ç, ð, Ð, ø, Ø, ß, ₽, ₩, ¥, £, €, ¢, $"));
//                assertThat(testMCV.listLocator()).hasText("à, è, ì, ò, ù, À, È, Ì, Ò, Ù, á, é, í, ó, ú, ý, Á, É, Í, Ó, Ú, , ñ, õ,  ç, Ç, ð, Ð, ø, Ø, ß, ₽, ₩, ¥, £, €, ¢, $");
//            }
//        }
//    }
//    @Test
//    void addItemEmojis() {
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("😀 😃 😄 😁 😆 😅 😂 🤣 🥲 🥹 ☺️ 😊 😇 🙂 🙃 😉 😌 😍 🥰 😘 😗 😙 😚 😋 😛 😝 😜 🤪 🤨 🧐 🤓 😎 🥸 🤩 🥳 😏 😒 😞 😔 😟 😕 🙁 ☹️ 😣 😖 😫 😩 🥺 😢 😭 😮‍💨 😤 😠 😡 🤬 🤯 😳 🥵 🥶 😱 😨 😰 😥 😓 🫣 🤗 🫡 🤔 🫢 🤭 🤫 🤥 😶  😐 😑 😬 🫠 🙄 😯 😦 😧 😮 😲 🥱 😴 🤤 😪 😵 😵‍💫 🫥 🤐 🥴 🤢 🤮 🤧 😷 🤒 🤕 🤑 🤠 😈 👿 👹 👺 🤡 💩 👻 💀 ☠️ 👽 👾 🤖 🎃 😺 😸 😹 😻 😼 😽 🙀 😿 😾"));
//                assertThat(testMCV.listLocator()).hasText("😀 😃 😄 😁 😆 😅 😂 🤣 🥲 🥹 ☺️ 😊 😇 🙂 🙃 😉 😌 😍 🥰 😘 😗 😙 😚 😋 😛 😝 😜 🤪 🤨 🧐 🤓 😎 🥸 🤩 🥳 😏 😒 😞 😔 😟 😕 🙁 ☹️ 😣 😖 😫 😩 🥺 😢 😭 😮‍💨 😤 😠 😡 🤬 🤯 😳 🥵 🥶 😱 😨 😰 😥 😓 🫣 🤗 🫡 🤔 🫢 🤭 🤫 🤥 😶  😐 😑 😬 🫠 🙄 😯 😦 😧 😮 😲 🥱 😴 🤤 😪 😵 😵‍💫 🫥 🤐 🥴 🤢 🤮 🤧 😷 🤒 🤕 🤑 🤠 😈 👿 👹 👺 🤡 💩 👻 💀 ☠️ 👽 👾 🤖 🎃 😺 😸 😹 😻 😼 😽 🙀 😿 😾");
//            }
//        }
//    }
//
//    @Test
//    void checkStatusBarHidden(){
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2"));
//                assertThat(testMCV.footerLocator()).isVisible();
//                testMCV.deleteItem("Task 1");
//                testMCV.deleteItem("Task 2");
//                assertThat(testMCV.footerLocator()).isHidden();
//            }
//        }
//    }
//
//    @Test
//    void checkClearCompleteButtonExists(){
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2"));
//                assertThat(testMCV.clearCompleteButton()).isHidden();
//                testMCV.checkItem("Task 1");
//                assertThat(testMCV.clearCompleteButton()).isVisible();
//            }
//        }
//    }
//    @Test
//    void checkClearCompleteButtonClearsItems(){
//        for (String currentFramework : parameters.listOfFrameworks) {
//            System.out.println(currentFramework);
//            for (browserParameters currentBrowser : browsersSetParameters) {
//                todoMCV testMCV = new todoMCV(currentBrowser.page);
//                testMCV.baseSetup(currentFramework, List.of("Task 1", "Task 2"));
//                testMCV.checkItem("Task 1");
//                assertThat(testMCV.listCompleteItems()).hasCount(1);
//                testMCV.clearCompleteButton().click();
//                assertThat(testMCV.listCompleteItems()).hasCount(0);
//                assertThat(testMCV.itemLocator("Task 1")).isHidden();
//            }
//        }
//    }
}