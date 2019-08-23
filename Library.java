/*A library management program for the library to maintain the record of books, 
members, transactions and the book borrowed by the members and returnd by the members,
all the transaction are recorded by the date format of the sql. you can check the books of the library,
as well as all the members of the library. you can also search the members by the search option 
as well as books also.
*/
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelListener;
import java.text.DateFormat;
import java.util.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import net.proteanit.sql.DbUtils;

public class Library implements ActionListener
{
	
	

	//All the initialization are here used in the program.
	JTabbedPane tp;  //to show the header panel.
	JOptionPane jp;  //To show the message while performing an event.
	String dateToStr;   //variable to store the date entered by the user and to use in the updation of sql data.
	JFrame f1; //used to show the table of the book and member in the more.
	JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9;   //All the panels used for different tabs.
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;   //All the buttons used in the program serially.
	TextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23;
	         //All the textFields used in the program to  write the text.
	JLabel leb;  //Used as a label to show the date and time globally in the app.
	static Connection con;  //used for the connection to the database.
	static PreparedStatement pst = null;    // Used for the updation of sql query.
	static Statement st = null;             // Used for the execution of sql query.
	
	
	
	Library()  // A constructor which execute first.
	{
		JFrame f =  new JFrame();  // Frame for the initial app.
		f.setVisible(true);
	    f.setSize(800,450);
		f.setTitle("LIBRARY MANAGEMENT SYSTEM");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	  
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
	    f.add(topPanel);
	   

		// All the tabs are individually a method when you click the according to them it works.
	    member(); 
	    books();
	    search();
	    returningBook();
	    borrowingBooks();
	    transactionRecord();
	    designedBy();
	    importantNotes();
	    moreEnquiy();
	  
		tp =new JTabbedPane();
		tp.addTab("ADD MEMBER", p1);
		tp.addTab("ADD BOOKS", p2);
		tp.addTab("SEARCH", p3);
		tp.addTab("RETURNING BOOKS", p4);
		tp.addTab("BORROWING BOOKS", p5);
		tp.addTab("TRANSACTION RECORD", p6);
		tp.addTab("DESIGNED BY", p7);
		tp.addTab("IMPORTANT NOTES", p8);
		tp.addTab("MORE ENQUIRY", p9);
		

		
		topPanel.add(tp,BorderLayout.CENTER);
		
        
		
	
	}
	
	// It is a method to call the system time to show in the every panel.
	public String dateCall()
	{
		 // Program to show the date in the app according to the system date.
		 Date currentDate = new Date(); 
		 // Here date and time is stored in the srring dateToStr and used to show in the label.
	     dateToStr = DateFormat.getDateTimeInstance
	    		 (DateFormat.LONG,DateFormat.SHORT).format(currentDate);  
	    	
    	return dateToStr;
	    
		
	}
      // This method is used for the more enquiry like know the book details and member details.
	public void moreEnquiy()
	{
		p9 = new JPanel();
		p9.setLayout(null);

		JLabel l1 = new JLabel("Book details :");
		JLabel l2 = new JLabel("Member details :");

		l1.setBounds(5,5,775,30);
		l2.setBounds(5,160,700,30);

		p9.add(l1);	p9.add(l2);

		b10 = new JButton(" Book details ");
		b10.setBounds(5,40,775,40);
		p9.add(b10);
		b10.addActionListener(this);
		
		b11 = new JButton(" Member details ");
		b11.setBounds(5,195,775,40);
		p9.add(b11);
		// To call the date method and show in the panel.
		dateCall();
		leb = new JLabel("Todaty is :" +dateToStr);  
	     leb.setBounds(280, 315, 775, 40);
		 p9.add(leb);		
		b11.addActionListener(this);
	}
    
