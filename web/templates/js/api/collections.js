function addCollection(data) {
    return axios({
        url:api.add_collection,
        type:"post",
        data:{...data, 'method':'put'}
    })
}
function deleteCollection(data) {
    return axios({
        url:api.delete_collection,
        type:"post",
        data:{...data, 'method':'DELETE'}
    })
}