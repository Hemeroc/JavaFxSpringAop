package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavafxAopApplication extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavafxAopApplication.class);

    @Autowired
    private ApplicationContext applicationContext;
    private MainWindowController mwc;

    public static void main(String[] args) {
        Application.launch(JavafxAopApplication.class, args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        ConfigurableApplicationContext run = new SpringApplication(SBA.class)
                .run(super.getParameters().getRaw().toArray(new String[0]));
        run.getAutowireCapableBeanFactory().autowireBean(this);
        ((AnnotationConfigApplicationContext) run).getBeanFactory().registerSingleton(
                this.getClass().getCanonicalName(), this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Parent parent = fxmlLoader.load(getClass().getClassLoader().getResourceAsStream("mainWindow.fxml"));
        mwc = fxmlLoader.getController();
        primaryStage.setScene(new Scene(parent));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void prooveSpringIsWorking(String value) {
        LOGGER.info("Spring is working: {}", value);
    }
}


