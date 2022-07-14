package dev.codenriver.webapp.serlet;

import java.io.BufferedReader;
import java.io.IOException; 
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class HelloSerlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter writer = resp.getWriter();
		writer.write("HELLO! :)");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BufferedReader reader = req.getReader();
		String requestBody = "";
		String line = "";
		while ((line=reader.readLine()) != null) {
			requestBody += line;
		}
		
		PrintWriter writer = resp.getWriter();
		writer.write("Hello, " + requestBody + "! :(");
	}
	
}
