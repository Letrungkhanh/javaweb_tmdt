package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    // Test nhanh servlet (có cũng được, không có cũng OK)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().print("Chat servlet hoạt động OK (GET)");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Hỗ trợ tiếng Việt
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        // Lấy tin nhắn từ client
        String message = request.getParameter("message");

        // Validate
        if (message == null || message.trim().isEmpty()) {
            response.getWriter().print("Bạn vui lòng nhập câu hỏi nhé 😊");
            return;
        }

        message = message.toLowerCase();
        String reply;

        // ===== LOGIC TƯ VẤN =====
        if (message.contains("it") || message.contains("lập trình")) {
            reply =
                "Nếu bạn học ngành IT, mình gợi ý:<br>" +
                "• MacBook Air M1/M2: web, Java, Python, nhẹ, pin lâu<br>" +
                "• MacBook Pro M2: backend, mobile, Docker<br>" +
                "• Nên chọn RAM tối thiểu 16GB, SSD 512GB";
        }
        else if (message.contains("macbook air")) {
            reply =
                "MacBook Air M1/M2 rất phù hợp cho sinh viên IT:<br>" +
                "• Code Java, Spring Boot<br>" +
                "• Web, Python<br>" +
                "• Nhẹ, pin tốt, giá hợp lý";
        }
        else if (message.contains("macbook pro")) {
            reply =
                "MacBook Pro phù hợp cho:<br>" +
                "• Backend nặng<br>" +
                "• Mobile (Android / iOS)<br>" +
                "• Docker, máy ảo, AI";
        }
        else if (message.contains("giá")) {
            reply =
                "Giá tham khảo hiện nay:<br>" +
                "• MacBook Air: từ khoảng 20 triệu<br>" +
                "• MacBook Pro: từ khoảng 30 triệu";
        }
        else if (message.contains("ram")) {
            reply =
                "Học IT bạn nên chọn MacBook RAM tối thiểu <b>16GB</b> để code mượt hơn.";
        }
        else {
            reply =
                "Mình có thể tư vấn cho bạn về:<br>" +
                "• MacBook cho sinh viên IT<br>" +
                "• MacBook Air / Pro<br>" +
                "• Cấu hình RAM, SSD<br>" +
                "Bạn cứ hỏi nhé 🙂";
        }

        // Trả kết quả về client
        response.getWriter().print(reply);
    }
}
