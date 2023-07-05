import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TokenRequestClient {
    public static void main(String[] args) {
        String authUrl = "https://example.com/auth";
        String username = "your_username";
        String password = "your_password";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(authUrl);
            httpPost.setHeader("Content-Type", "application/json");

            String payload = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
            httpPost.setEntity(new StringEntity(payload));

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 200 && entity != null) {
                String responseBody = EntityUtils.toString(entity);
                // Handle the response body (JSON) here
            } else {
                // Handle authentication errors or other status codes
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
}
