package com.mine.junit.easymock;

import org.easymock.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServletTest {  
  // 测试登陆失败  
  @Test  
   public void testLoginFailed() throws Exception {  
        // 使用 EasyMock 生成 Mock 对象；  
        MockControl mc = MockControl.createControl(HttpServletRequest.class);  
        HttpServletRequest request = (HttpServletRequest)mc.getMock();  
        // 设定 Mock 对象的预期行为和输出；  
        request.getParameter("username");  
        mc.setReturnValue("admin", 1);  
        request.getParameter("password");  
        mc.setReturnValue("1234", 1);  
        // 将 Mock 对象切换到 Replay 状态；  
        mc.replay();  
        // now start test:  
        LoginServlet servlet = new LoginServlet();  
        try {  
              // 里面会调用 Mock 对象方法进行单元测试；  
            servlet.doPost(request, null);  
            fail("Not caught exception!");  
        }  
        catch(RuntimeException re) {  
            assertEquals("Login failed.", re.getMessage());  
        }  
        // 对 Mock 对象的行为进行验证。  
        mc.verify();  
    }  
  
   // 测试登陆成功  
    @Test  
    public void testLoginOK() throws Exception {  
        // 使用 EasyMock 生成 Mock 对象；  
        MockControl requestCtrl = MockControl.createControl(HttpServletRequest.class);  
        HttpServletRequest requestObj = (HttpServletRequest)requestCtrl.getMock();  
        MockControl contextCtrl = MockControl.createControl(ServletContext.class);  
        final ServletContext contextObj = (ServletContext)contextCtrl.getMock();  
        MockControl dispatcherCtrl = MockControl.createControl(RequestDispatcher.class);  
        RequestDispatcher dispatcherObj = (RequestDispatcher)dispatcherCtrl.getMock();  
       // 设定 Mock 对象的预期行为和输出；  
        requestObj.getParameter("username");  
        requestCtrl.setReturnValue("admin", 1);  
        requestObj.getParameter("password");  
        requestCtrl.setReturnValue("123456", 1);  
        contextObj.getNamedDispatcher("dispatcher");  
        contextCtrl.setReturnValue(dispatcherObj, 1);  
        dispatcherObj.forward(requestObj, null);  
        dispatcherCtrl.setVoidCallable(1);  
        // 将 Mock 对象切换到 Replay 状态；  
        requestCtrl.replay();  
        contextCtrl.replay();  
        dispatcherCtrl.replay();  
          // 里面会调用 Mock 对象方法进行单元测试；  
        //为了让getServletContext()方法返回我们创建的ServletContext Mock对象，我们定义一个匿名类并覆写getServletContext()方法：  
        LoginServlet servlet = new LoginServlet() {  
            public ServletContext getServletContext() {  
                return contextObj;  
            }  
        };  
        servlet.doPost(requestObj, null);  
         // 对 Mock 对象的行为进行验证。  
        requestCtrl.verify();  
        contextCtrl.verify();  
        dispatcherCtrl.verify();  
    }  
}  
