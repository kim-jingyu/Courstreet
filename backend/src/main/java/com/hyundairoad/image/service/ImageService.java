package com.hyundairoad.image.service;

import com.hyundairoad.image.exception.ImageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static com.hyundairoad.global.constants.ImageConstants.UPLOAD_DIR;

/**
 * ImageService
 *
 * 작성자: 김진규
 */
@Service
@RequiredArgsConstructor
public class ImageService {
    /**
     * 파일을 업로드합니다.
     *
     * @param file 업로드할 파일
     * @return 새 파일 이름
     * @throws IOException 파일 저장 중 오류 발생 시
     */
    public String uploadFile(MultipartFile file) throws IOException {
        checkImageExists(file);
        String newFilename = getNewFilename(file);
        saveFile(file, newFilename);
        return newFilename;
    }

    /**
     * 파일을 삭제합니다.
     *
     * @param imgUrl 삭제할 파일의 URL
     * @throws IOException 파일 삭제 중 오류 발생 시
     */
    public void deleteFile(String imgUrl) throws IOException {
        Files.deleteIfExists(Paths.get(UPLOAD_DIR + File.separator + imgUrl));
    }

    /**
     * 파일을 업데이트합니다.
     *
     * @param oldImgUrl 이전 파일의 URL
     * @param file      새 파일
     * @return 새 파일 이름
     * @throws IOException 파일 저장 또는 삭제 중 오류 발생 시
     */
    public String updateFile(String oldImgUrl, MultipartFile file) throws IOException {
        checkImageExists(file);
        deleteFile(oldImgUrl);
        String newFilename = getNewFilename(file);
        saveFile(file, newFilename);
        return newFilename;
    }

    /**
     * 파일이 존재하는지 확인합니다.
     *
     * @param file 확인할 파일
     * @throws ImageNotFoundException 파일이 존재하지 않을 경우 예외 발생
     */
    private void checkImageExists(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ImageNotFoundException();
        }
    }

    /**
     * 파일을 저장합니다.
     *
     * @param file        저장할 파일
     * @param newFilename 새 파일 이름
     * @throws IOException 파일 저장 중 오류 발생 시
     */
    private void saveFile(MultipartFile file, String newFilename) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(getFullImageUrl(newFilename));
        Files.write(path, bytes);
    }

    /**
     * 새 파일 이름을 생성합니다.
     *
     * @param file 원본 파일
     * @return 새 파일 이름
     */
    private String getNewFilename(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "txt";
        return UUID.randomUUID().toString() + fileExtension;
    }

    /**
     * 전체 이미지 URL을 반환합니다.
     *
     * @param fileName 파일 이름
     * @return 전체 이미지 URL
     */
    private String getFullImageUrl(String fileName) {
        return UPLOAD_DIR + File.separator + fileName;
    }
}
