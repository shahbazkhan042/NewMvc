import com.lti.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		User u = new User();
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		u.setEmail(email);
		u.setPassword(password);
		
		UserDAO userdao=new UserDAO();
		User user=userdao.authenticateUser(u);
		
		if(user!=null)
		{
			session.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/loggedIn.jsp");
			rd.forward(request,response);
		}else
		{
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp?message=failed");
			rd.forward(request,response);
		}
			
	}

}
