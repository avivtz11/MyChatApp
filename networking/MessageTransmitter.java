package mychatappp.networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessageTransmitter extends Thread{
	String message, hostname;
	int port;
	WritableGUI gui;
	
	public MessageTransmitter(){
		
	}
	
	public MessageTransmitter(WritableGUI gui, String message, String hostname, int port){
		this.gui=gui;
		this.hostname=hostname;
		this.message=message;
		this.port=port;
	}
	
	@Override
	public void run() {
		try {
			Socket s= new Socket(hostname,port);
			s.getOutputStream().write(message.getBytes());
			s.close();
			gui.write("Me: "+message);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
