package utils.common;

import java.util.HashSet;
import java.util.Set;

public class API {
    String address;
    Set<Endpoint> endpoints = new HashSet<>();

    public API(String address) {
        this.address = address;
    }

    public API(String address, Set<Endpoint> endpoints) {
        this.address = address;
        this.endpoints = endpoints;
    }

    public Set<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(Set<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }
    public String getEndpointPathByName(String name){
        for(Endpoint point: endpoints){
            if(point.name.equals(name))
                return point.path;
        }
        return "/";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "API{" +
                "address='" + address + '\'' +
                ", endpoints=" + endpoints +
                '}';
    }
}
