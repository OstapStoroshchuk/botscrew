package ostap.storoshchuk.botscrew.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleDean {

    private String name;

    private static SingleDean ourInstance = new SingleDean();


    public static SingleDean getInstance(String name) {
        ourInstance.name = name;
        return ourInstance;
    }

    public static SingleDean getInstance() {
        return ourInstance;
    }

    private SingleDean() {

    }
}
