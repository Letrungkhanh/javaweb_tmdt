package admin;

import dal.AccountDAO;
import models.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/accounts")
public class AdminAccountServlet extends HttpServlet {

    private AccountDAO accountDAO;

    @Override
    public void init() {
        accountDAO = new AccountDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy danh sách user (role = Customer)
        List<Account> users = accountDAO.getAllCustomers();

        request.setAttribute("users", users);
        request.getRequestDispatcher("/jsp/admin/account/index.jsp")
               .forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");
        int accountId = Integer.parseInt(request.getParameter("id"));

        if ("lock".equals(action)) {
            accountDAO.updateAccountStatus(accountId, false); // khóa
        } 
        else if ("unlock".equals(action)) {
            accountDAO.updateAccountStatus(accountId, true); // mở
        }

        // quay lại danh sách
        response.sendRedirect(request.getContextPath() + "/admin/accounts");
    }
}
