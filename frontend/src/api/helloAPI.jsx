import instance from "./api";

const helloAPI = () => {
    return instance.get('/api/hello');
};

export default helloAPI;