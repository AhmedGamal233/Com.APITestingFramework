package pojos.pojoRequests.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  //removed or not based on all mandatory or not
public class UserProfile {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("zipCode")
    private String zipCode;
    @JsonProperty("address")
    private String address;
    @JsonProperty("address2")
    private String address2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("countryName")
    private String countryName;
    @JsonProperty("phoneNumber")
    private Object phoneNumber;
    @JsonProperty("profileImageName")
    private Object profileImageName;
}