	//This panel show the important notes.
	public void importantNotes()
	{
		p8 = new JPanel();
		p8.setLayout(null);

		JLabel l1 = new JLabel("Important notes :");
		JLabel l2 = new JLabel("  Below where some important points that you need to keep in mind while using system:");
		JLabel l3 = new JLabel("  If the book was borrowed previous month, for entering the return date please calculate and enter the number of days the book have been with member. ");
		JLabel l4 = new JLabel("  Ex:Borrowed date:25th february");
		JLabel l5 = new JLabel(" No of days in february 28-25=3");
		JLabel l6 = new JLabel("  No of date in march 15");
		JLabel l7 = new JLabel("  Total no of days 15+3=18 days.");

		l1.setBounds(1,20,775,20);
		l2.setBounds(1,42,700,20);
		l3.setBounds(30,64,700,20);
		l4.setBounds(30,86,700,20);
		l5.setBounds(30,108,700,20);
		l6.setBounds(30,130,700,20);
		l7.setBounds(30,152,700,20);
		
		p8.add(l1);	p8.add(l2);	p8.add(l3);	p8.add(l4);	p8.add(l5);	p8.add(l6);	p8.add(l7);	

		// To call the date method and show in the panel.
		dateCall();
		leb = new JLabel("Todaty is :" +dateToStr);  
	     leb.setBounds(280, 315, 775, 40);
		 p8.add(leb);


	}

	//This panel show the designed by name.
	public void designedBy()
	{
		p7 = new JPanel();
		p7.setLayout(null);

		JLabel l1 = new JLabel("Designed By :");
		JLabel l2 = new JLabel("  Rohit kumar soni");
		JLabel l3 = new JLabel("  Trained by - Bishnu barik");
		JLabel l4 = new JLabel("  Lakshya Institute of technology (LIT)");
		JLabel l5 = new JLabel("  Bhubaneswar");

		l1.setBounds(1,80,775,20);
		l2.setBounds(40,102,700,20);
		l3.setBounds(40,124,700,20);
		l4.setBounds(40,146,700,20);
		l5.setBounds(40,168,700,20);

		p7.add(l1);	p7.add(l2);	p7.add(l3);	p7.add(l4);	p7.add(l5);	
		// To call the date method and show in the panel.
		dateCall();
		leb = new JLabel("Todaty is :" +dateToStr);  
	     leb.setBounds(280, 315, 775, 40);
		 p7.add(leb);
	}

    //This panel show the transaction of the member and no. of books borrowed and returned.
	public void transactionRecord()
	{
		p6 = new JPanel();
		p6.setLayout(null);

		JLabel l1 = new JLabel("Enter member ID :");
		l1.setBounds(1,1,775,80);
		p6.add(l1);

		t23 = new TextField();
		t23.setBounds(5,91,775,80);
		p6.add(t23);

		b9 = new JButton(" check Transaction ");
		b9.setBounds(5,181,775,80);
		p6.add(b9);
		
		// To call the date method and show in the panel.
		dateCall();
		leb = new JLabel("Todaty is :" +dateToStr);  
	     leb.setBounds(280, 315, 775, 40);
		 p6.add(leb);
		
		b9.addActionListener(this);

	}

	//This panel show the no of books borrowed by the member.
	public void borrowingBooks()
	{
		p5 = new JPanel();
		p5.setLayout(null);

		JLabel l1 = new JLabel("Enter date borrow :");
		l1.setBounds(5,5,380,80);
		p5.add(l1);
		t20 = new TextField();
		t20.setBounds(400,5,380,80);
		p5.add(t20);

		JLabel l2 = new JLabel("Enter member ID  :");
		l2.setBounds(5,90,380,80);
		p5.add(l2);
		t21 = new TextField();
		t21.setBounds(400,90,380,80);
		p5.add(t21);

		JLabel l3 = new JLabel("Enter book ID  :");
		l3.setBounds(5,175,380,80);
		p5.add(l3);
		t22 = new TextField();
		t22.setBounds(400,175,380,80);
		p5.add(t22);

		b8 = new JButton(" Borrow book ");
		b8.setBounds(5,260,775,40);
		p5.add(b8);
		
		// To call the date method and show in the panel.
		dateCall();
		leb = new JLabel("Todaty is :" +dateToStr);  
	    leb.setBounds(280, 315, 775, 40);
		p5.add(leb);
		b8.addActionListener(this);


	}

