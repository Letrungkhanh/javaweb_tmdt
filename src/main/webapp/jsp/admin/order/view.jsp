<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Detail</title>

<!-- Bootstrap 5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body class="bg-light">

<div class="container my-4">

    <!-- TITLE -->
    <h3 class="mb-4">📦 Chi tiết đơn hàng</h3>

    <!-- ORDER INFO -->
    <div class="card mb-4 shadow-sm">
        <div class="card-header bg-primary text-white">
            Thông tin đơn hàng
        </div>
        <div class="card-body row">
            <div class="col-md-6">
                <p><strong>Mã đơn hàng:</strong> ${order.code}</p>
                <p><strong>Người đặt:</strong> ${order.customerName}</p>
                <p><strong>Số điện thoại:</strong> ${order.phone}</p>
            </div>
            <div class="col-md-6">
                <p><strong>Dịa chỉ:</strong> ${order.address}</p>
                <p><strong>Tổng số lượng:</strong> ${order.quantity}</p>
                <p>
                    <strong>Tổng tiền:</strong>
                    <span class="text-danger fw-bold">
                        <fmt:formatNumber value="${order.totalAmount}" type="currency"/>
                    </span>
                </p>
            </div>
        </div>
    </div>

    <!-- ORDER DETAILS TABLE -->
    <div class="card shadow-sm">
        <div class="card-header bg-dark text-white">
            Danh sách đơn hàng
        </div>
        <div class="card-body p-0">
            <table class="table table-hover table-bordered mb-0 align-middle text-center">
                <thead class="table-secondary">
                    <tr>
                        <th>Sản phẩm</th>
                        <th width="120">Giá</th>
                        <th width="100">Số lượng</th>
                        <th width="150">Tổng đơn hàng</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="d" items="${order.details}">
                        <tr>
                            <td class="text-start">${d.productName}</td>
                            <td>
							    <fmt:formatNumber value="${d.price}" type="currency"/>
							</td>

                            <td>${d.quantity}</td>
                            <td class="fw-bold text-danger">
                                <fmt:formatNumber value="${d.price * d.quantity}" type="currency"/>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
	<!-- ORDER ACTION -->
	<div class="mt-4 d-flex justify-content-between align-items-center">

    <!-- STATUS -->
    <div>
        <c:choose>
            <c:when test="${order.orderStatusId == 1}">
                <span class="badge bg-warning fs-6">⏳ Chờ xác nhận</span>
            </c:when>

            <c:when test="${order.orderStatusId == 2}">
                <span class="badge bg-info fs-6">📦 Đã xác nhận</span>
            </c:when>

            <c:when test="${order.orderStatusId == 3}">
                <span class="badge bg-success fs-6">🚚 Đang giao hàng</span>
            </c:when>
        </c:choose>
    </div>

    <!-- ACTION BUTTON -->
    <div class="d-flex gap-2">

        <c:if test="${order.orderStatusId == 1}">
    <form method="post" action="${pageContext.request.contextPath}/admin/orders">
        <input type="hidden" name="action" value="updateStatus">
        <input type="hidden" name="orderId" value="${order.orderId}">
        <input type="hidden" name="statusId" value="2">
        <button class="btn btn-success">
            ✅ Xác nhận đơn
        </button>
    </form>
</c:if>


        <c:if test="${order.orderStatusId == 2}">
    <form method="post" action="${pageContext.request.contextPath}/admin/orders">
        <input type="hidden" name="action" value="updateStatus">
        <input type="hidden" name="orderId" value="${order.orderId}">
        <input type="hidden" name="statusId" value="3">
        <button class="btn btn-primary">
            🚚 Giao cho đơn vị vận chuyển
        </button>
    </form>
</c:if>


        <a href="${pageContext.request.contextPath}/admin/orders"
           class="btn btn-secondary">
            Quay lại danh sách đơn hàng
        </a>
    </div>
</div>

		
    <!-- ACTION -->
   


</body>
</html>
