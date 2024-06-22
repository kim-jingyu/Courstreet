package hyundairoad.hyundairoad.mypage.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hyundairoad.hyundairoad.course.domain.dto.CourseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageCourseResponse {
    private Long courseId;
    private Long memberId;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endTime;
    private Resource image;

    public static MyPageCourseResponse of(CourseResponse courseResponse) {
        return MyPageCourseResponse.builder()
                .courseId(courseResponse.courseId())
                .memberId(courseResponse.memberId())
                .title(courseResponse.title())
                .content(courseResponse.content())
                .startTime(courseResponse.startTime())
                .endTime(courseResponse.endTime())
                .image(courseResponse.courseImage())
                .build();
    }

    public static MyPageCourseResponse ofLikeCourse(CourseResponse courseResponse) {
        return MyPageCourseResponse.builder()
                .courseId(courseResponse.courseId())
                .title(courseResponse.title())
                .content(courseResponse.content())
                .startTime(courseResponse.startTime())
                .endTime(courseResponse.endTime())
                .image(courseResponse.courseImage())
                .build();
    }

//    public static MyPageCourseResponse ofCommentsCourse(CourseResponseDTO courseResponseDTO) {
//        return MyPageCourseResponse.builder()
//                .courseId(courseResponseDTO.getCourseId())
//                .title(courseResponseDTO.getTitle())
//                .content(courseResponseDTO.getTitle())
//                .startTime(courseResponseDTO.getStartTime())
//                .endTime(courseResponseDTO.getEndTime())
//                .imgOriginalName(courseResponseDTO.getImgOriginalName())
//                .imgSavedName(courseResponseDTO.getImgSavedName())
//                .build();
//    }
}
