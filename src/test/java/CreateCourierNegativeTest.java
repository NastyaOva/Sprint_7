import data.TestCourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.Test;
import steps.CourierStep;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CONFLICT;

public class CreateCourierNegativeTest extends BaseApiTest {

    @Test
    @DisplayName("Проверка ошибки создания двух одинаковых курьеров")
    @Description("Проверка кода и тела ответа при попытке создать курьера, с параметрами которого уже существует другой курьер")
    public void conflictCreateDuplicateCourierTest() {
        CourierModel courierModel = TestCourierData.generationCourier();
        CourierStep.createCourier(courierModel);
        Response response = CourierStep.createCourier(courierModel);
        this.courierCash = courierModel;
        CourierStep.checkErrorMessage(response, HTTP_CONFLICT, "Этот логин уже используется. Попробуйте другой.");
    }

    @Test
    @DisplayName("Проверка ошибки создания курьера без ввода логина")
    @Description("Проверка кода и тела ответа при попытке создать курьера без указания его логина в теле запроса")
    public void errorCreateWithoutLoginCourierTest() {
        CourierModel courierModel = TestCourierData.generationCourier();
        courierModel.setLogin(null);
        Response response = CourierStep.createCourier(courierModel);
        CourierStep.checkErrorMessage(response, HTTP_BAD_REQUEST, "Недостаточно данных для создания учетной записи");
    }

    @Test
    @DisplayName("Проверка ошибки создания курьера без ввода пароля")
    @Description("Проверка кода и тела ответа при попытке создать курьера без указания пароля в теле запроса")
    public void errorCreateWithoutPasswordCourierTest() {
        CourierModel courierModel = TestCourierData.generationCourier();
        courierModel.setPassword(null);
        Response response = CourierStep.createCourier(courierModel);
        CourierStep.checkErrorMessage(response, HTTP_BAD_REQUEST, "Недостаточно данных для создания учетной записи");
    }

    @Test
    @DisplayName("Проверка ошибки создания курьера с логином, который уже используется")
    @Description("Проверка кода и тела ответа при попытке создать курьера с логином, который уже используется")
    public void conflictCreateDuplicateLoginCourierTest() {
        CourierModel firstCourierModel = TestCourierData.generationCourier();
        CourierStep.createCourier(firstCourierModel);
        this.courierCash = firstCourierModel;
        CourierModel secondCourierModel = TestCourierData.generationCourier();
        secondCourierModel.setLogin(firstCourierModel.getLogin());
        Response response = CourierStep.createCourier(secondCourierModel);
        CourierStep.checkErrorMessage(response, HTTP_CONFLICT, "Этот логин уже используется. Попробуйте другой.");
    }
}
