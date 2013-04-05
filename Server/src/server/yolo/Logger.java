package server.yolo;
import server.Config;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class Logger{

	static String logDir = "./data/logs";
	public static void logChat(String name, String msg)
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		//get current date time with Date()
		Date date = new Date();
		String time = dateFormat.format(date);
	
		String log = "[" + time + "]" + " " + name + " : " + msg;
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logDir + "/chat.log", true)));
			out.println(log);
			out.close();
		} catch (IOException e) {
			//System.out.println("[Logger] Couldn't find " + logDir + "/chat.log" + " . Not logging chat.");
		}
		
		if(Config.LOG_CHAT)
		{
			System.out.println(log);
		}
	}
	
	public static void logConsole(String msg)
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		//get current date time with Date()
		Date date = new Date();
		String time = dateFormat.format(date);
	
		String log = "[" + time + "]" + msg;
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logDir + "/game.log", true)));
			out.println(log);
			out.close();
		} catch (IOException e) {
			//System.out.println("[Logger] Couldn't find " + logDir + "/game.log" + " . Not logging chat.");
		}
		
		System.out.println(log);
	}
	
	
}
