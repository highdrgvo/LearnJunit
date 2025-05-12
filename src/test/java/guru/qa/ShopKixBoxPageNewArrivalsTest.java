package guru.qa;

import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.files.DownloadActions.click;

public class ShopKixBoxPageNewArrivalsTest {

    @BeforeAll
    static void actionsBeforeTestingNewArrivalsPage() {
        open("https://kixbox.ru/collection/new-arrivals");
    }

    @ValueSource(strings = {
            "Новинки"
    })
    @ParameterizedTest(name = "На странице отображается заголовок {0}")
    @Tag("MAJOR")
    void headerNewArrivalsPageTest(String expectedTitle) {
        $(".layout__content").shouldHave(text(expectedTitle));
    }



@CsvFileSource(resources = "/test_data/checkOpenNeededSection")
@ParameterizedTest(name = "При открытии раздела {0} с категорией {1} отображается ссылка {2}")
@Tag("MAJOR")
void CheckOpeningPageDependingCategoryAndGenderTest(String gender, String category, String url) {

    actions()
            .moveToElement($("nav.header-part-menu").find(byText(gender)))
            .pause(400)
            .perform();
    actions().moveToElement($("nav.header-part-menu").find(byText(category))).click().perform();
    webdriver().shouldHave(url(url));
    actions().pause(500).perform();
    }
}

