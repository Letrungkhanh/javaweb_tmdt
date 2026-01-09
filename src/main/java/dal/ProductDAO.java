package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Product;
import models.ProductReview;

public class ProductDAO extends DBContext {

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_Product WHERE IsActive = 1 AND CategoryId =1 ORDER BY CreatedDate DESC LIMIT 4 ";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductId"));
                p.setTitle(rs.getString("Title"));
                p.setAlias(rs.getString("Alias"));
                p.setImage(rs.getString("Image"));
                p.setDescription(rs.getString("Description"));
                p.setDetail(rs.getString("Detail"));
                p.setPrice(rs.getDouble("Price"));
                p.setPriceSale(rs.getDouble("PriceSale"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCategoryId(rs.getInt("CategoryId"));
                p.setNew(rs.getBoolean("IsNew"));
                p.setBestSeller(rs.getBoolean("IsBestSeller"));
                p.setActive(rs.getBoolean("IsActive"));
                p.setStar(rs.getInt("Star"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
 // Lấy tất cả sản phẩm active
    public List<Product> getAllProductss() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_Product WHERE IsActive = 1 ORDER BY CreatedDate DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductId"));
                p.setTitle(rs.getString("Title"));
                p.setAlias(rs.getString("Alias"));
                p.setImage(rs.getString("Image"));
                p.setDescription(rs.getString("Description"));
                p.setDetail(rs.getString("Detail"));
                p.setPrice(rs.getDouble("Price"));
                p.setPriceSale(rs.getDouble("PriceSale"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCategoryId(rs.getInt("CategoryId"));
                p.setNew(rs.getBoolean("IsNew"));
                p.setBestSeller(rs.getBoolean("IsBestSeller"));
                p.setActive(rs.getBoolean("IsActive"));
                p.setStar(rs.getInt("Star"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

 // Lấy sản phẩm theo ID
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM tb_Product WHERE ProductId = ? AND IsActive = 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductId"));
                p.setTitle(rs.getString("Title"));
                p.setAlias(rs.getString("Alias"));
                p.setDescription(rs.getString("Description"));
                p.setDetail(rs.getString("Detail"));
                p.setPrice(rs.getDouble("Price"));
                p.setPriceSale(rs.getDouble("PriceSale"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCategoryId(rs.getInt("CategoryId"));
                p.setNew(rs.getBoolean("IsNew"));
                p.setBestSeller(rs.getBoolean("IsBestSeller"));
                p.setActive(rs.getBoolean("IsActive"));
                p.setStar(rs.getInt("Star"));
                p.setImage(rs.getString("Image"));

                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
 // Thêm sản phẩm mới
    public void insertProduct(Product p) {
        String sql = "INSERT INTO tb_Product (Title, Alias, Description, Detail, Image, Price, PriceSale, Quantity, CategoryId, IsNew, IsBestSeller, IsActive, Star) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getAlias());
            ps.setString(3, p.getDescription());
            ps.setString(4, p.getDetail());
            ps.setString(5, p.getImage());
            ps.setDouble(6, p.getPrice());
            ps.setDouble(7, p.getPriceSale());
            ps.setInt(8, p.getQuantity());
            ps.setInt(9, p.getCategoryId());
            ps.setBoolean(10, p.isNew());
            ps.setBoolean(11, p.isBestSeller());
            ps.setBoolean(12, p.isActive());
            ps.setFloat(13, p.getStar());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật sản phẩm
    public void updateProduct(Product p) {
        String sql = "UPDATE tb_Product SET Title=?, Alias=?, Description=?, Detail=?, Image=?, Price=?, PriceSale=?, Quantity=?, CategoryId=?, IsNew=?, IsBestSeller=?, IsActive=?, Star=? " +
                     "WHERE ProductId=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getAlias());
            ps.setString(3, p.getDescription());
            ps.setString(4, p.getDetail());
            ps.setString(5, p.getImage());
            ps.setDouble(6, p.getPrice());
            ps.setDouble(7, p.getPriceSale());
            ps.setInt(8, p.getQuantity());
            ps.setInt(9, p.getCategoryId());
            ps.setBoolean(10, p.isNew());
            ps.setBoolean(11, p.isBestSeller());
            ps.setBoolean(12, p.isActive());
            ps.setFloat(13, p.getStar());
            ps.setInt(14, p.getProductId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteProduct(int productId) {
        // 1. Xóa mềm: chỉ set IsActive = 0
        String sql = "UPDATE tb_Product SET IsActive=0 WHERE ProductId=?";
        
     
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy sản phẩm theo categoryId (nếu cần cho frontend)
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_Product WHERE CategoryId=? AND IsActive=1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductId"));
                p.setTitle(rs.getString("Title"));
                p.setAlias(rs.getString("Alias"));
                p.setDescription(rs.getString("Description"));
                p.setDetail(rs.getString("Detail"));
                p.setImage(rs.getString("Image"));
                p.setPrice(rs.getDouble("Price"));
                p.setPriceSale(rs.getDouble("PriceSale"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCategoryId(rs.getInt("CategoryId"));
                p.setNew(rs.getBoolean("IsNew"));
                p.setBestSeller(rs.getBoolean("IsBestSeller"));
                p.setActive(rs.getBoolean("IsActive"));
                p.setStar(rs.getInt("Star"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Product> getAllMacBook() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_Product WHERE IsActive = 1 AND CategoryId = 2 ORDER BY CreatedDate DESC LIMIT 4 ";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductId"));
                p.setTitle(rs.getString("Title"));
                p.setAlias(rs.getString("Alias"));
                p.setImage(rs.getString("Image"));
                p.setDescription(rs.getString("Description"));
                p.setDetail(rs.getString("Detail"));
                p.setPrice(rs.getDouble("Price"));
                p.setPriceSale(rs.getDouble("PriceSale"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCategoryId(rs.getInt("CategoryId"));
                p.setNew(rs.getBoolean("IsNew"));
                p.setBestSeller(rs.getBoolean("IsBestSeller"));
                p.setActive(rs.getBoolean("IsActive"));
                p.setStar(rs.getInt("Star"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}

