//importing classes
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;

//search class
public class SearchNote extends JFrame implements MouseListener{

	//data members
	Client cn;
	JButton searchBtn;
	JButton addBtn;
	JTextField searchField;
	JTextArea note;
	JLabel back;

	//constructor
	SearchNote(){

		//slogan
		JLabel slogan = new JLabel();
		slogan.setText("Enter the username to search for previous notes of that user, or click back to go to main menu!");
		slogan.setBounds(100,50,600,40);
		
		//search field
		searchField = new JTextField("");
		searchField.setBounds(140,100,350,35);
		
		//buttons
		searchBtn = new JButton("Search");
		searchBtn.setBounds(500,100,80,35);
		searchBtn.addMouseListener(this);
		addBtn = new JButton("Add");
		addBtn.setBounds(590,100,80,35);
		addBtn.addMouseListener(this);
		
		//notes reading pane
		note = new JTextArea();
		JScrollPane readingPane = new JScrollPane(note);
		readingPane.setBounds(100,200,600,80); 

		//go back to menu
		back = new JLabel("<< Go Back to Home");
      	back.setBounds(580,400,200,35);
      	back.addMouseListener(this); 
		
		//frame
		add(slogan);
		add(searchField);
		add(searchBtn);
		add(readingPane);
		add(back);
	
	}

	//mouseclick
	public void mouseClicked(MouseEvent e){
		
		//making object of Object class
		Object actionSource= e.getSource();

	   //search button
	   if (actionSource==searchBtn){
	     
			try{

			    //binding socket
			    Socket client = new Socket("Localhost", 5555);

			    //data
			    EmployeeDB edb = new EmployeeDB();
			    edb.username = searchField.getText();
			    edb.usernote = "";
			    edb.type = "search note";

			    //sending search term
			    OutputStream outToServer = client.getOutputStream();
			    ObjectOutputStream out = new ObjectOutputStream(outToServer);
			    out.writeObject(edb);

			    //getting response
			    ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			    EmployeeDB tmpData = (EmployeeDB) in.readObject();
			    note.setText(tmpData.usernote);

			    //closing streams
        		outToServer.close();
                out.close();
        		client.close();

			}
			catch(Exception ce){
			    ce.printStackTrace();
			}
	
	    }
	    else if (actionSource==back){

	    	//opening search
			cn = new Client();
			cn.setLayout(null);
			cn.setBounds(100,100,800,500);
			cn.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			cn.setVisible(true);
			cn.setVisible(true);

			//closing menu
			this.setVisible(false);
			this.dispose();
			
	    }

	}   

   //null body implementation
    public void mousePressed(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) {}

}