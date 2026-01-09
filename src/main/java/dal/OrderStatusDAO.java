package dal;

import models.OrderStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusDAO extends DBContext {
    public List<OrderStatus> getAllStatuses() {
        List<OrderStatus> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_OrderStatus";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                OrderStatus s = new OrderStatus();
                s.setOrderStatusId(rs.getInt("OrderStatusId"));
                s.setName(rs.getString("Name"));
                s.setDescription(rs.getString("Description"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
