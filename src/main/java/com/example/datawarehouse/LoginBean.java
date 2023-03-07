package com.example.datawarehouse;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This class will responsible for checking that the username and password entered by the user  are consistent
 * with the database
 *
 * @author Gusto Htar Htar Hlaing
 */
@Stateless(name = "LoginEJB")
public class LoginBean {
  @EJB
  OracleClientProviderBean oracleClientProviderBean;

  public LoginBean() {

  }

  public int checkLogin(String username, String password) throws SQLException {
    Connection connection = oracleClientProviderBean.getOracleClient();

    PreparedStatement pstmtCheck = connection.prepareStatement("select * from libraryDW_dba.library_admin where username =? and password = ? ");
    pstmtCheck.setString(1, username);
    pstmtCheck.setString(2, password);
    ResultSet rsCheck = pstmtCheck.executeQuery();
    int found=0;
    while (rsCheck.next()) {
      found=1;
      break;
    }
      return found;


  }

}
