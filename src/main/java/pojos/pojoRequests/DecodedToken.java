package pojos.pojoRequests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  //removed or not based on all mandatory or not
public class DecodedToken {
    @JsonProperty("SessionId")
    private String sessionId;
    @JsonProperty("jti")
    private String jti;
    @JsonProperty("exp")
    private Integer exp;
}
