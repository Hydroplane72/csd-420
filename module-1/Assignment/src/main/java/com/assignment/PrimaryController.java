package com.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController {

    @FXML
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private ImageView card4;
    @FXML
    private Button refreshButton;

    private Random random = new Random();
    private List<Integer> selectedCards = new ArrayList<>();

    @FXML
    public void initialize() {
        refreshButton.setOnAction(event -> displayRandomCards());
        displayRandomCards();
    }

    private void displayRandomCards() {
        selectedCards.clear();
        List<Integer> allCards = new ArrayList<>();

        for (int i = 1; i <= 52; i++) {
            allCards.add(i);
        }

        Collections.shuffle(allCards, random);
        selectedCards = allCards.subList(0, 4);

        ImageView[] imageViews = {card1, card2, card3, card4};
        for (int i = 0; i < 4; i++) {
            int cardNumber = selectedCards.get(i);
            String imagePath = "/com/assignment/cards/" + cardNumber + ".png";
            try {
                Image image = new Image(PrimaryController.class.getResource(imagePath).toExternalForm());
                imageViews[i].setImage(image);
            } catch (Exception e) {
                System.out.println("Error loading card: " + cardNumber + " - " + e.getMessage());
            }
        }
    }
}
