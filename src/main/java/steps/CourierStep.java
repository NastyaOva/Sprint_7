package steps;

import io.qameta.allure.Step;
import models.CourierModel;
import io.restassured.response.Response;

import static constants.UrlPath.*;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
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

    @Step("Проверка кода и тела ответа успешного создания курьера")
    public static void checkResponseCreateCourier(Response response, int code) {
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

    @Step("Вывод тела ответа при создании курьера")
    public static void printResponseCreateCourier(CourierModel courierModel) {
        System.out.println(createCourier(courierModel).body().asString());
    }

    @Step("Авторизация курьера")
    public static Response loginCourier(CourierModel courierModel) {
        Response responseLogin = given()
                .log().all()
                .header("Content-type", "application/json")
                .body(courierModel)
                .when()
                .post(LOGIN_PATH_COURIER);
        return responseLogin;
    }

    @Step("Проверка кода и получение id курьера в теле ответа при успешной авторизации курьера")
    public static void checkResponseLoginCourier(Response responseLogin, int code) {
        responseLogin.then()
                .log().all()
                .statusCode(code)
                .assertThat().body("id", notNullValue());
    }

    @Step("Вывод тела ответа при авторизации курьера")
    public static void printResponseLoginCourier(CourierModel courierModel) {
        System.out.println(loginCourier(courierModel).body().asString());
    }

    @Step("Получение id курьера")
    public static Integer getCourierId(CourierModel courierModel) {
        Response id = CourierStep.loginCourier(courierModel);
        return id.path("id");
    }

    @Step("Удаление курьера")
    public static void deleteCourier(CourierModel courierModel) {
        given()
                .log().all()
                .pathParam("id", getCourierId(courierModel))
                .delete(DELETE_PATH_COURIER)
                .then()
                .statusCode(HTTP_OK);
        System.out.println("Курьер удален");
    }
}
