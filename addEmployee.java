import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.resource.cci.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/addEmployee"})
public class addEmployee extends HttpServlet
{

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        PrintWriter out=response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
       
        String uname=request.getParameter("uname");
        String pas=request.getParameter("pas");
        String mail=request.getParameter("mail");
        
        String compare="";
        
        if((uname.equals(compare)||pas.equals(compare)||mail.equals(compare)))
        {
          out.println("Registration unsuccessful.Try again..");
          RequestDispatcher rs = request.getRequestDispatcher("RegisterPage.html");
          rs.include(request, response);  
        }
        else
        {
           try
           {
             String query="insert into Login values(?,?,?)";
             Connection con=DbConnection.getConnection();
             PreparedStatement ps=con.prepareStatement(query);
             ps.setString(1,uname);
             ps.setString(2,pas);
             ps.setString(3,mail);
             
             //creating session
             Cookie ck=new Cookie("Username", uname);
             Cookie ck1=new Cookie("Password",pas);
             response.addCookie(ck);
             response.addCookie(ck1);
             //response.addCookie(new Cookie("Email",mail));
             Cookie ck2=new Cookie("Email",mail);
             response.addCookie(ck2);
        
             int count=ps.executeUpdate();
               if(count>0)
               {
                //out.println("Registered  "+uname+"  successfully");
                 
                     //printing cookie values
                      out.println("</br>Following values added (Printing cookie file) :-</br>");
                      Cookie[] cValues=request.getCookies();
                      for(int i=0;i<cValues.length;i++)
                      {
                       out.println("</br>"+cValues[i].getName()+"  "+cValues[i].getValue());
                      }
                 
                      //deleting cookie
                      /*
                      ck=new Cookie("Username","");
                      ck.setMaxAge(0);
                      response.addCookie(ck);
                      ck1=new Cookie("Password","");
                      ck1.setMaxAge(0);
                      response.addCookie(ck1);
                      ck2=new Cookie("Email","");
                      ck2.setMaxAge(0);
                      response.addCookie(ck2);
                      out.println("</br> Cookie deleted");
                      
                      Cookie[] cValues1=request.getCookies();
                      for(int i=0;i<cValues1.length;i++)
                      {
                       out.println("</br>"+cValues1[i].getName()+"  "+cValues1[i].getValue());
                      }
                      */
                
                RequestDispatcher rs = request.getRequestDispatcher("AdminWelcome.html");
                rs.include(request, response);
               }
         
            }
            catch(Exception e)
            {
              out.println("Exception "+e);
              out.println("Couldn't register!!<br/><br/>");
              RequestDispatcher rd=request.getRequestDispatcher("RegisterPage.html");
              rd.include(request, response);
            }
        }   
    }

    
}
