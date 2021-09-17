package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller
{
    static Pattern pattern;
    static Matcher matcher;
    static String city_value;
    static String city_id="moscow";
    @FXML
    public AnchorPane main_window;
    @FXML
    private Label weather_city;
    @FXML
    private Label w_value_now;
    @FXML
    public HBox temp_list;
    @FXML
    private ComboBox<String> city_button;
    @FXML
    private ScrollPane scroll;
    ObservableList<String> city_list = FXCollections.observableArrayList("Москва","Мурманск","Новосибирск","Санкт-Петербург","Сочи");
    @FXML
    void initialize()
    {
        save_city.check_city();
        city_button.setValue(city_value);
        city_button.setItems(city_list);
        temp_list.setSpacing(5);
        try {
            weather.weather_now();
            weather_city.setText(weather.w_city);
            w_value_now.setText(weather.weather_value_now);
            generate_weather_list_now();
        } catch (Exception e) {
            e.printStackTrace();
        }
        city_button.setOnAction(event ->
        {
            switch (city_button.getValue())
            {
                case "Мурманск" : city_id="murmansk";break;
                case "Москва" : city_id="moscow";break;
                case "Санкт-Петербург" : city_id="saint-petersburg";break;
                case "Сочи" : city_id="sochi";break;
                case "Новосибирск" : city_id="novosibirsk";break;
            }
            save_city.save_city(city_button.getValue(),city_id);
            try {
                weather.weather_now();
                weather_city.setText(weather.w_city);
                w_value_now.setText(weather.weather_value_now);
                generate_weather_list_now();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public static String get_string(String string, String code) throws Exception
    {
        pattern = Pattern.compile(code);
        matcher = pattern.matcher(string);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception();
    }
    private void generate_weather_list_now() throws IOException
    {
        temp_list.getChildren().clear();
        for(int i=0;i<weather.weather_hour_mas.size();i++)
        {
            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("weather_list.fxml"));
            temp_list.getChildren().add(fxmlLoader.load());
            w_list w_list=fxmlLoader.getController();
            w_list.set_param(weather.weather_hour_mas.get(i),weather.weather_hour_temp_mas.get(i));
        }
    }
}

