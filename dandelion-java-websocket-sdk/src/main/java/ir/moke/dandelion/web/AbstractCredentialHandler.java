package ir.moke.dandelion.web;

import ir.moke.dandelion.ClientConfig;

import java.io.File;

public abstract class AbstractCredentialHandler {
    public abstract void registerDevice();

    public abstract void storeToken();

    public void initialize() {
        if (!configurationFileExist()) {
            registerDevice();
            storeToken();
        } else {
            System.out.println("Configuration file already exist");
        }
    }

    private boolean configurationFileExist() {
        File file = new File(ClientConfig.STORAGE_DIRECTORY + ClientConfig.CLIENT_CONFIG_FILE);
        return file.exists();
    }
}
