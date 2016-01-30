package lan.dk.poc.rx;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by kevin on 30/01/2016 for POC-RxJava
 */
@Slf4j
@Service
public class FirstComp {

    @Autowired
    public FirstComp(RxComp rxComp) {
        rxComp.messageQueue
                .observeOn(Schedulers.newThread())
                .subscribe(i -> {
                    log.info("FirstComp Receiving");
                    try {
                        TimeUnit.SECONDS.sleep(10L);
                        log.info("FirstComp : " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
}
