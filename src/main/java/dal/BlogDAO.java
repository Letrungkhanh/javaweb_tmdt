package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Blog;
import models.BlogComment;
import models.BlogCategory;


public class BlogDAO extends DBContext {

	public List<Blog> getAllActiveBlogs() {
	    List<Blog> list = new ArrayList<>();
	    String sql = "SELECT * FROM tb_Blog WHERE IsActive = 1 ORDER BY CreatedDate DESC";

	    try (PreparedStatement ps = connection.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            list.add(mapResultSet(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	public List<Blog> getAllActiveBlogsWithCategory() {
	    List<Blog> list = new ArrayList<>();

	    String sql = """
	        SELECT b.*, c.BlogCategoryId, c.Title AS CategoryTitle
	        FROM tb_Blog b
	        LEFT JOIN tb_BlogCategory c ON b.CategoryId = c.BlogCategoryId
	        WHERE b.IsActive = 1
	        ORDER BY b.CreatedDate DESC
	    """;

	    try (PreparedStatement ps = connection.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Blog b = mapResultSet(rs);

	            BlogCategory cat = new BlogCategory();
	            cat.setBlogCategoryId(rs.getInt("BlogCategoryId"));
	            cat.setTitle(rs.getString("CategoryTitle"));

	            b.setCategory(cat);
	            list.add(b);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	public List<Blog> getAllBlogsForAdmin() {
	    List<Blog> list = new ArrayList<>();
	    String sql = "SELECT * FROM tb_Blog ORDER BY CreatedDate DESC";

	    try (PreparedStatement ps = connection.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            list.add(mapResultSet(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	public List<BlogComment> getLatestComments(int limit) {
	    List<BlogComment> list = new ArrayList<>();
	    String sql = "SELECT * FROM tb_BlogComment WHERE IsActive = 1 ORDER BY CreatedDate DESC LIMIT ?";

	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, limit);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            BlogComment c = new BlogComment();
	            c.setCommentId(rs.getInt("CommentId"));
	            c.setBlogId(rs.getInt("BlogId"));
	            c.setName(rs.getString("Name"));
	            c.setDetail(rs.getString("Detail"));
	            c.setCreatedDate(rs.getTimestamp("CreatedDate"));
	            list.add(c);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}



	public Blog getBlogById(int id) {
	    String sql = "SELECT * FROM tb_Blog WHERE BlogId = ?";

	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) return mapResultSet(rs);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


    // ================= INSERT =================
    public void insertBlog(Blog b) {
        String sql = "INSERT INTO tb_Blog " +
                "(Title, Alias, Description, Detail, Image, CategoryId, CreatedBy, IsActive) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAlias());
            ps.setString(3, b.getDescription());
            ps.setString(4, b.getDetail());
            ps.setString(5, b.getImage());
            ps.setInt(6, b.getCategoryId());
            ps.setInt(7, b.getCreatedBy());
            ps.setBoolean(8, b.isActive());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    public void updateBlog(Blog b) {
        String sql = "UPDATE tb_Blog SET " +
                "Title=?, Alias=?, Description=?, Detail=?, Image=?, CategoryId=?, ModifiedBy=?, IsActive=? " +
                "WHERE BlogId=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAlias());
            ps.setString(3, b.getDescription());
            ps.setString(4, b.getDetail());
            ps.setString(5, b.getImage());
            ps.setInt(6, b.getCategoryId());
            ps.setInt(7, b.getModifiedBy()); // ✅ index 7
            ps.setBoolean(8, b.isActive()); // ✅ index 8
            ps.setInt(9, b.getBlogId());    // ✅ index 9
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE (SOFT) =================
    public void deleteBlog(int id) {
        String sql = "DELETE FROM tb_Blog WHERE BlogId=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // ================= MAP =================
    private Blog mapResultSet(ResultSet rs) throws SQLException {
        Blog b = new Blog();
        b.setBlogId(rs.getInt("BlogId"));
        b.setTitle(rs.getString("Title"));
        b.setAlias(rs.getString("Alias"));
        b.setDescription(rs.getString("Description"));
        b.setDetail(rs.getString("Detail"));
        b.setImage(rs.getString("Image"));
        b.setCategoryId(rs.getInt("CategoryId"));
        b.setCreatedBy(rs.getInt("CreatedBy"));
        b.setModifiedBy(rs.getInt("ModifiedBy"));
        b.setActive(rs.getBoolean("IsActive"));
        b.setCreatedDate(rs.getTimestamp("CreatedDate"));
        b.setModifiedDate(rs.getTimestamp("ModifiedDate"));
        return b;
    }
}
