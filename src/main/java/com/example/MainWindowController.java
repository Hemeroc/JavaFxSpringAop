package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MainWindowController {

    private final JavafxAopApplication javafxAopApplication;

    public MainWindowController(@Lazy JavafxAopApplication javafxAopApplication) {
        this.javafxAopApplication = javafxAopApplication;
    }

    @FXML
    private TextField tfName;

    @FXML
    private void initialize() {
        tfName.setText("test");
    }

    @FXML
    public void save() {
        javafxAopApplication.prooveSpringIsWorking((tfName != null) ? tfName.getText() : "-- tfName was null --");
    }
}
