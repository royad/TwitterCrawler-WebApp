

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchLucene
 */
@WebServlet("/SearchLucene")
public class SearchLucene extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchLucene() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchedTerm = "";
		String searchTerm = request.getParameter("searchfield");
		request.setAttribute(searchedTerm, searchTerm);
		
		System.out.println("search term is " + searchTerm);
		System.out.println("searched term is " + searchedTerm);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String searchedTerm = "";

		String searchTerm = request.getParameter("searchfield");
		request.setAttribute("searchedTerm", searchTerm);

		//System.out.println("search term is " + searchTerm);
		//System.out.println("searched term is " + request.getAttribute("searchedTerm"));
        request.getRequestDispatcher("result.jsp").forward(request, response);
		//response.sendRedirect("result.jsp");
	}

}
