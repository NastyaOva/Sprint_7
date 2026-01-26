package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.OrderListModel;

import java.util.HashMap;
import java.util.Map;

import static constants.UrlPath.GET_PATH_ORDER;
import static io.restassured.RestAssured.given;

public class OrderListStep {

    @Step("Получение списка заказов")
    public static Response getOrderList(OrderListModel orderListModel) {
        Map<String, Object> map = new HashMap<>();
        if (orderListModel.getCourierId() != null) {
            map.put("courierId", orderListModel.getCourierId());
        }
        if (orderListModel.getNearestStation() != null) {
            map.put("nearestStation", orderListModel.getNearestStation());
        }
        if (orderListModel.getLimit() != null) {
            map.put("limit", orderListModel.getLimit());
        }
        if (orderListModel.getPage() != null) {
            map.put("page", orderListModel.getPage());
        }
        Response response = given()
                .log().all()
                .queryParams(map)
                .get(GET_PATH_ORDER)
                .then()
                .extract().response();
        return response;
    }

    @Step("Проверка кода при запросе на получение списка заказов")
    public static void checkCodeResponseGetOrderList(Response response, int code) {
        response.then()
                .log().all()
                .statusCode(code);
    }

    @Step("Вывод тела ответа при получении списка заказов")
    public static void printResponseGetOrderList(OrderListModel orderListModel) {
        System.out.println(getOrderList(orderListModel).body().asString());
    }
}
