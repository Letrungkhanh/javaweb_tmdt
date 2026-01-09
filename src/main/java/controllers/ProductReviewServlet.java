package controllers;

import dal.ProductReviewDAO;
import models.ProductReview;
import models.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/product/review")
public class ProductReviewServlet extends HttpServlet {

    private ProductReviewDAO reviewDAO;
    

    @Override
    public void init() {
        reviewDAO = new ProductReviewDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Account acc = (Account) session.getAttribute("account");

        int productId = Integer.parseInt(request.getParameter("productId"));
        float star = Float.parseFloat(request.getParameter("star"));
        String detail = request.getParameter("detail");

        ProductReview r = new ProductReview();
        r.setProductId(productId);
        r.setName(acc.getFullName());
        r.setPhone(acc.getPhone());
        r.setEmail(acc.getEmail());
        r.setStar(star);
        r.setDetail(detail);

        reviewDAO.insertReview(r);

        response.sendRedirect(
            request.getContextPath() + "/product/detail?productId=" + productId
        );
    }
}

