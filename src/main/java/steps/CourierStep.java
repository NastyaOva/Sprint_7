package steps;

import io.qameta.allure.Step;
import models.CourierModel;
import io.restassured.response.Response;

import static constants.UrlPath.*;
import static io.restassured.RestAssured.given;;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierStep {

    @Step("Создание курьера")
    public static Response createCourier(CourierModel courierModel) {
        Response response = given()
                .log().all()
                .header("Content-type", "application/json")
                .body(courierModel)
                .when()
                .post(CREATE_PATH_COURIER);
        return response;
    }

    @Step("Проверка кода и тела ответа успешного создания/удаления курьера")
    public static void checkResponseCourier(Response response, int code) {
        response.then()
                .log().all()
                .statusCode(code)
                .body("ok", equalTo(true));
    }

    @Step("Проверка кода и текст ошибки в теле ответа")
    public static void checkErrorMessage(Response response, int code, String expectedText) {
        response.then()
                .log().all()
                .statusCode(code)
                .body("message", equalTo(expectedText));
    }

    @Step("Авторизация курьера")
    public static Response loginCourier(CourierModel courierModel) {
        Response response = given()
                .log().all()
                .header("Content-type", "application/json")
                .body(courierModel)
                .when()
                .post(LOGIN_PATH_COURIER);
        return response;
    }

    @Step("Проверка кода и получение id курьера в теле ответа при успешной авторизации курьера")
    public static void checkResponseLoginCourier(Response response, int code) {
        response.then()
                .log().all()
                .statusCode(code)
                .assertThat().body("id", notNullValue());
    }

    @Step("Получение id курьера")
    public static Integer getCourierId(CourierModel courierModel) {
        Response id = CourierStep.loginCourier(courierModel);
        return id.path("id");
    }

    @Step("Удаление курьера")
    public static Response deleteCourier(CourierModel courierModel) {
       Response response = given()
                .log().all()
                .pathParam("id", getCourierId(courierModel))
                .delete(DELETE_PATH_COURIER);
        return response;
    }

    @Step("Удаление курьера без id")
    public static Response deleteCourierWithoutId() {
        Response response = given()
                .log().all()
                .delete(DELETE_PATH_COURIER_WITHOUT_ID);
        return response;
    }

    @Step("Удаление курьера с несуществующим id")
    public static Response deleteCourierWithWrongId() {
        Response response = given()
                .log().all()
                .pathParam("id", "0000")
                .delete(DELETE_PATH_COURIER);
        return response;
    }

    @Step("Вывод тела ответа")
    public static void printResponseCourier(Response response) {
        System.out.println(response.body().asString());
    }
}
