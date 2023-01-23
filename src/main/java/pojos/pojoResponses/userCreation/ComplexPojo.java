package pojos.pojoResponses.userCreation;

import lombok.Data;

import java.util.List;

@Data
public class ComplexPojo {
    private List<AccessoryModel> accessories;

    @Data
    public static class AccessoryModel {
        private AccessoryPrice accessoryPrice;
        private List<AccessoryPromotion> accessoryPromotions;
    }

    @Data
    public static class AccessoryPrice {
        private Price oneOffPrice;
        private Price oneOffDiscountPrice;
        private MerchandisingPromotionForPrice merchandisingPromotions;
    }

    @Data
    public static class Price {
        private String gross;
        private String net;
        private String vat;
    }

    @Data
    public static class MerchandisingPromotionForPrice {
        private String tag;
        private String label;
        private String mpType;
        private String startDate;
        private String endDate;
        private String priceEstablishedLabel;
        private Integer priority;
        private List<String> footNotes;
        private List<String> packageType;
        private boolean belowTheLine;
    }

    @Data
    public static class AccessoryPromotion {
        private String tag;
        private String label;
        private String type;
        private String priority;
        private String description;
        private String startDate;
        private String endDate;
        private List<String> packageType;
        private List<String> footNotes;
        private String promotionMedia;
    }
}
