package admin;



import models.TopProduct;
import dal.OrderDAO;
import models.CategoryRevenue;

import dal.StatisticsDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.*;
import java.time.LocalDate;


@WebServlet("/admin/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StatisticsDAO dao = new StatisticsDAO();
        OrderDAO orderDao = new OrderDAO();  

        //int year = 2025; // hoặc lấy năm hiện tại


		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		double thisMonth = dao.revenueByMonth(year, month);
		double lastMonth = dao.revenueByMonth(
		        month == 1 ? year - 1 : year,
		        month == 1 ? 12 : month - 1
		);
		List<TopProduct> topProducts = orderDao.getTop5BestSellingProducts();
		List<CategoryRevenue> categoryRevenue = dao.revenueByCategory();
		request.setAttribute("categoryRevenue", categoryRevenue);

	    request.setAttribute("topProducts", topProducts);
		
		request.setAttribute("thisMonthRevenue", thisMonth);
		request.setAttribute("lastMonthRevenue", lastMonth);
        request.setAttribute("totalProducts", dao.countProducts());
        request.setAttribute("totalOrders", dao.countOrders());
        request.setAttribute("totalRevenue", dao.totalRevenue());
        request.setAttribute("totalBlogs", dao.countBlogs());
        request.setAttribute("totalUnprocessedOrders", dao.unprocessedOrders());
        request.setAttribute("totalProcessedOrders", dao.processedOrders());
        request.setAttribute("shippedOrders", dao.shippedOrders());
        request.setAttribute("DeliveredOrders", dao.DeliveredOrders());
        request.setAttribute("CancelledOrder", dao.CancelledOrder());
        // ✅ TRUYỀN YEAR VÀO
        request.setAttribute("revenueByMonth", dao.revenueByMonth(year));

        request.getRequestDispatcher("/jsp/admin/dashboard/dashboard.jsp")
               .forward(request, response);
    }
}

