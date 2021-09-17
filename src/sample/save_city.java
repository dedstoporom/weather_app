package sample;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
public class save_city extends Controller
{
     static File file=new File("city_equals.txt");
     static String line_city;
     static String id;
     public static void save_city(String city,String id)
    {
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
            PrintWriter pw=new PrintWriter(file);
            pw.println("name="+city+"\n"+"id="+id);
            pw.close();
        }
        catch (IOException e)
        {
            System.out.print(e);
        }
    }
    public static void check_city()
    {
        try
        {
            BufferedReader br=new BufferedReader(new FileReader(file));
            city_value=get_string(br.readLine(), "[^name=].+");
            city_id=get_string(br.readLine(), "[^id=].+");
            if(city_value.equals("")){city_value="Москва";city_id="moscow";}
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.print(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
