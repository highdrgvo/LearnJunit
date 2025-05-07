package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Webtest {

    @BeforeEach // это предусловие в тест-кейсе
    void setUp() {
        open("https://duckduckgo.com/");
    }


    @ValueSource(strings = {
            "selenide", "JUnit 5"
    })
    // Параметризованный тест - это такой тест, в котором тест-кейсы идентичны, но входные тестовые данные отличаются
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список фото")
    @Tag("BLOCKER")
    void searchResultShouldNotEmpty(String searchQuery) {

        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("#header").find(byText("Images")).click();
        $$(".oc9XKvSI3VFua2OmmSXU").shouldBe(sizeGreaterThan(0));
    }



//    @CsvSource(value = {
//            "selenide, https://selenide.org",
//            "JUnit 5, https://junit.org"
//    })
    @CsvFileSource(resources = "/test_data/searchResultShouldContainExpectedLinkTest")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
    @Tag("BLOCKER")
    void searchResultShouldContainExpectedLinkTest(String searchQuery, String expectedLink) {

        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline']  li[data-layout='organic']")
                .shouldHave(text(expectedLink));
    }

//    @Test
//    @Tag("BLOCKER")
//    @DisplayName("Для поискового запроса 'junit 5' должен отдавать не пустой список ajnj")
//    void searchJUnit5Test() {
//
//        $("#searchbox_input").setValue("junit 5").pressEnter();
//        $("#header").find(byText("Images")).click();
//        $$(".oc9XKvSI3VFua2OmmSXU").shouldBe(sizeGreaterThan(0));
//    }
}

