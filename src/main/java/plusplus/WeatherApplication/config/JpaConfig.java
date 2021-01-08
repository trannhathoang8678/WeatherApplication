package plusplus.WeatherApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class JpaConfig {
    private Connection connection;
    private DataSource ds;
    @Autowired
    private Environment env;
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
        dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
        dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
        ds = dataSourceBuilder.build();
        return ds;
    }

    @Bean
    public Connection getConnection() {
        try
        {if (connection == null) {
            return ds.getConnection();
        }}
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}
