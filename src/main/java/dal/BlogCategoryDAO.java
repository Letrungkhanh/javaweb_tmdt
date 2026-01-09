package dal;

import java.sql.*;
import java.util.*;
import models.BlogCategory;

public class BlogCategoryDAO extends DBContext {

    public List<BlogCategory> getAllActive() {
        List<BlogCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_BlogCategory WHERE IsActive = 1";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BlogCategory c = new BlogCategory();
                c.setBlogCategoryId(rs.getInt("BlogCategoryId"));
                c.setTitle(rs.getString("Title"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
