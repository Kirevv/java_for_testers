package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        String username = CommonFunctions.randomString(5);
        var email = String.format("%s@localhost", username);

        //создать пользователя на почтовом сервере (JamesHelper)
        app.jamesCli().addUser(email, "password");

        //заполняем форму создания и отправляем (браузер)
        app.session().signup(username, email);

        //ждем почту (MailHelper)
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(30));

        //извлекаем ссылку из письма
        var url = CommonFunctions.extractUrl(messages.get(0).content());

        //переходим по ссылке и завершаем регистрацию (браузер)
        app.session().finishRegistration(url, username, "password");

        //проверяем, что пользователь может залогиниться(HttpSessionHelper)
        app.session().login(username, "password");
        Assertions.assertTrue(app.session().isLoggedIn());
    }
}
