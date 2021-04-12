package MyPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Users
 */
@WebServlet("/Users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			String nationality = request.getParameter("nationality");
			out.println("<center style='margin-top:100px'>");
			out.println("<div><p>your name is " + name + ".</p></div>");
			out.println("<div><p>you are " + age + " years old.</p></div>");
			out.println("<div><p>your nationality is " + nationality + ".</p></div>");
		}
		catch(Exception e) {
			out.println("Error: " + e.getMessage());
		}
		finally {
			out.println("<br><br>");
			out.println("<a href=index.html> Back to main page </a>");
			out.println("</center>");
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
