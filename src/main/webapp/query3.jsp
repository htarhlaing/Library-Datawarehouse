<%@ page import="com.example.datawarehouse.BookModel" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 12/20/2022
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page  import="java.awt.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="org.jfree.chart.*" %>
<%@ page  import="org.jfree.chart.entity.*" %>
<%@ page  import ="org.jfree.data.general.DatasetUtils"%>
<%@ page import="org.jfree.chart.plot.PlotOrientation" %>
<%@ page import="org.jfree.chart.plot.CategoryPlot" %>
<%@ page import="org.jfree.chart.renderer.category.BarRenderer" %>
<%@ page import="org.jfree.chart.axis.NumberAxis" %>
<%@ page import="org.jfree.chart.axis.ValueAxis" %>
<%@ page import="org.jfree.chart.axis.CategoryAxis" %>
<%@ page import="org.jfree.data.category.CategoryDataset" %>
<%@ page import="org.jfree.data.category.DefaultCategoryDataset" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/home.css">
    <title>Query 3</title>
</head>
<body>
<%
    if(session.getAttribute("username")==null)
    {
        response.sendRedirect("login.jsp");

    }
%>

<div class="wrapper" id="wrapper">
    <div class="sidebar">
        <h2>About Books</h2>
        <ul>
            <li ><a href="ServletBook?action=query1"><i class="fas"></i>what is the total number of books borrowed for each year?</a></li>
            <li ><a href="query2.jsp"><i class="fas"></i>what is the total number of books borrowed per quarter in a specific year?</a></li>
            <li class="active"><a href="query3.jsp"><i class="fas"></i>what is the total number of books borrowed for each month of specific year?</a></li>
        </ul>
        <h2>About Fines</h2>
        <ul>
            <li ><a href="ServletFine?action=query4"><i class="fas"></i>what is the total amount of fine for each year?</a></li>
            <li><a href="query5.jsp"><i class="fas"></i>what is the total amount of fines per quarter in a specific year?</a></li>
            <li><a href="query6.jsp"><i class="fas"></i>what is the total amount of fine for each month of specific year?</a></li>
        </ul>
        <h2>About Students</h2>
        <ul>
            <li><a href="query7.jsp"><i class="fas"></i>what is the total number of books borrowed by a specific student per month in a specific year? </a></li>
            <li><a href="query8.jsp"><i class="fas"></i>what is the total amount of fines per month for a specific student in a specific year? </a></li>
            <li><a href="ServletStudent?action=query9"><i class="fas"></i>what are the total number of books borrowed by males and females each year? </a></li>
            <li><a href="ServletStudent?action=query10"><i class="fas"></i>what are the total fines for males and females each year? </a></li>

        </ul>
        <div class="logOut">
            <form action="ServletLogout" method="post">
                <input type="submit" value="Logout" class="loginOut_btn">
            </form>
        </div>
    </div>
    <div class="main_content">
        <div class="header">Query 3 : what is the total number of books borrowed for each month of specific year?</div>
        <div class="sub_section">
            <form action="ServletBook" method="get">
                <ul>
                    <li><label >
                        Choose Year: </label>
                        <select id='getYear' name='getYear'>
                            <option selected value='2022'>2022</option>
                            <option value='2021'>2021</option>
                            <option value='2020'>2020</option>
                        </select>
                    </li>
                    <br>
                    <li>
                        <input type="submit" value="query3" name="action" class="find_btn"  required>
                    </li>
                    <br>
                </ul>
            </form>
        </div>
        <br>
        <%

            ArrayList<BookModel> month_list = new ArrayList<BookModel>();
            month_list = (ArrayList<BookModel>) request.getAttribute("month_list");
            int row;
            if(month_list==null)
            {row=0;

            }
            else {
                row=month_list.size();
            }
            final double[][] data=new double[1][row];
            String[] month_name=new String[row];
            if(month_list != null)
            {
                for(int i = 0; i < month_list.size(); i++)
                {
                    BookModel bookModel = month_list.get(i);

                    data[0][i]=bookModel.getQuantity();
                    month_name[i]=bookModel.getData();


                }

                String[] titles = new String[] {"Quantity"};
                final CategoryDataset dataset = DatasetUtils.createCategoryDataset(titles,month_name, data);

                JFreeChart chart = null;
                BarRenderer renderer = null;
                CategoryPlot plot = null;


                final CategoryAxis categoryAxis = new CategoryAxis("Month");
                final ValueAxis valueAxis = new NumberAxis("Quantity");
                renderer = new BarRenderer();

                Paint p1 = new GradientPaint(
                        0.0f, 0.0f, new Color(118, 138, 169),
                        0.0f, 0.0f, new Color(132, 99, 183));
                renderer.setSeriesPaint(0, p1);

                plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
                plot.setRenderer(renderer);
                plot.setOrientation(PlotOrientation.VERTICAL);
                chart = new JFreeChart("Total number of books borrowed", JFreeChart.DEFAULT_TITLE_FONT, plot, true);

                chart.setBackgroundPaint(new Color(249, 231, 236));



                try {

                    final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
                    final File file1 = new File(request.getServletContext().getRealPath(".") + "/barchart.png");
                    ChartUtils.saveChartAsPNG(file1, chart, 700, 400, info);
                } catch (Exception e) {
                    System.out.println(e);
                }
        %>

        <IMG  SRC="barchart.png" WIDTH="700" HEIGHT="400" BORDER="0" USEMAP="#chart">

        <%
            }
        %>
        <script>
            const getYear = document.querySelector('#getYear')
            getYear.value=<%= request.getParameter("getYear")%>;
        </script>


    </div>
</div>

<br>

</body>
</html>

