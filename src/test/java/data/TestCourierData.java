package data;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import models.CourierModel;

import java.util.Locale;

public class TestCourierData {

    public static Faker faker = new Faker(new Locale("ru"));

    public static CourierModel courier(String loginCourier, String passwordCourier, String firstnameCourier) {
        return new CourierModel(loginCourier, passwordCourier, firstnameCourier);
    }

    @Step("Генерация уникального курьера")
    public static CourierModel generationCourier() {
        return courier(faker.name().lastName() + faker.number().numberBetween(90, 100),
        faker.internet().password(),
        faker.name().firstName());
    }
}


