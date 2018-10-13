import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/Validation"})
public class Validation extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
     
        String uname=request.getParameter("uname");
        String pass=request.getParameter("pas");
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        
        String compare ="";
          if(Loginvalidation.checkUser(uname,pass) && !(uname.equals(compare) || pass.equals(compare)))
          {
            if(uname.equals("Sandeep.p")||uname.equals("Pranav.p"))
            {
              RequestDispatcher rs = request.getRequestDispatcher("AdminWelcome.html");
              rs.forward(request, response);
            }
            else
            {
               RequestDispatcher rs = request.getRequestDispatcher("view.html");
               rs.forward(request, response);
            }
          }
          else
          {
             out.println("Incorrect credentials");
             RequestDispatcher rd=request.getRequestDispatcher("LoginPage.html");
             rd.include(request, response);
            // out.println("\nIncorrect credentials.\n");
          }
         
    }


}
