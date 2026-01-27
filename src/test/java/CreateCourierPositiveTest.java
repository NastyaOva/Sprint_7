import data.TestCourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.Test;
import steps.CourierStep;

import static java.net.HttpURLConnection.HTTP_CREATED;

public class CreateCourierPositiveTest extends BaseApiTest {

    @Test
    @DisplayName("Проверка создания курьера")
    @Description("Проверка кода и тела ответа при создании курьера с заполнением всех параметров в теле запроса")
    public void createCourierTest() {
        CourierModel courierModel = TestCourierData.generationCourier();
        Response response = CourierStep.createCourier(courierModel);
        CourierStep.checkResponseCourier(response, HTTP_CREATED);
        this.courierCash = courierModel;
    }

    @Test
    @DisplayName("Проверка создания курьера без имени")
    @Description("Проверка кода и тела ответа при создании курьера с заполнением только обязательных параметров в теле запроса")
    public void createCourierWithoutFirstnameTest() {
        CourierModel courierModel = TestCourierData.generationCourier();
        courierModel.setFirstName(null);
        Response response = CourierStep.createCourier(courierModel);
        CourierStep.checkResponseCourier(response, HTTP_CREATED);
        this.courierCash = courierModel;
    }
}
