//spring.io/guides/gs/testing-web
package code.urmas.yritus;

import code.urmas.yritus.controller.AvalehtController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class YritusApplicationTests {


	@Autowired
	private AvalehtController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
