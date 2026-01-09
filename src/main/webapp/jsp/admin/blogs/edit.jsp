<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Blog</title>
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
            <a href="${pageContext.request.contextPath}/admin/blogs" class="nav-link active">
                Blogs
            </a>
        </li>
        <li class="nav-item"><a href="#" class="nav-link">Categories</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Orders</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Users</a></li>
    </ul>
</div>

<!-- Content -->
<div class="content">
    <h2>Edit Blog</h2>

    <form action="${pageContext.request.contextPath}/admin/blogs" method="post" class="mt-4">
        <!-- Hidden inputs -->
        <input type="hidden" name="action" value="edit"/>
        <input type="hidden" name="id" value="${blog.blogId}"/>

        <!-- Title -->
        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" name="title" class="form-control"
                   value="${blog.title}" required>
        </div>

        <!-- Alias -->
        <div class="mb-3">
            <label class="form-label">Alias</label>
            <input type="text" name="alias" class="form-control"
                   value="${blog.alias}">
        </div>

        <!-- Description -->
        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description" class="form-control" rows="2">${blog.description}</textarea>
        </div>

        <!-- Detail -->
        <div class="mb-3">
            <label class="form-label">Detail</label>
            <textarea name="detail" class="form-control" rows="5">${blog.detail}</textarea>
        </div>

        <!-- Image -->
        <div class="mb-3">
            <label class="form-label">Image (filename)</label>
            <input type="text" name="image" class="form-control" value="${blog.image}">
            <c:if test="${not empty blog.image}">
                <div class="mt-2">
                    <img src="${pageContext.request.contextPath}/images/${blog.image}"
                         style="max-width:150px; border:1px solid #ccc; padding:5px;">
                </div>
            </c:if>
        </div>

        <!-- Category -->
        <div class="mb-3">
            <label class="form-label">Category</label>
            <select name="categoryId" class="form-select">
                <c:forEach var="c" items="${categories}">
                    <option value="${c.blogCategoryId}"
                        <c:if test="${blog.categoryId == c.blogCategoryId}">selected</c:if>>
                        ${c.title}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- Active -->
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" name="isActive"
                   <c:if test="${blog.active}">checked</c:if>>
            <label class="form-check-label">Active</label>
        </div>

        <!-- Buttons -->
        <div class="mt-4">
            <button type="submit" class="btn btn-primary">Update Blog</button>
            <a href="${pageContext.request.contextPath}/admin/blogs"
               class="btn btn-secondary ms-2">Cancel</a>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
