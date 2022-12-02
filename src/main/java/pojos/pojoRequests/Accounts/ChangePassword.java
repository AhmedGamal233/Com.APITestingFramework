package pojos.pojoRequests.Accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  //removed or not based on all mandatory or not
public class ChangePassword {
    @JsonProperty("resetPasswordCode")
    private String resetPasswordCode;
    @JsonProperty("password")
    private String password;
}
