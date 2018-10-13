import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/DropdownUpdate"})
public class DropdownUpdate extends HttpServlet
{

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            
        String b=request.getParameter("whichUpdate");
        String newValue=request.getParameter("newValue");
        String uname=request.getParameter("uname");
        String query="update Login set "+b+"=? where Username=?";
        
        try
        {
            Connection con=DbConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, newValue);
            ps.setString(2, uname);
            ps.executeUpdate();
            
             out.println(b+"  updated successfully.");
             RequestDispatcher rs = request.getRequestDispatcher("AdminWelcome.html");
             rs.include(request, response);
        }
        catch(Exception e)
        {
          //   out.println(e);
             out.println("Incorrect details!!<br/>");
             RequestDispatcher rd=request.getRequestDispatcher("newPassword.html");
             rd.include(request, response);
        }
    }

    

}
