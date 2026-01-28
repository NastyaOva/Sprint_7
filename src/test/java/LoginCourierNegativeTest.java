import data.TestCourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.Before;
import org.junit.Test;
import steps.CourierStep;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class LoginCourierNegativeTest extends BaseApiTest {

    CourierModel courierModel;

    @Before
    public void create() {
        courierModel = TestCourierData.generationCourier();
        CourierStep.createCourier(courierModel);
        this.courierCash = courierModel;
    }

    @Test
    @DisplayName("Проверка ошибки авторизации несуществующего курьера")
    @Description("Проверка кода и тела ответа при попытке авторизации несуществующего курьера")
    public void errorLoginNotExistingCourierTest() {
        CourierModel notExistingCourier = TestCourierData.generationCourier();
        Response response = CourierStep.loginCourier(notExistingCourier);
        CourierStep.checkErrorMessage(response, HTTP_NOT_FOUND, "Учетная запись не найдена");
    }

    @Test
    @DisplayName("Проверка ошибки авторизации курьера при вводе неверного логина")
    @Description("Проверка кода и тела ответа при попытке авторизации курьера с указанием неверного логина в теле запроса")
    public void errorLoginWithWrongLoginTest() {
        CourierModel copyCourierModel = TestCourierData.generationCourier();
        copyCourierModel.setPassword(courierModel.getPassword());
        Response response = CourierStep.loginCourier(copyCourierModel);
        CourierStep.checkErrorMessage(response, HTTP_NOT_FOUND, "Учетная запись не найдена");
    }

    @Test
    @DisplayName("Проверка ошибки авторизации курьера при вводе неверного пароля")
    @Description("Проверка кода и тела ответа при попытке авторизации курьера с указанием неверного пароля в теле запроса")
    public void errorLoginWithWrongPasswordTest() {
        CourierModel copyCourierModel = TestCourierData.generationCourier();
        copyCourierModel.setLogin(courierModel.getLogin());
        Response response = CourierStep.loginCourier(copyCourierModel);
        CourierStep.checkErrorMessage(response, HTTP_NOT_FOUND, "Учетная запись не найдена");
    }

    @Test
    @DisplayName("Проверка ошибки авторизации курьера без ввода пароля")
    @Description("Проверка кода и тела ответа при попытке авторизации курьера без указания пароля в теле запроса")
    public void errorLoginWithoutPasswordTest() {
        CourierModel copyCourierModel = TestCourierData.generationCourier();
        copyCourierModel.setLogin(courierModel.getLogin());
        copyCourierModel.setFirstName(courierModel.getFirstName());
        copyCourierModel.setPassword(null);
        Response response = CourierStep.loginCourier(copyCourierModel);
        CourierStep.checkErrorMessage(response, HTTP_BAD_REQUEST, "Недостаточно данных для входа");
    }

    @Test
    @DisplayName("Проверка ошибки авторизации курьера без ввода логина")
    @Description("Проверка кода и тела ответа при попытке авторизации курьера без указания логина в теле запроса")
    public void errorLoginWithoutLoginTest() {
        CourierModel copyCourierModel = TestCourierData.generationCourier();
        copyCourierModel.setPassword(courierModel.getPassword());
        copyCourierModel.setFirstName(courierModel.getFirstName());
        copyCourierModel.setLogin(null);
        Response response = CourierStep.loginCourier(copyCourierModel);
        CourierStep.checkErrorMessage(response, HTTP_BAD_REQUEST, "Недостаточно данных для входа");
    }
}
