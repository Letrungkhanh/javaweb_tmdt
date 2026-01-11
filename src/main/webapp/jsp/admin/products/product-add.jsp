<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quản lý đơn đặt hàng</title>
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
        .sidebar a.active {
            background-color: #495057;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .content {
            flex-grow: 1;
            padding: 20px;
        }
        body {
		    min-height: 100vh;
		    display: flex;
		    background: #f4f6f9;
		    font-family: Arial, sans-serif;
		}
		
		/* Sidebar */
		.sidebar {
		    width: 220px;
		    background: #ffffff;
		    border-right: 1px solid #e0e0e0;
		    padding: 20px 15px;
		}
		
		.sidebar-title {
		    font-size: 16px;
		    font-weight: 600;
		    margin-bottom: 20px;
		    color: #333;
		}
		
		/* Menu */
		.sidebar-menu {
		    list-style: none;
		    padding: 0;
		    margin: 0;
		}
		
		.sidebar-menu li {
		    margin-bottom: 6px;
		}
		
		.sidebar-menu a {
		    display: block;
		    padding: 10px 12px;
		    color: #444;
		    text-decoration: none;
		    border-radius: 6px;
		    transition: all 0.2s ease;
		    font-size: 14px;
		}
		
		.sidebar-menu a:hover {
		    background-color: #f0f2f5;
		}
		
		.sidebar-menu a.active {
		    background-color: #e9ecef;
		    font-weight: 600;
		    color: #000;
		}
		
		/* Logout */
		.sidebar-menu .logout {
		    margin-top: 25px;
		}
		
		.sidebar-menu .logout a {
		    color: #c0392b;
		}
		
		.sidebar-menu .logout a:hover {
		    background-color: #fdecea;
		}
		
		/* Content */
		.content {
		    flex-grow: 1;
		    padding: 25px;
		    background: #f4f6f9;
		}
        
    </style>
</head>
<body>
<c:set var="uri" value="${pageContext.request.requestURI}" />

<div class="sidebar">
    <h5 class="sidebar-title">Người quản trị</h5>

    <ul class="sidebar-menu">

        <li>
            <a href="${pageContext.request.contextPath}/admin/dashboard"
               class="${uri.contains('/admin/dashboard') ? 'active' : ''}">
                Bảng điều khiển
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/products"
               class="${uri.contains('/admin/products') ? 'active' : ''}">
                Sản phẩm
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/blogs"
               class="${uri.contains('/admin/blogs') ? 'active' : ''}">
                Bài viết
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/productcategory"
               class="${uri.contains('/admin/productcategory') ? 'active' : ''}">
                Danh mục
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/orders"
               class="${uri.contains('/admin/orders') ? 'active' : ''}">
                Đơn hàng
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/accounts"
               class="${uri.contains('/admin/accounts') ? 'active' : ''}">
                Người dùng
            </a>
        </li>

        <li class="logout">
            <a href="${pageContext.request.contextPath}/logout">
                Đăng xuất
            </a>
        </li>

    </ul>
</div>

<!-- Content -->
<div class="content">
    <h2>
        <c:choose>
            <c:when test="${product.productId == 0}">Add Product</c:when>
            <c:otherwise>Edit Product</c:otherwise>
        </c:choose>
    </h2>

    <form action="${pageContext.request.contextPath}/admin/products" method="post" class="mt-4">

        <!-- ID hidden -->
        <input type="hidden" name="id" value="${product.productId}">

        <!-- Title -->
        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" name="title" class="form-control" value="${product.title}" required>
        </div>

        <!-- Alias -->
        <div class="mb-3">
            <label class="form-label">Alias</label>
            <input type="text" name="alias" class="form-control" value="${product.alias}">
        </div>

        <!-- Description -->
        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description" class="form-control" rows="2">${product.description}</textarea>
        </div>

        <!-- Detail -->
        <div class="mb-3">
            <label class="form-label">Detail</label>
            <textarea name="detail" class="form-control" rows="4">${product.detail}</textarea>
        </div>

        <!-- Image -->
        <div class="mb-3">
            <label class="form-label">Image (URL or filename)</label>
            <input type="text" name="image" class="form-control" value="${product.image}">
            <c:if test="${not empty product.image}">
                <img src="${pageContext.request.contextPath}/images/${product.image}" class="preview-img">
            </c:if>
        </div>

        <!-- Price, Sale Price, Quantity -->
        <div class="row">
            <div class="col-md-4 mb-3">
                <label class="form-label">Price</label>
                <input type="number" step="0.01" name="price" class="form-control" value="${product.price}" required>
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Sale Price</label>
                <input type="number" step="0.01" name="priceSale" class="form-control" value="${product.priceSale}">
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Quantity</label>
                <input type="number" name="quantity" class="form-control" value="${product.quantity}" required>
            </div>
        </div>

        <!-- Unit In Stock -->
        <div class="mb-3">
            <label class="form-label">Unit In Stock</label>
            <input type="number" name="unitInStock" class="form-control" value="${product.unitInStock}">
        </div>

        <!-- Checkboxes -->
        <div class="form-check mb-2">
            <input type="checkbox" name="isNew" class="form-check-input" <c:if test="${product.new}">checked</c:if>>
            <label class="form-check-label">New Product</label>
        </div>
        <div class="form-check mb-2">
            <input type="checkbox" name="isBestSeller" class="form-check-input" <c:if test="${product.bestSeller}">checked</c:if>>
            <label class="form-check-label">Best Seller</label>
        </div>
        <div class="form-check mb-3">
            <input type="checkbox" name="isActive" class="form-check-input" <c:if test="${product.active}">checked</c:if>>
            <label class="form-check-label">Active</label>
        </div>

        <!-- Category dropdown -->
        <div class="mb-3">
            <label class="form-label">Category</label>
            <select name="categoryId" class="form-select">
                <c:forEach var="cat" items="${categories}">
                    <option value="${cat.id}" <c:if test="${cat.id == product.categoryId}">selected</c:if>>
                        ${cat.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- Submit buttons -->
        <div class="mt-4">
            <button type="submit" class="btn btn-primary">
                <c:choose>
                    <c:when test="${product.productId == 0}">Add Product</c:when>
                    <c:otherwise>Update Product</c:otherwise>
                </c:choose>
            </button>
            <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-secondary ms-2">Cancel</a>
        </div>

    </form>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
