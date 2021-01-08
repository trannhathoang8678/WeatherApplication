package plusplus.WeatherApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherOfHour extends WeatherOfDay{
    private float windSpeed;
    private String windDirection;
    private int humidity;
}
