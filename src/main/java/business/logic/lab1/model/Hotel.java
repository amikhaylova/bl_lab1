package business.logic.lab1.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Hotel implements Serializable {
    private long id;
    private String name;
    private String price;

}
