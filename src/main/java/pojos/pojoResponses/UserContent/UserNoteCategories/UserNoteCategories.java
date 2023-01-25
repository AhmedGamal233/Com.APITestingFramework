package pojos.pojoResponses.UserContent.UserNoteCategories;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  //removed or not based on all mandatory or not
public class UserNoteCategories {
    @JsonProperty("items")
    private List<Item> items;
    @JsonProperty("totalCount")
    private Integer totalCount;
    @JsonProperty("currentPageIndex")
    private Integer currentPageIndex;
    @JsonProperty("requestedPageSize")
    private Integer requestedPageSize;
    @JsonProperty("currentPageSize")
    private Integer currentPageSize;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)  //removed or not based on all mandatory or not
    public static class Item {

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
        private Integer parentNoteCategoryId;
        @JsonProperty("defaultParentNoteCategoryId")
        private Integer defaultParentNoteCategoryId;
        @JsonProperty("noteTypeId")
        private Integer noteTypeId;
        @JsonProperty("defaultNoteTypeId")
        private Integer defaultNoteTypeId;
        @JsonProperty("collapsed")
        private Boolean collapsed;
        @JsonProperty("order")
        private Integer order;}
}