	//This panel is for the returning book.
	public void returningBook()
	{
		p4 = new JPanel();
		p4.setLayout(null);

		JLabel l1 = new JLabel("Please enter the date properly to calculate fines correctly. See important notes for details:");
		l1.setBounds(10,5,700,30);
		p4.add(l1);
		
		 //It will store the system current time in millis and then used in sql date.
		 long millis=System.currentTimeMillis();  
	     java.sql.Date date=new java.sql.Date(millis);  
	        
		JLabel l2 = new JLabel("Enter return date  :");
		l2.setBounds(10,30,380,80);
		p4.add(l2);
		t17 = new TextField("YYYY-MM-DD");
		t17.setBounds(400,30,380,75);
		p4.add(t17);

		JLabel l3 = new JLabel("Enter book ID  :");
		l3.setBounds(10,100,380,80);
		p4.add(l3);
		t18 = new TextField();
		t18.setBounds(400,105,380,75);
		p4.add(t18);
		
		JLabel l4 = new JLabel("Enter member ID   :");
		l4.setBounds(5,170,380,80);
		p4.add(l4);
		t19 = new TextField();
		t19.setBounds(400,180,380,75);
		p4.add(t19);

		b7 = new JButton(" Return book ");
		b7.setBounds(5,260,775,40);
		p4.add(b7);
		// To call the date method and show in the panel.
		dateCall();
		leb = new JLabel("Todaty is :" +dateToStr);  
	    leb.setBounds(280, 315, 775, 40);
		p4.add(leb);
		b7.addActionListener(this);
		
	}
	
	//This is used for the searching of the books and members according to the id and by name.
	public void search() 
	{
		p3 = new JPanel();
		p3.setLayout(null);

		
		JLabel l1 = new JLabel("Enter book ID  :");
		l1.setBounds(5,5,380,40);
		p3.add(l1);
		t13 = new TextField();
		t13.setBounds(5,47,380,40);
		p3.add(t13);
		b3 = new JButton(" Search for book ");
		b3.setBounds(5,89,380,40);
		p3.add(b3);
		b3.addActionListener(this);

		JLabel l2 = new JLabel("Enter book name  :");
		l2.setBounds(395,5,380,40);
		p3.add(l2);
		t14 = new TextField();
		t14.setBounds(395,47,380,40);
		p3.add(t14);
		b4 = new JButton(" Search for book ");
		b4.setBounds(395,89,380,40);
		p3.add(b4);
		b4.addActionListener(this);

		JLabel l3 = new JLabel("Enter member ID  :");
		l3.setBounds(5,140,380,40);
		p3.add(l3);
		t15 = new TextField();
		t15.setBounds(5,182,380,40);
		p3.add(t15);
		b5 = new JButton(" Search for member ");
		b5.setBounds(5,224,380,40);
		p3.add(b5);
		b5.addActionListener(this);

		JLabel l4 = new JLabel("Enter member name  :");
		l4.setBounds(395,140,380,40);
		p3.add(l4);
		t16 = new TextField();
		t16.setBounds(395,182,380,40);
		p3.add(t16);
		b6 = new JButton(" Search for member ");
		b6.setBounds(395,224,380,40);
		p3.add(b6);
		
		// To call the date method and show in the panel.
		dateCall();
		leb = new JLabel("Todaty is :" +dateToStr);  
	    leb.setBounds(280, 315, 775, 40);
		p3.add(leb);
		
		b6.addActionListener(this);

	}
	
	//It will add the books details in the database.
	public void books() 
	{
		p2 = new JPanel();
		p2.setLayout(null);

		
		JLabel l1 = new JLabel("Book name  :");
		JLabel l2 = new JLabel("Author name  :");
		JLabel l3 = new JLabel("Publisher name :");
		JLabel l4 = new JLabel("Book type:");
		JLabel l5 = new JLabel("Book ID:");
		JLabel l6 = new JLabel("Year  :");
		
		l1.setBounds(10,5,380,40);
		l2.setBounds(10,47,380,40);
		l3.setBounds(10,89,380,40);
		l4.setBounds(10,131,380,40);
		l5.setBounds(10,173,380,40);
		l6.setBounds(10,215,380,40);
		
		p2.add(l1);	p2.add(l2);	p2.add(l3);	p2.add(l4);	p2.add(l5);	p2.add(l6);
		

		t7 = new TextField();
		t8 = new TextField();
		t9 = new TextField();
		t10 = new TextField();
		t11 = new TextField();
		t12= new TextField();
		
		t7.setBounds(401,5,380,40);
		t8.setBounds(401,47,380,40);
		t9.setBounds(401,89,380,40);
		t10.setBounds(401,131,380,40);
		t11.setBounds(401,173,380,40);
		t12.setBounds(401,215,380,40);
		
		p2.add(t7);	p2.add(t8);	p2.add(t9);	p2.add(t10);	p2.add(t11);	p2.add(t12);
		
		
		b2 = new JButton("Create new book");
		b2.setBounds(1, 265, 775, 40);
		p2.add(b2);
		
		// To call the date method and show in the panel.
		dateCall();
		leb = new JLabel("Todaty is :" +dateToStr);  
	    leb.setBounds(280, 315, 775, 40);
		p2.add(leb);
		
		b2.addActionListener(this);
	}

