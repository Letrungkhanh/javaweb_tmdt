<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Lịch sử đơn hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body class="bg-light">

<!-- HEADER -->
<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold text-uppercase"
           href="${pageContext.request.contextPath}/home">
            MiniStore
        </a>

        <ul class="navbar-nav ms-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/profile">
                    👤 Profile
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                    🚪 Logout
                </a>
            </li>
        </ul>
    </div>
</nav>

<!-- CONTENT -->
<div class="container py-5">

    <h3 class="mb-4 fw-bold">📦 Lịch sử đơn hàng</h3>

    <!-- Không có đơn -->
    <c:if test="${empty orders}">
        <div class="alert alert-info">
            Bạn chưa có đơn hàng nào.
        </div>
    </c:if>

    <!-- Có đơn -->
    <c:if test="${not empty orders}">
        <div class="card shadow-sm border-0 rounded-4">
            <div class="card-body">

                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-dark">
                            <tr>
                                <th>Mã đơn</th>
                                <th>Người nhận</th>
                                <th>Ngày đặt</th>
                                <th>Số lượng</th>
                                <th>Tổng tiền</th>
                                <th>Trạng thái</th>
                                <th class="text-center">Chi tiết</th>
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${orders}" var="o">
                            <tr>
                                <td class="fw-semibold">${o.code}</td>

                                <td>${o.customerName}</td>

                                <td>
                                    <fmt:formatDate value="${o.createdDate}"
                                                    pattern="dd/MM/yyyy HH:mm"/>
                                </td>

                                <td>${o.quantity}</td>

                                <td class="text-danger fw-semibold">
                                    <fmt:formatNumber value="${o.totalAmount}" type="number"/> $
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${o.orderStatusId == 1}">
                                            <span class="badge bg-warning text-dark">Chờ xử lý</span>
                                        </c:when>
                                        <c:when test="${o.orderStatusId == 2}">
                                            <span class="badge bg-info">Đã xác nhận</span>
                                        </c:when>
                                        <c:when test="${o.orderStatusId == 3}">
                                            <span class="badge bg-primary">Đang giao</span>
                                        </c:when>
                                        <c:when test="${o.orderStatusId == 4}">
                                            <span class="badge bg-success">Hoàn thành</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-danger">Đã huỷ</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                               <td class="text-center">
								   <a href="${pageContext.request.contextPath}/profile?action=order-detail&orderId=${o.orderId}"
									   class="btn btn-sm btn-outline-primary">
									    Xem chi tiết
									</a>



								
								    <c:if test="${o.orderStatusId == 1}">
									    <a href="${pageContext.request.contextPath}/profile?action=cancel-order&orderId=${o.orderId}"
									       class="btn btn-sm btn-danger"
									       onclick="return confirm('Bạn chắc chắn muốn huỷ đơn này?')">
									        Huỷ đơn
									    </a>
									</c:if>

								</td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>

            <div class="card-footer bg-white text-end">
                <a href="${pageContext.request.contextPath}/profile"
                   class="btn btn-outline-dark">
                    ⬅ Quay lại Profile
                </a>
            </div>
        </div>
    </c:if>

</div>

<!-- FOOTER -->
<footer class="bg-dark text-white py-4 mt-5">
    <div class="container text-center">
        <h5 class="fw-bold">MiniStore</h5>
        <p class="mb-1">Cửa hàng công nghệ uy tín – chất lượng</p>
        <p class="small mb-0">© 2025 MiniStore. All rights reserved.</p>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>

