package com.servelet.banking_controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servelet.banking_dao.CustomerDao;
import com.servelet.banking_dto.Customer;

@WebServlet("/customerlogin")
public class CustomerLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CustomerDao dao = new CustomerDao();

		int custid = Integer.parseInt(req.getParameter("custid"));
		String password = req.getParameter("password");

		Customer customer = dao.login(custid);
		if (customer == null) {
			resp.getWriter().print("<h1>Invalid Customer Id</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			if (customer.getPassword().equals(password)) {
				req.getSession().setAttribute("customer", customer);
				resp.getWriter().print("<h1>Login Success<h1>");
				req.getRequestDispatcher("CustomerHome.html").include(req, resp);

			} else {
				resp.getWriter().print("<h1>Invalid password</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}
		}

	}

}
