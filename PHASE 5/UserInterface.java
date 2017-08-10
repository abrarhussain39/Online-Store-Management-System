
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class UserInterface {

	static int script_count=0;
	public static void main(String[] args) {
		
		boolean finished=false;
		Connection con=null;
		Statement stmt=null;
		Scanner s = new Scanner(System.in);
		while(!finished)
		{
		System.out.println("******Welcome******");
		System.out.println("Enter 2-3 If You are Customer | 4-12 If You are employee");
		System.out.println("1.  Connect To The Database");
		//System.out.println("2.  Load Scripts");
		System.out.println("2.  Order a Product");
		System.out.println("3.  Search Product's Using Parameters");
		System.out.println("4.  View a Table In The Database");
		System.out.println("5.  Add New Product");
		System.out.println("6.  Add New Supplier");
		System.out.println("7.  Update Employee Details");
		System.out.println("8.  Delete the details of returned product");
		System.out.println("9.  Search Customer's Using Parameters");
		System.out.println("10. Search Employee's Using Parameters");
		System.out.println("11. Find each Customer's total Orders");
		System.out.println("12. View Maximum Spending Customer");
		System.out.println("13. Exit");
		System.out.println("Enter your choice:");
		
		
		int choice = s.nextInt();
		switch(choice)
		{
		case 1:
			con=Database.connectDatabase();
			System.out.println("Connected to database successfully");
			break;
		/*case 2:
			if(con==null)
			{
				System.out.println("Uploading Scripts Require An Active Database Connection!!");
				break;
			}
			else
			{
			if(script_count==0)
			{
			ScriptRunner sr = new ScriptRunner(con, false, true);
			String path="/Abrar_Db/sql_scripts/F15_17_myDBcreate.sql";
			Reader reader=null;
			try {
				reader = new BufferedReader(
				        new FileReader(path));
			} catch (FileNotFoundException e1) {

				System.out.println(".SQL File Cannot Be Located! Please Correct The Path & Try Again!");
				break;
			}
			
			try {
				sr.runScript(reader);
			} catch (SQLException e) {

				System.out.println(e.getLocalizedMessage());
				break;
			}
			catch (IOException e) {

				System.out.println(e.getLocalizedMessage());
				break;
			}
			path="/Abrar_Db/sql_scripts/F15_17_myDBinsert.sql";
			try {
				reader = new BufferedReader(
				        new FileReader(path));
			} catch (FileNotFoundException e) {
				System.out.println(".SQL File Cannot Be Located! Please Correct The Path & Try Again!");
				break;
			}
			
			try {
				sr.runScript(reader);
			} catch (SQLException e) {

				System.out.println(e.getLocalizedMessage());
				break;
			}
			catch(IOException e){
				System.out.println(e.getLocalizedMessage());
				break;
			}
			System.out.println("Successfully uploaded the script in the database!!");
			
			script_count=1;
			}
			else
			{
				System.out.println("Scripts already uploaded, you can proceed with queries!!");
			}
			}
			break;*/
		case 2:
			if(con==null)
			{
				System.out.println("Ordering Product Require An Active Database Connection!!");
				break;
			}
			else
			{
				System.out.println("Enter Customer Id:");
				String c_id=s.next();
				System.out.println("Enter Product Id:");
				String p_id=s.next();
				String sql="";
				try {
					stmt=con.createStatement();
					sql="INSERT INTO F15_17_ORDERS VALUES('"+c_id+"','"+p_id+"')";
					int i=stmt.executeUpdate(sql);
					if(i>0){
					System.out.println("Order Placed Successfully!!!");
					}
					else{
						System.out.println("Unable to add Customer");
					}
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				}
			}
			break;
		case 3:
			if(con==null)
			{
				System.out.println("Searching Requires An Active Database Connection!!");
				break;
			}
			else
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter The Parameter With Which You Want to Search A Product:");
				String col_name=s.next();
				System.out.println("Enter The Value Of The Parameter:");
				
				try {
					String col_val=br.readLine();
					String sql="";
					stmt=con.createStatement();
					sql="Select * from F15_17_PRODUCT where "+col_name+"='"+col_val+"'";
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					String temp="";
					int i=0;
					if(rs.next()==true)
					{
					for(i=1;i<=rsmd.getColumnCount();i++)
					{
						temp=temp+rsmd.getColumnName(i)+" | ";
						System.out.print(rsmd.getColumnName(i)+" | ");
						
					}
					char[] count_char=temp.toCharArray();
					System.out.println("");
					for(i=0;i<count_char.length;i++)
					System.out.print("-");
					System.out.println("");
					while(rs.next())
					{
						for(i=1;i<=rsmd.getColumnCount();i++)
							System.out.print(rs.getString(i)+" | ");
						System.out.println("");
					}
					}
					else{
						System.out.println("No data found");
					}
					
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				}
				System.out.println("");
			}
			break;
		case 4:
			if(con==null)
			{
				System.out.println("Searching Table Require An Active Database Connection!!");
				break;
			}
			else
			{
			System.out.println("Enter the Table Name:");
			String table_name=s.next();
			int i=0;
			try {
				stmt=con.createStatement();
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage());
				break;
			}
			String sql_query="Select * from "+table_name;
			ResultSet rs;
			try {
				rs = stmt.executeQuery(sql_query);
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage());
				break;
			}
			ResultSetMetaData rsmd;
			try {
				rsmd = rs.getMetaData();
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage());
				break;
			}
			String temp="";
			try {
				for(i=1;i<=rsmd.getColumnCount();i++)
				{
					temp=temp+rsmd.getColumnName(i)+" | ";
					System.out.print(rsmd.getColumnName(i)+" | ");
					
				}
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage());
				break;
			}
			
			char[] count_char=temp.toCharArray();
			System.out.println("");
			for(i=0;i<count_char.length;i++)
			System.out.print("-");
			System.out.println("");
			try {
				while(rs.next())
				{
					for(i=1;i<=rsmd.getColumnCount();i++)
						System.out.print(rs.getString(i)+" | ");
					System.out.println("");
				}
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage());
				break;
			}
			System.out.println("");
			}
			break;
			
		case 5:
			if(con==null)
			{
				System.out.println("Adding Product Require An Active Database Connection!!");
				break;
			}
			else
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				
				try {
					System.out.println("Enter Product Id:");
					String p_id = br.readLine();
					System.out.println("Enter Brand Name:");
					String b_name = br.readLine();
					System.out.println("Enter Product Name:");
					String p_name = br.readLine();
					System.out.println("Enter Description");
					String des = br.readLine();
					System.out.println("Enter Category Id");
					String c_id = br.readLine();
					String sql = "INSERT INTO F15_17_PRODUCT " +
			                   "VALUES ('"+p_id+"','"+b_name+"','"+p_name+"','"+des+"','"+c_id+"')";
					stmt = con.createStatement();
					int i=stmt.executeUpdate(sql);
					if(i>0){
					System.out.println("1 Product Added Successfully!!");
					}
					else
					{
						System.out.println("Unable to add product");
					}
					
				} catch (IOException e1) {
					System.out.println(e1.getLocalizedMessage());
					break;
				}
				
				catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				}
				
				
				
			}
			break;
		case 6:
			if(con==null)
			{
				System.out.println("Adding Supplier Require An Active Database Connection!!");
				break;
			}
			else
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				try {
					System.out.println("Enter Supplier Id:");
					String s_id=br.readLine();
					System.out.println("Enter Supplier Name:");
					String s_name=br.readLine();
					System.out.println("Enter Supplier Contact Number:");
					String s_cnt=br.readLine();
					System.out.println("Enter Supplier Gender:");
					String s_gen=br.readLine();
					System.out.println("Enter Supplier Email:");
					String s_email=br.readLine();
					String sql = "INSERT INTO F15_17_SUPPLIER " +
			                   "VALUES ('"+s_id+"','"+s_name+"','"+s_cnt+"','"+s_gen+"','"+s_email+"')";
					stmt = con.createStatement();
					int i=stmt.executeUpdate(sql);
					if(i>0){
					System.out.println("1 Supplier Added Successfully!!");
					}
					else
					{
						System.out.println("Unable to add supplier");
					}
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				}
				
				
				
			}
			break;
		case 7:
			if(con==null)
			{
				System.out.println("Updating Employee Details Require An Active Database Connection!!");
				break;
			}
			else
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter SSN Of Employee");
				String ssn=s.next();
				String sql = "";
				PreparedStatement psmt=null;
				try {
					//stmt=con.createStatement();
					System.out.println("Enter Column Name:");
					String c_name=br.readLine();
					System.out.println("Enter Columne value:");
					String c_value=br.readLine();
					sql="update F15_17_EMPLOYEE set "+c_name+"='"+c_value+"' where ssn='"+ssn+"'";
					psmt=con.prepareStatement(sql);
					int i=psmt.executeUpdate();
					if(i>0){
						System.out.println("Updated Successfully!!");
					}
					else{
						System.out.println("Wrong SSN entered");
					}
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				}
				
			}
			break;
		case 8:
			if(con==null)
			{
				System.out.println("Deleting Return info Details Require An Active Database Connection!!");
				break;
			}
			else
			{
				//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter Customer Id:");
				
				String c_id=s.next();
				System.out.println("Enter Product Id:");
				String p_id=s.next();
				
				
				
				String sql="";
				try {
					stmt=con.createStatement();
					sql="delete from F15_17_RETURNS_INFO where c_id='"+c_id+"' and p_id='"+p_id+"'";
					int i = stmt.executeUpdate(sql);
					if(i>0)
					{
						System.out.println("Return Details Deleted Successfully!!!");
					}
					else{
						System.out.println("Wrong values entered");
					}
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
					break;
			
				}
			
				
			}
			break;
		
		case 9:
			if(con==null)
			{
				System.out.println("Searching Requires An Active Database Connection!!");
				break;
			}
			else
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter The Parameter With Which You Want to Search A Customer:");
				String col_name=s.next();
				System.out.println("Enter The Value Of The Parameter:");
				
				try {
					String col_val=br.readLine();
					String sql="";
					stmt=con.createStatement();
					sql="Select * from F15_17_CUSTOMER where "+col_name+"='"+col_val+"'";
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					String temp="";
					int i=0;
					if(rs.next()==true)
					{
					for(i=1;i<=rsmd.getColumnCount();i++)
					{
						temp=temp+rsmd.getColumnName(i)+" | ";
						System.out.print(rsmd.getColumnName(i)+" | ");
						
					}
					char[] count_char=temp.toCharArray();
					System.out.println("");
					for(i=0;i<count_char.length;i++)
					System.out.print("-");
					System.out.println("");
					while(rs.next())
					{
						for(i=1;i<=rsmd.getColumnCount();i++)
							System.out.print(rs.getString(i)+" | ");
						System.out.println("");
					}
					}
					else{
						System.out.println("No data found");
					}
					
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				}
				System.out.println("");
			}
			break;
		case 10:
			if(con==null)
			{
				System.out.println("Searching Requires An Active Database Connection!!");
				break;
			}
			else
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter The Parameter With Which You Want to Search An Employee:");
				String col_name=s.next();
				System.out.println("Enter The Value Of The Parameter:");
				
				try {
					String col_val=br.readLine();
					String sql="";
					stmt=con.createStatement();
					sql="Select * from F15_17_EMPLOYEE where "+col_name+"='"+col_val+"'";
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					String temp="";
					int i=0;
					if(rs.next()==true)
					{
					for(i=1;i<=rsmd.getColumnCount();i++)
					{
						temp=temp+rsmd.getColumnName(i)+" | ";
						System.out.print(rsmd.getColumnName(i)+" | ");
						
					}
					char[] count_char=temp.toCharArray();
					System.out.println("");
					for(i=0;i<count_char.length;i++)
					System.out.print("-");
					System.out.println("");
					while(rs.next())
					{
						for(i=1;i<=rsmd.getColumnCount();i++)
							System.out.print(rs.getString(i)+" | ");
						System.out.println("");
					}
					}
					else{
						System.out.println("No data found");
					}
					
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				}
				System.out.println("");
			}
			break;
		
			case 11:
			if(con==null)
			{
				System.out.println("Viewing the Table Require An Active Database Connection!!");
				break;
			}
				else
			{
				try
				{
				stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("SELECT CUST_NAME,C.C_ID,COUNT(*) as ttl_orders FROM F15_17_CUSTOMER C, F15_17_ORDERS O WHERE C.C_ID=O.C_ID GROUP BY CUST_NAME,C.C_ID ORDER BY COUNT(*) DESC");
				ResultSetMetaData rsmd = rs.getMetaData();
				String temp="";
				int i=0;
				if(rs.next()==true)
				{
				for(i=1;i<=rsmd.getColumnCount();i++)
				{
					temp=temp+rsmd.getColumnName(i)+" | ";
					System.out.print(rsmd.getColumnName(i)+" | ");
					
				}
				while (rs.next())
				{
					String C_NAME=rs.getString("CUST_NAME");
					String C_id= rs.getString("C_ID");
					String cnt= rs.getString("ttl_orders");
					System.out.println("\n"+C_NAME+"\t"+C_id+"\t"+cnt);
				}
				}
				else{
					System.out.println("No Data Found!!");
				}
				stmt.close();
				}
				catch (SQLException e){
					
				
				System.out.println(e.getLocalizedMessage());
				break;
			}
			}
			break;
			case 12:
				if(con==null)
				{
					System.out.println("Viewing the Table Require An Active Database Connection!!");
					break;
				}
					else
				{
					try
					{
					stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT DISTINCT F15_17_ORDERS_INFO.C_ID,F15_17_CUSTOMER.CUST_NAME,MAXAMT FROM(SELECT C_ID,SUM(UNIT_SP*QUANTITY) AS MAXAMT FROM  F15_17_ORDERS_INFO GROUP BY C_ID HAVING SUM(UNIT_SP*QUANTITY) IN (SELECT MAX(SUM(UNIT_SP*QUANTITY)) FROM F15_17_ORDERS_INFO GROUP BY C_ID))R JOIN F15_17_ORDERS_INFO ON R.C_ID=F15_17_ORDERS_INFO.C_ID JOIN F15_17_CUSTOMER ON R.C_ID=F15_17_CUSTOMER.C_ID");
					ResultSetMetaData rsmd = rs.getMetaData();
					String temp="";
					int i=0;
					if(rs.next()==true)
					{
					for(i=1;i<=rsmd.getColumnCount();i++)
					{
						temp=temp+rsmd.getColumnName(i)+" | ";
						System.out.print(rsmd.getColumnName(i)+" | ");
						
					}
					while (rs.next())
					{
						String C_id= rs.getString("C_ID");
						String C_NAME=rs.getString("CUST_NAME");
					
						String cnt= rs.getString("MAXAMT");
						System.out.println("\n"+C_NAME+"  \t"+C_id+"  \t"+cnt);
					}
					}
					else{
						System.out.println("No Data Found");
					}
					stmt.close();
					}
					catch (SQLException e){
						
					
					System.out.println(e.getLocalizedMessage());
					break;
				}
				}
				break;
			
		case 13:
			finished=true;
			break;
		default:
			break;
		}
		}
		System.out.println("Created by Team-17, Thanks for using !");
		s.close();
		try {
			if(con!=null)
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			
		}
	}
}
