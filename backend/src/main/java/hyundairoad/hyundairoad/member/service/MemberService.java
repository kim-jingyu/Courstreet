package hyundairoad.hyundairoad.member.service;

import hyundairoad.hyundairoad.course.domain.Course;
import hyundairoad.hyundairoad.course.exception.CourseNotFoundException;
import hyundairoad.hyundairoad.course.repository.CourseRepository;
import hyundairoad.hyundairoad.image.service.ImageService;
import hyundairoad.hyundairoad.member.domain.Member;
import hyundairoad.hyundairoad.member.domain.auth.dto.JoinRequest;
import hyundairoad.hyundairoad.member.domain.like.MemberCourseLike;
import hyundairoad.hyundairoad.member.domain.like.MemberPlaceLike;
import hyundairoad.hyundairoad.member.domain.like.dto.LikeCourseRequest;
import hyundairoad.hyundairoad.member.domain.like.dto.LikePlaceRequest;
import hyundairoad.hyundairoad.member.exception.*;
import hyundairoad.hyundairoad.member.repository.MemberCourseLikeRepository;
import hyundairoad.hyundairoad.member.repository.MemberPlaceLikeRepository;
import hyundairoad.hyundairoad.member.repository.MemberRepository;
import hyundairoad.hyundairoad.place.domain.Place;
import hyundairoad.hyundairoad.place.exception.PlaceNotFoundException;
import hyundairoad.hyundairoad.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import static hyundairoad.hyundairoad.global.constants.ImageConstants.UPLOAD_DIR;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberCourseLikeRepository memberCourseLikeRepository;
    private final MemberPlaceLikeRepository memberPlaceLikeRepository;
    private final CourseRepository courseRepository;
    private final PlaceRepository placeRepository;
    private final ImageService imageService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String saveProfileImage(Long memberId, MultipartFile multipartFile) throws IOException {
        Member member = getMember(memberId);
        member.changeProfileImg(UPLOAD_DIR + File.separator + imageService.uploadFile(multipartFile));
        memberRepository.save(member);
        return member.getProfileImgUrl();
    }

    @Transactional(readOnly = true)
    public Resource getProfileImage(Long memberId) throws MalformedURLException {
        return imageService.getImage(getMember(memberId).getProfileImgUrl());
    }

    public Void likeCourse(LikeCourseRequest likeCourseRequest) {
        MemberCourseLike findMemberCourseLike = memberCourseLikeRepository.findByMemberIdAndCourseId(likeCourseRequest.memberId(), likeCourseRequest.courseId());
        if (findMemberCourseLike == null) {
            memberCourseLikeRepository.save(MemberCourseLike.createMemberCourseLike(getMember(likeCourseRequest.memberId()), getCourse(likeCourseRequest.courseId())));
            return null;
        }
        if (findMemberCourseLike.getCount() != 0) throw new CannotLikeCourseException();
        findMemberCourseLike.like();
        return null;
    }

    public Void likePlace(LikePlaceRequest likePlaceRequest) {
        MemberPlaceLike findMemberPlaceLike = memberPlaceLikeRepository.findByMemberIdAndPlaceId(likePlaceRequest.memberId(), likePlaceRequest.placeId());
        if (findMemberPlaceLike == null) {
            memberPlaceLikeRepository.save(MemberPlaceLike.createMemberPlaceLike(getMember(likePlaceRequest.memberId()), getPlace(likePlaceRequest.placeId())));
            return null;
        }
        if (findMemberPlaceLike.getCount() != 0) throw new CannotLikePlaceException();
        findMemberPlaceLike.like();
        return null;
    }

    public Void cancelLikeCourse(LikeCourseRequest likeCourseRequest) {
        MemberCourseLike findMemberCourseLike = memberCourseLikeRepository.findByMemberIdAndCourseId(likeCourseRequest.memberId(), likeCourseRequest.courseId());
        if (findMemberCourseLike == null || findMemberCourseLike.getCount() != 1) throw new CannotCancelLikeCourseException();
        findMemberCourseLike.cancelLike();
        return null;
    }

    public Void cancelLikePlace(LikePlaceRequest likePlaceRequest) {
        MemberPlaceLike findMemberPlaceLike = memberPlaceLikeRepository.findByMemberIdAndPlaceId(likePlaceRequest.memberId(), likePlaceRequest.placeId());
        if (findMemberPlaceLike == null || findMemberPlaceLike.getCount() != 0) throw new CannotCancelLikePlaceException();
        findMemberPlaceLike.cancelLike();
        return null;
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

    private Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new);
    }

    private Place getPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(PlaceNotFoundException::new);
    }

    public void securityJoin(JoinRequest joinRequest) {
        joinRequest.setPassword(bCryptPasswordEncoder.encode(joinRequest.getPassword()));
        memberRepository.save(joinRequest.toEntity());
    }
}
