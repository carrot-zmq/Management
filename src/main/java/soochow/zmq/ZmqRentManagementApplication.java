package soochow.zmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("soochow.zmq.dao.mapper")
public class ZmqRentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZmqRentManagementApplication.class, args);
	}

}
