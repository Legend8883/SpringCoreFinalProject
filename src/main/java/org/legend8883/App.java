package org.legend8883;

import org.legend8883.config.AppConfig;
import org.legend8883.console.ConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ConsoleListener consoleListener = context.getBean(ConsoleListener.class);
        consoleListener.start();

        context.close();
    }
}
