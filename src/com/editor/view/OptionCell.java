package com.editor.view;

import com.editor.services.Constants;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.InputStream;

/**
 * Created by y3010 on 2016/10/23.
 */
public class OptionCell extends ListCell<String> {

    private HBox hbox;
    private Label label;
    private ImageView imgView;

    public OptionCell() {
        super();
        hbox = new HBox();
        label = new Label();
        imgView = new ImageView();
        label.setFont(new Font("Arial", 16));
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(imgView, label);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            setGraphic(null);
        } else {
            if (!item.equals("Options")) {
                label.setText(item );
                InputStream imgPath = Constants.IMAGE_MAP.get(item);
                imgView.setImage(new Image(imgPath));
            } else {
                label.setText("Options");
            }
            setGraphic(hbox);
        }
    }
}
