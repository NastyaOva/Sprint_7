package data;

import com.github.javafaker.Faker;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import models.CourierModel;
import models.OrderListModel;
import steps.CourierStep;

import java.util.Arrays;
import java.util.List;

public class TestOrderListData {

    static Faker list = new Faker();
    static ObjectMapper mapper = new ObjectMapper();

    //генерация параметров для получения списка активных/завершенных заказов курьера
    public static OrderListModel listDataById() {
        CourierModel courier = TestCourierData.generationCourier();
        Response id = CourierStep.loginCourier(courier);
        Integer courierId = id.path("id");
        return new OrderListModel(courierId, null, null, null);
    }

    //генерация параметров для получения списка активных/завершенных заказов курьера на двух из предложенных станций
    public static OrderListModel listDataByIdAndStation() throws JsonProcessingException {
        CourierModel courier = TestCourierData.generationCourier();
        Response id = CourierStep.loginCourier(courier);
        Integer courierId = id.path("id");
        return new OrderListModel(courierId, twoStation(), null, null);
    }

    //генерация заказов в кол-ве limit, доступных для взятия курьером
    public static OrderListModel listDataWithLimit() {
        Integer limit = list.number().numberBetween(1, 31);
        Integer page = 0;
        return new OrderListModel(null, null, limit, page);
    }

    //генерация заказов в кол-ве limit, доступных для взятия курьером возле определенной станции метро
    public static OrderListModel listDataWithLimitAndStation() throws JsonProcessingException {
        Integer limit = list.number().numberBetween(1, 31);
        Integer page = 0;
        return new OrderListModel(null, oneStation(), limit, page);
    }

    //генерация одного номера станции метро
    public static String oneStation() throws JsonProcessingException {
        String number = String.valueOf(list.number().numberBetween(1, 51));
        List<String> station = Arrays.asList(number);
        return mapper.writeValueAsString(station);
    }

    //генерация двух номеров станции метро
    public static String twoStation() throws JsonProcessingException {
        String firstNumber = String.valueOf(list.number().numberBetween(1, 51));
        String secondNumber = String.valueOf(list.number().numberBetween(1, 51));
        List<String> station = Arrays.asList(firstNumber, secondNumber);
        return mapper.writeValueAsString(station);
    }
}
