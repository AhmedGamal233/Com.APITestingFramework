package pojos.pojoRequests.Accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  //removed or not based on all mandatory or not
public class ForgetPassword {
    private String mail;
}
