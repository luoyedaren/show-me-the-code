package com.mine.junit;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {  
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        String username = request.getParameter("username");  
        String password = request.getParameter("password");  
        // check username & password:  
        if("admin".equals(username) && "123456".equals(password)) {  
            ServletContext context = getServletContext();  
            RequestDispatcher dispatcher = context.getNamedDispatcher("dispatcher");  
            dispatcher.forward(request, response);  
        }  
        else {  
            throw new RuntimeException("Login failed.");  
        }  
    }  
  
}  
