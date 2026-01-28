package data;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import models.CourierModel;
import models.OrderListModel;
import steps.CourierStep;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TestOrderListData {

    static Faker list = new Faker(new Locale("ru"));
    static ObjectMapper mapper = new ObjectMapper();

    public static OrderListModel orderList(Integer courierId, String nearestStation, Integer limit, Integer page) {
        return new OrderListModel(courierId, nearestStation, limit, page);
    }

    @Step("Генерация параметров для получения списка активных/завершенных заказов курьера")
    public static OrderListModel listDataById() throws JsonProcessingException {
        CourierModel courier = TestCourierData.generationCourier();
        Response id = CourierStep.loginCourier(courier);
        Integer courierId = id.path("id");
        Integer limit = list.number().numberBetween(1, 31);
        Integer page = 0;
        String nearestStation;
        if (courierId == null) {
            nearestStation = oneStation();
        } else {
            nearestStation = twoStation();
        }
        return new OrderListModel(courierId, nearestStation, limit, page);
    }

    @Step("Генерация одного номера станции метро")
    public static String oneStation() throws JsonProcessingException {
        String number = String.valueOf(list.number().numberBetween(1, 51));
        List<String> station = Arrays.asList(number);
        return mapper.writeValueAsString(station);
    }

    @Step("Генерация двух номеров станции метро")
    public static String twoStation() throws JsonProcessingException {
        String firstNumber = String.valueOf(list.number().numberBetween(1, 51));
        String secondNumber = String.valueOf(list.number().numberBetween(1, 51));
        List<String> station = Arrays.asList(firstNumber, secondNumber);
        return mapper.writeValueAsString(station);
    }
}
