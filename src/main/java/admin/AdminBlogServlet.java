package admin;

import dal.BlogDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Blog;
import models.BlogCategory;
import dal.BlogCategoryDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/blogs")
public class AdminBlogServlet extends HttpServlet {

    private BlogDAO blogDAO;
    private BlogCategoryDAO categoryDAO;
    @Override
    public void init() {
        blogDAO = new BlogDAO();
        categoryDAO = new BlogCategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//System.out.println(">>> AdminBlogServlet DOGET CALLED <<<");
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {

	        case "add":
	            request.setAttribute("categories", categoryDAO.getAllActive());
	            request.getRequestDispatcher("/jsp/admin/blogs/add.jsp")
	                   .forward(request, response);
	            break;


	        case "edit":
	            int id = Integer.parseInt(request.getParameter("id"));
	            Blog blog = blogDAO.getBlogById(id);

	            request.setAttribute("blog", blog);
	            request.setAttribute("categories", categoryDAO.getAllActive());

	            request.getRequestDispatcher("/jsp/admin/blogs/edit.jsp")
	                   .forward(request, response);
	            break;


            case "delete":
                int delId = Integer.parseInt(request.getParameter("id"));
                blogDAO.deleteBlog(delId);
                response.sendRedirect(request.getContextPath() + "/admin/blogs");
                break;

            default: // list
                List<Blog> list = blogDAO.getAllBlogsForAdmin();
                request.setAttribute("blogs", list);
                request.getRequestDispatcher("/jsp/admin/blogs/index.jsp")
                       .forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        System.out.println(">>> ACTION = " + action);

        if ("add".equals(action)) {
            Blog b = getBlogFromRequest(request);
            blogDAO.insertBlog(b);
            System.out.println(">>> INSERT BLOG OK");
        }

        if ("edit".equals(action)) {
            Blog b = getBlogFromRequest(request);
            b.setBlogId(Integer.parseInt(request.getParameter("id")));
            blogDAO.updateBlog(b);
            System.out.println(">>> UPDATE BLOG OK");
        }

        response.sendRedirect(request.getContextPath() + "/admin/blogs");
    }

    

    private Blog getBlogFromRequest(HttpServletRequest request) {
        Blog b = new Blog();
        b.setTitle(request.getParameter("title"));
        b.setAlias(request.getParameter("alias"));
        b.setDescription(request.getParameter("description"));
        b.setDetail(request.getParameter("detail"));
        b.setImage(request.getParameter("image"));
        b.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        b.setActive(request.getParameter("isActive") != null);
        // demo admin
        b.setCreatedBy(1);
        b.setModifiedBy(1);

        return b;
    }
}
