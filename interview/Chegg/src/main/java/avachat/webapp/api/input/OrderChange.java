package avachat.webapp.api.input;

import avachat.failure.Params;
import avachat.persistence.entity.UserAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderChange {
    UserAddress address;
    Map<String, Params> failures = new HashMap<>();
}
