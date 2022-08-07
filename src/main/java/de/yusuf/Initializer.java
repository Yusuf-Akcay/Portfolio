package de.yusuf;

import com.sun.faces.config.ConfigureListener;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import java.util.EnumSet;

@Configuration
public class Initializer
    implements ServletContextInitializer, WebMvcConfigurer {

  @Override
  public void onStartup( ServletContext servletContext ) {
    servletContext.setInitParameter( "com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString() );
    servletContext.setInitParameter( "primefaces.FONT_AWESOME", Boolean.TRUE.toString() );
    servletContext.setInitParameter( "primefaces.UPLOADER", "commons" );
    servletContext.setInitParameter( "primefaces.THEME", "saga" );
  }

  @Bean
  ServletRegistrationBean<FacesServlet> jsfServletRegistration() {
    ServletRegistrationBean<FacesServlet> srb = new ServletRegistrationBean<>( new FacesServlet(), "*.xhtml" );
    srb.setLoadOnStartup( 1 );
    return srb;
  }

  @Bean
  public FilterRegistrationBean<Filter> fileUploadFilter() {
    FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
    registration.setName( "PrimeFaces FileUpload Filter" );
    registration.setFilter( new FileUploadFilter() );
    registration.setDispatcherTypes( EnumSet.allOf( DispatcherType.class) );
    registration.setEnabled(true);
    registration.addUrlPatterns("/*");
    registration.setOrder(1);
    return registration;
  }

  @Override
  public void addViewControllers( ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName( "index.xhtml" );
    registry.setOrder( Ordered.HIGHEST_PRECEDENCE);
  }

  @Bean
  public ErrorPageRegistrar errorPageRegistrar() {
    return registry -> registry.addErrorPages(
        new ErrorPage( HttpStatus.FORBIDDEN, "/error/403.xhtml" ),
        new ErrorPage( HttpStatus.NOT_FOUND, "/error/404.xhtml" ),
        new ErrorPage( HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.xhtml" ) );
  }
}