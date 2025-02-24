package com.example.madaniyat_talimi_markazi_bot.entityAndService.enums;

import lombok.Getter;

@Getter
public enum MessageKeys {

    INVALID_MESSAGE_TEXT(1),
    INVALID_FILE_ID(2),
    SESSION_NOT_FOUND_M(3),
    INVALID_SESSION_STATUS_M(4),
    MESSAGE_NOT_FOUND(5),
    PROHIBITED_UPDATE_MESSAGE(6),
    INVALID_SESSION_CLIENT_ID(7),


    EMPTY_QUEUE_CLIENT_ID(30),
    EMPTY_LIST_M(31),
    QUE_NOT_FOUND(32),
    QUE_OWNER_EXCEPTION(33),
    INVALID_MESSAGE_TYPE(34),
    USER_NOT_FOUND(100),
    USER_ALREADY_EXISTS(101),
    USER_BAD_REQUEST(102),
    DATA_HAS_ALREADY_EXISTS(103),

    SESSION_NOT_FOUND(201);

    private final Integer code;

    MessageKeys(Integer code) {
        this.code = code;
    }

}
