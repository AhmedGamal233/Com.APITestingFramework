package pojos.pojoRequests.Accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  //removed or not based on all mandatory or not
public class SignUp {
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("subscriptionPlanId")
    private Integer subscriptionPlanId;
    @JsonProperty("subscriptionPlanSchemeId")
    private Integer subscriptionPlanSchemeId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("accountId")
    private Integer accountId;
    @JsonProperty("subscriptionId")
    private Integer subscriptionId;
    @JsonProperty("subscriptionPackageId")
    private Integer subscriptionPackageId;
}
