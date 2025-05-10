package guru.qa;

import guru.qa.data.Language;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideWebTest {

    @EnumSource(Language.class)
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectTextTest(Language language) {

        open("https://selenide.org/");
        $$("#languages a").findBy(text(language.name())).click();
        $("h3").shouldHave(text(language.description));
    }

    // Это просто надо знать наизусть!!! Тип метода (модификатор) должен быть static, тип возвращаемого значения
    // стрим (последовательность) аргументов, название копируем из названия теста
    // Arguments of 2 раза, потому что мы хотим два тестовых набора проверить и для RU и для EN
    // Почему мы назвали метод как тест? Потому что за счет названия Junit находит этот метод. Если мы хотим дать другое
    // название методу, то это явно надо прописать в @MethodSource("dataProvider")
    // Этот метод всегда должен быть рядом с тестом
    static Stream<Arguments> selenideSiteShouldDisplayCorrectButtons() {
        return Stream.of(
                Arguments.of(
                        Language.EN,
                        List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(
                        Language.RU,
                        List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"))
                );
    }

    @MethodSource // самая мощная аннотация, в которую мы можем передать кучу данных
    @EnumSource(Language.class) // Enum возвращает объекты. Нам пришел объект (RU/EN), мы взяли его имя и описание.
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectButtons(Language language, List<String> expectedButtons) {

        open("https://selenide.org/");
        $$("#languages a").findBy(text(language.name())).click();
        $$(".main-menu-pages a").filter(visible).shouldHave(texts(expectedButtons));

    }
}
