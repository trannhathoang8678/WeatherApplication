package plusplus.WeatherApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherOfHour  {
    private Date date;
    private int hour;
    private String place;
    private int maxDegree,minDegree;
    private String symbolUrl;
    private float windSpeed;
    private String windDirection;
    private int humidity;
}
