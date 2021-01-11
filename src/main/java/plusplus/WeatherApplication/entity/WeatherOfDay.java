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
    public WeatherOfDay(Date date, String place, int maxDegree, int minDegree, String symbolUrl) {
        this.date = date;
        this.place = place;
        this.maxDegree = maxDegree;
        this.minDegree = minDegree;
        this.symbolUrl = symbolUrl;
    }

    private int id;
    private Date date;
    private String place;
    private int maxDegree,minDegree;
    private String symbolUrl;
}
