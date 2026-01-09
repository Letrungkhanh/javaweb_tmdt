package dal;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import models.CategoryRevenue;


public class StatisticsDAO extends DBContext {

    // Tổng sản phẩm
    public int countProducts() {
        String sql = "SELECT COUNT(*) FROM tb_Product";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Tổng bài viết
    public int countBlogs() {
        String sql = "SELECT COUNT(*) FROM tb_Blog";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    // Tổng đơn hàng
    public int countOrders() {
        String sql = "SELECT COUNT(*) FROM tb_Order";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int unprocessedOrders() {
        String sql = "SELECT COUNT(*) FROM tb_Order WHERE OrderStatusId = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    public int processedOrders() {
        String sql = "SELECT COUNT(*) FROM tb_Order WHERE OrderStatusId = 2";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    
    public int shippedOrders() {
        String sql = "SELECT COUNT(*) FROM tb_Order WHERE OrderStatusId = 3";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int DeliveredOrders() {
        String sql = "SELECT COUNT(*) FROM tb_Order WHERE OrderStatusId = 4";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    public int CancelledOrder() {
        String sql = "SELECT COUNT(*) FROM tb_Order WHERE OrderStatusId = 5";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    

    // Tổng doanh thu (chỉ tính đơn đã thanh toán)
    public double totalRevenue() {
        String sql =  """
                SELECT IFNULL(SUM(TotalAmount), 0)
                FROM tb_Order
                WHERE OrderStatusId = 4
            """;
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    public Map<Integer, Double> revenueByMonth(int year) {
        Map<Integer, Double> data = new LinkedHashMap<>();

        String sql = """
            SELECT m.month,
                   COALESCE(SUM(o.TotalAmount), 0) AS revenue
            FROM (
                SELECT 1 month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
                UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8
                UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12
            ) m
            LEFT JOIN tb_Order o
              ON MONTH(o.CreatedDate) = m.month
             AND YEAR(o.CreatedDate) = ?
             AND o.OrderStatusId = 4
            GROUP BY m.month
            ORDER BY m.month
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.put(rs.getInt("month"), rs.getDouble("revenue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public double revenueByMonth(int year, int month) {
        String sql = """
            SELECT COALESCE(SUM(TotalAmount),0)
            FROM tb_Order
            WHERE OrderStatusId = 4
              AND YEAR(CreatedDate) = ?
              AND MONTH(CreatedDate) = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<CategoryRevenue> revenueByCategory() {
        List<CategoryRevenue> list = new ArrayList<>();

        String sql = """
            SELECT 
                c.CategoryId,
                c.Title,
                SUM(od.Quantity * od.Price) AS Revenue
            FROM tb_OrderDetail od
            JOIN tb_Order o ON od.OrderId = o.OrderId
            JOIN tb_Product p ON od.ProductId = p.ProductId
            JOIN tb_ProductCategory c ON p.CategoryId = c.CategoryId
            WHERE o.OrderStatusId = 4
            GROUP BY c.CategoryId, c.Title
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoryRevenue cr = new CategoryRevenue();
                cr.setCategoryId(rs.getInt("CategoryId"));
                cr.setTitle(rs.getString("Title"));
                cr.setRevenue(rs.getDouble("Revenue"));
                list.add(cr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
