import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/searchValidation2"})

public class searchValidation2
{
        
     public static boolean checkUser(String uname)
     {
        boolean b=false;
        
        try
        {
       
        String query="select * from Login where Username=?";
        //System.out.print("outside");
        Connection con=DbConnection.getConnection();
       
        PreparedStatement st=con.prepareStatement(query);
        st.setString(1,uname);
        
        ResultSet rs= st.executeQuery();
      
        b=rs.next();
        }
        catch(Exception e)
        {
            
           
            System.out.println("\n*****See here!!Excepton occured in login validate page");
        }
      return b; 
     }
}

