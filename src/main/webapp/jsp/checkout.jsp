<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Thanh Toán</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<!-- HEADER (GIỮ NGUYÊN) -->
<header class="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm py-3 fixed-top">
        <div class="container">
            <a class="navbar-brand fw-bold text-uppercase" href="${pageContext.request.contextPath}/home">
                MyShop
            </a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </nav>
</header>

<!-- END HEADER -->

<section class="py-5 bg-light">
    <div class="container">

        <h2 class="text-uppercase mb-4 text-center">Thông Tin Thanh Toán</h2>

        <div class="card shadow-lg p-4 mx-auto" style="max-width: 600px; border-radius: 15px;">

            <form action="${pageContext.request.contextPath}/checkout" method="post">

                <div class="mb-3">
                    <label for="fullname" class="form-label">Họ và tên</label>
                    <input type="text" id="fullname" name="fullname" class="form-control" placeholder="Nguyễn Văn A" required>
                </div>

                <div class="mb-3">
                    <label for="phone" class="form-label">Số điện thoại</label>
                    <input type="text" id="phone" name="phone" class="form-control" placeholder="0123 456 789" required>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" id="email" name="email" class="form-control" placeholder="example@mail.com">
                </div>

                <div class="mb-3">
                    <label for="address" class="form-label">Địa chỉ giao hàng</label>
                    <input type="text" id="address" name="address" class="form-control" placeholder="123 Đường ABC, Quận X, TP.HCM" required>
                </div>
                <div class="mb-3">
				    <label class="form-label">Phương thức thanh toán</label>
				
				    <div class="form-check">
				        <input class="form-check-input" type="radio"
				               name="paymentMethod" value="COD" checked>
				        <label class="form-check-label">
				            Thanh toán khi nhận hàng (COD)
				        </label>
				    </div>
				
				    <div class="form-check">
				        <input class="form-check-input" type="radio"
				               name="paymentMethod" value="VNPAY">
				        <label class="form-check-label">
				            Thanh toán VNPay
				        </label>
				    </div>
				</div>
				                

                <div class="d-grid">
                    <button type="submit" class="btn btn-success btn-lg rounded-pill">
                        Xác Nhận Thanh Toán
                    </button>
                </div>

            </form>

        </div>

    </div>
</section>

<!-- FOOTER (GIỮ NGUYÊN) -->
<footer class="bg-dark text-white py-4 mt-5">
    <div class="container text-center">
        <h5 class="fw-bold">MyShop</h5>
        <p class="mb-1">Cửa hàng bán điện thoại uy tín chất lượng.</p>
        <p class="small mb-0">© 2025 MyShop. All rights reserved.</p>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
