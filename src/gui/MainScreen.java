package mychatappp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import mychatappp.networking.MessageListener;
import mychatappp.networking.MessageTransmitter;
import mychatappp.networking.WritableGUI;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen implements WritableGUI{

	private JFrame frame;
	private JTextField ipTextField;
	private JTextField targetPort;
	private JTextField message;
	private JTextField receivePort;
	private JTextArea chat; 
	MessageListener listener;
	JButton sendButton = new JButton("Send");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 347);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getRootPane().setDefaultButton(sendButton);
		
		ipTextField = new JTextField();
		ipTextField.setBounds(206, 11, 277, 20);
		frame.getContentPane().add(ipTextField);
		ipTextField.setColumns(10);
		
		targetPort = new JTextField();
		targetPort.setText("1234");
		targetPort.setBounds(493, 11, 86, 20);
		frame.getContentPane().add(targetPort);
		targetPort.setColumns(10);
		
		chat = new JTextArea();
		chat.setBounds(10, 42, 569, 225);
		frame.getContentPane().add(chat);
		
		message = new JTextField();
		message.setBounds(10, 278, 472, 20);
		frame.getContentPane().add(message);
		message.setColumns(10);
		
		
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageTransmitter transmitter = new MessageTransmitter(MainScreen.this, message.getText(), ipTextField.getText(), Integer.parseInt(targetPort.getText()));
				transmitter.start();
			}
		});
		sendButton.setBounds(492, 278, 89, 23);
		frame.getContentPane().add(sendButton);
		
		JButton listenButton = new JButton("Listen");
		
		listenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener = new MessageListener(MainScreen.this,Integer.parseInt(receivePort.getText()));
				listener.start();
			}
		});
		listenButton.setBounds(10, 10, 89, 23);
		frame.getContentPane().add(listenButton);
		
		receivePort = new JTextField();
		receivePort.setText("8877");
		receivePort.setBounds(110, 11, 86, 20);
		frame.getContentPane().add(receivePort);
		receivePort.setColumns(10);
	}

	@Override
	public void write(String s) {
		chat.append(s+System.lineSeparator());
		
	}
}
