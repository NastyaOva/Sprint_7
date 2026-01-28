package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderListModel {
    private Integer courierId;
    private String nearestStation;
    private Integer limit;
    private Integer page;
}
