import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	// Client title
	String ClientTitle = "YoloScape Client";
	
	//Default Host and Port
	String DefaultHost = "92.237.54.134";
	String DefaultPort = "1337";
	
	private JFrame frame;
	private JTextField portField;
	private JTextField hostField;
	
	public static void main(String[] args) {
		JFrame gui = new GUI();
		gui.setVisible(true);
		gui.setLocationRelativeTo(null);
	}
	
	@SuppressWarnings({ "static-access" })
	public GUI() {
		setSize(367, 286);
		setResizable(false);
		setTitle("Server Info n Shit!");
		frame = this;
		 try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	    }
	    catch (ClassNotFoundException e) {
	    }
	    catch (InstantiationException e) {
	    }
	    catch (IllegalAccessException e) {
	    }

		getContentPane().setLayout(null);
		
		JButton runButton = new JButton("Enter YoloScape!");
		runButton.setBounds(70, 143, 233, 42);
		getContentPane().add(runButton);
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						int port = 43594;
						String host = "0.0.0.0";
						if(!hostField.getText().isEmpty() && hostField.getText() != null)
							host = hostField.getText();
						if(!portField.getText().isEmpty() && portField.getText() != null)
							port = Integer.valueOf(portField.getText());
						new client(host, port).execute(new String[]{"10", "0", "highmem", "members", "32"});
						new Jframe(host, port, ClientTitle);
					}
				}).start();
			}
		});
		
		// Server Host
		JLabel lblSetHost = new JLabel("Set Host:");
		lblSetHost.setBounds(70, 41, 70, 16);
		getContentPane().add(lblSetHost);

		hostField = new JTextField();
		hostField.setColumns(10);
		hostField.setBounds(152, 38, 151, 22);
		hostField.setText(DefaultHost);
		getContentPane().add(hostField);
		
		
		
		// Server Port
		JLabel lblSetPort = new JLabel("Set Port:");
		lblSetPort.setBounds(70, 76, 70, 16);
		getContentPane().add(lblSetPort);

		portField = new JTextField();
		portField.setBounds(152, 73, 151, 22);
		portField.setColumns(10);
		portField.setText(DefaultPort);
		getContentPane().add(portField);
		
	}
}