	// It is used to create the new member of the library.
	public void member()  
	{
		p1 = new JPanel();
		p1.setLayout(null);

		
		JLabel l1 = new JLabel("Member name  :");
		JLabel l2 = new JLabel("Age  :");
		JLabel l3 = new JLabel("ID No. :");
		JLabel l4 = new JLabel("Member type  :");
		JLabel l5 = new JLabel("Address  :");
		JLabel l6 = new JLabel("Telephone  :");
		
		l1.setBounds(10,5,380,40);
		l2.setBounds(10,47,380,40);
		l3.setBounds(10,89,380,40);
		l4.setBounds(10,131,380,40);
		l5.setBounds(10,173,380,40);
		l6.setBounds(10,215,380,40);
		
		p1.add(l1);	p1.add(l2);	p1.add(l3);	p1.add(l4);	p1.add(l5);	p1.add(l6);
		
		t1 = new TextField();
		t2= new TextField();
		t3 = new TextField();
		t4 = new TextField();
		t5 = new TextField();
		t6 = new TextField();
		
		t1.setBounds(401,5,380,40);
		t2.setBounds(401,47,380,40);
		t3.setBounds(401,89,380,40);
		t4.setBounds(401,131,380,40);
		t5.setBounds(401,173,380,40);
		t6.setBounds(401,215,380,40);
		
		p1.add(t1);	p1.add(t2);	p1.add(t3);	p1.add(t4);	p1.add(t5);	p1.add(t6);
		
		b1 = new JButton("Create new member");
		b1.setBounds(1, 265, 775, 40);
		p1.add(b1);
		
		// To call the date method and show in the panel.
		dateCall();
		leb = new JLabel("Todaty is :" +dateToStr);  
	    leb.setBounds(280, 315, 775, 40);
		p1.add(leb);
		
		b1.addActionListener(this);
	}
	
