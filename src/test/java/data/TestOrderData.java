package data;

import com.github.javafaker.Faker;
import models.OrderModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TestOrderData {

    static Faker order = new Faker();

    //создание заказа
    public static OrderModel orderScooter(String colorScooter) {
        String firstName = order.name().firstName();
        String lastName = order.name().lastName();
        String address = order.address().fullAddress();
        int metroStation = order.number().numberBetween(1, 100);
        String phone = "+7 " + order.regexify("[0-9]{3} [0-9]{3} [0-9]{2} [0-9]{2}");
        int rentTime = order.number().numberBetween(1, 6);
        String deliveryDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String comment = order.lorem().sentence();
        List<String> color = Arrays.asList(colorScooter);
        return new OrderModel(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    //создание заказа с выбором обоих цветов
    public static OrderModel orderBlackAndGrayScooter() {
        String firstName = order.name().firstName();
        String lastName = order.name().lastName();
        String address = order.address().fullAddress();
        int metroStation = order.number().numberBetween(1, 100);
        String phone = "+7 " + order.regexify("[0-9]{3} [0-9]{3} [0-9]{2} [0-9]{2}");
        int rentTime = order.number().numberBetween(1, 6);
        String deliveryDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String comment = order.lorem().sentence();
        List<String> color = Arrays.asList("BLACK", "GRAY");
        return new OrderModel(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}
