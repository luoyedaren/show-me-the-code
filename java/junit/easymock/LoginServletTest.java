import javax.servlet.*;  
import javax.servlet.http.*;  
import org.easymock.*;  
  
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
