import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import steps.CourierStep;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class DeleteCourierNegativeTest extends BaseApiTest {

    @Test
    @DisplayName("Проверка ошибки удаления курьера без указания id курьера")
    public void errorDeleteCourierWithoutId() {
        Response response = CourierStep.deleteCourierWithoutId();
        CourierStep.checkErrorMessage(response, HTTP_BAD_REQUEST, "Недостаточно данных для удаления курьера");
        CourierStep.printResponseCourier(response);
    }

    @Test
    @DisplayName("Проверка ошибки удаления с несуществующим id")
    public void errorDeleteCourierNotExistingId() {
        Response response = CourierStep.deleteCourierWithWrongId();
        CourierStep.checkErrorMessage(response, HTTP_NOT_FOUND, "Курьера с таким id нет.");
        CourierStep.printResponseCourier(response);
    }
}
