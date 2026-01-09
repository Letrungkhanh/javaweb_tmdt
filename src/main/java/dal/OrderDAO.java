package dal;

import models.Order;
import models.TopProduct;
import models.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class OrderDAO extends DBContext {

	public int insertOrder(Order order) {
	    String sql = """
	        INSERT INTO tb_Order 
	        (Code, CustomerName, Phone, Email, Address,
	         TotalAmount, Quantity, OrderStatusId, AccountId, CreatedDate,paymentMethod,paymentStatus)
	        VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?, NOW(),?,?)
	    """;

	    try {
	        PreparedStatement ps = connection.prepareStatement(
	                sql, Statement.RETURN_GENERATED_KEYS);

	        ps.setString(1, order.getCode());
	        ps.setString(2, order.getCustomerName());
	        ps.setString(3, order.getPhone());
	        ps.setString(4, order.getEmail());
	        ps.setString(5, order.getAddress());
	        ps.setDouble(6, order.getTotalAmount());
	        ps.setInt(7, order.getQuantity());
	       // ps.setInt(8, order.getOrderStatusId());
	        ps.setInt(8, order.getAccountId()); // ⭐ GẮN USER
	        ps.setString(9,order.getPaymentMethod());
	        ps.setString(10,order.getPaymentStatus());
	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            return rs.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1;
	}



	public void insertOrderDetail(int orderId, OrderDetail detail) {

	    String sql = """
	        INSERT INTO tb_OrderDetail
	        (OrderId, ProductId, Price, Quantity)
	        VALUES (?, ?, ?, ?)
	    """;

	    try {
	        // 1. Thêm chi tiết đơn hàng
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setInt(1, orderId);
	        ps.setInt(2, detail.getProductId());
	        ps.setDouble(3, detail.getPrice());
	        ps.setInt(4, detail.getQuantity());
	        ps.executeUpdate();

	        // 2. ⭐ TRỪ KHO
	        decreaseProductQuantity(
	            detail.getProductId(),
	            detail.getQuantity()
	        );

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM tb_Order WHERE OrderId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                order.setCode(rs.getString("Code"));
                order.setCustomerName(rs.getString("CustomerName"));
                order.setPhone(rs.getString("Phone"));
                order.setEmail(rs.getString("Email"));
                order.setAddress(rs.getString("Address"));
                order.setTotalAmount(rs.getDouble("TotalAmount"));
                order.setQuantity(rs.getInt("Quantity"));
                order.setOrderStatusId(rs.getInt("OrderStatusId"));
                order.setPaymentMethod(rs.getString("PaymentMethod"));
                order.setPaymentStatus(rs.getString("PaymentStatus"));

                // Nếu có các trường khác, thêm vào đây
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<OrderDetail> getOrderDetailsWithProductName(int orderId) {
        List<OrderDetail> list = new ArrayList<>();

        String sql = """
            SELECT od.OrderDetailId,
                   od.OrderId,
                   od.ProductId,
                   p.Title AS ProductName,
                   od.Price,
                   od.Quantity
            FROM tb_OrderDetail od
            INNER JOIN tb_Product p ON od.ProductId = p.ProductId
            WHERE od.OrderId = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setOrderDetailId(rs.getInt("OrderDetailId"));
                detail.setOrderId(rs.getInt("OrderId"));
                detail.setProductId(rs.getInt("ProductId"));

                // ⭐ DÒNG QUAN TRỌNG
                detail.setProductName(rs.getString("ProductName"));

                detail.setPrice(rs.getDouble("Price"));
                detail.setQuantity(rs.getInt("Quantity"));

                list.add(detail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_Order ORDER BY CreatedDate DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                order.setCode(rs.getString("Code"));
                order.setCustomerName(rs.getString("CustomerName"));
                order.setPhone(rs.getString("Phone"));
                order.setEmail(rs.getString("Email"));
                order.setAddress(rs.getString("Address"));
                order.setTotalAmount(rs.getDouble("TotalAmount"));
                order.setQuantity(rs.getInt("Quantity"));
                order.setOrderStatusId(rs.getInt("OrderStatusId"));
                order.setCreatedDate(rs.getDate("CreatedDate"));
                order.setPaymentMethod(rs.getString("PaymentMethod"));
                order.setPaymentStatus(rs.getString("PaymentStatus"));

                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<OrderDetail> getOrderDetails(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_OrderDetail WHERE OrderId=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setOrderDetailId(rs.getInt("OrderDetailId"));
                detail.setOrderId(rs.getInt("OrderId"));
                detail.setProductId(rs.getInt("ProductId"));
                detail.setPrice(rs.getDouble("Price"));
                detail.setQuantity(rs.getInt("Quantity"));
                list.add(detail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void updateOrderStatus(int orderId, int statusId, int modifiedBy) {
        String sql = "UPDATE tb_Order SET OrderStatusId=?, ModifiedBy=?, ModifiedDate=NOW() WHERE OrderId=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, statusId);
            ps.setInt(2, modifiedBy); // dùng biến modifiedBy
            ps.setInt(3, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM tb_Order WHERE OrderId=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Order> getOrdersByAccountId(int accountId) {
        List<Order> list = new ArrayList<>();

        String sql = """
            SELECT *
            FROM tb_Order
            WHERE AccountId = ?
            ORDER BY CreatedDate DESC
        """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("OrderId"));
                o.setCode(rs.getString("Code"));
                o.setCustomerName(rs.getString("CustomerName"));
                o.setPhone(rs.getString("Phone"));
                o.setEmail(rs.getString("Email"));
                o.setAddress(rs.getString("Address"));
                o.setTotalAmount(rs.getDouble("TotalAmount"));
                o.setQuantity(rs.getInt("Quantity"));
                o.setOrderStatusId(rs.getInt("OrderStatusId"));
                o.setCreatedDate(rs.getTimestamp("CreatedDate"));
                o.setAccountId(rs.getInt("AccountId"));
                o.setPaymentMethod(rs.getString("PaymentMethod"));
                o.setPaymentStatus(rs.getString("PaymentStatus"));


                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean cancelOrder(int orderId, int accountId) {

        String updateOrderSql = """
            UPDATE tb_Order
            SET OrderStatusId = 5
            WHERE OrderId = ?
              AND AccountId = ?
              AND OrderStatusId = 1
        """;

        String getDetailsSql = """
            SELECT ProductId, Quantity
            FROM tb_OrderDetail
            WHERE OrderId = ?
        """;

        try {
            connection.setAutoCommit(false);

            // 1️⃣ Lấy chi tiết đơn hàng
            List<OrderDetail> details = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(getDetailsSql)) {
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    OrderDetail d = new OrderDetail();
                    d.setProductId(rs.getInt("ProductId"));
                    d.setQuantity(rs.getInt("Quantity"));
                    details.add(d);
                }
            }

            // 2️⃣ Đổi trạng thái đơn
            PreparedStatement psUpdate = connection.prepareStatement(updateOrderSql);
            psUpdate.setInt(1, orderId);
            psUpdate.setInt(2, accountId);

            int updated = psUpdate.executeUpdate();
            if (updated == 0) {
                connection.rollback();
                return false;
            }

            // 3️⃣ Hoàn kho
            for (OrderDetail d : details) {
                increaseProductQuantity(d.getProductId(), d.getQuantity());
            }

            connection.commit();
            return true;

        } catch (Exception e) {
            try { connection.rollback(); } catch (Exception ex) {}
            e.printStackTrace();
        } finally {
            try { connection.setAutoCommit(true); } catch (Exception e) {}
        }

        return false;
    }

    public Order getOrderByIdAndAccount(int orderId, int accountId) {

        Order order = null;

        String sqlOrder = """
            SELECT *
            FROM tb_Order
            WHERE OrderId = ? AND AccountId = ?
        """;

        String sqlDetail = """
            SELECT od.*, p.Title AS ProductName
            FROM tb_OrderDetail od
            JOIN tb_Product p ON od.ProductId = p.ProductId
            WHERE od.OrderId = ?
        """;

        try {
            PreparedStatement ps = connection.prepareStatement(sqlOrder);
            ps.setInt(1, orderId);
            ps.setInt(2, accountId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                order.setCode(rs.getString("Code"));
                order.setCustomerName(rs.getString("CustomerName"));
                order.setPhone(rs.getString("Phone"));
                order.setAddress(rs.getString("Address"));
                order.setTotalAmount(rs.getDouble("TotalAmount"));
                order.setQuantity(rs.getInt("Quantity"));
                order.setOrderStatusId(rs.getInt("OrderStatusId"));
                order.setCreatedDate(rs.getTimestamp("CreatedDate"));
                order.setPaymentMethod(rs.getString("PaymentMethod"));
                order.setPaymentStatus(rs.getString("PaymentStatus"));

            }

            if (order != null) {
                PreparedStatement ps2 = connection.prepareStatement(sqlDetail);
                ps2.setInt(1, orderId);
                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {
                    OrderDetail d = new OrderDetail();
                    d.setProductName(rs2.getString("ProductName"));
                    d.setPrice(rs2.getDouble("Price"));
                    d.setQuantity(rs2.getInt("Quantity"));

                    order.getDetails().add(d);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }
    public void updatePaymentByCode(String code, String paymentStatus, int orderStatusId) {
        String sql = """
            UPDATE tb_Order
            SET PaymentStatus = ?, OrderStatusId = ?, ModifiedDate = NOW()
            WHERE Code = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, paymentStatus);
            ps.setInt(2, orderStatusId);
            ps.setString(3, code);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void confirmOrderReceived(int orderId, int accountId) {
        String sql = """
            UPDATE tb_Order
            SET OrderStatusId = 4,
                ModifiedDate = NOW()
            WHERE OrderId = ?
              AND AccountId = ?
              AND OrderStatusId = 3
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TopProduct> getTop5BestSellingProducts() {

        List<TopProduct> list = new ArrayList<>();

        String sql = """
            SELECT 
                p.ProductId,
                p.Title AS ProductName,
                SUM(od.Quantity) AS TotalSold
            FROM tb_OrderDetail od
            JOIN tb_Order o ON od.OrderId = o.OrderId
            JOIN tb_Product p ON od.ProductId = p.ProductId
            WHERE o.OrderStatusId >= 3
            GROUP BY p.ProductId, p.Title
            ORDER BY TotalSold DESC
            LIMIT 5
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TopProduct tp = new TopProduct();
                tp.setProductId(rs.getInt("ProductId"));
                tp.setProductName(rs.getString("ProductName"));
                tp.setTotalSold(rs.getInt("TotalSold"));

                list.add(tp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
 // Trừ số lượng sản phẩm trong kho
    public boolean decreaseProductQuantity(int productId, int quantity) {

        String sql = """
            UPDATE tb_Product
            SET Quantity = Quantity - ?
            WHERE ProductId = ?
              AND Quantity >= ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);

            int affected = ps.executeUpdate();
            //System.out.println("TRU KHO productId=" + productId + " rows=" + affected);

            return affected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void increaseProductQuantity(int productId, int quantity) {

        String sql = """
            UPDATE tb_Product
            SET Quantity = Quantity + ?
            WHERE ProductId = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updatePaymentSuccess(int orderId) {

        String sql = """
            UPDATE tb_Order
            SET PaymentStatus = 'PAID',
                OrderStatusId = 2,
                ModifiedDate = NOW()
            WHERE OrderId = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateVnpaySuccess(int orderId) {
        String sql = """
            UPDATE tb_Order
            SET PaymentMethod = 'VNPAY',
                PaymentStatus = 'PAID',
                OrderStatusId = 2
            WHERE OrderId = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
