package com.hyundairoad.hyundairoad.mypage.domain.dto;

import com.hyundairoad.hyundairoad.course.domain.dto.CourseDetailDto;
import com.hyundairoad.hyundairoad.course.domain.dto.CourseResponseDTO;
import com.hyundairoad.hyundairoad.course.domain.dto.LikedCourseResponseDTO;
import com.hyundairoad.hyundairoad.image.domain.vo.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageCourseResponseDTO {
    private Long courseId;
    private Long memberId;
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Image> imageList;
    private String imgOriginalName;
    private String imgSavedName;

    public static MyPageCourseResponseDTO of(CourseDetailDto courseDetailDto) {
        return MyPageCourseResponseDTO.builder()
                .courseId(courseDetailDto.getCourseId())
                .memberId(courseDetailDto.getMemberId())
                .title(courseDetailDto.getTitle())
                .content(courseDetailDto.getContent())
                .startTime(courseDetailDto.getStartTime())
                .endTime(courseDetailDto.getEndTime())
                .build();
    }

    public static MyPageCourseResponseDTO ofLikeCourse(LikedCourseResponseDTO likedCourseResponseDTO, List<Image> imageList) {
        return MyPageCourseResponseDTO.builder()
                .courseId(likedCourseResponseDTO.getCourseId())
                .title(likedCourseResponseDTO.getTitle())
                .content(likedCourseResponseDTO.getContent())
                .startTime(likedCourseResponseDTO.getStartTime())
                .endTime(likedCourseResponseDTO.getEndTime())
                .imageList(imageList)
                .build();
    }

    public static MyPageCourseResponseDTO ofCommentsCourse(CourseResponseDTO courseResponseDTO) {
        return MyPageCourseResponseDTO.builder()
                .courseId(courseResponseDTO.getCourseId())
                .title(courseResponseDTO.getTitle())
                .content(courseResponseDTO.getTitle())
                .startTime(courseResponseDTO.getStartTime())
                .endTime(courseResponseDTO.getEndTime())
                .imgOriginalName(courseResponseDTO.getImgOriginalName())
                .imgSavedName(courseResponseDTO.getImgSavedName())
                .build();
    }
}
