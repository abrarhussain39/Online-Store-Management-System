
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	static Connection con=null;
	public static Connection connectDatabase()
	{
		String url = "jdbc:oracle:thin:@omega.uta.edu:1521:CSE1";
        String db_id="axs6808";
        String db_pwd="Arlington123";
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection(url, db_id, db_pwd);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("LOADING ERROR:"+e.toString());
        }
        catch(SQLException se)
        {
            System.out.println("FAIL OF DB CONNECTION  : "+se.toString());               
        }
        catch(Exception ee)
        {
            System.out.println("Exception in connection : "+ee.toString());
            
        }
		return con;
	}
}
