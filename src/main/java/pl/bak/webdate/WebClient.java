package pl.bak.webdate;
import pl.bak.database.GetConstant;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class WebClient {
    private GetConstant properites = new GetConstant();

    private String ENDPOINT = properites.getPropValues().get(6);
    private static final ZoneId ZONE_UTC = ZoneId.of("UTC");
    private static final ZoneId ZONE_WARSAW = ZoneId.of("Europe/Warsaw");

    private static final HttpClient client = new DefaultHttpClient();
    private static final Gson gson = new Gson();

    public WebClient() throws IOException {
    }

    public String query() throws IOException {
        HttpGet request = new HttpGet(ENDPOINT);
        HttpResponse httpResponse = client.execute(request);
        String strResponse = EntityUtils.toString(httpResponse.getEntity());
        ApiResponse apiResponse = gson.fromJson(strResponse, ApiResponse.class);
        String inDate = apiResponse.getCurrentDateTime();
        final String pattern = "yyyy-MM-dd'T'HH:mm'Z'";
        final String pattern2 = "yyyy-MM-dd HH:mm";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        final LocalDateTime ldt = formatter.parse(inDate, LocalDateTime::from);
        final ZonedDateTime utc = ldt.atZone(ZONE_UTC);
        final String warsaw = utc.format(DateTimeFormatter.ofPattern(pattern2).withZone(ZONE_WARSAW));
        return warsaw;
    }

}