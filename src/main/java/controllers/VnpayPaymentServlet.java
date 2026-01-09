package controllers;

import config.VnpayConfig;
import utils.VnpayUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/vnpay-pay")
public class VnpayPaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int orderId = Integer.parseInt(req.getParameter("orderId"));
        long amount = Long.parseLong(req.getParameter("amount")) * 100000;

        Map<String, String> vnpParams = new HashMap<>();

        // ===== PARAMS BẮT BUỘC =====
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", VnpayConfig.TMN_CODE);
        vnpParams.put("vnp_Amount", String.valueOf(amount));
        vnpParams.put("vnp_CurrCode", "VND");

        // ❗ KHÔNG ĐƯỢC TRÙNG
        vnpParams.put("vnp_TxnRef",
                orderId + "_" + System.currentTimeMillis());

        vnpParams.put("vnp_OrderInfo",
                "Thanh_toan_don_hang_" + orderId);

        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_ReturnUrl", VnpayConfig.RETURN_URL);
        vnpParams.put("vnp_IpAddr", "127.0.0.1");
        vnpParams.put("vnp_Locale", "vn");

        // 🔥 BẮT BUỘC – NẾU THIẾU => CODE 70
       // vnpParams.put("vnp_SecureHashType", "HmacSHA512");

        // ===== TIME =====
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

        Calendar cal = Calendar.getInstance();
        vnpParams.put("vnp_CreateDate", sdf.format(cal.getTime()));

        cal.add(Calendar.MINUTE, 15);
        vnpParams.put("vnp_ExpireDate", sdf.format(cal.getTime()));

        // ===== HASH =====
        String hashData = VnpayUtil.buildHashData(vnpParams);
        String secureHash = VnpayUtil.hmacSHA512(
                VnpayConfig.HASH_SECRET, hashData);

        String query = VnpayUtil.buildQuery(vnpParams);

        // ===== DEBUG (RẤT QUAN TRỌNG) =====
        System.out.println("===== VNPAY DEBUG =====");
        System.out.println("HashData:");
        System.out.println(hashData);
        System.out.println("SecureHash:");
        System.out.println(secureHash);
        System.out.println("======================");

        String payUrl = VnpayConfig.PAY_URL
                + "?" + query
                + "&vnp_SecureHashType=HmacSHA512"
                + "&vnp_SecureHash=" + secureHash;


        System.out.println("PayURL:");
        System.out.println(payUrl);

        resp.sendRedirect(payUrl);
    }
}
