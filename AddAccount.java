package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addaccounts")
public class AddAccount  extends HttpServlet{

	Connection con;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/bank","root", "sql@123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String accno=req.getParameter("accno");
		String name=req.getParameter("holderName");
		String balance=req.getParameter("Balance");
		
		//parsing
		long accno1=Long.parseLong(accno);
		double balance1=Double.parseDouble(balance);
		
		PreparedStatement pstmt=null;
		
		String query="insert into bank_details1 values(?,?,?)";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setLong(1, accno1);
			pstmt.setString(2, name);
			pstmt.setDouble(3, balance1);		
			int count=pstmt.executeUpdate();
			
			PrintWriter pw=resp.getWriter();
			RequestDispatcher rd=req.getRequestDispatcher("/index.html");
			pw.print("<h1>"+count+"Record Inserted Successfully");
			rd.include(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
			
}
