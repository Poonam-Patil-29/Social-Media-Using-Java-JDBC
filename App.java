package com.ghri.FacebookFoundationalProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ghri.FacebookFoundationalProjectModels.Userutility;
import com.ghri.FacebookFoundationalProjectServiceDAO.FacebookDaoInterface;
import com.ghri.FacebookFoundationalProjectServiceDAO.FaceboookDaoImpl;

public class App 
{
	private static final String String = null;

	public static void printDetails(Userutility u)
	{
		int id = u.getId();
		String name = u.getName();
		String address = u.getAddress();
		int age = u.getAge();
		String gender = u.getGender();
		System.out.println(id+" "+name+" "+" "+address+" "+age+"  "+gender+" ");
	}

	    public static void main( String[] args )
	    {
	   	
		    try
		    {
		    	Scanner sc = new Scanner(System.in);
		    	System.out.println("- - - WELCOME TO FACEBOOK - - -");
		    	System.out.println();
		    	System.out.println("1. FOR REGISTRATION");
		    	System.out.println("2. FOR LOGIN");
		         System.out.println("\nENTER YOUR CHOICE");
		    	int choise = sc.nextInt();
		    	boolean flag = true;
		    	String emailogin = null;
				String passwordlogin = null;
				
				//getting choise to sign up and login in	
				
			    	switch(choise)
			    	{
			    	case 1:
			    		System.out.println("ENTER USER ID");
			    		int id = sc.nextInt();
			    		System.out.println("ENTER NAME");
			    		String name = sc.next();
			    		System.out.println("ENTER ADDRESS");
			    		String address = sc.next();
			    		System.out.println("ENTER AGE");
			    		int age = sc.nextInt();
			    		System.out.println("ENTER EMAIL ID");
			    		String email = sc.next();
			    		System.out.println("ENTER GENDER");
			    		String gender = sc.next();
			    		System.out.println("ENTER PASSWORD");
			    		String passward = sc.next();    		
			    		Userutility user = new Userutility();
			    		user.setId(id);
			    		user.setAddress(address);
			    		user.setAge(age);
			    		user.setEmail(email);
			    		user.setGender(gender);
			    		user.setName(name);
			    		user.setPassword(passward);
			    		
			    		FaceboookDaoImpl.signUp(user);
			    	
			    	case 2:
			    		boolean choise1 = true;
			    		boolean choise2 = true;
			    		 emailogin = null;
			    		 passwordlogin = null;
			    		
			    		while(choise1)
			    		{
			    			System.out.println("ENTER EMAIL ID");
			    			emailogin = sc.next();
			    			choise1 = FaceboookDaoImpl.loginEmailCheckIsAvailable(emailogin);
			    			
			    		}
			    		
			    		while(choise2)
			    		{
			    			System.out.println("ENTER PASSWORD");
			    			passwordlogin = sc.next();
			    			choise2 =FaceboookDaoImpl.logiEmailPasswordCheckIsAvailable(emailogin,passwordlogin);
			    		}
			    		
			    		System.out.println("LOGIN SUCCESSFUL");
			    		System.out.println();
				    	System.out.println();

			    		// after login user see menu 
			    		
			    		String option;
			    		String opton1;
			    		
						do {
			        		Scanner Sc = new Scanner(System.in);
			        		System.out.println("- - - LISTING ALL THE MENUS - - -");
			        		System.out.println(
			        				"\n 1. View all profiles"
			        				+"\n 2. Update profile"
			        				+ "\n 3. Delete profile"
			        				+ "\n 4. View my profile"
			        				+"\n 5. Search profile"
			        				+ "\n 6. Create Post"
			        				+ "\n 7. Show timeline"
			        				+"\n 8. See post created by other user"
			        				+ "\n 9. Log out");
			        		System.out.println();
			        		System.out.println("ENTER OPTION");
			        		System.out.println();

			        		int option1 = sc.nextInt();
			        		FacebookDaoInterface dao = new FaceboookDaoImpl();
			        		
			        		switch(option1)
			        		{
				        		case 1: //show all users
				        				System.out.println("- - - SHOWING ALL USERS - - -");
				        				ArrayList<Userutility> uList = dao.viewAllUser();
				        				for(Userutility userutility : uList)
				        				{
				        					printDetails(userutility);
				        				}
				        				break;
				        				
				        		case 2: // update
				        				dao.update(emailogin);
				        				break;
				        				
				        		case 3: //delete 
				        				dao.delete(emailogin);
				        				break;

				        		case 4: //view my profile
				        			
				        			List<Userutility> list = dao.View(emailogin);
				        			
				        			for(Userutility u : list)
				        			{
				        				printDetails(u);
				        			}
				        			
				        			break;
				        			
				        		case 5: //search profile
				        			System.out.println("ENTER NAME TO SEARCH");
				        			name = sc.next();
				        			List<Userutility> List = dao.searchprofile(name);
				        			for(Userutility u : List)
				        			{
				        				printDetails(u);
				        			}
				        							        			
				        			break;
				        			
				        		case 6: //create post
				        			System.out.println("MAKE POST");
				        			sc.nextLine();
				        			String message = sc.nextLine();
				        			dao.createpost(message,emailogin);
				        			break;
				        			
				        		case 7://Show Timeline
				        			dao.getTimeline(emailogin);
				        			break;
				        			
				        		case 8://see post created by other user
				        			dao.seeOthersPost(emailogin);
				        			break;
				        			
				        		case 9://logout
				        			flag = false;
				        			break;
				        		
				        		case 10://Like
				        			
			        		}
						
						
						if(flag==false)
						{
							break;
						}
		        		System.out.println("Do you want  to menu continue (yes/no)");
		        		opton1 = sc.next();
						
		        	}while(opton1.equalsIgnoreCase("yes"));
						
						System.out.println("Thank You for visiting facebook");
			    }
		    } 	
		    catch(Exception e)
		    {
		    	System.out.println(e);
		    }
	    }
}

