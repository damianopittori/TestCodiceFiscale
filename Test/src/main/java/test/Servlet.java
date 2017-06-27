package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

public class Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> listaComuni = creaListaComuni();
		request.setAttribute("listaComuni", listaComuni);
		RequestDispatcher requestDispatcherObj = request.getRequestDispatcher("/prima.jsp");
		requestDispatcherObj.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcherObj = req.getRequestDispatcher("/seconda.jsp");
		requestDispatcherObj.forward(req, resp);

	}

	private List<String> creaListaComuni() {

		List<String> listaComuni = new ArrayList<String>();

		Connection c;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost/test?useSSL=false", "root", "root");
			Statement s = (Statement) c.createStatement();
			ResultSet comuni = s.executeQuery("SELECT Comune FROM comuni ");

			while (comuni.next()) {
				String a = comuni.getString("Comune");
				listaComuni.add(a);
			}

		} catch (ClassNotFoundException ee) {
			ee.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaComuni;
	}
}
