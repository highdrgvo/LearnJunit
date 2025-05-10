package guru.qa;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShopKixBoxPageNewArrivalsTest extends Testbase {

    @ValueSource(strings = {
            "selenide", "JUnit 5"
    })
    // Параметризованный тест - это такой тест, в котором тест-кейсы идентичны, но входные тестовые данные отличаются
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список фото")
    @Tag("BLOCKER")
    void DropdownListsExistPageNewArrivalsTest(String dropDownlist) {

        $(".filter__items").shouldBe(visible);
        $("#header").find(byText("Images")).click();
        $$(".oc9XKvSI3VFua2OmmSXU").shouldBe(sizeGreaterThan(0));
    }
}
