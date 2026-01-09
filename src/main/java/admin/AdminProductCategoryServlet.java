package admin;

import dal.ProductCategoryDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.ProductCategory;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/productcategory")
public class AdminProductCategoryServlet extends HttpServlet {

    private ProductCategoryDAO categoryDAO;

    @Override
    public void init() {
        categoryDAO = new ProductCategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                request.getRequestDispatcher("/jsp/admin/productcategory/add.jsp")
                        .forward(request, response);
                break;

            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                ProductCategory c = categoryDAO.getById(id);
                request.setAttribute("category", c);
                request.getRequestDispatcher("/jsp/admin/productcategory/edit.jsp")
                        .forward(request, response);
                break;

            case "delete":
                int delId = Integer.parseInt(request.getParameter("id"));
                categoryDAO.delete(delId);
                response.sendRedirect(request.getContextPath() + "/admin/productcategory");
                break;

            default: // list
                List<ProductCategory> list = categoryDAO.getAll();
                request.setAttribute("categories", list);
                request.getRequestDispatcher("/jsp/admin/productcategory/index.jsp")
                        .forward(request, response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        ProductCategory c = new ProductCategory();
        c.setTitle(request.getParameter("title"));
        c.setAlias(request.getParameter("alias"));
        c.setDescription(request.getParameter("description"));
        c.setIcon(request.getParameter("icon"));
        c.setPosition(Integer.parseInt(request.getParameter("position")));
        c.setActive(request.getParameter("isActive") != null);
        c.setCreatedBy(1);
        c.setModifiedBy(1);

        if ("add".equals(action)) {
            categoryDAO.insert(c);
        } else if ("edit".equals(action)) {
            c.setCategoryId(Integer.parseInt(request.getParameter("id")));
            categoryDAO.update(c);
        }

        response.sendRedirect(request.getContextPath() + "/admin/productcategory");
    }
}
