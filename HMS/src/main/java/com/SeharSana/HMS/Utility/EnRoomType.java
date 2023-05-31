package com.SeharSana.HMS.Utility;

public enum EnRoomType {
    BALCONY("Balcony"),
    LUXURY("Luxury"),
    STANDARD("Standard");

    private String enRoomType;

    EnRoomType(String enRoomType) {
        this.enRoomType = enRoomType;
    }

    public String getRoomType() {
        return enRoomType;
    }

    public static EnRoomType fromString(String text) {
        for (EnRoomType enRoomType : EnRoomType.values()) {
            if (enRoomType.enRoomType.equalsIgnoreCase(text)) {
                return enRoomType;
            }
        }
        return null;
    }
}
