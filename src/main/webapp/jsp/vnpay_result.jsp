<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Kết quả thanh toán</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">

            <c:choose>
                <c:when test="${success}">
                    <div class="card shadow border-success">
                        <div class="card-body text-center">
                            <h3 class="text-success mb-3">✅ Thanh toán thành công</h3>
                            <p>Cảm ơn bạn đã mua hàng.</p>

                            <a href="${pageContext.request.contextPath}/orders"
                               class="btn btn-success mt-3">
                                Xem đơn hàng
                            </a>
                        </div>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="card shadow border-danger">
                        <div class="card-body text-center">
                            <h3 class="text-danger mb-3">❌ Thanh toán thất bại</h3>
                            <p>Giao dịch không thành công hoặc đã bị huỷ.</p>

                            <a href="${pageContext.request.contextPath}/cart"
                               class="btn btn-danger mt-3">
                                Quay lại giỏ hàng
                            </a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>

</body>
</html>
