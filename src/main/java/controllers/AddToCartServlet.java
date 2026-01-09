package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.*;

import dal.ProductDAO;
import models.CartItem;
import models.Product;

@WebServlet("/cart/add")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	HttpSession session = request.getSession(false);
    	if (session == null || session.getAttribute("account") == null) {
    	    response.sendRedirect(request.getContextPath() + "/login");
    	    return;
    	}

        String productIdStr = request.getParameter("productId");
        if (productIdStr == null || productIdStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        int productId = Integer.parseInt(productIdStr);

        ProductDAO dao = new ProductDAO();
        Product product = dao.getProductById(productId);
        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        Map<Integer, CartItem> cart =
                (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        if (cart.containsKey(productId)) {
            cart.get(productId).setQuantity(cart.get(productId).getQuantity() + 1);
        } else {
            cart.put(productId, new CartItem(product, 1));
        }

        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/cart/view");
    }
}

