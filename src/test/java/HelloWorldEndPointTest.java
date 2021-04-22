import io.dropwizard.testing.junit.ResourceTestRule;
import node40.com.resources.HelloWorldResource;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldEndPointTest {
    @Rule
    public final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new HelloWorldResource("Hello! How are you today?"))
            .build();

    @Test
    public void helloWorldTest() throws IOException {
        // Hit the endpoint and get the raw json string
        String resp = resources.client().target("/hello-world")
                .queryParam("name", "dropwizard")
                .request().get(String.class);
        String expected = "Hello! How are you today?";
        assertThat(resp).isEqualTo(expected);
    }

    @Test
    public void formatTest() {
        String actual = resources.client().target("/hello-world/format")
                .queryParam("reverse", true).request().get(String.class);
        String expected = "?yadot uoy era woH !olleH";
        assertThat(actual).isEqualTo(expected);
    }

}
