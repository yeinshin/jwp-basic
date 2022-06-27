package next.web;

import core.db.DataBase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.crypto.Data;
import java.io.IOException;

@WebServlet("/users/updateForm")
public class UpdateUserFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // a 태그 사용 --> GET 방식
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("userId Test : "+request.getParameter("userId"));
        // userId : 수정하려는 회원의 ID
        request.setAttribute("user",DataBase.findUserById(request.getParameter("userId")));
        RequestDispatcher rd = request.getRequestDispatcher("/user/updateform.jsp");
        rd.forward(request,response);
    }

}
