package plusplus.WeatherApplication.controller;

import org.aspectj.asm.internal.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plusplus.WeatherApplication.entity.DayHourRelationship;
import plusplus.WeatherApplication.entity.Display;
import plusplus.WeatherApplication.entity.WeatherOfDay;
import plusplus.WeatherApplication.entity.WeatherOfHour;
import plusplus.WeatherApplication.service.WeatherInfo;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    WeatherInfo weatherInfo;

    @PostMapping
    public void addWeatherOfDay(@RequestBody WeatherOfDay weatherOfDay) {
        weatherInfo.addWeatherOfDay(weatherOfDay.getDate(), weatherOfDay.getPlace(), weatherOfDay.getMaxDegree()
                , weatherOfDay.getMinDegree(), weatherOfDay.getSymbolUrl());
    }

    @PutMapping
    public void updateWeatherOfDay(@RequestBody WeatherOfDay weatherOfDay) {
        weatherInfo.updateWeatherOfDay(weatherOfDay.getId(), weatherOfDay.getDate(), weatherOfDay.getPlace()
                , weatherOfDay.getMaxDegree(), weatherOfDay.getMinDegree(), weatherOfDay.getSymbolUrl());
    }

    @DeleteMapping(value = "/{id}")
    public void deleteWeatherOfDay(@PathVariable int id) {
        weatherInfo.deleteWeatherOfDay(id);
    }

    @PostMapping(value = "/hour")
    public void addWeatherOfHour(@RequestBody WeatherOfHour weatherOfHour) {
        weatherInfo.addWeatherOfHour(weatherOfHour.getWindSpeed(), weatherOfHour.getWindDirection()
                , weatherOfHour.getSymbolUrl(), weatherOfHour.getHumidity());
    }

    @PutMapping(value = "/hour")
    public void updateWeatherOfHour(@RequestBody WeatherOfHour weatherOfHour) {
        weatherInfo.updateWeatherOfHour(weatherOfHour.getId(), weatherOfHour.getWindSpeed(), weatherOfHour.getWindDirection()
                , weatherOfHour.getSymbolUrl(), weatherOfHour.getHumidity());
    }

    @DeleteMapping(value = "/hour/{id}")
    public void deleteWeatherOfHour(@PathVariable int id) {
        weatherInfo.deleteWeatherOfHour(id);
    }

    @PostMapping(value = "/relationship")
    public void addDayHourRelationship(@RequestBody DayHourRelationship relationship) {
        weatherInfo.addDayHourRelationship(relationship.getDayID(),relationship.getHourID(), relationship.getTime());
    }

    @DeleteMapping(value = "/relationship")
    public void deleteDayHourRelationship(@RequestBody DayHourRelationship relationship) {
        weatherInfo.deleteDayHourRelationship(relationship.getDayID(),relationship.getHourID(), relationship.getTime());
    }
}
