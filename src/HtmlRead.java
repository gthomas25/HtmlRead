import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {
                if(line.contains("href")){
                    int indexSpot= line.indexOf("href");
                    //System.out.println(line.substring(indexSpot+6));
                    String html = line.substring(indexSpot+6);
                    int htmlspot= html.indexOf("\"" );
                    int htmlspot2= html.indexOf("'" );

                   // System.out.println(htmlspot);
                   // System.out.println(htmlspot2);
                    if (htmlspot<0){

                        System.out.println(html.substring( 0,htmlspot2));

                    }
                    else if(htmlspot2<0){
                        System.out.println(html.substring( 0,htmlspot));

                    } else if(htmlspot>htmlspot2)
                    {
                        System.out.println(html.substring( 0,htmlspot2));
                    } else {
                        System.out.println(html.substring( 0,htmlspot));


                    }


                }


            }
            reader.close();

        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}

