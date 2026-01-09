<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <style>
        body {
            min-height: 100vh;
            display: flex;
        }
        .sidebar {
            width: 220px;
            background-color: #343a40;
            color: white;
            flex-shrink: 0;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
        }
        .sidebar a.active,
        .sidebar a:hover {
            background-color: #495057;
        }
        .content {
            flex-grow: 1;
            padding: 20px;
        }
    </style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar d-flex flex-column p-3">
    <h4 class="mb-4">Admin Panel</h4>
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/products" class="nav-link active">
                Products
            </a>
        </li>
        <li class="nav-item"><a href="#" class="nav-link">Categories</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Orders</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Users</a></li>
    </ul>
</div>

<!-- Content -->
<div class="content">
    <h2>Edit Product</h2>

    <form action="${pageContext.request.contextPath}/admin/products" method="post" class="mt-4">

        <!-- ID (hidden) -->
        <input type="hidden" name="id" value="${product.productId}">

        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" name="title" class="form-control"
                   value="${product.title}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Alias</label>
            <input type="text" name="alias" class="form-control"
                   value="${product.alias}">
        </div>

        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description" class="form-control" rows="2">${product.description}</textarea>
        </div>

        <div class="mb-3">
    <label class="form-label">Detail</label>
    <textarea name="detail" class="form-control" rows="4">${product.detail}</textarea>
</div>

<div class="mb-3">
    <label class="form-label">Image</label>

    <!-- Hiển thị ảnh hiện tại -->
    <c:if test="${not empty product.image}">
        <div class="mb-2">
            <img src="${pageContext.request.contextPath}/images/${product.image}"
                 style="max-height: 120px; border: 1px solid #ddd; padding: 5px;">
        </div>
    </c:if>

    <!-- Input nhập tên ảnh -->
    <input type="text"
           name="image"
           class="form-control"
           placeholder="Ví dụ: product1.jpg"
           value="${product.image}">
</div>


        <div class="row">
            <div class="col-md-4 mb-3">
                <label class="form-label">Price</label>
                <input type="number" step="0.01" name="price"
                       class="form-control" value="${product.price}" required>
            </div>

            <div class="col-md-4 mb-3">
                <label class="form-label">Sale Price</label>
                <input type="number" step="0.01" name="priceSale"
                       class="form-control" value="${product.priceSale}">
            </div>

            <div class="col-md-4 mb-3">
                <label class="form-label">Quantity</label>
                <input type="number" name="quantity"
                       class="form-control" value="${product.quantity}" required>
            </div>
        </div>

        <div class="form-check mb-2">
           		<input type="checkbox" name="isNew"
    			<c:if test="${product['new']}">checked</c:if>>

            <label class="form-check-label">New Product</label>
        </div>

        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" name="isBestSeller"
                   <c:if test="${product.bestSeller}">checked</c:if>>
            <label class="form-check-label">Best Seller</label>
        </div>

        <div class="mt-4">
            <button type="submit" class="btn btn-primary">
                Update Product
            </button>
            <a href="${pageContext.request.contextPath}/admin/products"
               class="btn btn-secondary ms-2">
                Cancel
            </a>
        </div>

    </form>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
