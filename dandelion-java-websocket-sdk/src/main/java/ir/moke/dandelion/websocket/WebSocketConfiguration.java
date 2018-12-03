package ir.moke.dandelion.websocket;

import ir.moke.dandelion.ClientConfig;
import ir.moke.dandelion.logger.LoggerProducer;

import javax.websocket.ClientEndpointConfig;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class WebSocketConfiguration extends ClientEndpointConfig.Configurator {
    private static final Logger logger = LoggerProducer.produceLogger();

    @Override
    public void beforeRequest(Map<String, List<String>> headers) {
        Properties properties = ClientConfig.getProperties();
        String token = properties.getProperty("token");
        String deviceId = properties.getProperty("deviceId");
        logger.info("Token : " + token);
        logger.info("DeviceID : " + deviceId);
        headers.put("Authorization", Collections.singletonList("Bearer " + token));
    }
}
