<div align="center">
  <br />
  <img width="529" alt="image" src="https://github.com/user-attachments/assets/33914d0b-6c62-4b80-8fc9-d9b60937cfc3">
  <br />
</div>

# Courstreet : 더 현대 서울 방문객들의 소비를 촉진하기 위한 개인 맞춤형 가이드 시스템

## 🎯 내가 구현한 기능
- 백엔드
  - 전역 에러 핸들링
  - 인증(JWT 토큰 인증, OAuth2.0 구글/카카오 간편 인증, 비밀번호 이메일 인증)
  - 인가(관리자/일반 회원용 인가)
  - 회원(회원가입, 비밀번호 수정, 프로필 이미지 조회/수정, 회원 비활성화/탈퇴)
  - 코스/장소 좋아요 기능
  - 장소 별점 부여 기능
- 리액트
  - 회원가입
  - 로그인
  - 마이페이지

<br>

# 🖥 **프로젝트 개요**

### 팀원 소개

<table>
    <tr>
        <td height="140px" align="center"> <a href="https://github.com/Dayoung1014">
            <img src="https://avatars.githubusercontent.com/kim-jingyu" width="140px" /> <br><br> 👑 김진규 <br>(회원 도메인) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/uttamapaksa">
            <img src="https://avatars.githubusercontent.com/uttamapaksa" width="140px" /> <br><br> 🐟 김준섭 <br>(코스 도메인) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/ssh5212">
            <img src="https://avatars.githubusercontent.com/wlstnam" width="140px" /> <br><br> 🐲 남진수 <br>(코스 도메인) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/qkdk">
            <img src="https://avatars.githubusercontent.com/hodupie" width="140px" /> <br><br> 🐰 조희정 <br>(장소 도메인) </a> <br></td>
    </tr>
</table>

<br>

### 서비스 배경
1. 방문객의 소비 증대  
  더현대 서울의 객단가가 다소 낮다는 평가를 받고 있습니다. 개장 첫해보다 객단가가 감소했고, 업계 평균과 비교했을 때도 비교적 낮은 수준입니다. 방문객 수는 꾸준히 늘어나고 있지만, 매출 증가 속도가 이를 따라가지 못해 내실을 다지는 노력이 필요할 것이라고 생각합니다.
2. 맞춤형 가이드 제공  
   현재 인터넷 상에 다양한 더현대 추천 매장 및 가이드가 존재하지만, 개별 방문객의 취향에 꼭 맞는 가이드를 찾는 것은 어렵습니다. 저희는 방문객의 취향과 관심사를 반영한 맞춤형 가이드를 제공하여, 각자가 선호하는 매장과 제품을 쉽게 찾을 수 있도록 하고자 합니다. 이를 통해 방문객이 더 현대 서울에서 보다 합리적이고 실제적인 소비를 할 수 있도록 유도할 수 있을 것입니다.  
  또한 방문객들에게 개인화된 쇼핑 경험을 제공하여, 소비 촉진뿐만 아니라 전반적인 더현대 방문에 대한 만족도를 높이는 데 기여할 것입니다. 이 프로젝트는 더 현대 서울을 단순한 쇼핑 공간이 아닌 복합문화공간으로서의 이미지를 더욱 강화하고, 방문객에게 특별하고 개별화된 경험을 제공함으로써 브랜드 가치를 높이고자 합니다.

<br>

### 기대 효과
맞춤형 추천 코스를 통해 더현대에 입점한 매장과 공간들에 대한 고객들의 실제 평가 데이터를 수집할 수 있습니다. 이는 향후 더현대에 입점할 매장 선정 및 새로운 팝업스토어 브랜드 선정에 유용한 자료로 활용될 것입니다.  
또한, 이 서비스를 더현대 서울뿐만 아니라 더현대 대구 및 현대백화점 전 지점에 적용할 경우, 백화점 방문객들의 만족도 데이터를 체계적으로 축적할 수 있어 현대백화점의 지속적인 발전에 기여할 것으로 예상됩니다.

<br>

# 🔎 프로젝트 설계

