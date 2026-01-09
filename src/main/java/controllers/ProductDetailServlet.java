package controllers;

import dal.ProductDAO;
import dal.ProductReviewDAO;
import models.Product;
import models.ProductReview;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/product/detail")
public class ProductDetailServlet extends HttpServlet {

	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String productIdStr = request.getParameter("productId");
	        if (productIdStr == null) {
	            response.sendRedirect(request.getContextPath() + "/home");
	            return;
	        }

	        int productId = Integer.parseInt(productIdStr);

	        ProductDAO productDAO = new ProductDAO();
	        ProductReviewDAO reviewDAO = new ProductReviewDAO();

	        // 1️⃣ Lấy sản phẩm
	        Product product = productDAO.getProductById(productId);
	        if (product == null) {
	            response.sendRedirect(request.getContextPath() + "/home");
	            return;
	        }

	        // 🔥 2️⃣ Lấy danh sách review
	        List<ProductReview> reviews =
	                reviewDAO.getReviewsByProduct(productId);

	        // 🔥🔥🔥 3️⃣ THÊM ĐOẠN NÀY NGAY TẠI ĐÂY 🔥🔥🔥
	        double avgStar = reviewDAO.getAverageStar(productId);
	        int totalReview = reviewDAO.countReview(productId);

	        // 4️⃣ Đẩy dữ liệu sang JSP
	        request.setAttribute("product", product);
	        request.setAttribute("reviews", reviews);
	        request.setAttribute("avgStar", avgStar);
	        request.setAttribute("totalReview", totalReview);

	        // 5️⃣ Forward
	        request.getRequestDispatcher("/jsp/product-detail.jsp")
	               .forward(request, response);
	    }
}
