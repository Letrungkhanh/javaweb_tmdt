package admin;

import dal.OrderDAO;
import dal.OrderStatusDAO;
import models.Order;
import models.OrderStatus;
import models.OrderDetail;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import models.Account;

@WebServlet("/admin/orders")
public class AdminOrderServlet extends HttpServlet {

    private OrderDAO orderDAO;
    private OrderStatusDAO statusDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAO();
        statusDAO = new OrderStatusDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
        case "view":
            int id = Integer.parseInt(request.getParameter("id"));
            Order order = orderDAO.getOrderById(id);
            if(order != null) {
                // Lấy chi tiết sản phẩm kèm tên
                List<OrderDetail> details = orderDAO.getOrderDetailsWithProductName(id);
                order.setDetails(details); // cần thêm thuộc tính details vào Order
            }
            request.setAttribute("order", order);
            request.getRequestDispatcher("/jsp/admin/order/view.jsp")
                   .forward(request, response);
            break;



            case "delete":
                int delId = Integer.parseInt(request.getParameter("id"));
                orderDAO.deleteOrder(delId);
                response.sendRedirect(request.getContextPath() + "/admin/orders");
                break;

            default:
                List<Order> orders = orderDAO.getAllOrders();
                List<OrderStatus> statuses = statusDAO.getAllStatuses(); // <-- Lấy danh sách trạng thái
                request.setAttribute("orders", orders);
                request.setAttribute("statuses", statuses); // <-- set vào request
                request.getRequestDispatcher("/jsp/admin/order/index.jsp")
                        .forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("updateStatus".equals(action)) {

            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int statusId = Integer.parseInt(request.getParameter("statusId"));

            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("account") == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            Account admin = (Account) session.getAttribute("account");
            int adminId = admin.getAccountId();

            orderDAO.updateOrderStatus(orderId, statusId, adminId);

            response.sendRedirect(
                request.getContextPath() + "/admin/orders?action=view&id=" + orderId
            );
        }
    }


}
