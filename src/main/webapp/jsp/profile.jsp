<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>My Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
    <div class="container">

        <a class="navbar-brand fw-bold text-uppercase"
           href="${pageContext.request.contextPath}/home">
            MiniStore
        </a>

        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto align-items-center">

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/home">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/product">Products</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/cart/view">Cart</a>
                </li>

                <c:choose>
                    <c:when test="${not empty sessionScope.account}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle fw-semibold"
                               href="#" role="button" data-bs-toggle="dropdown">
                                ${sessionScope.account.fullName}
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/profile">
                                        👤 Profile
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/logout">
                                        🚪 Logout
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </c:when>

                    <c:otherwise>
                        <li class="nav-item">
                            <a class="btn btn-dark ms-3"
                               href="${pageContext.request.contextPath}/login">
                                Login
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </div>
</nav>
<div class="container py-5">

    <div class="row justify-content-center">
        <div class="col-md-8">

            <!-- CARD PROFILE -->
            <div class="card shadow-sm border-0 rounded-4">

                <!-- HEADER -->
                <div class="card-header bg-dark text-white rounded-top-4">
                    <h4 class="mb-0">
                        👤 Thông tin cá nhân
                    </h4>
                </div>

                <!-- BODY -->
                <div class="card-body p-4">

                    <table class="table table-borderless align-middle mb-0">
                        <tr>
                            <th class="text-muted w-25">Họ tên</th>
                            <td class="fw-semibold">
                                ${sessionScope.account.fullName}
                            </td>
                        </tr>

                        <tr>
                            <th class="text-muted">Username</th>
                            <td>${sessionScope.account.username}</td>
                        </tr>

                        <tr>
                            <th class="text-muted">Email</th>
                            <td>${sessionScope.account.email}</td>
                        </tr>

                        <tr>
                            <th class="text-muted">Số điện thoại</th>
                            <td>${sessionScope.account.phone}</td>
                        </tr>

                        <tr>
                            <th class="text-muted">Vai trò</th>
                            <td>
                                <span class="badge bg-primary">
                                    ${sessionScope.account.roleName}
                                </span>
                            </td>
                        </tr>

                        <tr>
                            <th class="text-muted">Ngày tạo</th>
                            <td>
                                <fmt:formatDate
                                    value="${sessionScope.account.createdDate}"
                                    pattern="dd/MM/yyyy HH:mm:ss"/>
                            </td>
                        </tr>

                        <tr>
                            <th class="text-muted">Đăng nhập gần nhất</th>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.account.lastLogin}">
                                        <fmt:formatDate
                                            value="${sessionScope.account.lastLogin}"
                                            pattern="dd/MM/yyyy HH:mm:ss"/>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-muted">Chưa có</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </table>

                </div>

                <!-- FOOTER -->
                <div class="card-footer bg-white rounded-bottom-4">
				    <div class="d-flex justify-content-between align-items-center flex-wrap gap-2">
				
				        <!-- Back home -->
				        <a href="${pageContext.request.contextPath}/home"
				           class="btn btn-outline-dark">
				            ⬅ Trang chủ
				        </a>
				
				        <!-- Actions -->
				        <div class="d-flex gap-2">
				            <a href="${pageContext.request.contextPath}/profile?action=change-password"
				               class="btn btn-outline-secondary">
				                🔒 Đổi mật khẩu
				            </a>
				
				            <a href="${pageContext.request.contextPath}/order-history"
				               class="btn btn-outline-primary">
				                📦 Lịch sử đơn hàng
				            </a>
				        </div>
				
				    </div>
				</div>
		           

            </div>

        </div>
    </div>

</div>
<footer class="bg-dark text-white mt-5 py-4">
    <div class="container text-center">
        <h5 class="fw-bold mb-2">MiniStore</h5>
        <p class="mb-1">Cửa hàng công nghệ uy tín – chất lượng</p>
        <p class="small mb-0">© 2025 MiniStore. All rights reserved.</p>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

</body>
</html>
