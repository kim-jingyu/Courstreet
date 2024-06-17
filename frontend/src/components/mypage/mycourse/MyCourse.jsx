import { Tabs, Tab, CourseContainer } from './MyCourse.style';
import CourseItem from '/src/components/course/course-item/CourseItem';

function MyCourse() {
    return (
        <>
            <Tabs>
                <Tab active>나의 코스</Tab>
                <Tab>좋아요</Tab>
                <Tab>나의 댓글</Tab>
            </Tabs>
            <CourseContainer>
                <CourseItem />
                <CourseItem />
                <CourseItem />
            </CourseContainer>
        </>
    )
}

export default MyCourse;