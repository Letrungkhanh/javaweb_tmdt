package controllers;

import dal.OrderDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

@WebServlet("/vnpay-return")
public class VnpayReturnServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();

        String responseCode = request.getParameter("vnp_ResponseCode");
        String txnRef = request.getParameter("vnp_TxnRef");

        // Tách orderId (VD: 25_1767540942151)
        int orderId = Integer.parseInt(txnRef.split("_")[0]);

        if ("00".equals(responseCode)) {

            // ✅ 1. Cập nhật trạng thái thanh toán trong DB
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.updateVnpaySuccess(orderId);

            // ✅ 2. Xoá giỏ hàng
            session.removeAttribute("cart");
            session.removeAttribute("totalAmount");

            // ✅ 3. Chuyển sang trang thành công
            response.sendRedirect(request.getContextPath() + "/jsp/thankyou.jsp?orderId=" + orderId);
        } else {
            response.sendRedirect(request.getContextPath() + "/jsp/fail.jsp");
        }

    }
}


