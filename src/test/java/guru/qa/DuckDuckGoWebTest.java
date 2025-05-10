package guru.qa;

import guru.qa.data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DuckDuckGoWebTest {

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
//            "Selenide, https://selenide.org, 40.23, false", // если у нас больше 2 значений, можно таким образом передать параметры
//            "JUnit 5, https://junit.org"
//    })


    @CsvFileSource(resources = "/test_data/searchResultShouldContainExpectedLinkTest")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}") // {0} - это из ресурсов берется значение с индектом 0 (selenide), индекс {1} - это https://selenide.org
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

