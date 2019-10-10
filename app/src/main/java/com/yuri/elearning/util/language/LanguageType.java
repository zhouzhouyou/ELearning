package com.yuri.elearning.util.language;

enum LanguageType {
    CHINESE("ch"),
    ENGLISH("en");

    private String language;

    LanguageType(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language == null ? "" : language;
    }
}