	//This method is for the connection to the database here the user is system and the password 
	//is also system according to my system it can be altered according to the user.
	public static Connection getMyConnection() 
	{
       try
       {
  	       Class.forName("oracle.jdbc.OracleDriver");  // To load the driver.
  	       // Here Type iv driver id used for the connection because it is vary handy.
		   con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return con;
		
	}
	
	public static void main(String[] args) throws Exception 
	{
		
		  new Library();   
	}
		
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		try 
		{
			//To connect the database, if the connection will be null it will show that it is not connected.
			  con = getMyConnection();
				if(con == null)
				{
					System.out.println("Not connected to database server.");
					System.exit(0);
				}
			  
				//code for button b1 to create a member of the library.
			if(ae.getSource() == b1)
			{
				String sql = "Insert into library values(?,?,?,?,?,?)" ;
				String member_name = t1.getText();
				int age = Integer.parseInt(t2.getText());
				int id_number = Integer.parseInt(t3.getText());
				String member_type = t4.getText();
				String address = t5.getText();
				int telephone = Integer.parseInt(t6.getText());
				
				pst = con.prepareStatement(sql);
				pst.setString(1, member_name);
				pst.setInt(2, age);
				pst.setInt(3, id_number);
				pst.setString(4, member_type);
				pst.setString(5, address);
				pst.setInt(6, telephone);
				pst.executeUpdate();
				
				//After becoming a member this message will show.
				JOptionPane.showMessageDialog(null, "Congratulation now you are the new member Of the library.");
				}
			
			//code for button b2 to add the details of the book.
			if(ae.getSource() == b2)
			{
				String sql1 = "Insert into book values(?,?,?,?,?,?)" ;
				String name = t7.getText();
				String author = t8.getText();
				String publisher = t9.getText();
				String type = t10.getText();
				int id = Integer.parseInt(t11.getText());
				int year = Integer.parseInt(t12.getText());
				
				pst = con.prepareStatement(sql1);
				pst.setString(1, name);
				pst.setString(2, author);
				pst.setString(3, publisher);
				pst.setString(4, type);
				pst.setInt(5, id);
				pst.setInt(6, year);
				pst.executeUpdate();
				
				//After successfully added this message will show.
				JOptionPane.showMessageDialog(null, "The book is successfully added to the library data.");
			}
			
			//code for button b3 to search book with its id.
			if(ae.getSource() == b3)
			{
				String name = null,author = null,publisher = null,type = null;
				int year = 0;
				String sql = "select * from book where id=?";
				int id = Integer.parseInt(t13.getText());
				
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					name = rs.getString(1);
					author = rs.getString(2);
					publisher = rs.getString(3);
					type = rs.getString(4);
					year = rs.getInt(6);
				}
				
				// This will show the details as a message.
				JOptionPane.showMessageDialog(null, "The name of the book is - "+name+"\n"+" The auther of the book is - "+author+"\n"+ 
						"\t The publisher of the book is - "+publisher+"\n"+" The Type of the book is - "+type+"\n"+"The year is - "+year);
				}
			
			//code for button b4 to search book with its name.
			if(ae.getSource() == b4)
			{
				String author = null,publisher = null,type = null;
				int year = 0,id = 0;
				String sql = "select * from book where name=?";
				String name = t14.getText();
				
				
				pst = con.prepareStatement(sql);
				pst.setString(1, name);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					
					author = rs.getString(2);
					publisher = rs.getString(3);
					type = rs.getString(4);
					id = rs.getInt(5);
					year = rs.getInt(6);
				}
				// This will show the details as a message.
				JOptionPane.showMessageDialog(null, "The auther of the book is  :  "+author+"\n"+ 
						" The publisher of the book is :  "+publisher+"\n"+" The Type of the book is :  "
						+type+"\n"+" The ID of the book is :  "+id+"\n"+"The year is  :  "+year);
			}
			
