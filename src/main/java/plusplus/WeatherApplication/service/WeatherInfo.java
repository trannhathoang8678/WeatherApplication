package plusplus.WeatherApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plusplus.WeatherApplication.config.JpaConfig;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class WeatherInfo {
    @Autowired
    JpaConfig jpaConfig;

    public void addWeatherOfDay(Date date, String place, int maxDegree, int minDegree, String symbolUrl) {
        if (!verifyWeatherOfDay(date, place)) return;
        String sql = "INSERT INTO WEATHER_DAY (date,place,max_degree,min_degree,symnolUrl) VALUE ('" + date + "','" +
                place + "'," + maxDegree + "," + minDegree + ",'" + symbolUrl + "');";
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
        String sql = "INSERT INTO WEATHER_HOUR (wind_speed,wind_direction,symbol_url,humidity) VALUE ('" + windSpeed + "','" +
                windDirection + "','" + windDirection + "','" + symbolUrl + "','" + humidity + "');";
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
        String sql = "SELECT 'id' FROM WEATHER_HOUR WHERE wind_speed ='" + windSpeed + "' AND wind_diretion='" + windDirection + "' AND symbol_url='" + symbolUrl +
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
            sql += " ,windspeed='" + windSpeed + "'";
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
    public void addDayHourRelationship(String name) {
        if (!verifyDayHourRelationship(name)) return;
        String sql = "INSERT INTO DAY_HOUR (name) VALUE ('" + name + "');";
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

    public boolean verifyDayHourRelationship(String name) {
        String sql = "SELECT 'id' FROM DAY_HOUR WHERE name ='" + name + "';";
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

    public void updateDayHourRelationship(int id, String name) {
        if (!isRelationshipIDexist(id)) {
            return;
        }
        String sql = "UPDATE DAY_HOUR SET name = '" + name + "' WHERE id = " + id + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update relationship succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update relationship failed");
        }
    }

    public void deleteDayHourRelationship(int id) {
        if (!isRelationshipIDexist(id)) {
            return;
        }
        String sql = "DELETE FROM DAY_HOUR WHERE id = " + id + " ;";
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

    public boolean isRelationshipIDexist(int id) {
        String sql = "SELECT 'id' FROM DAY_HOUR WHERE id ='" + id + "';";
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
}
