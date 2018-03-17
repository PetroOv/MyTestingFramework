package utils.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ConfigurationSetup {
    public Environment environment;
    public API api;
    Properties environmentProps;
    Properties applicationProps;

    public ConfigurationSetup() {
        getProperties();
        System.setProperty("env", "production");
        String env = System.getProperty("env");
        api = new API(getApplicationApiAddress(), getApplicationApiEndpoints());
        switch (env) {
            case "production":
                environment = getEnv("prod");
                break;
            case "qa":
                environment = getEnv("qa");
                break;
            default:
                break;
        }

    }

    public static void main(String[] args) {
        ConfigurationSetup cs = new ConfigurationSetup();
        System.out.println(cs.api.toString());
        System.out.println(cs.environment.toString());
    }

    private String getApplicationApiAddress() {
        return applicationProps.getProperty("api");
    }

    private Environment getEnv(String envName) {
        Map<String, String> ports = new HashMap<>();
        String uiPortName = String.format("%s.port.ui", envName);
        String apiPortName = String.format("%s.port.api", envName);
        String host = environmentProps.getProperty(String.format("%s.host", envName));
        ports.put("ui", environmentProps.getProperty(uiPortName));
        ports.put("api", environmentProps.getProperty(apiPortName));
        return new Environment(host, ports);
    }

    private Set<Endpoint> getApplicationApiEndpoints() {
        Set<Endpoint> result = new HashSet<>();
        String requiredProp = "api.endpoint.";
        for (String propName : applicationProps.stringPropertyNames()) {
            if (propName.startsWith(requiredProp)) {
                result.add(new Endpoint(propName.substring(requiredProp.length()), applicationProps.getProperty(propName)));
            }
        }
        return result;
    }

    private void getProperties() {
        environmentProps = new Properties();
        applicationProps = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream("environment.properties");
            environmentProps.load(input);
            input = getClass().getClassLoader().getResourceAsStream("common.properties");
            applicationProps.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
