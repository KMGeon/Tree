package dev.test.aswemake.enums;

public enum MemberTestEnum {
    VALID_EMAIL("mugeon1234@email.com"),
    VALID_PASSWORD("password1234!"),
    INVALID_EMAIL("error@error.com"),
    INVALID_PASSWORD("error1234!"),
    MARKET_EMAIL("abc@market.com"),
    MARKET_PASSWORD("market1234!");

    private final String message;

    MemberTestEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
