import data.TestCourierData;
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
    public void conflictCreateDuplicateCourier() {
        CourierModel courierModel = TestCourierData.generationCourier();
        Response response = CourierStep.createCourier(courierModel);
        this.courierCash = courierModel;
        CourierStep.checkErrorMessage(response, HTTP_CONFLICT, "Этот логин уже используется. Попробуйте другой.");
        CourierStep.printResponseCreateCourier(courierModel);
    }

    @Test
    @DisplayName("Проверка ошибки создания курьера без ввода логина")
    public void errorCreateWithoutLoginCourier() {
        CourierModel courierModel = TestCourierData.generationCourierWithoutLogin();
        Response response = CourierStep.createCourier(courierModel);
        CourierStep.checkErrorMessage(response, HTTP_BAD_REQUEST, "Недостаточно данных для создания учетной записи");
        CourierStep.printResponseCreateCourier(courierModel);
    }

    @Test
    @DisplayName("Проверка ошибки создания курьера без ввода пароля")
    public void errorCreateWithoutPasswordCourier() {
        CourierModel courierModel = TestCourierData.generationCourierWithoutPassword();
        Response response = CourierStep.createCourier(courierModel);
        CourierStep.checkErrorMessage(response, HTTP_BAD_REQUEST, "Недостаточно данных для создания учетной записи");
        CourierStep.printResponseCreateCourier(courierModel);
    }

    @Test
    @DisplayName("Проверка ошибки создания курьера с логином, который уже используется")
    public void conflictCreateDuplicateLoginCourier() {
        CourierModel firstCourierModel = TestCourierData.generationCourier();
        CourierStep.createCourier(firstCourierModel);
        this.courierCash = firstCourierModel;
        CourierModel secondCourierModel = TestCourierData.generationCourierWithExistingLogin(firstCourierModel.getLogin());
        Response response = CourierStep.createCourier(secondCourierModel);
        CourierStep.checkErrorMessage(response, HTTP_CONFLICT, "Этот логин уже используется. Попробуйте другой.");
        CourierStep.printResponseCreateCourier(secondCourierModel);
    }
}
