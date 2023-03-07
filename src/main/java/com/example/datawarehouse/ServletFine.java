package com.example.datawarehouse;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * This class will illustrate how to handle the middle three queries related to information about the fines
 *
 * @author Gusto Htar Htar Hlaing
 */
@WebServlet(name = "ServletFine", value = "/ServletFine")
public class ServletFine extends HttpServlet {
    @EJB
    FineQueryBean fineQueryBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("query4"))
        {   yearFineList(request, response);}
        if(action.equals("query5"))
        {
            String getYear = request.getParameter("getYear");
            try {
                List<BookModel> quarter_list = fineQueryBean.query5(getYear);
                request.setAttribute("quarter_list", quarter_list);
                request.getRequestDispatcher("query5.jsp").forward(request, response);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if(action.equals("query6"))
        {
            String getYear = request.getParameter("getYear");

            try {
                List<BookModel> month_list = fineQueryBean.query6(getYear);
                request.setAttribute("month_list", month_list);
                request.getRequestDispatcher("query6.jsp").forward(request, response);
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
     * This method will request the total fines for each year(2022,2021,2020) from the Oracle database and
     * then forward the  data to query4.jsp to display the results in bar chart
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void yearFineList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<BookModel> fine_list = fineQueryBean.query4();
            request.setAttribute("fines",fine_list);
            request.getRequestDispatcher("query4.jsp").forward(request, response);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
