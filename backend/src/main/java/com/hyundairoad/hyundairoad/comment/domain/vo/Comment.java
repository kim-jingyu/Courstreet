package com.hyundairoad.hyundairoad.comment.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Long commentId;
    private Long courseId;
    private Long memberId;
    private String content;
}
