package com.hyundairoad.hyundairoad.global.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResult {
    private String code;
    private String message;
}
