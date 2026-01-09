package utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class VnpayUtil {

	public static String buildHashData(Map<String, String> params) {
	    List<String> fieldNames = new ArrayList<>(params.keySet());
	    Collections.sort(fieldNames);

	    StringBuilder hashData = new StringBuilder();

	    for (String fieldName : fieldNames) {
	        String fieldValue = params.get(fieldName);
	        if (fieldValue != null && fieldValue.length() > 0) {
	            hashData.append(fieldName);
	            hashData.append('=');
	            hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8));
	            hashData.append('&');
	        }
	    }

	    // Xoá dấu &
	    if (hashData.length() > 0) {
	        hashData.setLength(hashData.length() - 1);
	    }

	    return hashData.toString();
	}
	public static String buildQuery(Map<String, String> params) {
	    List<String> fieldNames = new ArrayList<>(params.keySet());
	    Collections.sort(fieldNames);

	    StringBuilder query = new StringBuilder();

	    for (String fieldName : fieldNames) {
	        String fieldValue = params.get(fieldName);
	        if (fieldValue != null && fieldValue.length() > 0) {
	            query.append(URLEncoder.encode(fieldName, StandardCharsets.UTF_8));
	            query.append('=');
	            query.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8));
	            query.append('&');
	        }
	    }

	    if (query.length() > 0) {
	        query.setLength(query.length() - 1);
	    }

	    return query.toString();
	}


    // 🔐 HMAC SHA512
    public static String hmacSHA512(String key, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey =
                    new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            mac.init(secretKey);
            byte[] bytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            String s = Integer.toHexString(0xff & b);
            if (s.length() == 1) hex.append('0');
            hex.append(s);
        }
        return hex.toString();
    }
}
