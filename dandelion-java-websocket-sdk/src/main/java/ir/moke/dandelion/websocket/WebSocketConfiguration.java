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
        logger.info("DeviceID : " + credential.getDeviceId());
        logger.info("Token : " + credential.getToken());
        headers.put("Authorization", Collections.singletonList("Bearer " + credential.getToken()));
    }
}
