package com.example.datawarehouse;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
/**
 * The class will illustrate how to handle library's admin login
 * @author Gusto Htar Htar Hlaing
 */
@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    @EJB
    LoginBean loginBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname=request.getParameter("uname");
        String password=request.getParameter("password");
        int found=0;
        try {
            found=loginBean.checkLogin(uname,password);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(found==1)
        {
            HttpSession session=request.getSession();
            session.setAttribute("username",uname);
            response.sendRedirect("welcome.jsp");
        }
        else
        {
            request.setAttribute("message", " Wrong username or password !");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
