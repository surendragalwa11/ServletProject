
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/fireValidation"})
public class fireValidation extends HttpServlet
{

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
         String uname=request.getParameter("uname");
       
        response.setContentType("text/html");
        
        if(fireValidation2.checkUser(uname))
        {
            out.println("Fired "+uname+" successfully.");
            RequestDispatcher rs = request.getRequestDispatcher("AdminWelcome.html");
            rs.include(request, response);
        }
        else
        {
            out.println("Incorrect username!!<br/>");
             RequestDispatcher rd=request.getRequestDispatcher("fire.html");
             rd.include(request, response);
            // out.println("\nIncorrect credentials.\n");
        }
    }

    
}
