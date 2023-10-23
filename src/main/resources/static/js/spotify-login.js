const client_id = '2589aeb7464143cdaca99be59934136c';
const client_secret = 'c6d6314534e24a81902e7784a53c0c27';
function spotifyLogin() {

requestAuthorization();
    //const encodedData = btoa(clientId + ':' + clientSecret);

   /* $.ajax({
        url: 'https://accounts.spotify.com/api/token',
        type: 'POST',
        headers: {
            'Authorization': 'Basic ' + encodedData,
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: {
            'grant_type': 'client_credentials'
        },
        success: function (response) {
            const accessToken = response.access_token;
            // Use the access token for further API calls
            console.log('Access Token:', accessToken);
        },
        error: function (xhr, status, error) {
            console.error('Error:', error);
        }
    });*/

}
const AUTHORIZE = "https://accounts.spotify.com/authorize"
const redirect_uri = "http://localhost:8080/";

function requestAuthorization(){
    /*client_id = document.getElementById("clientId").value;
    client_secret = document.getElementById("clientSecret").value;*/
    localStorage.setItem("client_id", client_id);
    localStorage.setItem("client_secret", client_secret); // In a real app you should not expose your client_secret to the user

    let url = AUTHORIZE;
    url += "?client_id=" + client_id;
    url += "&response_type=code";
    url += "&redirect_uri=" + encodeURI(redirect_uri);
    url += "&show_dialog=true";
    url += "&scope=user-read-private user-read-email user-modify-playback-state user-read-playback-position user-library-read streaming user-read-playback-state user-read-recently-played playlist-read-private";
    window.location.href = url; // Show Spotify's authorization screen
}
function onPageLoad(){
    if(window.location.search.length > 0){
        handleRedirect();
    }
}
function handleRedirect(){
    let code = null;
    const queryString = window.location.search;
    if(queryString.length > 0){
        const params = new URLSearchParams(queryString);
        code = params.get("code");
    }
    console.log(code);
    return code;
}