package controllers;

import dal.OrderDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import models.CartItem;
import models.Order;
import models.OrderDetail;
import models.Account;

import java.io.IOException;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.getRequestDispatcher("/jsp/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod"); // ⭐ MỚI

        Map<Integer, CartItem> cart =
                (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        double total = 0;
        int totalQuantity = 0;

        for (CartItem item : cart.values()) {
            total += item.getProduct().getPrice() * item.getQuantity();
            totalQuantity += item.getQuantity();
        }

        String code = "HD" + System.currentTimeMillis();

        Order order = new Order(code, fullname, phone, email, address, total, totalQuantity);
        order.setAccountId(acc.getAccountId());
        order.setPaymentMethod(paymentMethod);
        order.setPaymentStatus("UNPAID");

        OrderDAO dao = new OrderDAO();
        int orderId = dao.insertOrder(order);

        for (CartItem item : cart.values()) {
            OrderDetail detail = new OrderDetail(
                    orderId,
                    item.getProduct().getProductId(),
                    item.getProduct().getPrice(),
                    item.getQuantity()
            );
            dao.insertOrderDetail(orderId, detail);
        }

        // ⭐ NẾU LÀ VNPAY → ĐI THANH TOÁN
        if ("VNPAY".equals(paymentMethod)) {
            response.sendRedirect(
                    request.getContextPath()
                            + "/vnpay-pay?orderId=" + orderId
                            + "&amount=" + (long) total
            );
            return;
        }

        // ⭐ COD
        session.removeAttribute("cart");
        response.sendRedirect(
                request.getContextPath() + "/jsp/thankyou.jsp?orderId=" + orderId
        );
    }


}

