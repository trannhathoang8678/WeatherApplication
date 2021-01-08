package plusplus.WeatherApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plusplus.WeatherApplication.config.JpaConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class UserInfo {
    @Autowired
    JpaConfig jpaConfig;
    public void addUser(String phonenumber) {
        if (!verifyUser(phonenumber)) return;
        String sql = "INSERT INTO USER (phonenumber) VALUE ('" + phonenumber + "');";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add user succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add user failed");
        }
    }

    public boolean verifyUser(String phonenumber) {
        String sql = "SELECT 'id' FROM USER WHERE phonenumber ='" + phonenumber + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getIDHaveSameName = statement.executeQuery(sql);
            if (getIDHaveSameName.next()) {
                System.out.println("This user has already existed");
                return false;
            } else {
                System.out.println("Verify user successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify user failed");
            return false;
        }
    }

    public void updateUser(int id, String phonenumber) {
        if (!isUserIDexist(id)) {
            return;
        }
        String sql = "UPDATE USER SET phonenumber = '" + phonenumber + "' WHERE id = " + id + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update user succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update user failed");
        }
    }

    public void deleteUser(int id) {
        if (!isUserIDexist(id)) {
            return;
        }
        String sql = "DELETE FROM USER WHERE id = " + id + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete user succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete user failed");
        }
    }

    public boolean isUserIDexist(int id) {
        String sql = "SELECT 'id' FROM USER WHERE id ='" + id + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This userID exist");
                return true;
            } else {
                System.out.println("This userID is not existed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check is userID exist failed");
            return false;
        }
    }
    public void addDisplay(int userID, int weatherID, int rank) {
        if (!verifyDisplay(userID, weatherID)) return;
        String sql = "INSERT INTO DISPLAY VALUE (" + userID + "," + weatherID + "," + rank + ");";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add display succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add display failed");
        }
    }

    public boolean verifyDisplay(int userID, int weatherID) {
        String sql = "SELECT USER_ID FROM DISPLAY WHERE USER_ID =" + userID + " AND WEATHEAR_id=" + weatherID + ";";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getSameDisplay = statement.executeQuery(sql);
            if (getSameDisplay.next()) {
                System.out.println("This display has already existed");
                return false;
            } else {
                System.out.println("Verify display successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify display failed");
            return false;
        }
    }

    public void updateDisplay(int userID, int weatherID, int rank) {
        //time by year
        if (!isDisplayExist(userID, weatherID)) {
            return;
        }
        String sql = "UPDATE DISPLAY SET WEATHEAR_id = " + weatherID;

        if (rank != -1)
            sql += " ,rank='" + rank + "'";
        sql += " WHERE USER_ID =" + userID + " AND WEATHEAR_id =" + weatherID + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update display succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update display failed");
        }
    }

    public void deleteDisplay(int peopleID, int userID) {
        if (!isDisplayExist(peopleID, userID)) {
            return;
        }
        String sql = "DELETE FROM DISPLAY WHERE WEATHEAR_id =" + peopleID + " AND USER_id =" + userID + ";";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete display succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete display failed");
        }
    }

    public boolean isDisplayExist(int userID, int weatherID) {
        String sql = "SELECT 'id' FROM DISPLAY WHERE USER_ID =" + userID + " AND WEATHEAR_id =" + weatherID + ";";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This display ID exist");
                return true;
            } else {
                System.out.println("This display ID is not existed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check is display ID exist failed");
            return false;
        }
    }
}