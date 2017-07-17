package test;

import com.fdc.test.BasicControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by chenghai on 2017/7/13.
 */
public class MockControllerTest extends BasicControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void showAnalyseCustomerList() throws Exception {
		String responsString = mockMvc.perform(get("http://localhost:8080/housemarket.customer.showanalysecustomerlist")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.param("method", "housemarket.customer.showanalysecustomerlist")
				.param("accountId","123")
				.param("adminId","596885d2c05c3313187b0a02")
		)
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andDo(MockMvcResultHandlers.log()).andReturn().getResponse().getContentAsString();

		log.debug("housemarket.customer.showanalysecustomerlist 返回的结构是 {}", responsString);
	}

}
