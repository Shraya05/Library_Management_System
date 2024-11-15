package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Library {

	public static final String DBurl="jdbc:mysql://localhost:3306/my_db";
	public static final String name="admin";
	public static final String pass="pass";

	public static void addbook(Scanner sc)
	{
		sc.nextLine();
		System.out.println("Enter book name:");
		String bookname=sc.nextLine();
		System.out.println("Enter author name:");
		String author=sc.next();
		System.out.println("Enter year of publication:");
		int year=sc.nextInt();
	
		try(Connection con=DriverManager.getConnection(DBurl,name,pass);
				PreparedStatement pst=con.prepareStatement("insert into books (bookname,author,year) values(?,?,?)")){
		pst.setString(1,bookname);
		pst.setString(2,author);
		pst.setInt(3,year);
		pst.executeUpdate();
				System.out.println("book added successsfully!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void updatebook(Scanner sc)
	{
		System.out.println("Enter book id:");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter book name:");
		String bookname=sc.nextLine();
		System.out.println("Enter author name:");
		String author=sc.nextLine();
		System.out.println("Enter the year of publication:");
		int year=sc.nextInt();
		try(Connection con = DriverManager.getConnection(DBurl,name,pass);
				PreparedStatement pst=con.prepareStatement("update books set bookname=?,author=?,year=? where id=?")){
					pst.setString(1,bookname);
					pst.setString(2,author);
					pst.setInt(3,year);
					pst.setInt(4,id);
					int rows=pst.executeUpdate();
					if(rows>0)
					{
						System.out.print("books updated successfully");
					}
					else
					{
						System.out.println("book not found");
					}
				}
				catch(Exception e)
		{
					e.printStackTrace();
		}
	}

	public static void searchbook(Scanner sc)
	{
		
		try(Connection con=DriverManager.getConnection(DBurl,name,pass);
		PreparedStatement pst = con.prepareStatement("select * from books where bookname like ?")){
			System.out.println("Enter book name:");
			String bookname1 = sc.nextLine();
			pst.setString(1,"%"+bookname1+"%");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.print("Id:"+rs.getInt("id"));
				System.out.print("bookname:"+rs.getString("bookname"));
				System.out.print("year:"+rs.getInt("year"));
				System.out.print("author:"+rs.getString("author"));
				System.out.print("Borrowed:"+rs.getBoolean("isBorrowed") !=null ?"yes":"no");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void borrowbook(Scanner sc)
	{
		int id=sc.nextInt();
		try(Connection con=DriverManager.getConnection(DBurl,name,pass);
				PreparedStatement pst=con.prepareStatement("update books set isBorrow=true where id=? and isBorrow=false")){
			pst.setInt(1,id);
			int rows=pst.executeUpdate();
			if(rows>0)
			{
				System.out.println("books marked as borrowed");
			}
			else
			{
				System.out.println("book not found");
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public static void returnbook(Scanner sc)
	{
		int id=sc.nextInt();
		try(Connection con=DriverManager.getConnection(DBurl,name,pass);
				PreparedStatement pst=con.prepareStatement("update books set isBorrow=false where id=? and isBorrow=true")){
			pst.setInt(1,id);
			int row=pst.executeUpdate();
			if(row>0)
			{
				System.out.println("book returnrd Successfully");
			}
			else
			{
				System.out.println("book not found or book not borrowed");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}



