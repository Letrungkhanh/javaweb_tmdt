<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>${product.title}</title>

    <!-- STYLES -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper-bundle.min.css">
</head>

<body>

<!-- 🌟 HEADER GIỮ NGUYÊN Y NHƯ HOME -->
<header class="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm py-3">
        <div class="container">

            <a class="navbar-brand fw-bold text-uppercase" href="${pageContext.request.contextPath}/home">
                MiniStore
            </a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNav" aria-controls="navbarNav"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">

                <ul class="navbar-nav ms-auto">

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/product">Products</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/cart">
                            Cart
                        </a>
                    </li>

                </ul>

            </div>
        </div>
    </nav>
</header>
<!-- END HEADER -->

<!-- ⭐ PRODUCT DETAIL SECTION -->
<section class="py-5 bg-light">
    <div class="container">

        <div class="row mb-4">
            <div class="col">
                <h2 class="text-uppercase text-dark">${product.title}</h2>
                <hr>
            </div>
        </div>

        <div class="row g-5">

            <!-- IMAGE -->
            <div class="col-md-5">
                <img src="${pageContext.request.contextPath}/images/${product.image}"
                     alt="${product.title}"
                     class="img-fluid rounded shadow-sm">
            </div>

            <!-- PRODUCT INFO -->
            <div class="col-md-7">

                <h3 class="mb-3 text-primary fw-bold">$${product.priceSale}</h3>
                <p class="text-muted"><del>$${product.price}</del></p>
				<div class="mb-3">
				
				    <span class="text-warning fs-5">
				        <c:forEach begin="1" end="${Math.round(avgStar)}">★</c:forEach>
				        <c:forEach begin="${Math.round(avgStar) + 1}" end="5">☆</c:forEach>
				    </span>
				
				    <span class="text-muted ms-2">
				        (${avgStar} / 5) • ${totalReview} reviews
				    </span>
				
				</div>

                <p class="mt-3"><strong>Mô tả:</strong></p>
                <p>${product.description}</p>

                <p class="mt-3"><strong>Chi tiết:</strong></p>
                <p>${product.detail}</p>

                <!-- ADD TO CART -->
           <c:choose>
			    <c:when test="${not empty sessionScope.account}">
			       <a href="${pageContext.request.contextPath}/cart/add?productId=${product.productId}"
					   class="btn btn-black">
					    Add to Cart
					</a>

			    </c:when>
			
			    <c:otherwise>
			        <a href="${pageContext.request.contextPath}/login"
			           class="btn btn-outline-dark">
			            Login to Buy
			        </a>
			    </c:otherwise>
			</c:choose>

			            
            </div>
        </div>

    </div>
</section>
<!-- ⭐ REVIEW SECTION -->
<section class="py-5 bg-white">
    <div class="container">

        <h4 class="mb-4 text-uppercase fw-bold">Customer Reviews</h4>

        <c:if test="${empty reviews}">
            <p class="text-muted">Chưa có đánh giá nào cho sản phẩm này.</p>
        </c:if>

        <c:forEach var="r" items="${reviews}">
            <div class="border rounded p-3 mb-3 shadow-sm">

                <div class="d-flex justify-content-between align-items-center mb-1">
                    <strong class="text-dark">${r.name}</strong>
                    <small class="text-muted">
                        <fmt:formatDate value="${r.createdDate}" pattern="dd/MM/yyyy HH:mm"/>
                    </small>
                </div>

                <!-- STAR -->
                <div class="mb-2 text-warning">
                    <c:forEach begin="1" end="${r.star}">
                        ★
                    </c:forEach>
                </div>

                <!-- COMMENT -->
                <p class="mb-0 text-secondary">
                    ${r.detail}
                </p>
            </div>
        </c:forEach>

    </div>
</section>
<!-- ⭐ WRITE REVIEW -->
<section class="py-4 bg-light">
    <div class="container">

        <c:if test="${not empty sessionScope.account}">
            <h4 class="mb-3 text-uppercase fw-bold">Write a Review</h4>

            <form action="${pageContext.request.contextPath}/product/review"
                  method="post"
                  class="border rounded p-4 bg-white shadow-sm">

                <input type="hidden" name="productId" value="${product.productId}">

                <!-- STAR -->
                <div class="mb-3">
                    <label class="form-label fw-semibold">Rating</label>
                    <select name="star" class="form-select" required>
                        <option value="5">★★★★★ - Excellent</option>
                        <option value="4">★★★★ - Good</option>
                        <option value="3">★★★ - Average</option>
                        <option value="2">★★ - Poor</option>
                        <option value="1">★ - Very bad</option>
                    </select>
                </div>

                <!-- COMMENT -->
                <div class="mb-3">
                    <label class="form-label fw-semibold">Your Comment</label>
                    <textarea name="detail"
                              class="form-control"
                              rows="4"
                              placeholder="Write your experience..."
                              required></textarea>
                </div>

                <button class="btn btn-dark px-4">
                    Submit Review
                </button>
            </form>
        </c:if>

        <c:if test="${empty sessionScope.account}">
            <div class="alert alert-warning mt-3">
                <a href="${pageContext.request.contextPath}/login" class="fw-bold">
                    Login
                </a>
                to write a review.
            </div>
        </c:if>

    </div>
</section>



<!-- 🌟 FOOTER GIỮ NGUYÊN NHƯ HOME -->
<footer class="bg-dark text-white py-4 mt-5">
    <div class="container text-center">

        <h5 class="fw-bold">MyShop</h5>
        <p class="mb-1">Cửa hàng bán điện thoại uy tín chất lượng.</p>

        <p class="small mb-0">© 2025 MyShop. All rights reserved.</p>

    </div>
</footer>
<!-- END FOOTER -->

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/swiper-bundle.min.js"></script>

</body>
</html>
