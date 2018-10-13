
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/searchResult"})
public class searchResult extends HttpServlet
{

    
    protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
       
        String Uname=request.getParameter("uname");
        try
        {
         Connection con=DbConnection.getConnection();  
         String query="select * from Login where username=?";
         
         
//         Statement st=con.createStatement();
//         ResultSet rs=st.executeQuery(query);
            
         PreparedStatement ps=con.prepareStatement(query);
         ps.setString(1, Uname);
         ResultSet rs=ps.executeQuery();
         
         while(rs.next())
         {
             
             response.getWriter().println("<br/>&nbspUsername:&nbsp&nbsp"+rs.getString(1)+"<br/><br/>&nbspPassword:&nbsp&nbsp"+rs.getString(2)+"<br/><br/>&nbspEmail:&nbsp&nbsp&nbsp&nbsp&nbsp"+rs.getString(3));
         }
       
       }
       catch(Exception e)
       {
          response.getWriter().println(e);
       }
    }
    
     

}
