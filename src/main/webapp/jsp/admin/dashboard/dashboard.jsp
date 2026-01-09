<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Orders</title>
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
		.dashboard-card {
		    border-radius: 14px;
		    transition: transform 0.2s ease, box-shadow 0.2s ease;
		}
		
		.dashboard-card:hover {
		    transform: translateY(-5px);
		    box-shadow: 0 8px 20px rgba(0,0,0,0.12);
		}
		
		.dashboard-icon {
		    font-size: 40px;
		    opacity: 0.85;
		}
				
        
    </style>
</head>
<body>
<c:set var="uri" value="${pageContext.request.requestURI}" />

<div class="sidebar">
    <h5 class="sidebar-title">Admin Panel</h5>

    <ul class="sidebar-menu">

        <li>
            <a href="${pageContext.request.contextPath}/admin/dashboard"
               class="${uri.contains('/admin/dashboard') ? 'active' : ''}">
                Dashboard
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/products"
               class="${uri.contains('/admin/products') ? 'active' : ''}">
                Products
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/blogs"
               class="${uri.contains('/admin/blogs') ? 'active' : ''}">
                Blogs
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/productcategory"
               class="${uri.contains('/admin/productcategory') ? 'active' : ''}">
                Categories
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/orders"
               class="${uri.contains('/admin/orders') ? 'active' : ''}">
                Orders
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/accounts"
               class="${uri.contains('/admin/accounts') ? 'active' : ''}">
                User
            </a>
        </li>

        <li class="logout">
            <a href="${pageContext.request.contextPath}/logout">
                Logout
            </a>
        </li>

    </ul>
</div>

