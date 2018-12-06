package ir.moke.dandelion.websocket;

import ir.moke.dandelion.logger.LoggerProducer;
import ir.moke.dandelion.model.Credential;
import ir.moke.dandelion.DandelionCredentialFactory;

import javax.websocket.ClientEndpointConfig;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class WebSocketConfiguration extends ClientEndpointConfig.Configurator {
    private static final Logger logger = LoggerProducer.produceLogger();

    @Override
    public void beforeRequest(Map<String, List<String>> headers) {
        Credential credential = DandelionCredentialFactory.getCredential();
        String deviceId = credential.getDeviceId() ;
        String token = credential.getToken() ;
        logger.info("DeviceID : " + deviceId);
        logger.info("Token : " + token);
        headers.put("Authorization", Collections.singletonList("Bearer " + token));
    }
}
