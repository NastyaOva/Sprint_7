package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.OrderModel;

import static constants.UrlPath.*;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.notNullValue;

public class OrderStep {

    @Step("Создание заказа")
    public static Response createOrder(OrderModel orderModel) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(orderModel)
                .when()
                .post(CREATE_PATH_ORDER);
    }

    @Step("Проверка кода и получение track заказа в теле ответа при успешном создании заказа")
    public static void checkResponseCreateOrder(Response response, int code) {
        response.then()
                .log().all()
                .statusCode(code)
                .assertThat().body("track", notNullValue());
    }

    @Step("Отмена заказа")
    public static void cancelOrder(OrderModel orderModel) {
        Integer track = createOrder(orderModel).path("track");
        given()
                .log().all()
                .queryParam("track", track)
                .put(CANCEL_PATH_ORDER)
                .then()
                .statusCode(HTTP_OK);
    }
}
