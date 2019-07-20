function goBack(fromUrl){
    if(fromUrl === "login.jsp" || fromUrl==="register.jsp") {
        return "index.jsp";
    }
    return fromUrl;
}
