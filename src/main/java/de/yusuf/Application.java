package de.yusuf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Value("${server.port}")
    int port;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .headless(false)
                .run(args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    private void applicationReadyEvent() throws IOException {
//        Desktop.getDesktop().browse(URI.create("http://localhost:" + port));
//    }
}
