package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class ShopKixBoxPageNewArrivalsTest {

    @BeforeAll
    static void actionsBeforeTestingShopKixBox() {
        Configuration.baseUrl = "https://kixbox.ru";
    }


    @ValueSource(strings = {
            "Новинки"
    })
    @ParameterizedTest(name = "На странице отображается заголовок {0}")
    @Tag("MAJOR")
    void displayHeaderNewArrivalsPageTest(String expectedTitle) {
        open("/collection/new-arrivals");
        $("h1.page-headding").shouldBe(visible).shouldHave(text(expectedTitle));
    }


    @CsvFileSource(resources = "/test_data/checkOpenNeededSection")
    @ParameterizedTest(name = "При открытии раздела {0} с категорией {1} отображается ссылка {2}")
    @Tag("MAJOR")
    void CheckDisplayUrlTest(String gender, String category, String url) {

        actions()
                .moveToElement($("nav.header-part-menu").find(byText(gender)))
                .pause(400)
                .perform();
        actions()
                .moveToElement($("nav.header-part-menu").find(byText(category))).click()
                .perform();
        webdriver().shouldHave(url(url));
        actions()
                .pause(500)
                .perform();
    }


    static Stream<Arguments> kixBoxHasCorrectSectionsMainPageFooterTest() {
        return Stream.of(
                Arguments.of(
                        List.of("О НАС", "ПОМОЩЬ ПОКУПАТЕЛЮ", "СОЦИАЛЬНЫЕ СЕТИ"))
        );
    }

    @ParameterizedTest
    @MethodSource("kixBoxHasCorrectSectionsMainPageFooterTest")
    @Tag("MAJOR")
    void kixBoxHasCorrectSectionsMainPageFooterTest(List<String> expectedSections) {
        open("https://kixbox.ru/");
        $$("div.menu-title").shouldHave(exactTexts(expectedSections));

    }
}