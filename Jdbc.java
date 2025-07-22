package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class Jdbc {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
			System.out.println("connected");
			Statement stmt = con.createStatement();
			//stmt.execute("create table student_details(id int(3),firstName varchar(10),lastName varchar(10))");
			//stmt.execute("insert into student_details values(123,'vamsi','krishna')");
			
			while(true){
				System.out.println("1.insert the data\n2.update the data\n3.Delete the data\n4.Display the data\n5.Exit");
				Scanner sc=new Scanner(System.in);
				int input=sc.nextInt();
			
			switch(input){
			case 1:
				String str = "insert into student_details values(?,?,?)";
				PreparedStatement pre = con.prepareStatement(str);
				System.out.println("Enter the id of student");
				int id=sc.nextInt();
				pre.setInt(1, id);
				System.out.println("Enter the First Name");
				String firstName = sc.next();
				pre.setString(2, firstName);
				System.out.println("Enter the last Name");
				String lastName = sc.next();
				pre.setString(3, lastName);
				
				int rowsAffected = pre.executeUpdate();
				System.out.println("Rows inserted: " + rowsAffected);
				//System.out.println("Data is inserted");
				pre.close();
				break;
				
			case 2:
				System.out.println("Enter the column Name to modify the data");
				String str2=sc.next();
				String update = "update student_details set "+ str2+" = ? where id = ?";
				PreparedStatement pre1 =con.prepareStatement(update);
				System.out.println("Enter the "+str2);
				System.out.println("enter the data to modify");
				String firstName1 = sc.next();
				pre1.setString(1, firstName1);
				System.out.println("Enter the id of student");
				int id1=sc.nextInt();
				pre1.setInt(2, id1);
				int rowsAffected1 = pre1.executeUpdate();
				System.out.println("Rows updated: " + rowsAffected1);
				//System.out.println("data is updated");
				pre1.close();
				break;
			case 3:
				System.out.println("enter the id ");
				int id3= sc.nextInt();
				String str3="delete from student_details where id= ?";
				PreparedStatement pre3=con.prepareStatement(str3);
				pre3.setInt(1, id3);
				int rowsAffected3 = pre3.executeUpdate();
				System.out.println("Rows deleted: " + rowsAffected3);
				pre3.close();
				break;
			case 4:
				String str4="select * from student_details";
				PreparedStatement pre4 = con.prepareStatement(str4);
				ResultSet rs = pre4.executeQuery();
				while(rs.next()){
					System.out.println("id:"+rs.getInt("id")+" firstName: "+rs.getString("firstName")+" lastName: "+rs.getNString("lastName"));
				}
				break;
			case 5:
				System.exit(1);
			}
			}
			
			
			
			
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
