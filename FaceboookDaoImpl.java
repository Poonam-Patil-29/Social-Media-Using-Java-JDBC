package com.ghri.FacebookFoundationalProjectServiceDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.ghri.FacebookFoundationalProject.App;
import com.ghri.FacebookFoundationalProjectModels.Userutility;

public class FaceboookDaoImpl implements FacebookDaoInterface{
	//static Connection con;
	static PreparedStatement ps;
	static Statement st;
	static Scanner sc = new Scanner(System.in);
	static PreparedStatement stm;
	public static void signUp(Userutility user) 
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
		    ps = con.prepareStatement("Insert into userinformation values(?,?,?,?,?,?,?)");
			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3,user.getAddress());
			ps.setInt(4, user.getAge());
			ps.setString(5,user.getEmail());
			ps.setString(6,user.getGender());
			ps.setString(7, user.getPassword());
			
			if(ps.executeUpdate()==1)
				System.out.println("\nAccount Created Successfully");
		}
		catch (Exception e) 
		{
		e.printStackTrace();	
		}	
	}
	
	
	public static boolean loginEmailCheckIsAvailable(String emaillogin)
	{
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");
			 st = con.createStatement();
			String querycheck = "select email from userinformation where email ='"+emaillogin+"'";
			ResultSet rs = st.executeQuery(querycheck);
			if(rs.next())
			{
				
				return false;
				
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Wrong email");
		return true;
	}
	public static boolean logiEmailPasswordCheckIsAvailable(String emaillogin, String passwordlogin) {
		try 
		{
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
				
			 st = con.createStatement();
			String querycheck = "select email,password from userinformation where email = '"+emaillogin+"' and password = '"+passwordlogin+"'";
			ResultSet rs = st.executeQuery(querycheck);
			if(rs.next())
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Wrong password");
		return true;
	}
	
	
	public ArrayList<Userutility> viewAllUser() {
		ArrayList<Userutility> sList = new ArrayList<Userutility>();
		try 
		{
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
				 st = con.createStatement();
			ResultSet rs = st.executeQuery("select userid,name,address,age,gender from userinformation");
			while(rs.next())
			{
				Userutility s = new Userutility();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				int age = rs.getInt(4);
				String gender = rs.getString(5);
				
			
				
				s.setId(id);
				s.setName(name);
				s.setAddress(address);
				s.setAge(age);
				s.setGender(gender);
				sList.add(s);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return sList;
	}

	
	public void update(String email) throws Exception
	{
		int choice;
		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
			
		//getting choise
		
		System.out.println("1.update name\n2.update password\n3.update age\n4. update address\n5.update gender");
		choice = sc.nextInt();
		
		switch(choice)
		{
		
		//update username
		
			case 1:	System.out.println("Enter the new username: ");
					String tempuser = sc.next();
					
					PreparedStatement stm = con.prepareStatement("update userinformation set name = ? where email = ?");
					stm.setString(1,tempuser);
					stm.setString(2, email);
					stm.executeUpdate();
					System.out.println("The name updated successfully");
					break;
					
					//update password
					
			
			case 2: System.out.println("Enter the new password: ");
					String temppass = sc.next();
			
					stm = con.prepareStatement("update userinformation set password = ? where email = ?");
					stm.setString(1,temppass);
					stm.setString(2, email);
					stm.executeUpdate();
					System.out.println("The password updated successfully");
					break;
				
					//update age
					
			case 3:System.out.println("Enter the age: ");
			       int tempage = sc.nextInt();
			       
			       stm = con.prepareStatement("update userinformation set age = ? where email = ?");
			       stm.setInt(1, tempage);
			       stm.setString(2, email);
			       stm.executeUpdate();
			       System.out.println("The age updated sucessfully");
			       break;
			       
			       //update address
			       
			case 4:System.out.println("Enter address: ");
			       String tempaddress = sc.next();
			       
				       stm = con.prepareStatement("update userinformation set address = ? where email = ?");
				       stm.setString(1,tempaddress);
					   stm.setString(2, email);
					   stm.executeUpdate();
					   System.out.println("The address updated successfully");
					   break;
					   
					   ///update gender
					   
			case 5:System.out.println("Enter gender: ");
			       String tempgender = sc.next();
			       
			       stm = con.prepareStatement("update userinformation set gender = ? where email = ?");
			       stm.setString(1, tempgender);
			       stm.setString(2, email);
			       stm.executeUpdate();
			       System.out.println("The gender updated successfully");
			       break;
			       

		}
	}
	
	public void delete(String email) throws SQLException
	{
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
		Scanner sc=new Scanner(System.in);
		 System.out.println("Enter email to delete your account");
		String emailToDelete=sc.next();
		PreparedStatement prepareStmt=con.prepareStatement("delete from userinformation where email=?");
		prepareStmt.setString(1,emailToDelete);
		
	if(prepareStmt.executeUpdate()==1)
	{
	System.out.println("Account deleted successfully");
	}
	else
	{
		System.out.println("You do not have account associated with this email ");
	}
	}
	
	//view user
	
	public ArrayList<Userutility> View(String emailogin) throws Exception
	
	{
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
		
		ArrayList<Userutility> sList = new ArrayList<Userutility>();
		
			 
			 ps = con.prepareStatement("select userid,name,address,age,gender from userinformation where email=?");
			 ps.setString(1,emailogin);
			ResultSet rs = ps.executeQuery();
			 
			 
			while(rs.next())
			{
			Userutility s = new Userutility();
				int id = rs.getInt(1);
				String name1 = rs.getString(2);
				String address = rs.getString(3);
				int age = rs.getInt(4);
				String gender = rs.getString(5);
				
			
				
				s.setId(id);
				s.setName(name1);
				s.setAddress(address);
				s.setAge(age);
				s.setGender(gender);
				sList.add(s);
			}
		
		return sList;

		
	}
	
	public ArrayList<Userutility> searchprofile(String name) throws Exception 
	{	
		ArrayList<Userutility> sList = new ArrayList<Userutility>();
		
		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
			
	    ps = con.prepareStatement("select userid,name, address,age,gender from userinformation where name like ?");
	    ps.setString(1, name+"%");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			Userutility s = new Userutility();
			int id = rs.getInt(1);
			String name1 = rs.getString(2);
			String address = rs.getString(3);
			int age = rs.getInt(4);
			String gender = rs.getString(5);
			
		
			
			s.setId(id);
			s.setName(name1);
			s.setAddress(address);
			s.setAge(age);
			s.setGender(gender);
			sList.add(s);
		}
		

		return sList;
		
	}
	
	public void createpost(String msg , String mail) throws Exception
	{
		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
			
		ps = con.prepareStatement("insert into user_post(user_message,user_email,post_date) values(?,?, current_timestamp())");
		ps.setString(1, msg);
		ps.setString(2, mail);
	
		if(ps.executeUpdate()==1)
		{
			System.out.println("\nPost created Sucessfully");
		}

	}
	
	static void PrintPost(Userutility uu)
	{
		String msg = uu.getUser_message();
		String date = uu.getPost_date();
		String time = uu.getPost_time();
		int like = uu.getPost_like();
		int dislike = uu.getPost_dislike();
		
		System.out.println("\nMessage : "+msg+"\nDate :"+date+"\nTime : "+time+" Like : "+like+" DisLike : "+dislike+"\n");
		
		
	}
	
	public void getTimeline(String emailogin) 
	{
		try
		{
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
				
			ps = con.prepareStatement("select user_email,user_message,post_date from user_post where user_email = ? ");
			ps.setString(1, emailogin);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println();
				String mail = rs.getString(1);
				System.out.println("Mail-Id: "+mail);
				String post = rs.getString(2);
				System.out.println("Post : "+post);
				String date = rs.getString(3);
				System.out.println("Date and Time : "+date);
				System.out.println();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	public void seeOthersPost(String emailogin)
	{	
		try
		{
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
				
			ps = con.prepareStatement("select u.name,p.user_email,p.user_message,p.post_date from userinformation as u inner join  user_post as p on u.email = p.user_email");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println();
				String name = rs.getString(1);
				System.out.println("Name : "+name);
				String mail = rs.getString(2);
				System.out.println("Mail-Id: "+mail);
				String post = rs.getString(3);
				System.out.println("Post : "+post);
				String date = rs.getString(4);
				System.out.println("Date and Time : "+date);
				System.out.println();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	
	
	
	
	
	
	
	/*
	
	public void registration()
	{
		try 
		 {
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
			 Scanner sc=new Scanner(System.in);
			 
			 System.out.println("Enter Name");
			 String name=sc.next();
			 
			 System.out.println("Enter Email Id");
			 String email=sc.next();
			 
			 System.out.println("Enter Address");
			 String address=sc.next();
			 
			 System.out.println("Enter Contact Number");
			 Long contact=sc.nextLong();
			 
			 System.out.println("Enter Age");
			 int age=sc.nextInt();
			 
			 System.out.println("Enter Gender");
			 String gender=sc.next();
			 
			 System.out.println("Enter Password");
			 String pswd=sc.next();
			 
			 PreparedStatement prepareStmt=con.prepareStatement("insert into signup values (?, ?, ?, ?, ?, ?, ?)");
			 
			 prepareStmt.setString(1,name);
			 prepareStmt.setString(2,email);
			 prepareStmt.setString(3,address);
			 prepareStmt.setLong(4,contact);
			 prepareStmt.setInt(5,age);
			 prepareStmt.setString(6,gender);
			 prepareStmt.setString(7,pswd);
			 
			 if(prepareStmt.executeUpdate()==1)
			 System.out.println(":::::  Account Created successfully  :::::");
			 
			 			
		 }
		 catch(Exception ex)
		 {
			 System.out.println(ex);
		 }
	}

	public void delete() 
	{
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
			Scanner sc=new Scanner(System.in);
			 System.out.println("Enter email to delete your account");
			String emailToDelete=sc.next();
			PreparedStatement prepareStmt=con.prepareStatement("delete from signup where email=?");
			prepareStmt.setString(1,emailToDelete);
			
		if(prepareStmt.executeUpdate()==1)
		{
		System.out.println("Account deleted successfully");
		}
		else
		{
			System.out.println("You do not have account associated with this email ");
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	public void loginform() 
	{
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook", "root","root");
			Scanner sc=new Scanner(System.in);
			
			System.out.println("Enter Login ID");
			String email=sc.next();
			
			System.out.println("Enter Password");
			String pswd=sc.next();
			

			PreparedStatement prepareStmt=con.prepareStatement("select * from signup where email=? and pswd=?");
			prepareStmt.setString(1,email);
			prepareStmt.setString(2, pswd);
			ResultSet rs=prepareStmt.executeQuery();
			if(rs.next())
			{
				System.out.println("Login Successful");
			}	
			else
			{
				System.out.println("Login Failed");
			}
		}
		catch(Exception ex)
		{
		System.out.println(ex);
		}
	}

	public void update() 
	{
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");	
			Scanner sc=new Scanner(System.in);
			
			System.out.println("Enter Email id");
			String emailToUpdate=sc.next();
			
			System.out.println("Enter name to update");
			String newName=sc.next();
			
			System.out.println("Enter address to update");
			String newAddress=sc.next();
			
			System.out.println("Enter contact number to update");
			Long newContact=sc.nextLong();
			
			System.out.println("Enter age to update");
			int newage=sc.nextInt();
			
			System.out.println("Enter gender to update");
			String newGender=sc.next();
			
			System.out.println("Enter password to update");
			String newPswd=sc.next();
			
			PreparedStatement prepareStmt=con.prepareStatement("update signup set name =?, address=? , contact=? ,age=? , gender=? , pswd=? where email = ?");
			prepareStmt.setString(1, newName);
			prepareStmt.setString(2, newAddress);
			prepareStmt.setLong(3, newContact);
			prepareStmt.setInt(4, newage);
			prepareStmt.setString(5, newGender);
			prepareStmt.setString(6, newPswd);
			prepareStmt.setString(7, emailToUpdate);
			
			if(prepareStmt.executeUpdate()==1)
				System.out.println("Record Updated");
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		*/
		

}
