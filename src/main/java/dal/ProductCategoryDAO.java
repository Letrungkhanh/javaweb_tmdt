package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.ProductCategory;

public class ProductCategoryDAO extends DBContext {

    // Lấy tất cả category active
    public List<ProductCategory> getAllActive() {
        List<ProductCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_ProductCategory WHERE IsActive = 1";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductCategory c = mapResultSet(rs);
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy tất cả category
    public List<ProductCategory> getAll() {
        List<ProductCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_ProductCategory ORDER BY CategoryId DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductCategory c = mapResultSet(rs);
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy category theo id
    public ProductCategory getById(int id) {
        String sql = "SELECT * FROM tb_ProductCategory WHERE CategoryId=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Insert
    public void insert(ProductCategory c) {
        String sql = "INSERT INTO tb_ProductCategory (Title, Alias ,`Description`,Icon, IsActive, CreatedBy) VALUES (?, ?,?,?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getTitle());
            ps.setString(2, c.getAlias());
            ps.setString(3, c.getDescription());
            ps.setString(4,c.getIcon());
            ps.setBoolean(5, c.isActive());
            ps.setInt(6, c.getCreatedBy());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update(ProductCategory c) {
        String sql = "UPDATE tb_ProductCategory SET Title=?, Alias=?,`Description`=?,Icon=?,IsActive=?, ModifiedBy=? WHERE CategoryId=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getTitle());
            ps.setString(2, c.getAlias());
            ps.setString(3, c.getDescription());
            ps.setString(4,c.getIcon());
            ps.setBoolean(5, c.isActive());
            ps.setInt(6, c.getModifiedBy());
            ps.setInt(7, c.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete (soft delete)
 // Delete thật sự
    public void delete(int id) {
        String sql = "DELETE FROM tb_ProductCategory WHERE CategoryId=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Map result set
    private ProductCategory mapResultSet(ResultSet rs) throws SQLException {
        ProductCategory c = new ProductCategory();
        c.setCategoryId(rs.getInt("CategoryId"));
        c.setTitle(rs.getString("Title"));
        c.setAlias(rs.getString("Alias"));
        c.setDescription(rs.getString("Description"));
        c.setIcon(rs.getString("Icon"));
        c.setActive(rs.getBoolean("IsActive"));
        c.setCreatedBy(rs.getInt("CreatedBy"));
        c.setModifiedBy(rs.getInt("ModifiedBy"));
        return c;
    }
}
