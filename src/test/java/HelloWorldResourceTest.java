import node40.com.resources.HelloWorldResource;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldResourceTest {
    private HelloWorldResource resource;

    @Before
    public void setup() {
        resource = new HelloWorldResource("Hello! How are you today?");
    }

    @Test
    public void  returnTemplate() {
        String result = resource.sayHello();
        assertThat(result).contains("Hello! How are you today?");
    }
}
