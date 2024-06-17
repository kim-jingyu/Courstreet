import './CourseItem.style'
import { CourseItemWrapper, CourseHeader, CourseImage, CourseDetails, UserName, DateRange, CourseTitle, CourseContent } from './CourseItem.style';

function CourseItem() {
    return (
        <CourseItemWrapper>
          <CourseHeader>
            <CourseImage />
            <CourseDetails>
              <UserName>JADEN</UserName>
              <DateRange>2022.05.01 ~ 2022.06.01</DateRange>
            </CourseDetails>
          </CourseHeader>
          <CourseTitle>코스 이름</CourseTitle>
          <CourseContent>코스 내용</CourseContent>
        </CourseItemWrapper>
    )
}

export default CourseItem;