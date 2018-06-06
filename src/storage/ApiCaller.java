package storage;

import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


public class ApiCaller {

    public  InputStream sendGet(String lat, String lon) throws Exception {

        String url ="http://nominatim.openstreetmap.org/reverse?format=json&lat="+lat+"&lon="+lon;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        
        InputStream contentResponse = response.getEntity().getContent();

        return contentResponse;

    }
}