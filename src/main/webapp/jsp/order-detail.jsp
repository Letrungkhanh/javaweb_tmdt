<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết đơn hàng</title>

    <!-- Bootstrap -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <style>
        body {
            background-color: #f8f9fa;
        }
    </style>
</head>

<body>

<div class="container py-5">

    <div class="card shadow rounded-4">

        <!-- HEADER -->
        <div class="card-header bg-dark text-white rounded-top-4">
            <h4 class="mb-0">
                📦 Chi tiết đơn hàng
                <span class="badge bg-light text-dark ms-2">
                    ${order.code}
                </span>
            </h4>
        </div>

        <!-- BODY -->
        <div class="card-body">

            <!-- Thông tin đơn hàng -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <p><strong>👤 Người nhận:</strong> ${order.customerName}</p>
                    <p><strong>📞 Điện thoại:</strong> ${order.phone}</p>
                </div>
                <div class="col-md-6">
                    <p><strong>🏠 Địa chỉ:</strong> ${order.address}</p>
                    <p>
                        <strong>🕒 Ngày đặt:</strong>
                        <fmt:formatDate value="${order.createdDate}"
                                        pattern="dd/MM/yyyy HH:mm"/>
                    </p>
                </div>
            </div>

            <hr>

            <!-- BẢNG SẢN PHẨM -->
            <table class="table table-bordered align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>Sản phẩm</th>
                        <th style="width: 120px">Giá</th>
                        <th style="width: 100px">Số lượng</th>
                        <th style="width: 140px">Thành tiền</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${order.details}" var="d">
                 
                    
                        <tr>
                            <td>${d.productName}</td>
                            <td>
                                <fmt:formatNumber value="${d.price}" type="number"/> ₫
                            </td>
                            <td>${d.quantity}</td>
                            <td class="text-danger fw-semibold">
                                <fmt:formatNumber
                                    value="${d.price * d.quantity}"
                                    type="number"/> ₫
                            </td>
                        </tr>
                    </c:forEach>

                    <!-- Không có sản phẩm -->
                    <c:if test="${empty order.details}">
                        <tr>
                            <td colspan="4" class="text-center text-muted">
                                Không có sản phẩm trong đơn hàng
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>

            <!-- TỔNG TIỀN -->
            <div class="d-flex justify-content-end mt-4">
                <h5>
                    Tổng tiền:
                    <span class="text-danger fw-bold fs-4">
                        <fmt:formatNumber value="${order.totalAmount}"
                                          type="number"/> ₫
                    </span>
                </h5>
            </div>
            <div class="mt-4 d-flex justify-content-between align-items-center">

				  <c:if test="${order.orderStatusId == 3}">
				    <form method="post" action="${pageContext.request.contextPath}/profile">
				        <input type="hidden" name="action" value="confirm-received">
				        <input type="hidden" name="orderId" value="${order.orderId}">
				        <button class="btn btn-success">
				            ✅ Tôi đã nhận được hàng
				        </button>
				    </form>
				</c:if>

            

        </div>

        <!-- FOOTER -->
        <div class="card-footer bg-white text-end rounded-bottom-4">
            <a href="${pageContext.request.contextPath}/profile?action=order-history"
               class="btn btn-outline-dark">
                ⬅ Quay lại lịch sử đơn hàng
            </a>
        </div>

    </div>

</div>

</body>
</html>
