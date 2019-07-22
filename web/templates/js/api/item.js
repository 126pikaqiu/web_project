function addHot(data) {
    return axios({
        url:api.add_hot,
        type:'post',
        data:data
    })
}