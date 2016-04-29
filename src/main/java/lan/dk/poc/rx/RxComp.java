package lan.dk.poc.rx;

import org.springframework.stereotype.Service;
import rx.subjects.BehaviorSubject;

/**
 * Created by kevin on 30/01/2016 for POC-RxJava
 */
@Service
public class RxComp {

    final BehaviorSubject<String> messageQueue;

    public RxComp() {
        this.messageQueue = BehaviorSubject.create("Foo");
    }
}
