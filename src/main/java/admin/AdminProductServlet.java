package admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import dal.ProductDAO;
import models.Product;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/products")
public class AdminProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO(); // khởi tạo DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }


    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = productDAO.getAllProductss();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/jsp/admin/products/admin-product.jsp").forward(request, response);
    }
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Product product = new Product(); // product rỗng
        request.setAttribute("product", product);

        request.getRequestDispatcher("/jsp/admin/products/product-edit.jsp")
               .forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        Product product = new Product();
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            product = productDAO.getProductById(id);
        }
        request.setAttribute("product", product);
        request.getRequestDispatcher("/jsp/admin/products/product-edit.jsp").forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            productDAO.deleteProduct(id);
        }
        response.sendRedirect(request.getContextPath() + "/admin/products");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        String title = request.getParameter("title");
        String alias = request.getParameter("alias");
        String description = request.getParameter("description");
        String detail = request.getParameter("detail");
        String priceStr = request.getParameter("price");
        String priceSaleStr = request.getParameter("priceSale");
        String quantityStr = request.getParameter("quantity");

        // Parse số, nếu rỗng -> 0
        double price = (priceStr != null && !priceStr.trim().isEmpty()) ? Double.parseDouble(priceStr.trim()) : 0;
        double priceSale = (priceSaleStr != null && !priceSaleStr.trim().isEmpty()) ? Double.parseDouble(priceSaleStr.trim()) : 0;
        int quantity = (quantityStr != null && !quantityStr.trim().isEmpty()) ? Integer.parseInt(quantityStr.trim()) : 0;

        boolean isNew = request.getParameter("isNew") != null;
        boolean isBestSeller = request.getParameter("isBestSeller") != null;

        Product product = new Product();
        product.setTitle(title != null ? title : "");
        product.setAlias(alias != null ? alias : "");
        product.setDescription(description != null ? description : "");
        product.setDetail(detail != null ? detail : "");
        product.setPrice(price);
        product.setPriceSale(priceSale);
        product.setQuantity(quantity);
        product.setCategoryId(1); // default category
        product.setNew(isNew);
        product.setBestSeller(isBestSeller);
        product.setActive(true);
        String image = "";

        String imageParam = request.getParameter("image");

        if (imageParam != null && !imageParam.trim().isEmpty()) {
            // Có nhập ảnh mới → dùng ảnh mới
            product.setImage(imageParam.trim());
        } else {
            // Không nhập → giữ ảnh cũ khi update
            if (idStr != null && !idStr.trim().isEmpty() && !idStr.equals("0")) {
                Product old = productDAO.getProductById(Integer.parseInt(idStr));
                if (old != null) {
                    product.setImage(old.getImage());
                }
            }
        }



        if (idStr == null || idStr.trim().isEmpty() || idStr.equals("0")) {
            productDAO.insertProduct(product);
        } else {
            product.setProductId(Integer.parseInt(idStr.trim()));
            productDAO.updateProduct(product);
        }

        response.sendRedirect(request.getContextPath() + "/admin/products");
    }


}
