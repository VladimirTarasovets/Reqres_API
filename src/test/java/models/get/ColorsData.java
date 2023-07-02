package models.get;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ColorsData {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;
}
