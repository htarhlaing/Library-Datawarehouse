package com.example.datawarehouse;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * This class will illustrate how to handle the last four queries related to information about the students
 *
 * @author Gusto Htar Htar Hlaing
 */
@WebServlet(name = "ServletStudent", value = "/ServletStudent")
public class ServletStudent extends HttpServlet {
    @EJB
    StudentQueryBean studentQueryBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("query7"))
        {
            String getYear = request.getParameter("getYear");
            String student_id=request.getParameter("student_id");
            try {
                List<BookModel> month_list = studentQueryBean.query7(getYear,student_id);
                request.setAttribute("month_list",month_list);
                request.getRequestDispatcher("query7.jsp").forward(request, response);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if(action.equals("query8"))
        {
            String getYear = request.getParameter("getYear");
            String student_id=request.getParameter("student_id");
            try {
                List<BookModel> month_list = studentQueryBean.query8(getYear,student_id);
                request.setAttribute("month_list",month_list);
                request.getRequestDispatcher("query8.jsp").forward(request, response);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if(action.equals("query9"))
        {
            yearBookList(request, response);
        }
        if(action.equals("query10"))
        {
            yearFineList(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    /**
     * This method will request the total number of books borrowed by males and females in(2022,2021,2020)
     * from the Oracle database and
     * then forward the  data to query9.jsp to display the results in bar chart
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void yearBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<BookModel> year_list = studentQueryBean.query9();
            request.setAttribute("years",year_list);
            request.getRequestDispatcher("query9.jsp").forward(request, response);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * This method will request the total fines for males and females in(2022,2021,2020)
     * from the Oracle database and
     * then forward the  data to query10.jsp to display the results in bar chart
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void yearFineList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        try {
            List<BookModel> year_list = studentQueryBean.query10();
            request.setAttribute("years",year_list);
            request.getRequestDispatcher("query10.jsp").forward(request, response);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
