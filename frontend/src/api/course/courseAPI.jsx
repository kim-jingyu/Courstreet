import instance from "../api";

const courseAPI = (memberId) => {
    return instance.get('/api/courses', {
        params: {
            memberId,
        },
    });
};

export default courseAPI;