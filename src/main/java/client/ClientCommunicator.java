package client;

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

    private static final String SERVER_HOST = "http://localhost:7000";
    private static final String HTTP_POST = "POST";

    private Gson gson = new Gson();

    private ClientCommunicator() {}

    public String toLowerCase(String string) throws RuntimeException {
        String response = sendAndReceive("/to-lower-case", string);

        return getValueAsString(response);
    }

    public String trim(String string) {
        String response = sendAndReceive("/trim", string);

        return getValueAsString(response);
    }

    public int parseInteger(String string) {
        String response = sendAndReceive("/parse-integer", string);

        return getValueAsInt(response);
    }

    private String getValueAsString(String response) {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> myMap = gson.fromJson(response, type);

        return myMap.get("value");
    }

    private int getValueAsInt(String response) {
        Type type = new TypeToken<Map<String, Integer>>(){}.getType();
        Map<String, Integer> myMap = gson.fromJson(response, type);

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

    private String sendAndReceive(String path, String string) {
        try {
            HttpURLConnection con = ClientCommunicator.SINGLETON.openConnection(path);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("{\"value\": \"" + string + "\"}");
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
            throw new RuntimeException("Error calling server");
        }
    }
}
