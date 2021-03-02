package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import logic.GUIConnector;
import logic.Game;
import logic.Position;
import logic.Tickets;
import logic.playerType;
import org.json.JSONException;

/**
 * This class is implementing the method which are defined in the gui connector.
 * all method for visualization that are required are written here. display
 * symbol of player, create shape for every player,show mister x travel log,
 * show erro messages are some importat methods of this class
 *
 * @author kobra
 */
public class JavaFxGUI implements GUIConnector {

    private final GridPane grdMisterXTickets;
    private final GridPane grdTickets;
    private final Pane innerPane;
    private final ImageView imgMap;
    Circle circle0 = null;
    Circle circle1 = null;
    Circle circle2 = null;
    Circle circle3 = null;
    Circle circle4 = null;
    Circle circle5 = null;
    Polygon[] polygons = new Polygon[6];
    Polygon polygon1 = null;
    Polygon polygon2 = null;
    Polygon polygon3 = null;
    Polygon polygon4 = null;
    Polygon polygon5 = null;
    Circle[] circles = new Circle[6];
    Label nomOfTaxis;
    Label nomOfBuses;
    Label nomOfTrains;
    Label nomOfBoats;
    private Label lblWinner;
    private Label[] lbaArray = new Label[24];
    private String imgPath = "gui/img/";
    private String imgExt = ".png";
    Image image = null;
    int idLables = 0;
    Label lblQuestion = new Label(" ");
    private Label lblPlayer;
    ComboBox<String> comboBox = null;
    Tickets ticket;
    Rectangle rec = null;
    boolean isSelected = false;
    Game game;
    ImageView imageViewMisterX = null;
    CheckBox blackTicket;

    /**
     * main constructor
     *
     * @param grdMister
     * @param grdTicket
     * @param innerPane
     * @param map
     * @param lblPlayer
     * @param trains
     * @param buses
     * @param taxis
     * @param boats
     * @param lblArray
     * @param lblQuestion
     * @param blackTicket
     */
    public JavaFxGUI(GridPane grdMister, GridPane grdTicket, Pane innerPane,
            ImageView map, Label lblPlayer, Label trains, Label buses, Label taxis,
            Label boats, Label[] lblArray, Label lblQuestion, CheckBox blackTicket) {
        this.grdMisterXTickets = grdMister;
        this.grdTickets = grdTicket;
        this.innerPane = innerPane;
        this.imgMap = map;
        this.lblPlayer = lblPlayer;
        this.nomOfTrains = trains;
        this.nomOfBuses = buses;
        this.nomOfTaxis = taxis;
        this.nomOfBoats = boats;
        this.lbaArray = lblArray;
        this.lblQuestion = lblQuestion;
        this.blackTicket = blackTicket;
    }

    @Override
    public void displaySymbol(Position coord, int idPlayer, boolean isStaringPoint) {
        String color = "";
        if (idPlayer == 0) {
            color = "orange";
            lblQuestion.setText("?");
        } else {
            color = playerType.values()[idPlayer].toString().toLowerCase();
        }
        if (polygons[idPlayer] != null) {
            this.innerPane.getChildren().remove(polygons[idPlayer]);
        }
        if (circles[idPlayer] != null) {
            this.innerPane.getChildren().remove(circles[idPlayer]);
        }
        if (comboBox != null) {
            this.innerPane.getChildren().remove(comboBox);
        }
        if (lblQuestion != null && idPlayer == 0) {
            this.innerPane.getChildren().remove(lblQuestion);
        }
        circles[idPlayer] = bindToPane(circles[idPlayer], coord, idPlayer, color);
    }

    /**
     * bind the given shape of the current player to the main pane
     *
     * @param circle
     * @param coord
     * @param idPlayer
     * @param color
     * @return
     */
    private Circle bindToPane(Circle circle, Position coord, int idPlayer, String color) {
        if (idPlayer == 0) {
            circle = new Circle(coord.x(), coord.y(), 20.0);
            lblQuestion.setStyle("-fx-text-fill: white");
            lblQuestion.setFont(new Font("Arial", 25));
            lblQuestion.setScaleX(2.3);
            lblQuestion.setScaleY(1);
            lblQuestion.translateXProperty().bind(circle.centerXProperty().subtract(6.0));
            lblQuestion.translateYProperty().bind(circle.centerYProperty().subtract(13.0));
        } else {
            circle = new Circle(coord.x(), coord.y(), 10.0);
        }
        circle.setStroke(Color.BLACK);
        circle.setStyle("-fx-fill: " + color + ";");
        double xNorm = coord.x();
        double yNorm = coord.y();
        circle.centerXProperty().bind(this.innerPane.widthProperty().multiply(xNorm));
        circle.centerYProperty().bind(this.innerPane.heightProperty().multiply(yNorm));
        polygons[idPlayer] = new Polygon();
        polygons[idPlayer].getPoints().addAll(new Double[]{
            0.0, 0.0,
            -15.0, 15.0,
            15.0, 15.0});
        polygons[idPlayer].setFill(javafx.scene.paint.Color.LIGHTGRAY);
        polygons[idPlayer].setStroke(Color.BLACK);
        polygons[idPlayer].translateXProperty().bind(circle.centerXProperty());
        polygons[idPlayer].translateYProperty().bind(circle.centerYProperty());
        this.innerPane.getChildren().add(polygons[idPlayer]);
        this.innerPane.getChildren().add(circle);
        if (idPlayer == 0) {
            this.innerPane.getChildren().add(lblQuestion);
        }
        isSelected = false;
        return circle;
    }

    /**
     * hides mister x
     */
    public void hideMisterX() {
        if (circles[0] != null) {
            this.innerPane.getChildren().remove(circles[0]);
            this.innerPane.getChildren().remove(polygons[0]);
            this.innerPane.getChildren().remove(lblQuestion);
        }
    }

