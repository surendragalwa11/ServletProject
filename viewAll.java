

import com.mysql.cj.protocol.Resultset;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/viewAll"})
public class viewAll extends HttpServlet
{
   
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
       response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
        
        try
        {
          String query="select Username,Email from Login";
          Connection con=DbConnection.getConnection();
          PreparedStatement ps=con.prepareStatement(query);
          ResultSet rs=ps.executeQuery();
          out.println("<html><table>");
          while(rs.next())
          {
              out.println("<tr><td>"+rs.getString(1)+"</td><td>&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp "+rs.getString(2)+"</td></tr>");
          }
        }
        catch(Exception e)
        {
          out.println(e);
        }
        out.println("</table></html>");    
    }

    

}
