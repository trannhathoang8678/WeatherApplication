package plusplus.WeatherApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plusplus.WeatherApplication.entity.Display;
import plusplus.WeatherApplication.entity.WeatherOfDay;
import plusplus.WeatherApplication.entity.WeatherOfHour;
import plusplus.WeatherApplication.service.UserInfo;
import plusplus.WeatherApplication.service.WeatherInfo;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserInfo userInfo;
    @Autowired
    WeatherInfo weatherInfo;

    @GetMapping(value = "/{id}")
    public List<WeatherOfDay> showWeatherOfDaysRegistered(@PathVariable int id) {
        List<String> places = userInfo.getPlaces(id);
        List<WeatherOfDay> weatherOfDays= new LinkedList<>();
        for(String place:places)
        weatherOfDays.addAll(weatherInfo.getWeatherOfDays(place));
        return weatherOfDays;
    }

    @GetMapping
    public List<WeatherOfHour> showWeatherOfHour(@RequestParam String place) {
        return weatherInfo.getWeatherForecastHours(place);
    }

    @PostMapping
    public void addDisplay(@RequestBody Display display) {
        userInfo.addDisplay(display.getUserID(), display.getPlace(), display.getRank());
    }

    @PutMapping
    public void updateDisplay(@RequestBody Display display) {
        userInfo.updateDisplay(display.getUserID(), display.getPlace(), display.getRank());
    }

    @DeleteMapping
    public void deleteDisplay(@RequestBody Display display) {
        userInfo.deleteDisplay(display.getUserID(), display.getPlace());
    }
    @GetMapping(value="/login/{phonenumber}")
    public String login(@PathVariable String phonenumber)
    {
        if(!userInfo.verifyUser(phonenumber))
            return "Login success";
        else return "Login failed";
    }
    @PostMapping(value="/register/{phonenumber}")
    public void register(@PathVariable String phonenumber)
    {
        userInfo.addUser(phonenumber);
    }
}
