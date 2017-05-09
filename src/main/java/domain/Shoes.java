package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Shoes {
    private long id;
    private Size size;
    private Color color;
    private Gender gender;

}
