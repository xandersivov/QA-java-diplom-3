import org.apache.commons.lang3.RandomStringUtils;

public class User {
    //Переменные для входа пользователя
    public static final String EMAIL = "testUserSivov@yandex.ru";
    public static final String PASSWORD = "123456";

    //Переменные для регистрации пользователя
    public final String RANDOM_NAME = RandomStringUtils.randomAlphabetic(10);
    public final String RANDOM_EMAIL = RANDOM_NAME + "@yandex.ru";
}
