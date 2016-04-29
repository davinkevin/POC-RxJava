package lan.dk.poc.rx;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by kevin on 29/04/2016 for POC-RxJava
 */
@Data
@ToString
@Builder
@Getter
public class Message {

    private final String title;
    private final String message;

    public Message(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
