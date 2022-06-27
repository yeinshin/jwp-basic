package next.web;

import core.db.DataBase;
import next.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

// 개인정보 수정은 로그인 사용자만 가능하며 자신의 정보만 수정 가능해야 한다.
@WebServlet("/users/updateForm")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // a 태그 사용 --> GET 방식
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("userId Test : "+request.getParameter("userId"));
        String userId = request.getParameter("userId");

        HttpSession session = request.getSession();
        Object object = session.getAttribute("user");

        if(object!=null){
            User user = (User)object;
            if(user.getUserId().equals(userId)){
                RequestDispatcher rd = request.getRequestDispatcher("/user/updateform.jsp");
                rd.forward(request,response);
                return;
            }
        }

        throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email")
        );

        DataBase.updateUser(user);
        response.sendRedirect("/user/list");
    }

}
