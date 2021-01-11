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
    private int id;
    private Date date;
    private int hour;
    private String place;
    private int maxDegree,minDegree;
    private String symbolUrl;
    private float windSpeed;
    private String windDirection;
    private int humidity;
    public WeatherOfHour(Date date, int hour, String place, int maxDegree, int minDegree, String symbolUrl, float windSpeed, String windDirection, int humidity) {
        this.date = date;
        this.hour = hour;
        this.place = place;
        this.maxDegree = maxDegree;
        this.minDegree = minDegree;
        this.symbolUrl = symbolUrl;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
    }

    public WeatherOfHour(int id, float windSpeed, String windDirection, String symbolUrl, int humidity) {
        this.id = id;
        this.symbolUrl = symbolUrl;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
    }

    public WeatherOfHour(float windSpeed, String windDirection, String symbolUrl, int humidity) {
        this.symbolUrl = symbolUrl;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
    }


}
