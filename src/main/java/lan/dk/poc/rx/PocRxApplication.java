package lan.dk.poc.rx;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rx.subjects.ReplaySubject;

import java.util.stream.IntStream;

@SpringBootApplication
public class PocRxApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocRxApplication.class, args);
	}


	//@Service
	public static class AtBoot implements CommandLineRunner{

		private final ReplaySubject<Message> messages$ = ReplaySubject.create(0);

		@Override
		public void run(String... strings) throws Exception {

			messages$
					.subscribe(
							System.out::println,
							System.out::println,
							System.out::println
					);

			IntStream
					.range(1,10)
					.mapToObj(i -> Message.builder().title("Title " + i).message("Message " + i).build())
					.forEach(messages$::onNext);

			messages$
					.subscribe(
							System.out::println,
							System.out::println,
							System.out::println
					);

		}
	}
}
