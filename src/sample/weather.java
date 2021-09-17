package sample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class weather extends Controller
{
    static Document page;

    static String w_city;
    static String weather_value_now;
    static String weather_hour;
    static String weather_hour_temp;
    static ArrayList<String> weather_hour_mas=new ArrayList<>();
    static ArrayList<String> weather_hour_temp_mas=new ArrayList<>();

    public static Document get_page() throws IOException
    {
        String url="https://yandex.ru/pogoda/"+city_id;
        Document page= Jsoup.parse(new URL(url),3000);
        return page;
    }
    public static void weather_now() throws Exception
    {
        weather_hour_temp_mas.clear();
        weather_hour_mas.clear();
        page = get_page();
        int index = 0;
        Element main_box = page.selectFirst("div[class=header-title header-title_in-fact]");
        w_city = main_box.child(0).text();
        Element box_now = page.selectFirst("div[class=fact__temp-wrap]");
        String condition = box_now.selectFirst("div[class=link__condition day-anchor i-bem]").text();
        Element weather_now = box_now.select("span").get(1);
        Element weather_today = page.selectFirst("ul[class=swiper-wrapper]");
        weather_value_now="Сейчас " + weather_now.text() + "°" + ", " + condition;
        System.out.println(weather_value_now);
        for (int i = 0; i <= 1; i++) {
            if (i == 0) {
                while (!weather_today.child(index).is("li[class=fact__hour-separator]")) {
                    weather_hour = get_string(weather_today.child(index).text(), "\\d{2}\\:\\d{2}");
                    weather_hour_mas.add(weather_hour);
                    index++;
                }
            }
            if (i == 1) {
                index = 0;
                while (!weather_today.child(index).is("li[class=fact__hour-separator]")) {
                    weather_hour_temp = weather_today.select("div[class=fact__hour-temp]").get(index).text();
                    weather_hour_temp_mas.add(weather_hour_temp);
                    index++;
                }
            }
        }
    }

}
