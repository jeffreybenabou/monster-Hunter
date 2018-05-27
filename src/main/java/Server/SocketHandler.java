package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketHandler extends Thread {
	Socket incoming;
	
	SocketHandler(Socket _in)
	{
		this.incoming=_in;
		
	}
	
	public void run()
	{
		String clientSentence; 
	    String capitalizedSentence;
	    Object obj;


		try
		{
	    
			 DataOutputStream outToClient =
	        		   new DataOutputStream (incoming.getOutputStream() );
          
               
           ObjectInputStream inFromClient =
        		   new ObjectInputStream (incoming.getInputStream());
          
		while(true) {
	           
	        	  try {
					obj = inFromClient.readObject();
				
	        	/*
	        	  if ( obj instanceof Student)
	        	  {
	        		  s = (Student) obj;
	        		  //do something
	        		  
	        		
	        		  
	        	  }*/
	           
	        	  } catch (ClassNotFoundException e) {

						e.printStackTrace();
					}
	      
	        }
		}
		catch(IOException e)
		{
			
		} /*catch (IOException e) {
			e.printStackTrace();
		}*/

	}
}
