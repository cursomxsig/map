package mx.org.inegi.geo.map.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.org.inegi.geo.map.context.ContextAttributes;
import mx.org.inegi.geo.map.xml.loader.DataLoader;

/**
 *
 * @author yan.luevano
 */
public class ReloadData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6040530827196394910L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			ServletContext context = request.getSession().getServletContext();
			DataLoader dataLoader = new DataLoader();
			dataLoader.loadData();
			context.setAttribute(ContextAttributes.SERVER_TABLES,
					dataLoader.getServerData());
			out.print("<html><body><h1>It's Done!</h1></body></html>");
		} finally {
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
