package com.example.datawarehouse;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "StudentQueryEJB")
public class StudentQueryBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;
    public StudentQueryBean() {
    }
    /**
     * This method will perform query about the total monthly number of books borrowed by student id and the year input by the
     * user
     * @param student_id student id input by the user
     * @param year the year chosen by the user
     *
     * @return return month list to ServletBook.jsp to show the result
     * @throws SQLException
     */
    public List<BookModel> query7(String year,String student_id) throws SQLException{
        List<BookModel> month_list = new ArrayList<BookModel>();

        Integer month=0;
        String month_name;
        BookModel bookModel=null;
        try {

            Connection connection = oracleClientProviderBean.getOracleClient();
            PreparedStatement pstmtCheck = connection.prepareStatement("select t.theMonth as month,sum(quantity) as quantity from libraryDW_DBA.borrowreturn_fact b inner join libraryDW_DBA.time_dim t on b.timeid=t.timeid where b.student_id=? and t.theYear=? group by t.theMonth");
            pstmtCheck.setString(1, student_id);
            pstmtCheck.setString(2,year);
            ResultSet bookQuantity = pstmtCheck.executeQuery();
            while(bookQuantity.next())
            {
                month=bookQuantity.getInt("quantity");
                month_name=bookQuantity.getString("month");
                month_list.add(new BookModel(month,month_name));
            }
        }
        catch (Exception ee) {

            ee.printStackTrace();
        }
        return month_list;

    }
    /**
     * This method will perform query about the total fines per month for student id and the year input by the
     * user
     * @param student_id student id input by the user
     * @param year the year chosen by the user
     *
     * @return return month list to ServletBook.jsp to show the result
     * @throws SQLException
     */
    public List<BookModel> query8(String year,String student_id) throws SQLException{
        List<BookModel> month_list = new ArrayList<BookModel>();

        Integer month=0;
        String month_name;
        BookModel bookModel=null;
        try {

            Connection connection = oracleClientProviderBean.getOracleClient();
            PreparedStatement pstmtCheck = connection.prepareStatement("select t.theMonth as month,sum(fine) as fine from libraryDW_DBA.borrowreturn_fact b inner join libraryDW_DBA.time_dim t on b.timeid=t.timeid where b.student_id=? and t.theYear=? group by t.theMonth");
            pstmtCheck.setString(1, student_id);
            pstmtCheck.setString(2,year);
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
    /**
     * This method will perform query about the total number of books borrowed by males and females
     * in (2022,2021,2020)
     *
     * @return return year list to ServletBook.jsp to show the result
     * @throws SQLException
     */
    public List<BookModel> query9() throws SQLException{
        List<BookModel> year_list = new ArrayList<BookModel>();

        Integer years=0;
        String years_name;
        String gender;
        BookModel bookModel=null;
        try {

            Connection connection = oracleClientProviderBean.getOracleClient();
            PreparedStatement pstmtCheck = connection.prepareStatement("select t.theYear as year,s.student_gender as gender,sum(quantity) as quantity from libraryDW_DBA.borrowreturn_fact b inner join libraryDW_DBA.student_dim s on b.student_id=s.student_id inner join libraryDW_DBA.time_dim t on b.timeid=t.timeid\n" +
                    "group by s.student_gender,t.theYear order by s.student_gender,t.theYear");
            ResultSet bookQuantity = pstmtCheck.executeQuery();
            while(bookQuantity.next())
            {
                years=bookQuantity.getInt("quantity");
                years_name=bookQuantity.getString("year");
                gender=bookQuantity.getString("gender");
                year_list.add(new BookModel(years,years_name,gender));

            }
        }
        catch (Exception ee) {

            ee.printStackTrace();
        }
        return year_list;

    }
    /**
     * This method will perform query about the total fines by males and females
     * in (2022,2021,2020)
     *
     * @return return year list to ServletBook.jsp to show the result
     * @throws SQLException
     */
    public List<BookModel> query10() throws SQLException{
        List<BookModel> year_list = new ArrayList<BookModel>();

        Integer years=0;
        String years_name;
        String gender;
        BookModel bookModel=null;
        try {

            Connection connection = oracleClientProviderBean.getOracleClient();
            PreparedStatement pstmtCheck = connection.prepareStatement("select t.theYear as year,s.student_gender as gender,sum(fine) as fine_amount from libraryDW_DBA.borrowreturn_fact b inner join libraryDW_DBA.student_dim s on b.student_id=s.student_id inner join libraryDW_DBA.time_dim t on b.timeid=t.timeid\n" +
                    "group by s.student_gender,t.theYear order by s.student_gender,t.theYear");
            ResultSet bookQuantity = pstmtCheck.executeQuery();
            while(bookQuantity.next())
            {
                years=bookQuantity.getInt("fine_amount");
                years_name=bookQuantity.getString("year");
                gender=bookQuantity.getString("gender");
                year_list.add(new BookModel(years,years_name,gender));
            }
        }
        catch (Exception ee) {

            ee.printStackTrace();
        }
        return year_list;

    }
}
