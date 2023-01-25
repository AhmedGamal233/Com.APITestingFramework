package pojos.pojoResponses.AccountsRes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  //removed or not based on all mandatory or not
public class SignInRes {
    @JsonProperty("token")
    public static String accessToken;
    @JsonProperty("expiration")
    private String expiration;
    @JsonProperty("token")
    private String token;
}
