package apiResourcesEndPoints;

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

    private String resource;
    //constructor of enum
    API_Resources (String resource){
        this.resource=resource;
    }

    public String getResource(){
        return resource;
    }


}

