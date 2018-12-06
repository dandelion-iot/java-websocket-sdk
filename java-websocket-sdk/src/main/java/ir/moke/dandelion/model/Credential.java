package ir.moke.dandelion.model;

public class Credential {
    private String deviceId ;
    private String token ;

    public Credential(String deviceId, String token) {
        this.deviceId = deviceId;
        this.token = token;
    }

    public Credential() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
