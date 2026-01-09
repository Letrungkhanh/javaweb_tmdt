<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dal.OrderDAO, models.Order, java.util.*" %>

<%
    int orderId = Integer.parseInt(request.getParameter("orderId"));
    OrderDAO dao = new OrderDAO();
    Order order = dao.getOrderById(orderId);
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Thanh Toán Thành Công</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <div class="card shadow p-4 mx-auto" style="max-width: 600px;">
        <h2 class="text-center text-success">Cảm ơn bạn đã mua hàng!</h2>
        <p>Mã đơn hàng của bạn: <strong><%= order.getCode() %></strong></p>
        <p>Tổng tiền: <strong>$<%= order.getTotalAmount() %></strong></p>

        <a href="<%= request.getContextPath() %>/home" class="btn btn-primary mt-3">
            Tiếp tục mua sắm
        </a>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
