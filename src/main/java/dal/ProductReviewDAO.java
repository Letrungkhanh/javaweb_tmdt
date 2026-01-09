package dal;

import models.ProductReview;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductReviewDAO extends DBContext {

    // 1️⃣ Thêm review
    public void insertReview(ProductReview r) {
        String sql = """
            INSERT INTO tb_ProductReview
            (ProductId, Name, Phone, Email, Star, Detail, IsActive)
            VALUES (?, ?, ?, ?, ?, ?, 1)
        """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, r.getProductId());
            ps.setString(2, r.getName());
            ps.setString(3, r.getPhone());
            ps.setString(4, r.getEmail());
            ps.setFloat(5, r.getStar());
            ps.setString(6, r.getDetail());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2️⃣ Lấy review đã duyệt
    public List<ProductReview> getReviewsByProduct(int productId) {
        List<ProductReview> list = new ArrayList<>();

        String sql = """
            SELECT * FROM tb_ProductReview
            WHERE ProductId = ? AND IsActive = 1
            ORDER BY CreatedDate DESC
        """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductReview r = new ProductReview();
                r.setName(rs.getString("Name"));
                r.setStar(rs.getFloat("Star"));
                r.setDetail(rs.getString("Detail"));
                r.setCreatedDate(rs.getTimestamp("CreatedDate"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        public double getAverageStar(int productId) {
            String sql = """
                SELECT AVG(Star) 
                FROM tb_ProductReview
                WHERE ProductId = ? AND IsActive = 1
            """;

            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, productId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

        public int countReview(int productId) {
            String sql = """
                SELECT COUNT(*) 
                FROM tb_ProductReview
                WHERE ProductId = ? AND IsActive = 1
            """;

            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, productId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    

}
