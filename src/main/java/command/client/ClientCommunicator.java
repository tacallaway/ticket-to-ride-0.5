package command.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ClientCommunicator {
    public static ClientCommunicator SINGLETON = new ClientCommunicator();

    private static final String SERVER_HOST = "http://localhost:7001";
    private static final String HTTP_POST = "POST";

    private Gson gson = new Gson();

    private ClientCommunicator() {}

    public Object send(CommandData data) {

        String response = sendAndReceive(data.command, data.value);

        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> myMap = gson.fromJson(response, type);

        return myMap.get("value");
    }

    private HttpURLConnection openConnection(String path) {
        HttpURLConnection con = null;
        try {
            URL url = new URL(SERVER_HOST + path);
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod(HTTP_POST);
            con.setDoOutput(true);
            con.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return con;
    }

    private String sendAndReceive(String command, String value) {
        try {
            HttpURLConnection con = ClientCommunicator.SINGLETON.openConnection("/execute");

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("{\"command\": \"" + command + "\", \"value\": \"" + value + "\"}");
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error calling standard.server");
        }
    }
}
