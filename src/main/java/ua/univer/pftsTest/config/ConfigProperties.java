package ua.univer.pftsTest.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "pfts.server")
public class ConfigProperties {

    private String hostName;
    private String url;
    //private String sid = "0";

    public static String SERVER_HOST_NAME;
    public static String SERVER_URL;
    public static String USER_SID = "0";


    public void setHostName(String hostName) {
        this.hostName = hostName;
        SERVER_HOST_NAME = hostName;
    }

    public void setUrl(String url) {
        this.url = url;
        SERVER_URL = url;
    }

  /*  public void setSid(String sid) {
        this.sid = sid;
        USER_SID = sid;
    }*/
}
