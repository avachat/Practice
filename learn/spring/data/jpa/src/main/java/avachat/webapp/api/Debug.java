package avachat.webapp.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class Debug {

    @RequestMapping(method = GET, value = "/debug/ping")
    public String ping() {
        return String.valueOf(new Date() + "\n");
    }

}
