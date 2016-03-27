//importing classes
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

//add note
public class AddNote extends JFrame implements MouseListener, Serializable{

   //non static references
   Client cn;
   JButton submit;
   JLabel back;
   JLabel success;
   JLabel slogan;
   JLabel userLegend;
   JTextField username;
   JLabel noteLegend;
   JTextArea note;
   JScrollPane readingPane;

   
   //constructor
   AddNote(){

      //slogan
      slogan = new JLabel();
      slogan.setText("Enter username and note, then click submit!");
      slogan.setBounds(100,50,600,40);

      //username
      userLegend = new JLabel("Username");
      userLegend.setBounds(100,100,80,35);
      username = new JTextField();
      username.setBounds(180,100,170,35);

      //note
      noteLegend = new JLabel("Note");
      noteLegend.setBounds(100,150,80,35);
      note = new JTextArea();
      readingPane = new JScrollPane(note); 
      readingPane.setBounds(180,150,170,70);

      //button
      submit = new JButton("Submit");
      submit.setBounds(100,250,80,35);
      submit.addMouseListener(this);

      //success
      success = new JLabel();
      success.setBounds(100,300,350,35);

      //back link
      back = new JLabel("<< Go Back to Home");
      back.setBounds(580,400,200,35);
      back.addMouseListener(this);

      //adding element
      add(slogan);
      add(userLegend);
      add(username);
      add(noteLegend);
      add(readingPane);
      add(submit);
      add(success);
      add(back);

   }

   //mouseclick
    public void mouseClicked(MouseEvent e){

    	//making object of Object class
		Object actionSource = e.getSource();

	   	//add new note
		if (actionSource==submit){

			try{

				//data
				EmployeeDB edb = new EmployeeDB();
				edb.username = username.getText();
				edb.usernote = note.getText();	
				edb.type = "add note";

				//binding socket
                Socket client = new Socket("Localhost", 5555);				

                //sending data
                OutputStream outToServer = client.getOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(outToServer);
                out.writeObject(edb);
        		
                //closing streams
        		outToServer.close();
                out.close();
        		client.close();

                //success
                username.setText("");
                note.setText("");
                success.setText("Your note has been saved successfully!");
        	}
			catch(Exception i){
				i.printStackTrace();
			}
		}

	   //go back		
	   else if (actionSource==back){
	     
            //making object of Client
		    cn = new Client();
			cn.setLayout(null);
			cn.setBounds(100,100,800,500);
			cn.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			cn.setVisible(true);
			cn.setVisible(true);

			//closing add note screen
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
