package ir.moke.dandelion;

import java.io.FileReader;
import java.util.Properties;

public class ClientConfig {
    public static final String HTTP_ENDPOINT = "http://localhost:8080";
    public static final String TOKEN_URI = "/api/v1/security/token";
    public static final String STORAGE_DIRECTORY = System.getenv("HOME") + "/dandelion_client/";
    public static final String CLIENT_CONFIG_FILE = "client.conf";

    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(ClientConfig.STORAGE_DIRECTORY + ClientConfig.CLIENT_CONFIG_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}
