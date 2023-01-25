package pojos.pojoResponses.UserContent.UserNoteCategories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  //removed or not based on all mandatory or not
public class NoteCategory {
    @JsonProperty("createdOn")
    private String createdOn;
    @JsonProperty("updatedOn")
    private Object updatedOn;
    @JsonProperty("wileyGroupId")
    private Integer wileyGroupId;
    @JsonProperty("wileyProblemId")
    private Integer wileyProblemId;
    @JsonProperty("wileyObjectiveId")
    private Integer wileyObjectiveId;
    @JsonProperty("ownerShip")
    private String ownerShip;
    @JsonProperty("excluded")
    private Boolean excluded;
    @JsonProperty("noteCategoryId")
    private Integer noteCategoryId;
    @JsonProperty("defaultNoteCategoryId")
    private Integer defaultNoteCategoryId;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("parentNoteCategoryId")
    private Object parentNoteCategoryId;
    @JsonProperty("defaultParentNoteCategoryId")
    private Object defaultParentNoteCategoryId;
    @JsonProperty("noteTypeId")
    private Integer noteTypeId;
    @JsonProperty("defaultNoteTypeId")
    private Integer defaultNoteTypeId;
    @JsonProperty("collapsed")
    private Boolean collapsed;
    @JsonProperty("order")
    private Integer order;
}
