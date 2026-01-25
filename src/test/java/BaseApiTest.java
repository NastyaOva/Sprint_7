import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CourierModel;
import models.OrderModel;
import org.junit.After;
import org.junit.BeforeClass;
import steps.CourierStep;
import steps.OrderStep;

import static data.TestData.BASE_URL;

public class BaseApiTest {

    protected CourierModel courierCash;
    protected OrderModel orderCash;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @After
    public void tearDown() {
        if (courierCash != null) {
            CourierStep.deleteCourier(courierCash);
        } else if (orderCash != null) {
            OrderStep.cancelOrder(orderCash);
        }
    }
}
