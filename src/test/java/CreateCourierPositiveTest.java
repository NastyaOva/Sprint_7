import data.TestCourierData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.Test;
import steps.CourierStep;

import static java.net.HttpURLConnection.HTTP_CREATED;

public class CreateCourierPositiveTest extends BaseApiTest {

    @Test
    @DisplayName("Проверка создания курьера")
    public void createCourierTest() {
        CourierModel courierModel = TestCourierData.generationCourier();
        Response response = CourierStep.createCourier(courierModel);
        CourierStep.checkResponseCourier(response, HTTP_CREATED);
        CourierStep.printResponseCourier(response);
        this.courierCash = courierModel;
    }

    @Test
    @DisplayName("Проверка создания курьера без имени")
    public void createCourierWithoutFirstname() {
        CourierModel courierModel = TestCourierData.generationCourierWithoutFirstname();
        Response response = CourierStep.createCourier(courierModel);
        CourierStep.checkResponseCourier(response, HTTP_CREATED);
        CourierStep.printResponseCourier(response);
        this.courierCash = courierModel;
    }
}
