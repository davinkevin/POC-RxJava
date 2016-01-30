package lan.dk.poc.rx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PocRxApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocRxApplication.class, args);
	}

	@RestController
	public static class Controller {

		final RxComp rxComp;

		@Autowired
		public Controller(RxComp rxComp) {
			this.rxComp = rxComp;
		}

		@RequestMapping("/{message}")
		void sendMessageInQueue(@PathVariable("message") String message) {
			rxComp.messageQueue.onNext(message);
		}
	}

}
