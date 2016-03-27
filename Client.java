//importing classes
import javax.swing.*;
import java.awt.event.*;

//client
public class Client extends JFrame implements MouseListener{
	
	//non-static references
	SearchNote sn;
	AddNote an;
	JLabel welcome;
	JButton search;
	JButton add;

	//constructor
	Client(){
		
		//creating elements
		welcome = new JLabel("Select your operation by clicking the respective button!");
		welcome.setBounds(100,50,600,40);
		search = new JButton("Search Note");
		search.setBounds(100,100,150,50);
		search.addMouseListener(this);
		add = new JButton("Add a New Note");
		add.setBounds(260,100,150,50);
		add.addMouseListener(this);

		//adding elements
		add(welcome);
		add(search);
		add(add);
	}

	//main method
	public static void main(String [] args){

		Client menu = new Client();
		menu.setLayout(null);
		menu.setBounds(100,100,800,500);
		menu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		menu.setVisible(true);

	}

	//mouse click action
	public void mouseClicked(MouseEvent e){
		
		//making object of Object class
		Object actionSource = e.getSource();

		//search button
		if (actionSource==search){

			//opening search screen
			sn = new SearchNote();
			sn.setLayout(null);
			sn.setBounds(100,100,800,500);
			sn.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			sn.setVisible(true);
			sn.setVisible(true);

			//closing menu
			this.setVisible(false);
			this.dispose();
			this.setVisible(false);

		} 

		//add button
		if (actionSource==add){

			//opening add note screen
			an = new AddNote();
			an.setLayout(null);
			an.setBounds(100,100,800,500);
			an.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			an.setVisible(true);
			an.setVisible(true);

			//closing menu
			this.setVisible(false);
			this.dispose();
			this.setVisible(false);

		}

	}

	//null body implementation
	public void mousePressed(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) {}

}