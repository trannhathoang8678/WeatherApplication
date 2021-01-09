package plusplus.WeatherApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import plusplus.WeatherApplication.entity.Display;
import plusplus.WeatherApplication.entity.WeatherOfDay;
import plusplus.WeatherApplication.entity.WeatherOfHour;
import plusplus.WeatherApplication.service.UserInfo;
import plusplus.WeatherApplication.service.WeatherInfo;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserInfo userInfo;
    @Autowired
    WeatherInfo weatherInfo;

    @GetMapping(value = "/{id}")
    public String showWeatherOfDaysRegistered(@PathVariable int id,Model model) {
        List<String> places = userInfo.getPlacesOfUser(id);
        List<WeatherOfDay> weatherOfDays = new LinkedList<>();
        for (String place : places)
            weatherOfDays.addAll(weatherInfo.getWeatherOfDays(place));
        model.addAttribute("weatherOfDays",weatherOfDays);
        return "homePage";
    }

    @GetMapping(value ={"/homePage"})
    public String showWeatherOfDays(Model model) {
        List<String> places = userInfo.getAllPlaces();
     //   System.out.println(places.size());
        List<WeatherOfDay> weatherOfDays = new LinkedList<>();
        for (String place : places)
            weatherOfDays.add(weatherInfo.getWeatherOfDay(place));
        model.addAttribute("weatherOfDays",weatherOfDays);
        return "homePage";
    }

    @GetMapping
    public String showWeatherOfHour(@RequestParam String place,Model model) {
        model.addAttribute("weatherOfHours",weatherInfo.getWeatherForecastHours(place));
        model.addAttribute("weatherOfDays",weatherInfo.getWeatherOfDays(place));
        model.addAttribute("place",place);
        return "weatherInDetailed";
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

    @GetMapping(value = "/login/{phonenumber}")
    public String login(@PathVariable String phonenumber) {
        if (!userInfo.verifyUser(phonenumber))
            return "Login success";
        else return "Login failed";
    }

    @PostMapping(value = "/register/{phonenumber}")
    public void register(@PathVariable String phonenumber) {
        userInfo.addUser(phonenumber);
    }
}
