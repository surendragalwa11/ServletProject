
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/passConfirm"})
public class passConfirm extends HttpServlet
{

  protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
  {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String uname=request.getParameter("uname");
        String pass1=request.getParameter("pass1");
        String pass2=request.getParameter("pass2");
        String compare="";
           
        if(pass1.equals(pass2) && !(pass1.equals(compare)))
        {
            try
            {
                Connection con=DbConnection.getConnection();
                String query="update login set Password=? where Username=?"; 
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, pass1);
                ps.setString(2, uname);
                int count=ps.executeUpdate();
                    
                
                if(count>0)
                {
                    out.println("Password updated successfully.");
                    RequestDispatcher rs = request.getRequestDispatcher("AdminWelcome.html");
                    rs.include(request, response);
                }
                else
                {
                    out.println("Incorrect details!!<br/>");
                    RequestDispatcher rd=request.getRequestDispatcher("newPassword.html");
                    rd.include(request, response);
                }    
                
            }
            catch(Exception e)
            {
                out.println(e);
             }
        }
        else
        {
            out.println("Password doesn't match!!<br/>");
            RequestDispatcher rd=request.getRequestDispatcher("newPassword.html");
            rd.include(request, response);
        }        
    }
}
