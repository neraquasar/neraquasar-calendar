package org.neraquasar.calendar.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author Konstantin Valer'evich Dichenko
 * Created 15.08.2017
 */
@Configuration
@ComponentScan("org.neraquasar.calendar")
@Import(
        {
                ThymeleafConfig.class
        })
public class DefaultWebAppInitializer implements WebApplicationInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWebAppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(DefaultWebAppInitializer.class);
        servletContext.addListener(new ContextLoaderListener(webAppContext));

        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        security.addMappingForUrlPatterns(null, false, "/*");

        webAppContext.setServletContext(servletContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("spring-mvc", new DispatcherServlet(webAppContext));
        servlet.addMapping("/webapp/templates/*");
        servlet.setLoadOnStartup(1);
    }
}
