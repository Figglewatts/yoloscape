package server;

import java.io.BufferedReader;
import java.io.Console;
import java.lang.Exception;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import server.model.players.Player;
import server.model.players.Client;
import server.model.players.PlayerHandler;
/**
* Command Line Server starter for RuneLocus
*
* @author Figglewatts
*/

public class ServerStart {
	
	static boolean appRunning = true; // if true, app is running
	static boolean serverRunning = false; // if true, server is running
	
	static Console console = System.console();
	
	static int port;
	
	public static void main(String[] args)
	{
		Start();
		while (appRunning == true)
		{
			Update();
		}
	}
	
	// run once when the app is launched
	static void Start()
	{
		GetPort();
	}
	
	// run while the app is running
	static void Update()
	{
		CheckCommand(GetInput());
	}
	
	// check commands from user input
	static void CheckCommand(String input)
	{
		switch(input)
		{
			case "help":
				System.out.print("port - prompts user to enter server port \nrun - runs the server \ncheckserver - checks the server status");
				break;
			case "port":
				GetPort();
				break;
			case "currentplayers":
			if(Server.isRunning)
				GetCurrentPlayers();
			else
				System.out.println("Server isn't running");
			case "run":
				RunServer(port);
				break;
			case "checkserver":
				CheckServer();
				break;
			case "exit":
				Exit();
				break;
			case "message":
				if(Server.isRunning){
					System.out.println("Message:");
					String msg = GetInput();
					if(msg != "")
					{
						SendMessageToPlayers(msg);
					}
					else
					{
						System.out.println("Invalid Message");
					}
				}
				else
				{
					System.out.println("Server isn't running");
				}
				break;
			default:
				System.out.println("Command not recognized.");
		}
	}
	
	// gets user input
	static String GetInput()
	{
		return console.readLine("\n>");
	}
	
	// prompts the user to select a port for the server
	static void GetPort()
	{
		System.out.println("Please type the port you want the server to be initialized on.");
		String portString = GetInput();
		try
		{
			port = Integer.parseInt(portString);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// runs the server on the port selected by the user
	static void RunServer(final int portNumber)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					if (portNumber >= 1)
					{
						Server.runServer(portNumber);
						serverRunning = true;
						System.out.println("Server is running.");
					}
					else
					{
						System.out.println("Port must be more than or equal to 1.");
					}
				}
				catch (NullPointerException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	// checks if the server is running
	static void CheckServer()
	{
		if (Server.isRunning == true)
		{
			System.out.println("Server is running.");
		}
		else if (Server.isRunning == false)
		{
			System.out.println("Server is not running.");
		}
		else
		{
			System.out.println("Unable to get server status.");
		}
	}
	
	// exits the program and stops the server
	static void Exit()
	{
		System.exit(0);
	}
	//sends a message to each connected player.
	static void SendMessageToPlayers(String msg)
	{
		for(Player p : Server.playerHandler.players) {
			if(p == null)
				continue;
			Client c = (Client)p;
			c.sendMessage("[Server] " + msg);
		}
	}
	
	static void GetCurrentPlayers(){
		for(Player p : Server.playerHandler.players) {
			System.out.println(p.playerName);
		}
		System.out.println("Current player count :" + Server.playerHandler.players.length + "/" + Config.MAX_PLAYERS);
	}
}