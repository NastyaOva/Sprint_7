import data.TestOrderListData;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.OrderListModel;
import org.junit.Test;
import steps.OrderListStep;

import static java.net.HttpURLConnection.HTTP_OK;

public class GetOrderListTest extends BaseApiTest {

    @Test
    @DisplayName("Получение списка всех активных/завершенных заказов курьера")
    public void getListOrderById() {
        OrderListModel orderListModel = TestOrderListData.listDataById();
        Response response = OrderListStep.getOrderList(orderListModel);
        OrderListStep.checkCodeResponseGetOrderList(response, HTTP_OK);
        OrderListStep.printResponseGetOrderList(orderListModel);
    }

    @Test
    @DisplayName("Получение списка всех активных/завершенных заказов курьера на одной из двух станций метро")
    public void getListDataByIdAndStation() throws JsonProcessingException {
        OrderListModel orderListModel = TestOrderListData.listDataByIdAndStation();
        Response response = OrderListStep.getOrderList(orderListModel);
        OrderListStep.checkCodeResponseGetOrderList(response, HTTP_OK);
        OrderListStep.printResponseGetOrderList(orderListModel);
    }

    @Test
    @DisplayName("Получение списка ограниченного кол-ва заказов, доступных для взятия курьером")
    public void getListDataWithLimit() {
        OrderListModel orderListModel = TestOrderListData.listDataWithLimit();
        Response response = OrderListStep.getOrderList(orderListModel);
        OrderListStep.checkCodeResponseGetOrderList(response, HTTP_OK);
        OrderListStep.printResponseGetOrderList(orderListModel);
    }

    @Test
    @DisplayName("Получение списка ограниченного кол-ва заказов, доступных для взятия курьером возле определенной станции метро")
    public void getListDataWithLimitAndStation() throws JsonProcessingException {
    OrderListModel orderListModel = TestOrderListData.listDataWithLimitAndStation();
        Response response = OrderListStep.getOrderList(orderListModel);
        OrderListStep.checkCodeResponseGetOrderList(response, HTTP_OK);
        OrderListStep.printResponseGetOrderList(orderListModel);
    }
}
