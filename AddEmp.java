package Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addemp")
public class AddEmp extends HttpServlet{
	Connection con=null;
	@Override
	public void init() throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8","root","sql123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw=resp.getWriter();
		String name=req.getParameter("name");
		double salary=Double.parseDouble(req.getParameter("salary"));
		String birthdate=req.getParameter("birthdate");
		String joiningdate=req.getParameter("joiningdate");
		PreparedStatement pstmt=null;    
		String query="insert into emplyee values(?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, 0);
			pstmt.setString(2, name);
			pstmt.setDouble(3, salary);
			pstmt.setString(4, birthdate);
			pstmt.setString(5, joiningdate);
			int count=pstmt.executeUpdate();
			pw.print("<h1>"+count+" Record Inserted Successfully</h1>");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
