//importing classes
import java.net.*;
import java.io.*;
import java.io.File;
import java.util.*;

public class Server{

	//save note
	public boolean saveNote(String username, String note){

		try{

			File file = new File("uploads/"+username+".notes");
			FileWriter writer = new FileWriter(file); 
			writer.write(note+"\n"); 
			writer.flush();
			writer.close();
		}
		catch(Exception f){

			f.printStackTrace();
			return false;
		}	

		return true;
	}

	//search note
	public String searchNote(String key){

		try{
			
			File dir = new File("uploads");
			int results = 0;

			//checking if upload dir exists
			if (dir.exists() && dir.isDirectory()) {

				//getting files list
				String[] children = dir.list();

				//if upload dir is empty
				if (children == null)
					return "Uploads directory is empty!";

				//if upload dir is not empty
				else{

					//traversing files list
					for (int i = 0; i < children.length; i++){
						//if file exists
						if(children[i].equals(key+".notes")){

							//opening file
							String content = new Scanner(new File("uploads/"+key+".notes")).useDelimiter("\\Z").next();
							results++;
							return content;
						}
					}
					//if file doesn't exist
					if(results<=0)
						return "No notes were found for the selected user!";
				}
			}	
			//making uploads directory
			else
				dir.mkdir();
		}
		catch(Exception ie){
			ie.printStackTrace();
		}

		return "success!";
	}
   
	//main method
	public static void main(String [] args){

		//server status	
		System.out.println("Server started listening at port 5555");

		//Server class object
		Server s = new Server();

		//listening
		while(true){

			try{
				
				//making socket object
				ServerSocket serverSocket = new ServerSocket(5555);
				serverSocket.setSoTimeout(99999999);
				
				//opening socket
				Socket server = serverSocket.accept();

				//streams object
				OutputStream outToClient = server.getOutputStream();
				ObjectInputStream in = new ObjectInputStream(server.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(outToClient);

				//parsing data
				EmployeeDB tmpData = (EmployeeDB) in.readObject();
				String operation = tmpData.type;

				//selecting operation
				if(operation.equals("add note"))
					s.saveNote(tmpData.username,tmpData.usernote);
				else if(operation.equals("search note")){

					String msg = s.searchNote(tmpData.username);
				    tmpData.username = "";
				    tmpData.usernote = msg;
				    tmpData.type = "";

				    //sending data
				    out.writeObject(tmpData);
				
				}

				//closing server
				serverSocket.close();
				server.close();
			}

			//printing exception notice
			catch(Exception e){
				e.printStackTrace();
			}	
		}  
	}	
}
