import java.sql.*;

public class Java_HW2 {
	public static void main(String args[])
	{
		Connection conn = null;
		int result;
		
		String url = "jdbc:oracle:thin:@localhost:1521:jeongdojeon";
		String user = "jeongdojeon";
		String pass = "jeongdojeon";
		
		String query = null;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Find!");
		}
		catch(ClassNotFoundException e)
		{
			System.err.println("error = " + e);
			System.exit(1);
		}
		
		try
		{
			conn = DriverManager.getConnection(url,user,pass);
		}
		catch(SQLException e)
		{
			System.err.println("sql error = " + e);
			System.exit(1);
		}
		
		try
		{
			conn.setAutoCommit(false);
			
			Statement stmt = conn.createStatement();
			
			
			//Table Create
			System.out.println();
			System.out.println("Create");
			stmt.executeUpdate("create table dept("+"id int,"+"name varchar2(10))");
			System.out.println("dept created!!!");
			conn.commit();
			
			//Insert
			System.out.println();
			System.out.println("Insert");
			result = stmt.executeUpdate("insert into dept values(10, 'Admin')");
			System.out.println(result + " row inserted");
			result = stmt.executeUpdate("insert into dept values(20, 'Plan')");
			System.out.println(result + " row inserted");
			conn.commit();
			
			//Select
			System.out.println();
			System.out.println("Select");
			ResultSet rs = stmt.executeQuery("select * from dept");
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString("name");
				System.out.println("ID = " + id + ", NAME = "+name);
			}
			rs.close();
			conn.commit();
			
			//drop
			
			 //stmt.executeUpdate("drop table dept");
			// conn.commit();
			 
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();
		}
		catch(Exception e)
		{
			System.err.println("sql error = " + e);
		}
	}
}
