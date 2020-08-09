package soochow.zmq.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class UserTest {

    @Test
    public void testGenerate() throws IOException {
        User user = new User();
        new ObjectMapper().writeValue(System.out, user);
    }
}
