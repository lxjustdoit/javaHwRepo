import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * week02 hw6
 */
public class HttpClientDemo {

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String get(String url) throws IOException, ParseException, URISyntaxException {
        URIBuilder uri = new URIBuilder(url);
        HttpGet httpGet = new HttpGet(uri.build());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity e = response.getEntity();
            System.out.println(response.getStatusLine());
            return EntityUtils.toString(e, "UTF-8");
        }finally {
            if(response != null){
                response.close();
            }
        }
    }

    public static String post(String url, Map<String, String> header) throws IOException, ParseException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        for(String key: header.keySet()){
            httpPost.setHeader(key, header.get(key));
        }
        try{
            response = httpClient.execute(httpPost);
            System.out.println(response.getStatusLine());
            HttpEntity e = response.getEntity();
            return EntityUtils.toString(e, "UTF-8");
        }finally {
            if(response != null){
                response.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8088";
        String respose = HttpClientDemo.get(url);
        System.out.println("url: "+url+", response: "+respose);
    }
}
