package utils.common;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    String host;
    Map<String, String> ports = new HashMap<>();

    public Environment(String host) {
        this.host = host;
    }

    public Environment(String host, Map<String, String> ports) {
        this.ports = ports;
        this.host = host;
    }

    public Map<String, String> getPorts() {
        return ports;
    }

    public void setPorts(Map<String, String> ports) {
        this.ports = ports;
    }

    public String getUIAddress() {
        return String.format("http://%s:%s", host, ports.get("ui"));
    }

    public String getAPIAddress() {
        return String.format("http://%s:%s", host, ports.get("api"));
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "host='" + host + '\'' +
                ", ports=" + ports +
                '}';
    }
}
