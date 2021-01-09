package plusplus.WeatherApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plusplus.WeatherApplication.config.JpaConfig;
import plusplus.WeatherApplication.entity.WeatherOfDay;
import plusplus.WeatherApplication.entity.WeatherOfHour;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class WeatherInfo {
    @Autowired
    JpaConfig jpaConfig;

    public void addWeatherOfDay(Date date, String place, int maxDegree, int minDegree, String symbolUrl) {
        if (!verifyWeatherOfDay(date, place)) return;
        String sql = "INSERT INTO WEATHER_DAY (date,place,max_degree,min_degree,symbol_url) VALUE ('" + date + "','" +
                place + "'," + maxDegree + "," + minDegree + ",'" + symbolUrl + "');";
       // System.out.println(sql);
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add weather of day succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add weather of day failed");
        }
    }

    public boolean verifyWeatherOfDay(Date date, String place) {
        String sql = "SELECT 'id' FROM WEATHER_DAY WHERE date ='" + date + "' AND place='" + place + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getIDHaveSameName = statement.executeQuery(sql);
            if (getIDHaveSameName.next()) {
                System.out.println("This weather of day has already existed");
                return false;
            } else {
                System.out.println("Verify weather of day successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify weather of day failed");
            return false;
        }
    }

    public void updateWeatherOfDay(int id,Date date, String place, int maxDegree, int minDegree, String symbolUrl) {
        if (!isWeatherOfDayIDexist(id)) {
            return;
        }
        String sql = "UPDATE WEATHER_DAY SET id = " + id;
        if (date != null)
            sql += " ,date='" + date + "'";
        if (place != null)
            sql += " ,place='" + place + "'";
        if (maxDegree != -1)
            sql += " ,max_degree='" + maxDegree + "'";
        if (minDegree != -1)
            sql += " ,min_degree='" + minDegree + "'";
        if (symbolUrl != null)
            sql += " ,symbol_url='" + symbolUrl + "'";
        sql += " WHERE id =" + id + ";";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update weather of day succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update weather of day failed");
        }
    }

    public void deleteWeatherOfDay(int id) {
        if (!isWeatherOfDayIDexist(id)) {
            return;
        }
        String sql = "DELETE FROM WEATHER_DAY WHERE id = " + id + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete weather of day succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete weather of day failed");
        }
    }

    public boolean isWeatherOfDayIDexist(int id) {
        String sql = "SELECT 'id' FROM WEATHER_DAY WHERE id ='" + id + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This weatherOfDayID exist");
                return true;
            } else {
                System.out.println("This weatherOfDayID is not existed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check whether weatherID exist failed");
            return false;
        }
    }
    public void addWeatherOfHour(Float windSpeed, String windDirection, String symbolUrl, int humidity) {
        if (!verifyWeatherOfHour(windSpeed, windDirection,symbolUrl,humidity)) return;
        String sql = "INSERT INTO WEATHER_HOUR (wind_speed,wind_direction,symbol_url,humidity) VALUE (" + windSpeed + ",'" +
                 windDirection + "','" + symbolUrl + "','" + humidity + "');";
       // System.out.println(sql);
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add weather of hour succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add weather of hour failed");
        }
    }

    public boolean verifyWeatherOfHour(Float windSpeed, String windDirection, String symbolUrl, int humidity) {
        String sql = "SELECT 'id' FROM WEATHER_HOUR WHERE wind_speed ='" + windSpeed + "' AND wind_direction='" + windDirection + "' AND symbol_url='" + symbolUrl +
                "' AND humidity='" + humidity + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getIDHaveSameName = statement.executeQuery(sql);
            if (getIDHaveSameName.next()) {
                System.out.println("This weather of hour has already existed");
                return false;
            } else {
                System.out.println("Verify weather of hour successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify weather of hour failed");
            return false;
        }
    }

    public void updateWeatherOfHour(int id,Float windSpeed, String windDirection, String symbolUrl, int humidity) {
        if (!isWeatherOfHourIDexist(id)) {
            return;
        }
        String sql = "UPDATE WEATHER_HOUR SET id = " + id;
        if (windSpeed != -1)
            sql += " ,wind_speed='" + windSpeed + "'";
        if (windDirection != null)
            sql += " ,wind_diretion='" + windDirection + "'";
        if (humidity != -1)
            sql += " ,humidity='" + humidity + "'";
        if (symbolUrl != null)
            sql += " ,symbol_url='" + symbolUrl + "'";
        sql += " WHERE id =" + id + ";";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update weather of hour succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update weather of hour failed");
        }
    }

    public void deleteWeatherOfHour(int id) {
        if (!isWeatherOfHourIDexist(id)) {
            return;
        }
        String sql = "DELETE FROM WEATHER_HOUR WHERE id = " + id + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete weather of hour succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete weather of hour failed");
        }
    }

    public boolean isWeatherOfHourIDexist(int id) {
        String sql = "SELECT 'id' FROM WEATHER_HOUR WHERE id ='" + id + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This weatherOfHourID exist");
                return true;
            } else {
                System.out.println("This weatherOfHourID is not existed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check whether weatherID exist failed");
            return false;
        }
    }
    public void addDayHourRelationship(int dayID,int hourID,int time) {
        if (!verifyDayHourRelationship(dayID,hourID,time)) return;
        String sql = "INSERT INTO `DAY_HOUR` VALUE (" + dayID + "," + hourID + "," + time +");";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add relationship succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add relationship failed");
        }
    }

    public boolean verifyDayHourRelationship(int dayID,int hourID,int time) {
        String sql = "SELECT 'DAY_ID' FROM `DAY_HOUR` WHERE DAY_ID =" + dayID + " AND HOUR_ID = " + hourID +" AND time =" + time + " ;";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getIDHaveSameName = statement.executeQuery(sql);
            if (getIDHaveSameName.next()) {
                System.out.println("This relationship has already existed");
                return false;
            } else {
                System.out.println("Verify relationship successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify relationship failed");
            return false;
        }
    }


    public void deleteDayHourRelationship(int dayID,int hourID,int time) {
        if (!isRelationshipIDexist(dayID,hourID,time)) {
            return;
        }
        String sql = "DELETE FROM `DAY_HOUR` WHERE DAY_id = " + dayID + " AND HOUR_id =" + hourID +" AND time =" + time + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete relationship succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete relationship failed");
        }
    }

    public boolean isRelationshipIDexist(int dayID,int hourID,int time) {
        String sql = "SELECT 'id' FROM `DAY_HOUR` WHERE DAY_id = " + dayID + " AND HOUR_id =" + hourID +" AND time =" + time + " ;";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This relationshipID exist");
                return true;
            } else {
                System.out.println("This relationshipID is not existed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check is relationshipID exist failed");
            return false;
        }
    }
    public List<WeatherOfDay> getWeatherOfDays(String place) {
        List<WeatherOfDay> weatherOfDays = new LinkedList<>();
        WeatherOfDay weatherOfDay;
        Date fromDate = new Date(new java.util.Date().getTime());
        Date nextWeek = new Date(System.currentTimeMillis()+7*24*60*60*1000);
        String sql = "SELECT * FROM WEATHER_DAY WHERE date >='" + fromDate + "' AND date <= '" + nextWeek + "' AND place = '" + place +"' ;";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getWeather = statement.executeQuery(sql);
            while (getWeather.next())
            {
                weatherOfDay = new WeatherOfDay(getWeather.getInt(1),getWeather.getDate(2),getWeather.getString(3),getWeather.getInt(4),
                        getWeather.getInt(5),getWeather.getString(6));
                weatherOfDays.add(weatherOfDay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return  weatherOfDays;
        }
    }
    public WeatherOfDay getWeatherOfDay(String place) {

        WeatherOfDay weatherOfDay=null;
        Date currentDate = new Date(new java.util.Date().getTime());
        String sql = "SELECT * FROM WEATHER_DAY WHERE `date` = '" + currentDate + "' AND place = '" + place +"' ;";
        //System.out.println(sql);
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getWeather = statement.executeQuery(sql);
            if (getWeather.next())
            {
                weatherOfDay = new WeatherOfDay(getWeather.getInt(1),getWeather.getDate(2),getWeather.getString(3),getWeather.getInt(4),
                        getWeather.getInt(5),getWeather.getString(6));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return  weatherOfDay;
        }
    }
    public List<WeatherOfHour> getWeatherForecastHours( String place) {
        List<WeatherOfHour> weatherOfHours = new LinkedList<>();
        WeatherOfHour weatherOfHour;
        LocalDateTime now = LocalDateTime.now();
        Date currentDate = new Date(new java.util.Date().getTime());
        Date tomorrow = new Date(System.currentTimeMillis()+24*60*60*1000);
        int currenthour = now.getHour();
        String sql = "SELECT date,time,place,max_degree,min_degree,h.symbol_url,wind_speed,wind_direction,humidity" +
                " FROM WEATHER_DAY d JOIN `DAY_HOUR` dh ON d.id = dh.DAY_id JOIN WEATHER_HOUR h ON " +
                " dh.HOUR_id = h.id WHERE (date ='" + currentDate + "' AND time >= " + currenthour + ") OR (date = '"
                + tomorrow +"' AND time <=" + currenthour + ");";
   //     System.out.println(sql);
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getWeather = statement.executeQuery(sql);
            while (getWeather.next())
            {
                weatherOfHour = new WeatherOfHour(getWeather.getDate(1),getWeather.getInt(2),getWeather.getString(3),getWeather.getInt(4)
                ,getWeather.getInt(5),getWeather.getString(6),getWeather.getFloat(7),getWeather.getString(8),getWeather.getInt(9));
                weatherOfHours.add(weatherOfHour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return  weatherOfHours;
        }
    }
}
