package ir.moke.dandelion.web;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ir.moke.dandelion.ClientConfig;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Consumer;

public class WebSocketCredentialHandler extends AbstractCredentialHandler {
    private Properties properties;

    public WebSocketCredentialHandler() {
        this.properties = new Properties();
    }

    @Override
    public void registerDevice() {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(ClientConfig.HTTP_ENDPOINT + ClientConfig.TOKEN_URI)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = jsonResponse.getBody().getObject();
        String token = jsonObject.getString("token");
        String deviceId = jsonObject.getString("deviceId");
        properties.setProperty("token", token);
        properties.setProperty("deviceId", deviceId);
    }

    @Override
    public void storeToken() {

        File filePath = new File(ClientConfig.STORAGE_DIRECTORY) ;
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        try {
            System.out.println(ClientConfig.STORAGE_DIRECTORY + ClientConfig.CLIENT_CONFIG_FILE);
            FileWriter fileWriter = new FileWriter(ClientConfig.STORAGE_DIRECTORY + ClientConfig.CLIENT_CONFIG_FILE);
            properties.store(fileWriter, "Dandelion Client Configuration");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