<div class="content">
    <h2 class="mb-4 fw-bold">📊 Dashboard Overview</h2>

    <div class="row g-4">

        <!-- Tổng sản phẩm -->
               <div class="col-md-3">
            <div class="card dashboard-card bg-primary text-white shadow-sm">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h6 class="text-uppercase mb-1">Tổng sản phẩm</h6>
                        <h2 class="fw-bold mb-0">${totalProducts}</h2>
                    </div>
                    <div class="dashboard-icon">📦</div>
                </div>
            </div>
        </div>

        <!-- Tổng đơn hàng -->
        <div class="col-md-3">
            <div class="card dashboard-card bg-success text-white shadow-sm">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h6 class="text-uppercase mb-1">Tổng đơn hàng</h6>
                        <h2 class="fw-bold mb-0">${totalOrders}</h2>
                    </div>
                    <div class="dashboard-icon">🧾</div>
                </div>
            </div>
        </div>

        <!-- Đơn hàng chờ xử lý -->
        <div class="col-md-3">
            <div class="card dashboard-card bg-secondary text-white shadow-sm">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h6 class="text-uppercase mb-1">Chờ xử lý</h6>
                        <h2 class="fw-bold mb-0">${totalUnprocessedOrders}</h2>
                    </div>
                    <div class="dashboard-icon">⏳</div>
                </div>
            </div>
        </div>

        <!-- Đơn hàng đang xử lý -->
        <div class="col-md-3">
            <div class="card dashboard-card bg-info text-white shadow-sm">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h6 class="text-uppercase mb-1">Đang xử lý</h6>
                        <h2 class="fw-bold mb-0">${totalProcessedOrders}</h2>
                    </div>
                    <div class="dashboard-icon">⚙️</div>
                </div>
            </div>
        </div>

        <!-- Đã gửi hàng -->
        <div class="col-md-3">
            <div class="card dashboard-card bg-primary text-white shadow-sm">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h6 class="text-uppercase mb-1">Đã gửi hàng</h6>
                        <h2 class="fw-bold mb-0">${shippedOrders}</h2>
                    </div>
                    <div class="dashboard-icon">🚚</div>
                </div>
            </div>
        </div>

        <!-- Đã giao hàng -->
        <div class="col-md-3">
            <div class="card dashboard-card bg-success text-white shadow-sm">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h6 class="text-uppercase mb-1">Đã giao hàng</h6>
                        <h2 class="fw-bold mb-0">${DeliveredOrders}</h2>
                    </div>
                    <div class="dashboard-icon">✅</div>
                </div>
            </div>
        </div>

        <!-- Đơn hàng bị huỷ -->
        <div class="col-md-3">
            <div class="card dashboard-card bg-danger text-white shadow-sm">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h6 class="text-uppercase mb-1">Đơn huỷ</h6>
                        <h2 class="fw-bold mb-0">${CancelledOrder}</h2>
                    </div>
                    <div class="dashboard-icon">❌</div>
                </div>
            </div>
        </div>

        <!-- Tổng doanh thu -->
        <div class="col-md-3">
            <div class="card dashboard-card bg-warning text-dark shadow-sm">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h6 class="text-uppercase mb-1">Tổng doanh thu</h6>
                        <h2 class="fw-bold mb-0">$${totalRevenue}</h2>
                    </div>
                    <div class="dashboard-icon">💰</div>
                </div>
            </div>
        </div>

        <!-- Tổng blog -->
        <div class="col-md-3">
            <div class="card dashboard-card bg-info text-white shadow-sm">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h6 class="text-uppercase mb-1">Bài viết</h6>
                        <h2 class="fw-bold mb-0">${totalBlogs}</h2>
                    </div>
                    <div class="dashboard-icon">📝</div>
                </div>
            </div>
        </div>

        
       
				
		<div class="row mt-4">
		 <div style="width:50%" class="card mt-4 shadow-sm">
		    <div class="card-body">
		        <h5 class="mb-3">📈 Doanh thu theo tháng</h5>
		        <canvas id="revenueChart" height="100"></canvas>
		    </div>
		</div>	
		
		<div style="width:50%"  class="card mt-4 shadow-sm">
		    <div class="card-body">
		        <h5 class="mb-3">💰 Doanh thu theo danh mục</h5>
		        <canvas id="categoryRevenueChart" height="120"></canvas>
		    </div>
		</div>
    <!-- TOP 5 PRODUCTS -->
   <div class="card shadow-sm mt-4">
    <div class="card-header bg-dark text-white">
        🥇 Top 5 sản phẩm bán chạy
    </div>

    <div class="card-body p-0">
        <table class="table table-hover mb-0 align-middle text-center">
            <thead class="table-light">
                <tr>
                    <th>#</th>
                    <th class="text-start">Sản phẩm</th>
                    <th>Đã bán</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${topProducts}" var="p" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td class="text-start">${p.productName}</td>
                        <td>
                            <span class="badge bg-success fs-6">
                                ${p.totalSold}
                            </span>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty topProducts}">
                    <tr>
                        <td colspan="3" class="text-muted py-3">
                            Chưa có dữ liệu
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>


				

    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    const labels = [
    	<c:forEach var="e" items="${revenueByMonth}" varStatus="loop">
        "Tháng ${e.key}"<c:if test="${!loop.last}">,</c:if>
    </c:forEach>

    ];

    const data = [
        <c:forEach var="e" items="${revenueByMonth}">
            ${e.value},
        </c:forEach>
    ];

    new Chart(document.getElementById("revenueChart"), {
        type: "line",
        data: {
            labels: labels,
            datasets: [{
                label: "Doanh thu",
                data: data,
                borderColor: "#0d6efd",
                backgroundColor: "rgba(13,110,253,0.1)",
                tension: 0.4,
                fill: true
            }]
        }
    });
</script>
<script>
const categoryLabels = [
    <c:forEach var="c" items="${categoryRevenue}" varStatus="loop">
        "${c.title}"<c:if test="${!loop.last}">,</c:if>
    </c:forEach>
];

const categoryData = [
    <c:forEach var="c" items="${categoryRevenue}" varStatus="loop">
        ${c.revenue}<c:if test="${!loop.last}">,</c:if>
    </c:forEach>
];

new Chart(document.getElementById('categoryRevenueChart'), {
    type: 'bar',
    data: {
        labels: categoryLabels,
        datasets: [{
            label: 'Doanh thu theo danh mục',
            data: categoryData,
            backgroundColor: [
                '#4e73df',
                '#1cc88a',
                '#36b9cc',
                '#f6c23e',
                '#e74a3b'
            ]
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: { display: false }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
</script>



<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
