import data.TestCourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.Test;
import steps.CourierStep;

import static java.net.HttpURLConnection.HTTP_OK;

public class DeleteCourierPositiveTest extends BaseApiTest{

    @Test
    @DisplayName("Проверка удаления курьера")
    @Description("Проверка кода и тела ответа при успешном удалении курьера")
    public void successDeleteCourierTest() {
        CourierModel courierModel = TestCourierData.generationCourier();
        CourierStep.createCourier(courierModel);
        Response response = CourierStep.deleteCourier(courierModel);
        CourierStep.checkResponseCourier(response, HTTP_OK);
    }
}
