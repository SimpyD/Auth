import com.google.gson.Gson;
import com.google.gson.JsonObject;

// ...

String responseBody = EntityUtils.toString(entity);

// Parse the response body as JSON
Gson gson = new Gson();
JsonObject responseJson = gson.fromJson(responseBody, JsonObject.class);

// Extract the token
String token = responseJson.get("token").getAsString();


//



