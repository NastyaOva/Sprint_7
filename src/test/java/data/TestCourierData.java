package data;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import models.CourierModel;

public class TestCourierData {

    static Faker user = new Faker();

    @Step("Генерация уникального курьера")
    public static CourierModel generationCourier() {
        String loginCourier = user.name().lastName() + user.number().numberBetween(90, 100);
        String passwordCourier = user.regexify("[0-9]{5}");
        String firstnameCourier = user.name().firstName();
        return new CourierModel(loginCourier, passwordCourier, firstnameCourier);
    }

    @Step("Генерация курьера без имени")
    public static CourierModel generationCourierWithoutFirstname() {
        String loginCourier = user.name().lastName() + user.number().numberBetween(90, 100);
        String passwordCourier = user.regexify("[0-9]{5}");
        return new CourierModel(loginCourier, passwordCourier, null);
    }

    @Step("Генерация курьера без логина")
    public static CourierModel generationCourierWithoutLogin() {
        String passwordCourier = user.regexify("[0-9]{5}");
        String firstnameCourier = user.name().firstName();
        return new CourierModel(null, passwordCourier, firstnameCourier);
    }

    @Step("Генерация курьера без пароля")
    public static CourierModel generationCourierWithoutPassword() {
        String loginCourier = user.name().lastName() + user.number().numberBetween(90, 100);
        String firstnameCourier = user.name().firstName();
        return new CourierModel(loginCourier, null, firstnameCourier);
    }

    @Step("Генерация курьера с существующим логином (также для проверки авторизации с неправильным паролем)")
    public static CourierModel generationCourierWithExistingLogin(String loginCourier) {
        String passwordCourier = user.regexify("[0-9]{5}");
        String firstnameCourier = user.name().firstName();
        return new CourierModel(loginCourier, passwordCourier, firstnameCourier);
    }

    @Step("Генерация курьера с существующим логином (также для проверки авторизации с неправильным логином)")
    public static CourierModel generationCourierWithExistingPassword(String passwordCourier) {
        String loginCourier = user.name().lastName() + user.number().numberBetween(90, 100);
        String firstnameCourier = user.name().firstName();
        return new CourierModel(loginCourier, passwordCourier, firstnameCourier);
    }

    @Step("Генерация существующего курьера без пароля")
    public static CourierModel generationExistingCourierWithoutPassword(String loginCourier, String firstnameCourier) {
        return new CourierModel(loginCourier, null, firstnameCourier);
    }

    @Step("Генерация существующего курьера без логина")
    public static CourierModel generationExistingCourierWithoutLogin(String passwordCourier, String firstnameCourier) {
        return new CourierModel(null, passwordCourier, firstnameCourier);
    }
}


