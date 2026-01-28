import data.TestOrderData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderStep;

import static java.net.HttpURLConnection.HTTP_CREATED;

@RunWith(Parameterized.class)
public class CreateOrderPositiveTest extends BaseApiTest {

    private final String firstColorScooter;
    private final String secondColorScooter;

    public CreateOrderPositiveTest(String firstColorScooter, String secondColorScooter) {
        this.firstColorScooter = firstColorScooter;
        this.secondColorScooter = secondColorScooter;
    }

    @Parameterized.Parameters(name = "Color: {0}")
    public static Object[][] getColor() {
        return new Object[][]{
                {"BLACK", null},
                {"GRAY", null},
                {null, null},
                {"BLACK", "GRAY"}
        };
    }

    @Test
    @DisplayName("Проверка создание заказа")
    @Description("Проверка кода и тела ответа при создании заказа с разными комбинациями цвета скутера: один цвет, два цвета, без цвета")
    public void createOrderScooterTest() {
        OrderModel orderModel = TestOrderData.orderScooter(firstColorScooter, secondColorScooter);
        Response response = OrderStep.createOrder(orderModel);
        OrderStep.checkResponseCreateOrder(response, HTTP_CREATED);
        this.orderCash = orderModel;
    }
}
