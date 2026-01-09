<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product Category</title>
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
            <a href="${pageContext.request.contextPath}/admin/productcategory" class="nav-link active">
                Product Categories
            </a>
        </li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/products" class="nav-link">Products</a></li>
    </ul>
</div>

<!-- Content -->
<div class="content">
    <h2>Edit Product Category</h2>

    <form action="${pageContext.request.contextPath}/admin/productcategory" method="post" class="mt-4">

        <!-- Hidden inputs -->
        <input type="hidden" name="action" value="edit"/>
        <input type="hidden" name="id" value="${category.categoryId}"/>

        <!-- Title -->
        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" name="title" class="form-control"
                   value="${category.title}" required>
        </div>

        <!-- Alias -->
        <div class="mb-3">
            <label class="form-label">Alias</label>
            <input type="text" name="alias" class="form-control"
                   value="${category.alias}">
        </div>

        <!-- Description -->
        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description" class="form-control" rows="2">${category.description}</textarea>
        </div>

        <!-- Icon -->
        <div class="mb-3">
            <label class="form-label">Icon</label>
            <input type="text" name="icon" class="form-control" value="${category.icon}">
        </div>

        <!-- Position -->
        <div class="mb-3">
            <label class="form-label">Position</label>
            <input type="number" name="position" class="form-control" 
                   value="${category.position != null ? category.position : 0}">
        </div>

        <!-- Active -->
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" name="isActive"
                   <c:if test="${category.active}">checked</c:if>>
            <label class="form-check-label">Active</label>
        </div>

        <!-- Buttons -->
        <div class="mt-4">
            <button type="submit" class="btn btn-primary">Update Category</button>
            <a href="${pageContext.request.contextPath}/admin/productcategory"
               class="btn btn-secondary ms-2">Cancel</a>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
