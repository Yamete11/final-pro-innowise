package org.innowise.config;

public enum URLsEnum {

    AMAZON_URL("https://www.amazon.com/"),
    DEMOQA_URL("https://demoqa.com/"),
    ONLINER_URL("https://www.onliner.by/"),
    TRANSLATE_URL("https://translate.google.com/"),
    RELAX_URL("https://www.relax.by/");


    private final String url;

    URLsEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
