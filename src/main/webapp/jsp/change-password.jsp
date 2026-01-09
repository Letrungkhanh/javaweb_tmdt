<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5" style="max-width:500px">

    <h4 class="mb-4">🔒 Đổi mật khẩu</h4>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <form method="post"
          action="${pageContext.request.contextPath}/profile">

        <input type="hidden" name="action" value="change-password"/>

        <div class="mb-3">
            <label>Mật khẩu cũ</label>
            <input type="password" name="oldPassword"
                   class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Mật khẩu mới</label>
            <input type="password" name="newPassword"
                   class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Xác nhận mật khẩu mới</label>
            <input type="password" name="confirmPassword"
                   class="form-control" required>
        </div>

        <button class="btn btn-dark w-100">
            Đổi mật khẩu
        </button>
    </form>

    <a href="${pageContext.request.contextPath}/profile"
       class="btn btn-link mt-3">
        ⬅ Quay lại hồ sơ
    </a>

</div>

</body>
</html>