### 기능 명세서
<table>
    <thead>
        <tr>
            <th>구분</th>
            <th>주 기능</th>
            <th>상세 기능</th>
            <th>설명</th>
            <th>담당자</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan="1">글로벌 설정</td>
            <td>에러 핸들링</td>
            <td>전역 에러 핸들링</td>
            <td>
                - ErroCode, ErrorResponse 생성 및 RuntimeException을 상속한 HyundaiRoadException를 선언하여 전역 예외 처리를 통한<br>
                - RestControllerAdvice를 활용한 GlobalExceptionHandler를 선언하여 전역 예외 처리
            </td>
            <td>김진규</td>
        </tr>
        <tr>
            <td rowspan="1">API 명세화</td>
            <td>백엔드 API 명세화</td>
            <td>Swagger 설정을 통한 백엔드 API 명세화</td>
            <td>- Swagger를 활용하여 백엔드 API 명세화</td>
            <td>김진규</td>
        </tr>
        <tr>
            <td rowspan="4">인증</td>
            <tr>
              <td>관리자용</td>
              <td>JWT, Refresh Token, Access Token을 통한 토큰 인증</td>
              <td>
                  - JWT 및 AccessToken, RefreshToken 개념 적용<br>
                  - Redis 캐시를 사용하여 RefreshToken 저장 후 인증에 활용<br>
                  - RefreshToken을 사용하여 AccessToken을 갱신<br>
                  - Redis에서 RefreshToken을 제거하여 로그아웃 기능 처리<br>
                  - AdminCheck Parameter용 어노테이션 선언 및 AdminLoginArgumentResolver를 활용한 토큰 로그인 인증 도메인 구현
              </td>
              <td>김진규</td>
            </tr>
            <tr>
                <td>일반 회원용</td>
                <td>구글, 카카오 간편 인증</td>
                <td>
                    - JWT 및 AccessToken, RefreshToken 개념 적용<br>
                    - Redis 캐시를 사용하여 RefreshToken 저장 후 인증에 활용<br>
                    - RefreshToken을 사용하여 AccessToken을 갱신<br>
                    - Redis에서 RefreshToken을 제거하여 로그아웃 기능 처리<br>
                    - MebverCheck Parameter용 어노테이션 선언
                </td>
                <td>김진규</td>
            </tr>
            <tr>
                <td>비밀번호 찾기</td>
                <td>구글 SMTP 서버를 활용한 비밀번호 찾기를 위한 이메일 인증</td>
                <td>
                    - 구글 SMTP 서버를 활용하여 비밀번호 찾기를 위해 사용자에 이메일로 인증 번호 전송<br>
                    - RedisTemplate을 활용하여 인증 번호를 캐시에 저장 후 인증 번호와 대조
                </td>
                <td>김진규</td>
            </tr>
        </tr>
        <tr>
          <td rowspan="3">인가</td>
          <tr>
                <td>관리자 인가</td>
                <td>관리자용 인가 기능 구현</td>
                <td>
                    - AdminOnly 메서드용 어노테이션 선언 및 AdminOnlyChecker AOP를 활용한 admin 도메인용 인가 기능 구현
                </td>
                <td>김진규</td>
            </tr>
            <tr>
                    <td>일반 회원 인가</td>
                    <td>일반 회원용 인가 기능 구현</td>
                    <td>
                        - MemberOnly 메서드용 어노테이션 선언 및 MemberOnlyChecker AOP를 활용한 전역적으로 member 도메인을 위한 인가 기능 구현
                    </td>
                    <td>김진규</td>
                </tr>
            </tr>
            <tr>
                <td rowspan="8">회원</td>
                <tr>
                  <td rowspan="3">관리자</td>
                  <tr>
                    <td>관리자 생성</td>
                    <td></td>
                    <td>김진규</td>    
                  </tr>
                  <tr>
                    <td>관리자 비밀번호 수정</td>
                    <td></td>
                    <td>김진규</td>
                  </tr>
                </tr>
                <tr>
                    <td rowspan="4">일반 회원</td>
                    <td>회원 프로필 이미지 조회</td>
                    <td></td>
                    <td>김진규</td>
                </tr>
                <tr>
                    <td>회원 프로필 이미지 수정</td>
                    <td></td>
                    <td>김진규</td>
                </tr>
                <tr>
                    <td>회원 비활성화</td>
                    <td>
                        - 인증된 회원 정보를 받아 회원의 상태를 비활성화 상태로 업데이트
                    </td>
                    <td>김진규</td>
                </tr>
                <tr>
                    <td>회원 탈퇴</td>
                    <td>
                        - 회원, 코스, 장소, 좋아요, 별점 도메인 연관관계를 고려하여 Cascading, OrphanRemoval 옵션을 설정하여 회원 탈퇴 시 좋아요 및 별점에 의해 코스와 장소와 관련된 테이블을 삭제하고, 해당 회원이 만든 코스를 삭제
                    </td>
                    <td>김진규</td>
                </tr>
            </tr>
            <tr>
              <tr>
                  <td rowspan="3">이미지</td>
                  <td>이미지 파일 기능</td>
                  <td>이미지 파일 업로드</td>
                  <td>- 로컬 서버에 이미지 파일 업로드 기능 구현</td>
                  <td>김진규</td>
              </tr>
              <tr>
                  <td>이미지 파일 삭제</td>
                  <td></td>
                  <td>- 로컬 서버에 존재하는 이미지 파일 삭제 기능 구현</td>
                  <td>김진규</td>
              </tr>
              <tr>
                  <td>이미지 파일 수정</td>
                  <td></td>
                  <td>- 로컬 서버에 존재하는 이미지 파일 수정 기능 구현</td>
                  <td>김진규</td>
              </tr>
            </tr>
            <tr>
                <td rowspan="4">좋아요</td>
                <td rowspan="2">코스 좋아요</td>
                <td>코스 좋아요 기능</td>
                <td>- MemberCourseLike 도메인의 좋아요 count 예 1을 추가해 좋아요 기능 구현</td>
                <td>김진규</td>
            </tr>
            <tr>
                <td>코스 좋아요 취소 기능</td>
                <td>- MemberCourseLike 도메인의 좋아요 count 를 0 으로 초기화하여 좋아요 취소 기능 구현</td>
                <td>김진규</td>
            </tr>
            <tr>
                <td rowspan="2">장소 좋아요</td>
                <td>장소 좋아요 기능</td>
                <td>- MemberPlaceLike 도메인의 좋아요 count 예 1을 추가해 좋아요 기능 구현</td>
                <td>김진규</td>
            </tr>
            <tr>
                <td>장소 좋아요 취소 기능</td>
                <td>- MemberPlaceLike 도메인의 좋아요 count 를 0 으로 초기화하여 좋아요 취소 기능 구현</td>
                <td>김진규</td>
            </tr>
            <tr>
                <td rowspan="1">별점</td>
                <td>별점 부여</td>
                <td>장소에 별점 부여 기능</td>
                <td>- MemberPlaceStar 도메인의 rate 를 별점이 이미 부여되어 있는지 여부에 따라 별점을 추가하거나 업데이트하는 기능 구현</td>
                <td>김진규</td>
            </tr>
            <tr>
                <td rowspan="5">홈 화면 (코스 게시판)</td>
                <td>오늘의 픽</td>
                <td>관리자가 선정한 코스 모아보기</td>
                <td>- 캐러셀 방식<br>- 코스 이미지, 코스 제목 노출</td>
                <td>남진수</td>
            </tr>
            <tr>
                <td rowspan="3">코스 검색</td>
                <td>테마 태그 검색</td>
                <td>- 선택한 테마에 해당되는 코스만 노출</td>
                <td>남진수</td>
            </tr>
            <tr>
                <td>최신순, 추천순 정렬</td>
                <td></td>
                <td>남진수</td>
            </tr>
            <tr>
                <td>코스 제목 검색</td>
                <td></td>
                <td>남진수</td>
            </tr>
            <tr>
                <td>코스 게시판</td>
                <td>모든 사용자가 생성한 코스 리스트 보기</td>
                <td>- 코스 이미지, 코스 제목, 코스 내용 일부 노출</td>
                <td>남진수</td>
            </tr>
            <tr>
                <td rowspan="3">코스 보기</td>
                <td>코스 담기</td>
                <td>내 코스에 저장</td>
                <td>- 사용자 아이디로 새롭게 코스 생성</td>
                <td>남진수</td>
            </tr>
            <tr>
                <td>회원 코스 조회</td>
                <td>회원과 관련된 코스 조회</td>
                <td>- 특정 회원의 정보를 받아 코스의 정보를 조회</td>
                <td>김진규</td>
            </tr>
            <tr>
                <td>오늘 추천 코스 조회</td>
                <td>오늘의 추천 코스 조회</td>
                <td>- 오늘의 추천 코스 리스트를 조회</td>
                <td>김진규</td>
            </tr>
            <tr>
                <td rowspan="10">코스 만들기</td>
                <td>기초 데이터 입력</td>
                <td></td>
                <td>- 방문일, 방문 시작 시간, 방문 종료 시간, 함께 방문하는 사람, 테마, 성별, 연령 입력</td>
                <td>김준섭</td>
            </tr>
            <tr>
                <td>필수 방문 장소 선택</td>
                <td></td>
                <td>- 다중 선택 가능</td>
                <td>김준섭</td>
            </tr>
            <tr>
                <td rowspan="7">코스 생성</td>
                <td>알고리즘으로 코스 생성</td>
                <td>- 한 시간에 한 장소 추천<br>- 점심시간(12-13), 저녁시간(16-17)에는 식당 추천</td>
                <td>남진수</td>
            </tr>
            <tr>
                <td>코스 제목, 내용 작성</td>
                <td></td>
                <td>김준섭</td>
            </tr>
            <tr>
                <td>코스 이미지 추가</td>
                <td></td>
                <td>김준섭</td>
            </tr>
            <tr>
                <td>코스 장소 추가</td>
                <td></td>
                <td>김준섭</td>
            </tr>
            <tr>
                <td>코스 장소 삭제</td>
                <td>- 드래그로 장소 순서 변경</td>
                <td>김준섭</td>
            </tr>
            <tr>
                <td>코스 장소 순서 변경</td>
                <td></td>
                <td>김준섭</td>
            </tr>
            <tr>
                <td>코스 장소마다 메모 추가</td>
                <td></td>
                <td>김준섭</td>
            </tr>
            <tr>
                <td>코스 저장</td>
                <td></td>
                <td></td>
                <td>김준섭</td>
            </tr>
            <tr>
                <td rowspan="5">마이페이지</td>
                <td rowspan="3">내 코스 보기</td>
                <td>내가 작성한 코스만 보기</td>
                <td></td>
                <td>조희정</td>
            </tr>
            <tr>
                <td>내 코스 수정하기</td>
                <td></td>
                <td>조희정</td>
            </tr>
            <tr>
                <td>내 코스 삭제하기</td>
                <td></td>
                <td>조희정</td>
            </tr>
            <tr>
                <td rowspan="3">좋아요 보기</td>
                <td>좋아요한 코스만 보기</td>
                <td></td>
                <td>조희정</td>
            </tr>
            <tr>
                <td>좋아요 보기</td>
                <td>좋아요한 장소만 보기</td>
                <td>조희정</td>
            </tr>
            <tr>
                <td rowspan="6">지도 검색</td>
            </tr>
            <tr>
                <td>지도 보기</td>
                <td>층마다 지도 보기</td>
                <td></td>
                <td>조희정</td>
            </tr>
            <tr>
                <td>장소 검색</td>
                <td>장소 이름으로 검색</td>
                <td></td>
                <td>조희정</td>
            </tr>
            <tr>
                <td>장소 정보 보기</td>
                <td>지도로 장소 위치 보기</td>
                <td></td>
                <td>조희정</td>
            </tr>
            <tr>
                <td>장소 상세정보 보기</td>
                <td>- 모달로 운영시간, 전화번호 보기</td>
                <td></td>
                <td>조희정</td>
            </tr>
            <tr>
                <td>장소 평점 주기</td>
                <td>- 별을 눌러 장소마다 평점 주기</td>
                <td></td>
                <td>조희정</td>
            </tr>
        </tr>
    </tbody>
</table>

<br>

### 시스템 구성도
<div align="center">
  <br />
  <img width="695" alt="image" src="https://github.com/user-attachments/assets/55d796ca-fb22-4dd5-b556-4c149e7c64c5">
  <br />
</div>

<br>

### ERD

<div align="center">
  <br />
  <img width="554" alt="image" src="https://github.com/user-attachments/assets/938e71df-bfbc-4f71-90fe-e14ee7fce231">
  <br />
</div>


<br>

### 로그인 화면

<div align="center">
  <br />
  <img width="455" alt="image" src="https://github.com/user-attachments/assets/ace314a2-ac00-4765-8af5-9f4e97dbfe6c">
  <br />
</div>
