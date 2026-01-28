import data.TestOrderListData;
import io.qameta.allure.Description;
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
    @Description("Проверка кода и тела ответа при получении списка заказов с указанием id курьера в запросе")
    public void getListOrderByIdTest() throws JsonProcessingException {
        OrderListModel orderListModel = TestOrderListData.listDataById();
        orderListModel.setNearestStation(null);
        orderListModel.setLimit(null);
        orderListModel.setPage(null);
        Response response = OrderListStep.getOrderList(orderListModel);
        OrderListStep.checkCodeResponseGetOrderList(response, HTTP_OK);
    }

    @Test
    @DisplayName("Получение списка всех активных/завершенных заказов курьера на одной из двух станций метро")
    @Description("Проверка кода и тела ответа при получении списка заказов с указанием id курьера и фильтра станций метро в запросе")
    public void getListDataByIdAndStationTest() throws JsonProcessingException {
        OrderListModel orderListModel = TestOrderListData.listDataById();
        orderListModel.setLimit(null);
        orderListModel.setPage(null);
        Response response = OrderListStep.getOrderList(orderListModel);
        OrderListStep.checkCodeResponseGetOrderList(response, HTTP_OK);
    }

    @Test
    @DisplayName("Получение списка ограниченного кол-ва заказов, доступных для взятия курьером")
    @Description("Проверка кода и тела ответа при получении списка заказов с указанием кол-ва заказов доступных для взятия курьером и номера текущей страницы в запросе")
    public void getListDataWithLimitTest() throws JsonProcessingException {
        OrderListModel orderListModel = TestOrderListData.listDataById();
        orderListModel.setCourierId(null);
        orderListModel.setNearestStation(null);
        Response response = OrderListStep.getOrderList(orderListModel);
        OrderListStep.checkCodeResponseGetOrderList(response, HTTP_OK);
    }

    @Test
    @DisplayName("Получение списка ограниченного кол-ва заказов, доступных для взятия курьером возле определенной станции метро")
    @Description("Проверка кода и тела ответа при получении списка заказов с указанием в запросе номера текущей страницы и кол-ва заказов доступных для взятия курьером возле определенной станции метро")
    public void getListDataWithLimitAndStationTest() throws JsonProcessingException {
        OrderListModel orderListModel = TestOrderListData.listDataById();
        orderListModel.setCourierId(null);
        Response response = OrderListStep.getOrderList(orderListModel);
        OrderListStep.checkCodeResponseGetOrderList(response, HTTP_OK);
    }
}
