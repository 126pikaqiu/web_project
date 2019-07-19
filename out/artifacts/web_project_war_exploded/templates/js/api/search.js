function searchByOrder(data) {
    return axios({
        url: api.search,
        type: "GET",
        data: {...data}
    })
}