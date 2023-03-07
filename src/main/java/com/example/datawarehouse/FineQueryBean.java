package com.example.datawarehouse;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "FineQueryEJB")
public class FineQueryBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;
    public FineQueryBean() {
    }
    /**
     * This method will perform query about the total fines paid by students for each year (2022,2021,2020)
     *
     * @return return fines list  to ServletBook.java to show the result
     * @throws SQLException
     */
    public List<BookModel> query4() throws SQLException{
        List<BookModel> fine_list = new ArrayList<BookModel>();

        Integer fine=0;
        String years_name;
        BookModel bookModel=null;
        try {

            Connection connection = oracleClientProviderBean.getOracleClient();
            PreparedStatement pstmtCheck = connection.prepareStatement("select t.theYear as year,sum(fine) as fine from libraryDW_dba.borrowreturn_fact b inner join libraryDW_dba.time_dim t on b.timeid=t.timeid group by t.theYear");
            ResultSet bookFine = pstmtCheck.executeQuery();
            while(bookFine.next())
            {
                fine=bookFine.getInt("fine");
                years_name=bookFine.getString("year");
                fine_list.add(new BookModel(fine,years_name));
            }
        }
        catch (Exception ee) {

            ee.printStackTrace();
        }
        return fine_list;

    }
    /**
     * This method will perform query about the total amount of fines per quarter based on the year chosen
     * by the user
     *
     * @param year  the year chosen by the user
     * @return return quarter list to ServletBook.jsp to show the result
     * @throws SQLException
     */
    public List<BookModel> query5(String year) throws SQLException{
        List<BookModel> quarter_list = new ArrayList<BookModel>();

        Integer quarter=0;
        String quarter_name;
        BookModel bookModel=null;
        try {

            Connection connection = oracleClientProviderBean.getOracleClient();
            PreparedStatement pstmtCheck = connection.prepareStatement("select t.theQuarter as quarter,sum(fine) as fine from libraryDW_DBA.borrowreturn_fact b inner join libraryDW_DBA.time_dim t on b.timeid=t.timeid where t.theYear=? group by t.theQuarter");
            pstmtCheck.setString(1, year);
            ResultSet bookQuantity = pstmtCheck.executeQuery();
            while(bookQuantity.next())
            {

                quarter=bookQuantity.getInt("fine");
                quarter_name=bookQuantity.getString("quarter");
                quarter_list.add(new BookModel(quarter,quarter_name));
            }
        }
        catch (Exception ee) {

            ee.printStackTrace();
        }
        return quarter_list;

    }
    /**
     * This method will perform query about the total amount of fines for each month based on the year chosen
     * by the user
     *
     * @param year  the year chosen by the user
     * @return return month list to ServletBook.jsp to show the result
     * @throws SQLException
     */
    public List<BookModel> query6(String year) throws SQLException{
        List<BookModel> month_list = new ArrayList<BookModel>();

        Integer month=0;
        String month_name;
        BookModel bookModel=null;
        try {

            Connection connection = oracleClientProviderBean.getOracleClient();
            PreparedStatement pstmtCheck = connection.prepareStatement("select t.theMonth as month,sum(fine) as fine from libraryDW_DBA.borrowreturn_fact b inner join libraryDW_DBA.time_dim t on b.timeid=t.timeid where t.theYear=? group by t.theMonth");
            pstmtCheck.setString(1, year);
            ResultSet bookQuantity = pstmtCheck.executeQuery();
            while(bookQuantity.next())
            {

                month=bookQuantity.getInt("fine");
                month_name=bookQuantity.getString("month");
                month_list.add(new BookModel(month,month_name));

            }
        }
        catch (Exception ee) {

            ee.printStackTrace();
        }
        return month_list;

    }
}
