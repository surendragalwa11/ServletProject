import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/fireValidation2"})

public class fireValidation2
{
        
     public static boolean checkUser(String uname)
     {
        boolean b=false;
        
        try
        {
       
        String query="delete from Login where Username=?";
        //System.out.print("outside");
        Connection con=DbConnection.getConnection();
       
        PreparedStatement st=con.prepareStatement(query);
        st.setString(1,uname);
        
        int count= st.executeUpdate();
        if(count>0)
         b=true;
        }
        catch(Exception e)
        {
            
           
            System.out.println("\n*****See here!!Excepton occured in login validate page");
        }
      return b; 
     }
}

