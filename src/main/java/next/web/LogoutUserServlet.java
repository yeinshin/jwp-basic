package next.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/users/logout")
public class LogoutUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 로그아웃 버튼 클릭 -> 세션 제거
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/");
    }
}
