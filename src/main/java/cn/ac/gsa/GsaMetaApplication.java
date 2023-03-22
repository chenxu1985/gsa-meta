package cn.ac.gsa;

import cn.ac.gsa.utility.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@MapperScan("cn.ac.gsa.mapper")
@EnableScheduling
public abstract class GsaMetaApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GsaMetaApplication.class);
    }

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(GsaMetaApplication.class, args);
        SpringContextUtil.setApplicationContext(app);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }
}
