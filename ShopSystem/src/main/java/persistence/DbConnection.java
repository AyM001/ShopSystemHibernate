package persistence;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

// Conexiunea am facut-o cu ajutorul unui singleton pentru a restrictiona accesul la baza de date
public class DbConnection {

    private SessionFactory sessionFactory;

    private static DbConnection dbConnection;

    private DbConnection() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL,
                "jdbc:mysql://localhost:3306/shopsystem?serverTimezone=UTC");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "pineaple001");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS , true);
        configuration.addAnnotatedClass(CategoryModel.class);
        configuration.addAnnotatedClass(StockModel.class);
        configuration.addAnnotatedClass(ClientModel.class);
        configuration.setProperties(properties);
        sessionFactory = configuration.buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    // Singleton
    public static DbConnection getInstance(){
        if (dbConnection == null) {
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }
}
