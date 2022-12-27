package apiResourcesEndPoints;

import io.restassured.RestAssured;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum API_Resources {
    USERS("api/users"),
    SPECIFIC_USERS("api/users/{id}"),
    SIGN_IN("/SignIn"),
    FORGET_PASSWORD("/ForgetPassword"),
    CHANGE_PASSWORD("/ChangeForgottenPassword"),
    SIGN_UP("/SignUp"),
    USER_PROFILE("/UserProfiles"),
    ACCOUNT_PROFILE("/AccountProfiles"),
    NOTE_CATEGORIES("/NoteCategories"),
    NOTE_TEMPLATE("/NoteTemplates"),
    NOTE_TYPES("/NoteTypes"),
    PATIENTS("/Patients"),
    CPT("/api/Cpt"),
    DIAGNOSIS("/api/Diagnosis"),
    FROM_CATEGORIES("/api/FormCategories"),
    FORMS("/api/Forms"),
    If_There_Is_Extra_Resources("");

    private final String endPoint;
}

