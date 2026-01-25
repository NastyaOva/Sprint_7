import data.TestCourierData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.Test;
import steps.CourierStep;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class LoginCourierNegativeTest extends BaseApiTest{

    @Test
    @DisplayName("Проверка ошибки авторизации несуществующего курьера")
    public void errorLoginNotExistingCourier() {
        CourierModel courierModel = TestCourierData.generationCourier();
        Response response = CourierStep.loginCourier(courierModel);
        CourierStep.checkErrorMessage(response, HTTP_NOT_FOUND, "Учетная запись не найдена");
        CourierStep.printResponseLoginCourier(courierModel);
    }

    @Test
    @DisplayName("Проверка ошибки авторизации курьера при вводе неверного логина")
    public void errorLoginWithWrongLogin() {
        CourierModel courierModel = TestCourierData.generationCourier();
        CourierStep.createCourier(courierModel);
        CourierModel copyCourierModel = TestCourierData.generationCourierWithExistingPassword(courierModel.getPassword());
        Response response = CourierStep.loginCourier(copyCourierModel);
        CourierStep.checkErrorMessage(response, HTTP_NOT_FOUND, "Учетная запись не найдена");
        CourierStep.printResponseLoginCourier(copyCourierModel);
    }

    @Test
    @DisplayName("Проверка ошибки авторизации курьера при вводе неверного пароля")
    public void errorLoginWithWrongPassword() {
        CourierModel courierModel = TestCourierData.generationCourier();
        CourierStep.createCourier(courierModel);
        CourierModel copyCourierModel = TestCourierData.generationCourierWithExistingLogin(courierModel.getLogin());
        Response response = CourierStep.loginCourier(copyCourierModel);
        CourierStep.checkErrorMessage(response, HTTP_NOT_FOUND, "Учетная запись не найдена");
        CourierStep.printResponseLoginCourier(copyCourierModel);
    }

    @Test
    @DisplayName("Проверка ошибки авторизации курьера без ввода пароля")
    public void errorLoginWithoutPassword() {
        CourierModel courierModel = TestCourierData.generationCourier();
        CourierStep.createCourier(courierModel);
        CourierModel copyCourierModel = TestCourierData.generationExistingCourierWithoutPassword(courierModel.getLogin(), courierModel.getFirstName());
        Response response = CourierStep.loginCourier(copyCourierModel);
        CourierStep.checkErrorMessage(response, HTTP_BAD_REQUEST, "Недостаточно данных для входа");
        CourierStep.printResponseLoginCourier(copyCourierModel);
    }

    @Test
    @DisplayName("Проверка ошибки авторизации курьера без ввода логина")
    public void errorLoginWithoutLogin() {
        CourierModel courierModel = TestCourierData.generationCourier();
        CourierStep.createCourier(courierModel);
        CourierModel copyCourierModel = TestCourierData.generationExistingCourierWithoutLogin(courierModel.getPassword(), courierModel.getFirstName());
        Response response = CourierStep.loginCourier(copyCourierModel);
        CourierStep.checkErrorMessage(response, HTTP_BAD_REQUEST, "Недостаточно данных для входа");
        CourierStep.printResponseLoginCourier(copyCourierModel);
    }
}
