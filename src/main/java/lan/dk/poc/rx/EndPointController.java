package lan.dk.poc.rx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Created by kevin on 29/04/2016 for POC-RxJava
 */
@Slf4j
@RestController
public class EndPointController {

    private final BehaviorSubject<Message> messages$ = BehaviorSubject.create();

    @RequestMapping("start")
    public void start() {
        IntStream
                .range(1,10)
                .mapToObj(i -> Message.builder().title("Title " + i).message("Message " + i).build())
                .forEach(messages$::onNext);
        messages$.onNext(null);
    }

    @RequestMapping("sse")
    public SseEmitter sse() {
        UUID uuid = UUID.randomUUID();
        final SseEmitter emitter = new SseEmitter(1000L*60*60);

        log.info("Registering to SSE {}", uuid);

        Action1<Message> publisher = publish(emitter);

        Subscription subscribe = messages$
                .subscribe(
                        publisher,
                        emitter::completeWithError,
                        emitter::complete
                );

        emitter.onTimeout(subscribe::unsubscribe);
        emitter.onCompletion(() -> {
            log.info("Completion of {}", uuid);
            subscribe.unsubscribe();
        });

        return emitter;
    }

    private <U> Action1<U> publish(SseEmitter sseEmitter) {
        return v -> { try {
            log.info("Send data to a request {}", v);
            sseEmitter.send(v, MediaType.APPLICATION_JSON);
        } catch (IOException ignored) {} };
    }

}
