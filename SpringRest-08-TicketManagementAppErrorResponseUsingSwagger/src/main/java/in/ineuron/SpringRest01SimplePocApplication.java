package in.ineuron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringRest01SimplePocApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringRest01SimplePocApplication.class, args);
		
		System.out.println(applicationContext.getClass().getName());
	}

}
