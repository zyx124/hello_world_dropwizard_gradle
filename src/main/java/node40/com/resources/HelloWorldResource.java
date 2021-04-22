package node40.com.resources;

import com.codahale.metrics.annotation.Timed;
import node40.com.api.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final AtomicLong counter;

    public HelloWorldResource(String template) {
        this.template = template;
        this.counter = new AtomicLong();

    }

    @GET
    @Timed
    public String sayHello() {
        final String value = template;
        return new Saying(counter.incrementAndGet(), value).getContent();
    }

    @GET
    @Path("/format")
    @Timed
    public String helloReverse(@QueryParam("reverse") Optional<Boolean> reverse) {
        final String value = template;

        if (reverse.orElse(false)) {
            StringBuilder output = new StringBuilder(template).reverse();
            return output.toString();
        }
        return value;
    }
}