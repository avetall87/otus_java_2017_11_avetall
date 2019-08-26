import domain.AddressDataSet;
import domain.DataSet;
import domain.PhoneDataSet;
import domain.UserDataSet;
import org.hibernate.cfg.Configuration;
import service.impl.util.HibernateSessionFactory;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        initHibernate();
        System.out.println(Arrays.toString(HibernateSessionFactory.getSessionFactory().getStatistics().getEntityNames()));
        System.out.println("Finished !!!");
    }

    private static void initHibernate() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(DataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "docker");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        HibernateSessionFactory.init(configuration);
    }
}
