package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class Testbase {

    @BeforeAll
    static void actionsBeforeTestingRegistrationForm() {
        Configuration.baseUrl = "https://kixbox.ru/collection/new-arrivals";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }
}
