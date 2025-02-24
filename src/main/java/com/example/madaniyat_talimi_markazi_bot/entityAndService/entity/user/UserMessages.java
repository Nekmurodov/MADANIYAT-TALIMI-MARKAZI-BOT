package com.example.madaniyat_talimi_markazi_bot.entityAndService.entity.user;

import com.example.madaniyat_talimi_markazi_bot.entityAndService.entity.AbsEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserMessages extends AbsEntity {
    private Integer telMessageId;
    private String message;
}
