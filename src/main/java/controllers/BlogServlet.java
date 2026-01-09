package controllers;

import dal.BlogDAO;
import models.Blog;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/blogs")
public class BlogServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BlogDAO blogDAO = new BlogDAO();
        List<Blog> blogs = blogDAO.getAllActiveBlogs();
        request.setAttribute("blogs", blogs);
        request.getRequestDispatcher("/jsp/admin/blogs/index.jsp").forward(request, response);
    }
}
