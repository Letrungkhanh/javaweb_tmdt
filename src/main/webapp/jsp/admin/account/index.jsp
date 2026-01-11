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


<div class="content">
    <h3>Tài khoản người dùng</h3>

    <table class="table table-bordered table-hover mt-3">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Tên người dùng</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Điện thoại</th>
                <th>Trạng thái</th>
                <th>Ngày đặt</th>
                <th>Chức năng</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td>${u.accountId}</td>
                    <td>${u.username}</td>
                    <td>${u.fullName}</td>
                    <td>${u.email}</td>
                    <td>${u.phone}</td>
                    <td>
                        <c:choose>
                            <c:when test="${u.active}">
                                <span class="badge bg-success">Mở</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger">Đã khoá</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <fmt:formatDate value="${u.createdDate}" pattern="dd/MM/yyyy HH:mm"/>
                    </td>
                    <td>
					    <c:choose>
					
					       
					        <c:when test="${u.active}">
					            <form method="post"
					                  action="${pageContext.request.contextPath}/admin/accounts"
					                  style="display:inline">
					
					                <input type="hidden" name="action" value="lock"/>
					                <input type="hidden" name="id" value="${u.accountId}"/>
					
					                <button class="btn btn-danger btn-sm"
					                        onclick="return confirm('Khóa tài khoản này?')">
					                    Khoá
					                </button>
					            </form>
					        </c:when>
					
					       
					        <c:otherwise>
					            <form method="post"
					                  action="${pageContext.request.contextPath}/admin/accounts"
					                  style="display:inline">
					
					                <input type="hidden" name="action" value="unlock"/>
					                <input type="hidden" name="id" value="${u.accountId}"/>
					
					                <button class="btn btn-success btn-sm">
					                    Mở khoá
					                </button>
					            </form>
					        </c:otherwise>
					
					    </c:choose>
					</td>

					                    
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
