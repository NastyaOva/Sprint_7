import data.TestCourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.Test;
import steps.CourierStep;

import static java.net.HttpURLConnection.HTTP_OK;

public class LoginCourierPositiveTest extends BaseApiTest{

    @Test
    @DisplayName("Проверка авторизации курьера")
    @Description("Проверка кода и тела ответа при успешной авторизации курьера")
    public void successLoginCourierTest() {
        CourierModel courierModel = TestCourierData.generationCourier();
        CourierStep.createCourier(courierModel);
        this.courierCash = courierModel;
        Response response = CourierStep.loginCourier(courierModel);
        CourierStep.checkResponseLoginCourier(response, HTTP_OK);
    }
}
