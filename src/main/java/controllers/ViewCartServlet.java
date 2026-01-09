package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
@WebServlet("/cart/view")
public class ViewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy giỏ hàng từ session
        HttpSession session = request.getSession();
        Object cartObj = session.getAttribute("cart");

        // Đưa cart vào request để JSP sử dụng
        request.setAttribute("cart", cartObj);

        // Forward sang JSP hiện có
        request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
    }
}

