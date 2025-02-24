package com.example.madaniyat_talimi_markazi_bot.entityAndService.enums;

import lombok.Getter;

@Getter
public enum Languages {
    UZ("uz"),
    RU("ru"),
    EN("en");

    private final String key;

    Languages(String key) {
        this.key = key;
    }

}
