package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/users/loginForm")
public class LoginUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LoginUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward("/user/login.jsp",request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("login userId Test : {}", request.getParameter("userId"));

        String userId = request.getParameter("userId");
        User user = DataBase.findUserById(userId);
        
        // 입력한 아이디가 존재할 경우
        if(user!=null){
            String password = request.getParameter("password");
            // 입력한 아이디와 비밀번호까지 일치할 경우 --> 로그인 성공
            if(user.getPassword().equals(password)){
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                response.sendRedirect("/");
                return;
           }
        }

        // 입력한 아이디가 존재하지 않을 경우 or password 틀렸을 경우
        request.setAttribute("loginFailed",true);
        forward("/user/login.jsp",request,response);
    }

    private void forward(String url , HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request,response);
    }
}
