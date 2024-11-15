package com.library;

import java.util.Scanner;


public class Main {

	public static void main(String[] args)
	{
		
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("Library management system");
			System.out.println("1.Add a book");
			System.out.println("2.Update a book");
			System.out.println("3.Search a book");
			System.out.println("4.Borrow a book");
			System.out.println("5.Return a book");
			System.out.println("6.Exit");
			System.out.println("Enter a choice");
			int choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				Library.addbook(sc);
				break;
			case 2:
				Library.updatebook(sc);
				break;
			case 3:
				Library.searchbook(sc);
				break;
			case 4:
				Library.borrowbook(sc);
				break;
			case 5:
				Library.returnbook(sc);
				break;
			case 6:
				System.out.println("existing...");
				break;
			default:
				System.out.println("invalid option");
				
			}

	}
}
}
