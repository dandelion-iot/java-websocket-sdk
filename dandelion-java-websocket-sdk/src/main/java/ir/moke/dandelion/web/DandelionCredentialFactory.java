package ir.moke.dandelion.web;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ir.moke.dandelion.ClientConfig;
import ir.moke.dandelion.logger.LoggerProducer;
import ir.moke.dandelion.model.Credential;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class DandelionCredentialFactory {
    private static final Logger logger = LoggerProducer.produceLogger();
    private static Properties properties = new Properties();

    public static void initialize() {
        if (!configurationFileExist()) {
            logger.info("Initialize client .");
            registerDevice();
            storeToken();
        } else {
            logger.info("Configuration file already exist");
        }
    }

    private static boolean configurationFileExist() {
        File file = new File(ClientConfig.STORAGE_DIRECTORY + ClientConfig.CLIENT_CONFIG_FILE);
        return file.exists();
    }

    private static void registerDevice() {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            logger.info("Register new client on server .");
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

    private static void storeToken() {

        File filePath = new File(ClientConfig.STORAGE_DIRECTORY);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        try {
            String clientConfigFile = ClientConfig.STORAGE_DIRECTORY + ClientConfig.CLIENT_CONFIG_FILE ;
            logger.info("Store token on " + clientConfigFile);
            FileWriter fileWriter = new FileWriter(clientConfigFile);
            properties.store(fileWriter, "Dandelion Client Configuration");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Credential getCredential() {
        Credential credential = new Credential();
        try {
            properties.load(new FileReader(ClientConfig.STORAGE_DIRECTORY + ClientConfig.CLIENT_CONFIG_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        credential.setDeviceId(properties.getProperty("deviceId"));
        credential.setToken(properties.getProperty("token"));
        return credential;
    }
}
