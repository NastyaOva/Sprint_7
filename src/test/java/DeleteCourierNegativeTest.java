import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import steps.CourierStep;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class DeleteCourierNegativeTest extends BaseApiTest {

    @Test
    @DisplayName("Проверка ошибки удаления курьера без указания его id")
    @Description("Проверка кода и тела ответа при попытке удалить курьера без указания его id в запросе")
    public void errorDeleteCourierWithoutIdTest() {
        Response response = CourierStep.deleteCourierWithoutId();
        CourierStep.checkErrorMessage(response, HTTP_BAD_REQUEST, "Недостаточно данных для удаления курьера");
    }

    @Test
    @DisplayName("Проверка ошибки удаления с несуществующим id")
    @Description("Проверка кода и тела ответа при попытке удалить курьера с несуществующим id в запросе")
    public void errorDeleteCourierNotExistingIdTest() {
        Response response = CourierStep.deleteCourierWithWrongId();
        CourierStep.checkErrorMessage(response, HTTP_NOT_FOUND, "Курьера с таким id нет.");
    }
}

