import data.TestOrderData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderStep;

import static java.net.HttpURLConnection.HTTP_CREATED;

@RunWith(Parameterized.class)
public class CreateOrderPositiveTest extends BaseApiTest{
   private final String colorScooter;

    public CreateOrderPositiveTest(String colorScooter) {
        this.colorScooter = colorScooter;
    }

    @Parameterized.Parameters(name = "Color: {0}")
    public static Object[][] getColor() {
        return new Object[][]{
                {"BLACK"},
                {"GRAY"},
                {null}
        };
    }

    @Test
    @DisplayName("Проверка создание заказа со всеми вариантами выбора цвета скутера, в том числе без выбора цвета")
    public void createOrderScooter() {
        OrderModel orderModel = TestOrderData.orderScooter(colorScooter);
        Response response = OrderStep.createOrder(orderModel);
        OrderStep.checkResponseCreateOrder(response, HTTP_CREATED);
        OrderStep.printResponseOrder(response);
        this.orderCash = orderModel;
    }

    @Test
    @DisplayName("Проверка создание заказа с выбором обоих цветов скутера")
    public void createOrderBlackAndGrayScooter() {
        OrderModel orderModel = TestOrderData.orderBlackAndGrayScooter();
        Response response = OrderStep.createOrder(orderModel);
        OrderStep.checkResponseCreateOrder(response, HTTP_CREATED);
        OrderStep.printResponseOrder(response);
        this.orderCash = orderModel;
    }
}
