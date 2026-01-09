package controllers;

import dal.AccountDAO;
import models.Account;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import dal.OrderDAO;
import models.Order;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private AccountDAO accountDAO = new AccountDAO();

    
    private void showOrderHistory(HttpServletRequest request,
            HttpServletResponse response,
            Account acc)
			throws ServletException, IOException {
			
			OrderDAO dao = new OrderDAO();
			List<Order> orders = dao.getOrdersByAccountId(acc.getAccountId());
			
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/jsp/order-history.jsp")
			.forward(request, response);
			}
    private void cancelOrder(HttpServletRequest request,
            HttpServletResponse response,
            Account acc)
			throws ServletException, IOException {
			
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			
			OrderDAO dao = new OrderDAO();
			
			// chỉ huỷ đơn của chính mình + trạng thái chờ xử lý
			boolean success = dao.cancelOrder(orderId, acc.getAccountId());
			
			response.sendRedirect(
			request.getContextPath() + "/profile?action=order-history"
			);
			}
    private void showOrderDetail(HttpServletRequest request,
            HttpServletResponse response,
            Account acc)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        OrderDAO dao = new OrderDAO();
        Order order = dao.getOrderByIdAndAccount(orderId, acc.getAccountId());

        if (order == null) {
            response.sendRedirect(request.getContextPath() + "/profile?action=order-history");
            return;
        }

        request.setAttribute("order", order);
        request.getRequestDispatcher("/jsp/order-detail.jsp")
               .forward(request, response);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "view";

        switch (action) {

            case "order-history":
                showOrderHistory(request, response, acc);
                break;

            case "cancel-order":
                cancelOrder(request, response, acc);
                break;

            case "change-password":
                request.getRequestDispatcher("/jsp/change-password.jsp")
                       .forward(request, response);
                break;
            case "order-detail":
                showOrderDetail(request, response, acc);
                break;
            



            default:
                request.getRequestDispatcher("/jsp/profile.jsp")
                       .forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        // ✅ 1. KHÁCH XÁC NHẬN ĐÃ NHẬN HÀNG (3 → 4)
        if ("confirm-received".equals(action)) {

            int orderId = Integer.parseInt(request.getParameter("orderId"));

            OrderDAO dao = new OrderDAO();
            dao.confirmOrderReceived(orderId, acc.getAccountId());

            response.sendRedirect(
                request.getContextPath() + "/profile?action=order-detail&orderId=" + orderId
            );
            return;
        }

        // ✅ 2. ĐỔI MẬT KHẨU (GIỮ NGUYÊN CODE CŨ)
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp");
            request.getRequestDispatcher("/jsp/change-password.jsp")
                   .forward(request, response);
            return;
        }

        if (!accountDAO.checkOldPassword(acc.getAccountId(), oldPassword)) {
            request.setAttribute("error", "Mật khẩu cũ không đúng");
            request.getRequestDispatcher("/jsp/change-password.jsp")
                   .forward(request, response);
            return;
        }

        accountDAO.updatePassword(acc.getAccountId(), newPassword);

        request.setAttribute("success", "Đổi mật khẩu thành công");
        request.getRequestDispatcher("/jsp/change-password.jsp")
               .forward(request, response);
    }


}