    @Override
    public void clear() {
        idLables = 0;
        if (circles != null) {
            for (int i = 0; i < circles.length; i++) {
                this.innerPane.getChildren().remove(circles[i]);
                lblQuestion.setText(" ");
            }
        }
        if (comboBox != null) {
            this.innerPane.getChildren().remove(comboBox);
        }
        if (lbaArray != null) {
            for (int i = 0; i < lbaArray.length; i++) {
                lbaArray[i].setGraphic(null);
            }
        }
        if (polygons != null) {
            for (int i = 0; i < polygons.length; i++) {
                this.innerPane.getChildren().remove(polygons[i]);
            }
        }
    }

    @Override
    public void showCurrentPlayer(int idPlayer) {
        String playerColor = "";
        if (idPlayer == 0) {
            playerColor = "MISTER X";
        } else {
            playerColor = playerType.values()[idPlayer].toString();

        }
        this.lblPlayer.setText(playerColor);
    }

    @Override
    public void showInDropDownList(Game game, boolean[] listOfTicket, Position coord, boolean blackTicket) {
        this.game = game;
        if (comboBox != null) {
            this.innerPane.getChildren().remove(comboBox);
        }
        assert (listOfTicket != null);
        ObservableList<String> options
                = FXCollections.observableArrayList();
        if (listOfTicket[0]) {
            options.add(Tickets.TRAIN.toString());
        }
        if (listOfTicket[1]) {
            options.add(Tickets.BUS.toString());
        }
        if (listOfTicket[2]) {
            options.add(Tickets.TAXI.toString());
        }
        if (listOfTicket[3]) {
            options.add(Tickets.BOAT.toString());
        }
        comboBox = new ComboBox(options);
        if (comboBox != null) {
            comboBox.translateXProperty().bind(this.innerPane.widthProperty().multiply(coord.x()));
            comboBox.translateYProperty().bind(this.innerPane.heightProperty().multiply(coord.y()));
            innerPane.getChildren().add(comboBox);
        }
        if (comboBox != null) {
            getSElectedItem(blackTicket);
        }
    }

    /**
     * get the selected ticket
     */
    private void getSElectedItem(boolean blackTicket) {
        comboBox.valueProperty().addListener((e) -> {
            String s = comboBox.getValue();
            ticket = Tickets.valueOf(s);
            isSelected = true;
            try {
                this.game.changeRound(ticket, blackTicket);
            } catch (JSONException ex) {
                Logger.getLogger(JavaFxGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void updateLabelTicketsNum(int[] numOfTickets) {
        nomOfTrains.setText(numOfTickets[0] + "x");
        nomOfBuses.setText(numOfTickets[1] + "x");
        nomOfTaxis.setText(numOfTickets[2] + "x");
        nomOfBoats.setText(numOfTickets[3] + "x");
    }

    @Override
    public void gameWon(String winnerName, int idPlayer) {

        String playerColor = "";
        String text = "Detective ";
        if (winnerName == null) {
            winnerName = "no one";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Results");
        if (idPlayer == 0) {
            playerColor = "ORANGE";
            text = "Mister X ";
        } else {
            playerColor = playerType.values()[idPlayer].toString();
        }
        alert.setHeaderText("" + text + " with bellow color won the game");
        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
        buttonBar.setStyle("-fx-font-size: 24px;" + "-fx-background-color:" + playerColor + ";");
        alert.showAndWait();
        this.innerPane.setDisable(true);
        this.imgMap.setDisable(true);
    }

    @Override
    public void showMisterXTicket(String ticket) {
        String img = imgPath + ticket.toUpperCase() + imgExt;
        if (!ticket.equals(null)) {
            image = new Image(img);
            ImageView imageViewMisterXTicket = new ImageView(image);
            lbaArray[idLables].setGraphic(imageViewMisterXTicket);
            imageViewMisterXTicket.fitWidthProperty().bind(lbaArray[idLables].widthProperty());
            imageViewMisterXTicket.fitHeightProperty().bind(lbaArray[idLables].heightProperty());
        }
        idLables++;
    }

    @Override
    public void disableBlackTicketCheckBox(boolean isDisable) {
        this.blackTicket.setDisable(isDisable);
    }

    @Override
    public void showMsg(int x, int id) {
        String s = "";
        switch (x) {
            case -1:
                s = " ";
                break;
            case 1:
                s = "length of station is not valid";
                break;
            case 2:
                s = "id of the station " + id + " is not valid";
                break;
            case 3:
                s = "posistion of the station " + id + "  the is not valid";
                break;
            case 4:
                s = "train neighbor " + id + " is not valid";
                break;
            case 5:
                s = "bus neighbor " + id + " the is not valid";
                break;
            case 6:
                s = "taxi neighbor" + id + " the is not valid";
                break;
            case 7:
                s = "boat neighbor" + id + " the is not valid";
                break;
            case 8:
                s = "The number of players is not correct";
                break;
            case 9:
                s = "The number of detectives is not correct";
                break;
            case 10:
                s = "the length of the detective array is not valid";
                break;
            case 11:
                s = "mister x position is not valid";
                break;
            case 12:
                s = "detectives in the position" + id + " is not valid";
                break;
            case 13:
                s = "detective is null";
                break;
            case 14:
                s = "invalid MrX's last shown Pos";
                break;
            case 15:
                s = "invalid player turn number";
                break;
            case 16:
                s = "invalid round number";
                break;
            case 0:
                s = " loaded file is not valid";
                break;
            default:
                break;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("" + s);
        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
        alert.showAndWait();
        this.innerPane.setDisable(true);
        this.imgMap.setDisable(true);

    }
}
