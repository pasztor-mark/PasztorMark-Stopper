package com.example.pasztormarkstopper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class StopwatchController {
    @FXML
    private Label timeLabel;
    @FXML
    private Button kezdogomb;
    @FXML
    private ListView<String> korListView;

    private Timeline ido;
    private long kezdes;

    @FXML
    protected void onStartLapButtonClick() {
        if (ido != null && ido.getStatus() == Animation.Status.RUNNING) {

            long elapsedMillis = System.currentTimeMillis() - kezdes;
            korListView.getItems().add(formatTime(elapsedMillis));
        } else {

            kezdes = System.currentTimeMillis();
            ido = new Timeline(new KeyFrame(Duration.millis(10), event -> {
                long elapsedMillis = System.currentTimeMillis() - kezdes;
                timeLabel.setText(formatTime(elapsedMillis));
            }));
            ido.setCycleCount(Timeline.INDEFINITE);
            korListView.getItems().clear();
            ido.play();
            kezdogomb.setText("Köridő");
        }
    }

    @FXML
    protected void onEndButtonClick() {
        if (ido != null) {
            ido.stop();
            kezdogomb.setText("Start");
        }
    }

    private String formatTime(long elteltMs) {
        long ora = elteltMs / 3600000;
        long perc = (elteltMs % 3600000) / 60000;
        long masodperc = (elteltMs % 60000) / 1000;
        long ms = elteltMs % 1000;
        return String.format("%02d:%02d:%02d.%03d", ora, perc, masodperc, ms);
    }
}