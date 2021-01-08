package plusplus.WeatherApplication.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherOfDay {
    private int id;
    private Date date;
    private String place;
    private int maxDegree,minDegree;
    private String symbolUrl;
}
