package guru.qa;

import org.junit.jupiter.api.*;

@DisplayName("Тесты на email рассылку")
// Можно тут тег SMOKE также поставить и все тесты попадут в SMOKE

public class EmailTest {

    @Test
    @DisplayName("Email should be sent for new user")
    void emailShouldBeSentForNewUser() {
        System.out.println("Hello world");
    }

    @Test
    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB")
    })
    @DisplayName("Email should be sent for banned user")
    void emailShouldBeSentForBannedUser() {

        System.out.println("Hello world");
    }

    @Disabled("Bug name") // Если тест нашел баг, но он не крит и надо катиться в прод, то мы пишем disabled и в скобках прикрепляем ссылку/id на баг в трекере, чтобы не забыть про него. Также у нас все ок будет в CI.
    @Test
    @Tag("SMOKE") // к примеру нам надо прогнать только смоук тесты , просто понять, что ключевой функционал работает.
    @DisplayName("Email should be sent if payment method will be changed")
    void emailShouldBeSentAfterChangePaymentMethod() {

        throw new AssertionError("Failed");
    }
}
