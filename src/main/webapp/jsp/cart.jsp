<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Giỏ hàng</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<!-- HEADER (GIỮ NGUYÊN) -->
<header class="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm py-3">
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

        <h2 class="text-uppercase mb-4">Your Cart</h2>
        <hr>

        <!-- Nếu giỏ hàng trống -->
        <c:if test="${empty sessionScope.cart}">
            <div class="alert alert-warning">Giỏ hàng hiện đang trống.</div>

            <a href="${pageContext.request.contextPath}/home" class="btn btn-dark">
                ← Tiếp tục mua sắm
            </a>
        </c:if>

        <!-- Nếu có sản phẩm -->
        <c:if test="${not empty sessionScope.cart}">
            <div class="card shadow-sm p-4">
                <table class="table align-middle">

                    <thead class="table-dark">
                        <tr>
                            <th>Sản phẩm</th>
                            <th width="150">Giá</th>
                            <th width="120">Số lượng</th>
                            <th width="150">Tổng</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:set var="grandTotal" value="0" />

                        <c:forEach var="item" items="${sessionScope.cart}">
                            <c:set var="p" value="${item.value.product}" />
                            <c:set var="qty" value="${item.value.quantity}" />
                            <c:set var="total" value="${p.priceSale * qty}" />
                            <c:set var="grandTotal" value="${grandTotal + total}" />

                            <tr>

                                <!-- Ảnh + tên sản phẩm -->
                                <td>
                                    <div class="d-flex align-items-center">
                                        <img src="${pageContext.request.contextPath}/images/${p.image}"
                                             class="rounded"
                                             style="width: 90px; height: auto; margin-right: 15px;">
                                        <div>
                                            <h5 class="mb-1">${p.title}</h5>
                                            <p class="text-muted small">${p.description}</p>
                                        </div>
                                    </div>
                                </td>

                                <!-- Giá -->
                                <td>
                                    <span class="fw-bold text-primary">$${p.priceSale}</span><br>
                                    <small class="text-muted"><del>$${p.price}</del></small>
                                </td>

                                <!-- Số lượng -->
                                <td>
                                    <span class="px-3 py-1 border rounded">${qty}</span>
                                </td>

                                <!-- Tổng -->
                                <td class="fw-bold">$${total}</td>
                            </tr>

                        </c:forEach>

                    </tbody>

                    <tfoot>
                        <tr>
                            <th colspan="3" class="text-end">Tổng cộng:</th>
                            <th class="text-danger fs-5">$${grandTotal}</th>
                        </tr>
                    </tfoot>
                </table>

                <!-- Buttons -->
                <div class="d-flex justify-content-between mt-3">
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-dark">
                        ← Tiếp tục mua sắm
                    </a>

<a href="${pageContext.request.contextPath}/checkout" 
   class="btn btn-primary btn-lg">
    Thanh toán →
</a>

                </div>

            </div>
        </c:if>

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
