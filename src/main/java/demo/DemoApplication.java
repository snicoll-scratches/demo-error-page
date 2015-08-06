package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
		return container -> {
			container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
			container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
		};
	}


	@Controller
	static class FooController {

		@RequestMapping("/bam")
		public void bam(@RequestParam String message) {
			throw new IllegalStateException("Oooooops " + message);
		}

	}
}
