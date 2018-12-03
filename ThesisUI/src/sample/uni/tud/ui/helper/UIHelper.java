package sample.uni.tud.ui.helper;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class UIHelper {

    private TextField recieverMessageBox = null;
    private TextField senderMessageBox = null;
    private TextField senderInbox = null;
    private Label sendMsgLabel = null;
    private Label msgCounterLabel = null;
    private Label dummyLabel = new Label("--");

    public VBox createUI() {
        dummyLabel.setPrefWidth(100);
        VBox mainFrame = new VBox(5);
        mainFrame.setPadding(new Insets(25, 10, 0, 10));
        mainFrame.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        mainFrame.setPrefWidth(1600);
        mainFrame.setPrefHeight(900);
        HBox participantBox = new HBox();
        Label msgLabel = new Label("MESSAGE");
        msgLabel.setMinWidth(200);
        Label serverLabel = new Label("SERVER");
        serverLabel.setMinWidth(200);
        Label attackerLabel = new Label("ATTACKER");
        attackerLabel.setMinWidth(200);
        Label clientLabel = new Label("CLIENT");
        clientLabel.setMinWidth(200);
        participantBox.getChildren().addAll(msgLabel, serverLabel, attackerLabel, clientLabel);
        mainFrame.getChildren().add(participantBox);
        return mainFrame;
    }

    public HBox addNewMessage(boolean isSender) {
        HBox newCommunication = new HBox();
        sendMsgLabel = new Label("Challenge");
        sendMsgLabel.setMinWidth(200);
        sendMsgLabel.setVisible(false);
        senderMessageBox = new TextField();
        senderMessageBox.setMinWidth(200);
        if (isSender) {
            recieverMessageBox = new TextField();
            recieverMessageBox.setVisible(false);
            recieverMessageBox.setMinWidth(200);
            sendMsgLabel.setText("Challenge");
            msgCounterLabel = new Label();
            msgCounterLabel.setPrefWidth(100);
            newCommunication.getChildren().addAll(msgCounterLabel, senderMessageBox, sendMsgLabel);
        } else {
            sendMsgLabel.setText("Response to the challenge");
            sendMsgLabel.setVisible(true);
            senderInbox = new TextField();
            newCommunication.getChildren().addAll(dummyLabel, senderInbox, sendMsgLabel);
        }
        if (recieverMessageBox != null && isSender) {
            newCommunication.getChildren().add(recieverMessageBox);
        }
        return newCommunication;
    }

    public Button createBtn(String buttonName) {
        Button button = new Button(buttonName);
        return button;
    }

    public TextField getRecieverMessageBox() {
        return recieverMessageBox;
    }

    public TextField getSenderMessageBox() {
        return senderMessageBox;
    }

    public TextField getSenderInbox() {
        return senderInbox;
    }

    public Label getSendMsgLabel() {
        return sendMsgLabel;
    }

    public Label getMsgCounterLabel() {
        return msgCounterLabel;
    }
}
