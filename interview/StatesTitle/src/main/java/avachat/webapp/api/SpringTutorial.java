package avachat.webapp.api;

import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;





@RestController
public class SpringTutorial {

    public static class QuoteValue {
        private long id;
        private String quote;

        public QuoteValue() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }
    }


    public static class QuoteResponse {
        String type;
        QuoteValue value;

        public QuoteResponse() {
        }


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public QuoteValue getValue() {
            return value;
        }

        public void setValue(QuoteValue value) {
            this.value = value;
        }
    }

    private static final String API_URL = "https://gturnquist-quoters.cfapps.io/api/random";
    private final RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(method = GET, value = "/spring/tutorial/quote")
    public String fetchRandomQuote() {
        QuoteResponse quoteResponse = restTemplate.getForObject(API_URL, QuoteResponse.class);
        return quoteResponse.getValue().getQuote() + "\n";
    }
}
