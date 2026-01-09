package controllers;

import dal.ProductDAO;
import models.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import models.Blog;
import dal.BlogDAO;
import models.BlogComment;

@WebServlet("/home")
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.getAllProducts();
        List<Product> products_macbook = dao.getAllMacBook();
        // Đẩy danh sách sản phẩm sang JSP
        request.setAttribute("products", products);
        request.setAttribute("products_macbook", products_macbook);
        

        // 2. Lấy danh sách blog mới nhất
        BlogDAO blogDAO = new BlogDAO();
        List<Blog> latestBlogs = blogDAO.getAllActiveBlogs();
        if (latestBlogs.size() > 3) { // chỉ hiển thị 3 bài mới nhất
            latestBlogs = latestBlogs.subList(0, 3);
        }
        request.setAttribute("latestBlogs", latestBlogs);
        
        
        BlogDAO BlogDAO = new BlogDAO();
        List<BlogComment> comments = blogDAO.getLatestComments(5); // 5 comment mới nhất
        request.setAttribute("comments", comments);


        // Forward tới home.jsp
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }
}
