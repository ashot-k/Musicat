$(document).ready(function () {
    $('#imageFile').change(function () {
        showImage(this);
    });
})

function showImage(fileInput) {
    let file = fileInput.files[0];
    let reader = new FileReader();
    reader.onload = function (e) {
        $('#preview').attr('src', e.target.result)
    };
    reader.readAsDataURL(file);
}


function deleteTrackField(e) {
    e.parentNode.remove();
}

function addTrackField(preloaded) {
    const trackContainer = document.querySelector('#trackContainer');
    let trackTemplate =
        `<li style="display: flex; align-items: center; gap: 10px" class="list-group-item">
        <input class="trackName" style="overflow-wrap: anywhere; height: 100%; width: 100%;" value="">
        <button class="btn-close border-1 border-dark"  onclick="deleteTrackField(this)"></button>
        </li>`;

    if (preloaded) {
        trackTemplate =
            `<li style="display: flex; align-items: center; gap: 10px" class="list-group-item">
             <input class="trackName" style="overflow-wrap: anywhere; height: 100%; width: 100%;" 
             value="${preloaded}">
             <button class="btn-close border-1 border-dark"  onclick="deleteTrackField(this)"></button>
             </li>`;
    }
    trackContainer.insertAdjacentHTML('beforeend', trackTemplate);
}
function test(){
   sendTrackData()
}
function sendTrackData() {
    const trackList = document.getElementsByClassName("trackName");
    const tracks = [];
    if (trackList.length > 0) {
        for (let i = 0; i < trackList.length; i++) {
            if (trackList[i].value.length > 0)
                tracks[i] = trackList[i].value;
        }
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(tracks),//send data directly
        url: '/admin/add-tracks',
        asynch: false,
        success: function (data) {
            console.log(data)
            $("#form").submit();
        },
        error: function (xhr, status, error) {
            console.log("Error:", error);
        }
    });
}
