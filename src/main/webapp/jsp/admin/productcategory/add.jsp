<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product Category</title>
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
            <a href="${pageContext.request.contextPath}/admin/products" class="nav-link">
                Products
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/productcategory" class="nav-link active">
                Product Categories
            </a>
        </li>
        <li class="nav-item"><a href="#" class="nav-link">Orders</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Users</a></li>
    </ul>
</div>

<!-- Content -->
<div class="content">
    <h2>Add Product Category</h2>

    <form action="${pageContext.request.contextPath}/admin/productcategory" method="post" class="mt-4">
        <input type="hidden" name="action" value="add"/>

        <!-- Title -->
        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" name="title" class="form-control" required>
        </div>

        <!-- Alias -->
        <div class="mb-3">
            <label class="form-label">Alias</label>
            <input type="text" name="alias" class="form-control">
        </div>

        <!-- Description -->
        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description" class="form-control" rows="2"></textarea>
        </div>

        <!-- Icon -->
        <div class="mb-3">
            <label class="form-label">Icon</label>
            <input type="text" name="icon" class="form-control">
        </div>

        <!-- Position -->
        <div class="mb-3">
            <label class="form-label">Position</label>
            <input type="number" name="position" class="form-control" value="0">
        </div>

        <!-- Active -->
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" name="isActive" checked>
            <label class="form-check-label">Active</label>
        </div>

        <!-- Buttons -->
        <div class="mt-4">
            <button type="submit" class="btn btn-primary">Add Category</button>
            <a href="${pageContext.request.contextPath}/admin/productcategory" class="btn btn-secondary ms-2">Cancel</a>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
