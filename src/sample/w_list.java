package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
public class w_list implements Initializable
{
    public ArrayList<w_list> mas_list=new ArrayList<>();
    @FXML
    private AnchorPane list_box;
    @FXML
    public Label list_time;

    @FXML
    private Label list_temp;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
    public void set_param(String st,String st1)
    {
        list_temp.setText(st);
        list_time.setText(st1);
        if(st==null){
            list_time.styleProperty().set("-fx-background-color:black");
            list_time.setText("NEXT DAY");
            list_box.styleProperty().set("-fx-background-color:#ECECEC");
        }
    }
}
