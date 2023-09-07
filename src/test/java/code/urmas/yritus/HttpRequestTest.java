package code.urmas.yritus;

import org.assertj.core.api.AbstractBooleanArrayAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception{
        //assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("index.html");
        //assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
         //       String.class)).contains("index.html");
    }


}
