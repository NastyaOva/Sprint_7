package data;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import models.OrderModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TestOrderData {

    static Faker order = new Faker();

    @Step("Создание заказа")
    public static OrderModel orderScooter(String firstColorScooter, String secondColorScooter) {
        String firstName = order.name().firstName();
        String lastName = order.name().lastName();
        String address = order.address().fullAddress();
        int metroStation = order.number().numberBetween(1, 100);
        String phone = "+7 " + order.regexify("[0-9]{3} [0-9]{3} [0-9]{2} [0-9]{2}");
        int rentTime = order.number().numberBetween(1, 6);
        String deliveryDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String comment = order.lorem().sentence();
        List<String> color = Arrays.asList(firstColorScooter, secondColorScooter);
        return new OrderModel(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}
