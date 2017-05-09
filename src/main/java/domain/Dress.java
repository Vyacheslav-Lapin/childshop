package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dress {
    private long id;
    private Size size;
    private Color color;
}
