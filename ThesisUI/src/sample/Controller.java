package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.lang3.time.StopWatch;
import sample.uni.tud.ui.helper.UIHelper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Controller {

    private UIHelper uiHelper = null;
    private Stage stage = null;
    private static int msgCounter = 0;
    private String encryptedMessage = null;
    private HashMap<String, String> challengeResponseMap = new HashMap<>();
    private StopWatch watch;

    public Controller(Stage stage) {
        watch = new StopWatch();
        this.stage = stage;
        uiHelper = new UIHelper();
        populateChallengeResponseMap();
    }

    private void populateChallengeResponseMap() {
        challengeResponseMap.put("C100", "R100");
        challengeResponseMap.put("C101", "R101");
        challengeResponseMap.put("C102", "R102");
        challengeResponseMap.put("C103", "R103");
        challengeResponseMap.put("C104", "R104");
        challengeResponseMap.put("C105", "R105");
        challengeResponseMap.put("C106", "R106");
        challengeResponseMap.put("C107", "R107");
        challengeResponseMap.put("C108", "R108");
        challengeResponseMap.put("C109", "R109");

    }

    public void createUI() {
        GridPane mainPane = new GridPane();
        VBox mainFrame = uiHelper.createUI();
        mainPane.add(mainFrame, 0, 0);
        Button createMessageBtn = uiHelper.createBtn("New Message");
        createMessageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Button btn = (Button) event.getSource();
                String btnName = btn.getText();
                if ("New Message".equals(btnName)) {
                    createNewMsgUI(btn, mainFrame);
                } else if ("Send".equals(btnName)) {
                    if (watch.isStopped()) {
                        watch.reset();
                        watch.start();
                    }
                    sendChallengeToClient(btn, mainFrame);
                    watch.stop();
                    System.out.println("Time taken :" + watch.getNanoTime());
                    System.out.print("");
                }
            }
        });
        mainPane.add(createMessageBtn, 0, 2);
        stage.setScene(new Scene(mainPane, 1024, 768));
    }

    private void createNewMsgUI(Button btn, VBox mainFrame) {
        HBox newCommunication = uiHelper.addNewMessage(true);
        btn.setText("Send");
        mainFrame.getChildren().add(newCommunication);
        uiHelper.getMsgCounterLabel().setText(++msgCounter + "");
    }


    // no hash :1440184
    //with hash 12343963

    private void sendChallengeToClient(Button btn, VBox mainFrame) {
        TextField senderMessageBoxOld = uiHelper.getSenderMessageBox();
        String serverChallengeText = senderMessageBoxOld.getText();
        uiHelper.getSendMsgLabel().setVisible(true);
        TextField recieverMessageBox = uiHelper.getRecieverMessageBox();
        recieverMessageBox.setVisible(true);
        recieverMessageBox.setText(serverChallengeText);
        String response = challengeResponseMap.get(serverChallengeText);
        if (response != null) {
            btn.setText("New Message");
            HBox newCommunication = uiHelper.addNewMessage(false);
            TextField senderMessageBox = uiHelper.getSenderInbox();
            senderMessageBoxOld.setEditable(false);
            recieverMessageBox.setEditable(false);
            senderMessageBox.setEditable(false);
            //comment the below one line to remove hash
            String responseText = hashMe(response);
            senderMessageBox.setText(responseText);//comment this  to remove hash
            //senderMessageBox.setText(response);//uncomment this to remove hash
            mainFrame.getChildren().add(newCommunication);
        }
    }

    private String hashMe(String plainText) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(plainText.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
