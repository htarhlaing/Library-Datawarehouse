package com.example.datawarehouse;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will illustrate how to handle the first three queries related to information about the number
 * of borrowed books
 *
 * @author Gusto Htar Htar Hlaing
 */
@WebServlet(name = "ServletBook", value = "/ServletBook")
public class ServletBook extends HttpServlet {

    @EJB
    BookQueryBean bookQueryBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if(action.equals("query1"))
        {   yearBookList(request, response);
        }

        if(action.equals("query2"))
        {
            String getYear = request.getParameter("getYear");
            try {
                List<BookModel> quarter_list = bookQueryBean.query2(getYear);
                request.setAttribute("quarter_list", quarter_list);
                request.getRequestDispatcher("query2.jsp").forward(request, response);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(action.equals("query3"))
        {
            String getYear = request.getParameter("getYear");
            try {
                List<BookModel> month_list = bookQueryBean.query3(getYear);
                request.setAttribute("month_list", month_list);
                request.getRequestDispatcher("query3.jsp").forward(request, response);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    /**
     * This method will request the total borrowed books for each year(2022,2021,2020) from the Oracle database and
     * then forward the  data to query1.jsp to display the results in bar chart
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void yearBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        try {
            List<BookModel> year_list = bookQueryBean.query1();
            request.setAttribute("years",year_list);
            request.getRequestDispatcher("query1.jsp").forward(request, response);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        }

}
