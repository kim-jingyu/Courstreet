package com.hyundairoad.course.controller;

import com.hyundairoad.course.domain.dto.CourseCreateRequest;
import com.hyundairoad.course.domain.dto.CourseResponse;
import com.hyundairoad.course.domain.dto.CourseUpdateRequest;
import com.hyundairoad.course.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Collections;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private CourseResponse courseResponse;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}",
                        preprocessRequest(Preprocessors.prettyPrint()),
                        preprocessResponse(Preprocessors.prettyPrint())))
                .build();

        Resource courseImage = new ByteArrayResource("image content".getBytes());
        courseResponse = CourseResponse.builder()
                .courseId(1L)
                .memberId(1L)
                .nickname("test_nickname")
                .title("test_title")
                .content("test_content")
                .startTime(LocalDate.of(2023, 6, 1))
                .endTime(LocalDate.of(2023, 6, 30))
                .theme("test_theme")
                .withWhom("test_with_whom")
                .visibility("PUBLIC")
                .courseImage(courseImage)
                .liked(true)
                .build();
    }

    @Test
    public void getAllCourses() throws Exception {
        Mockito.when(courseService.getAllCourses()).thenReturn(Collections.singletonList(courseResponse));

        this.mockMvc.perform(get("/api/course/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseId").value(courseResponse.courseId()))
                .andExpect(jsonPath("$[0].memberId").value(courseResponse.memberId()))
                .andExpect(jsonPath("$[0].nickname").value(courseResponse.nickname()))
                .andExpect(jsonPath("$[0].title").value(courseResponse.title()))
                .andExpect(jsonPath("$[0].content").value(courseResponse.content()))
                .andExpect(jsonPath("$[0].startTime").value(courseResponse.startTime().toString()))
                .andExpect(jsonPath("$[0].endTime").value(courseResponse.endTime().toString()))
                .andExpect(jsonPath("$[0].theme").value(courseResponse.theme()))
                .andExpect(jsonPath("$[0].withWhom").value(courseResponse.withWhom()))
                .andExpect(jsonPath("$[0].visibility").value(courseResponse.visibility()))
                .andExpect(jsonPath("$[0].liked").value(courseResponse.liked()))
                .andDo(document("get-all-courses",
                        preprocessRequest(Preprocessors.prettyPrint()),
                        preprocessResponse(Preprocessors.prettyPrint())));
    }

    @Test
    public void getCourseDetail() throws Exception {
        Mockito.when(courseService.getCourseDetail(1L)).thenReturn(courseResponse);

        this.mockMvc.perform(get("/api/course/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value(courseResponse.courseId()))
                .andExpect(jsonPath("$.memberId").value(courseResponse.memberId()))
                .andExpect(jsonPath("$.nickname").value(courseResponse.nickname()))
                .andExpect(jsonPath("$.title").value(courseResponse.title()))
                .andExpect(jsonPath("$.content").value(courseResponse.content()))
                .andExpect(jsonPath("$.startTime").value(courseResponse.startTime().toString()))
                .andExpect(jsonPath("$.endTime").value(courseResponse.endTime().toString()))
                .andExpect(jsonPath("$.theme").value(courseResponse.theme()))
                .andExpect(jsonPath("$.withWhom").value(courseResponse.withWhom()))
                .andExpect(jsonPath("$.visibility").value(courseResponse.visibility()))
                .andExpect(jsonPath("$.liked").value(courseResponse.liked()))
                .andDo(document("get-course-detail",
                        preprocessRequest(Preprocessors.prettyPrint()),
                        preprocessResponse(Preprocessors.prettyPrint())));
    }

    @Test
    public void deleteCourse() throws Exception {
        Mockito.doNothing().when(courseService).deleteCourse(1L);

        this.mockMvc.perform(delete("/api/course/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(document("delete-course",
                        preprocessRequest(Preprocessors.prettyPrint()),
                        preprocessResponse(Preprocessors.prettyPrint())));
    }

    @Test
    public void updateCourse() throws Exception {
        Mockito.doNothing().when(courseService).updateCourse(Mockito.eq(1L), Mockito.any(CourseUpdateRequest.class));

        this.mockMvc.perform(put("/api/course/{id}", 1L)
                        .param("name", "New Course Name") // Example parameter, adjust as needed
                        .param("description", "Updated description")) // Example parameter, adjust as needed
                .andExpect(status().isOk())
                .andDo(document("update-course",
                        preprocessRequest(Preprocessors.prettyPrint()),
                        preprocessResponse(Preprocessors.prettyPrint())));
    }

    @Test
    public void createCourse() throws Exception {
        Mockito.doNothing().when(courseService).createCourse(Mockito.any(CourseCreateRequest.class));

        this.mockMvc.perform(post("/api/course")
                        .param("name", "New Course") // Example parameter, adjust as needed
                        .param("description", "Course description")) // Example parameter, adjust as needed
                .andExpect(status().isOk())
                .andDo(document("create-course",
                        preprocessRequest(Preprocessors.prettyPrint()),
                        preprocessResponse(Preprocessors.prettyPrint())));
    }

    @Test
    public void getCourseSearched() throws Exception {
        Mockito.when(courseService.searchCourse("keyword")).thenReturn(Collections.singletonList(courseResponse));

        this.mockMvc.perform(get("/api/course")
                        .param("keyword", "keyword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseId").value(courseResponse.courseId()))
                .andExpect(jsonPath("$[0].memberId").value(courseResponse.memberId()))
                .andExpect(jsonPath("$[0].nickname").value(courseResponse.nickname()))
                .andExpect(jsonPath("$[0].title").value(courseResponse.title()))
                .andExpect(jsonPath("$[0].content").value(courseResponse.content()))
                .andExpect(jsonPath("$[0].startTime").value(courseResponse.startTime().toString()))
                .andExpect(jsonPath("$[0].endTime").value(courseResponse.endTime().toString()))
                .andExpect(jsonPath("$[0].theme").value(courseResponse.theme()))
                .andExpect(jsonPath("$[0].withWhom").value(courseResponse.withWhom()))
                .andExpect(jsonPath("$[0].visibility").value(courseResponse.visibility()))
                .andExpect(jsonPath("$[0].liked").value(courseResponse.liked()))
                .andDo(document("search-course",
                        preprocessRequest(Preprocessors.prettyPrint()),
                        preprocessResponse(Preprocessors.prettyPrint())));
    }

    @Test
    public void getTodayPick() throws Exception {
        Mockito.when(courseService.getTodayPick()).thenReturn(Collections.singletonList(courseResponse));

        this.mockMvc.perform(get("/api/course/today-pick"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseId").value(courseResponse.courseId()))
                .andExpect(jsonPath("$[0].memberId").value(courseResponse.memberId()))
                .andExpect(jsonPath("$[0].nickname").value(courseResponse.nickname()))
                .andExpect(jsonPath("$[0].title").value(courseResponse.title()))
                .andExpect(jsonPath("$[0].content").value(courseResponse.content()))
                .andExpect(jsonPath("$[0].startTime").value(courseResponse.startTime().toString()))
                .andExpect(jsonPath("$[0].endTime").value(courseResponse.endTime().toString()))
                .andExpect(jsonPath("$[0].theme").value(courseResponse.theme()))
                .andExpect(jsonPath("$[0].withWhom").value(courseResponse.withWhom()))
                .andExpect(jsonPath("$[0].visibility").value(courseResponse.visibility()))
                .andExpect(jsonPath("$[0].liked").value(courseResponse.liked()))
                .andDo(document("get-today-pick",
                        preprocessRequest(Preprocessors.prettyPrint()),
                        preprocessResponse(Preprocessors.prettyPrint())));
    }
}