			//code for button b5 to search the member with its member id.
			if(ae.getSource() == b5)
			{
				String member_name = null,member_type = null,address = null;
				int age = 0,telephone = 0;
				String sql = "select * from library where id_number=?";
				int id_number = Integer.parseInt(t15.getText());
				
				pst = con.prepareStatement(sql);
				pst.setInt(1, id_number);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					member_name = rs.getString(1);
					age = rs.getInt(2);
					member_type = rs.getString(4);
					address = rs.getString(5);
					telephone = rs.getInt(6);
				}
				
				
				// This will show the details as a message.
				JOptionPane.showMessageDialog(null, "The name of the member is :  "+member_name+"\n"+
				"The age of the member is  : "+age+ "\n"+"The member type is  : "+member_type+"\n"+
			    "The address of the member is  : "+address+"\n"+"Telephone of the member is  : "+telephone);
				
				
				}
			
			//code for button b6 to search the member with its member name.
			if(ae.getSource() == b6)
			{
				String member_type = null,address = null;
				int age = 0,telephone = 0,id_number = 0;
				String sql = "select * from library where member_name=?";
				String member_name = t16.getText();
				
				pst = con.prepareStatement(sql);
				pst.setString(1, member_name);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next())
				{
					id_number = rs.getInt(3);
					age = rs.getInt(2);
					member_type = rs.getString(4);
					address = rs.getString(5);
					telephone = rs.getInt(6);
				}
				// This will show the details as a message.
				JOptionPane.showMessageDialog(null, "The id_number of the member is :  "+id_number+"\n"+
						"The age of the member is : "+age+"\n"+"The member_type is "+member_type+"\n"+
						"The address of the member is : "+address+"\n"+"Telephone of the member is  : "+telephone);
			}
			
			//code for button b7 to return the according to the id, member id and date.
			if(ae.getSource() == b7)
			{
				
				String type = "Returned";
				 String str=t17.getText(); //Here user date is stored in the string str.
				 java.sql.Date date = java.sql.Date.valueOf(str);//Here the str date is stored in date 
				                                                      //as in the format of sql.
				 
				 String sql = "insert into borrower values(?,?,?,?)";
				 java.sql.Date curr_date = date;  //Here ste date is used to save in the database.
				 int id_number = Integer.parseInt(t18.getText());
				 int id = Integer.parseInt(t19.getText());
				 
				 pst = con.prepareStatement(sql);
				 pst.setDate(1, curr_date);
				 pst.setInt(2, id);
				 pst.setInt(3, id_number);
				 pst.setString(4, type);
				 pst.executeUpdate();
				 
				 //This will show the message when the book is successfully returned.
				 JOptionPane.showMessageDialog(null, "Your record id successfully added.");
				 
			}
			
			//code for button b7 to borrow the according to the id, member id and date.
			if(ae.getSource() == b8)
			{
				 String type = "borrower";
				String str=t20.getText(); 
				java.sql.Date date = java.sql.Date.valueOf(str);
				String sql = "insert into borrower values(?,?,?,?)";
				java.sql.Date curr_date = date;
				int id_number = Integer.parseInt(t21.getText());
			    int id = Integer.parseInt(t22.getText());
			    pst = con.prepareStatement(sql);
				 pst.setDate(1, curr_date);
				 pst.setInt(2, id_number);
				 pst.setInt(3, id);
				pst.setString(4, type);
				 
				 pst.executeUpdate();
				//This will show the message when the book is successfully borrowed.
				JOptionPane.showMessageDialog(null,"Book is borrowed on your ID.");
				
			}
			
			//code for button b9 to show the transaction of the member in the type borrow and returned.
			if(ae.getSource() == b9)
			{
				String sql = "select * from borrower where id=?";
				int id_number = Integer.parseInt(t23.getText());
				pst = con.prepareStatement(sql);
				pst.setInt(1, id_number);
				ResultSet rs = pst.executeQuery();
				
				f1 = new JFrame();
				f1.setVisible(true);
				f1.setSize(1400, 1000);
				f1.setLayout(null);
				
				JTextArea a = new JTextArea();
				a.setVisible(true);
				a.setSize(1400, 1000);
				f1.add(a);
				
				//Create a table and show the details in the table format.  
				JTable tab = new JTable();
				tab.setSize(1400, 1000);
				tab.setName("TRANSACTION DETAILS");
				tab.setRowHeight(40);
				tab.setRowMargin(30);

				a.add(tab);
				//here a jar file is used to directly fetch the data from the resultSet to the table.
				tab.setModel(DbUtils.resultSetToTableModel(rs));
				//DbUtils is used from that jar file(E:\J2EE (SOFTWARE)\JAR\commons-dbutils-1.5-test-sources.jar).
				 
				
				
			}
			
			//code for button b10 to display the data of the book stored in the database.
			if(ae.getSource() == b10)
			{
				
				String sql1 = "select * from book";
				pst = con.prepareStatement(sql1);
				ResultSet rs = pst.executeQuery(sql1);
				
				f1 = new JFrame();
			    f1.setVisible(true);
				f1.setSize(1400, 1000);
				f1.setLayout(null);
				
				JTextArea a = new JTextArea();
				a.setVisible(true);
				a.setSize(1400, 1000);
				f1.add(a);
				
				JTable tab = new JTable();
				tab.setSize(1400, 1000);
				tab.setName("BOOK DETAILS");
				tab.setRowHeight(40);
				tab.setRowMargin(30);
				a.add(tab);
				tab.setModel(DbUtils.resultSetToTableModel(rs));
				

				
				
			}
			
			//code for button b11 to display the data of the members stored in the database.
			if(ae.getSource() == b11)
			{
				
				String sql2 = "select * from library";
				pst = con.prepareStatement(sql2);
				ResultSet rs = pst.executeQuery(sql2);
				
				f1 = new JFrame();
				f1.setVisible(true);
				f1.setSize(1400, 1000);
				f1.setLayout(null);
				
				
				JTable tab = new JTable();
				tab.setSize(1400, 1000);
				tab.setName("MEMBER DETAILS");
				tab.setRowHeight(40);
				tab.setRowMargin(30);

				f1.add(tab);
				
				tab.setModel(DbUtils.resultSetToTableModel(rs));
			}
				
		
				
					
		}
	
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}
}


		
		
	

	
	

