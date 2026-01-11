package controllers;

import dal.BlogDAO;
import models.Blog;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/blogdetail")
public class BlogDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy id từ URL
        String idRaw = request.getParameter("id");
        if (idRaw == null) {
            response.sendRedirect(request.getContextPath() + "/blogs");
            return;
        }

        int id = Integer.parseInt(idRaw);

        // 2. Gọi DAO
        BlogDAO blogDAO = new BlogDAO();
        Blog blog = blogDAO.getBlogById(id);

        if (blog == null) {
            response.sendRedirect(request.getContextPath() + "/blogs");
            return;
        }

        // 3. Đẩy dữ liệu sang JSP
        request.setAttribute("blog", blog);

        // 4. Forward
        request.getRequestDispatcher("/jsp/blogdetail.jsp")
               .forward(request, response);
    }
}
