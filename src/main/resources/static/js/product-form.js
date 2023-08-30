

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



//const tracks = [];
function addTrack(preloaded) {
    var ul = document.getElementById("trackListing");
    var li = document.createElement("li");
    var input = document.createElement("input");
    var span = document.createElement("span");
    span.className = "d-flex w-100 justify-content-between align-items-center";

    if(preloaded) {
        input.value = preloaded;
        input.id = input.textContent;
        input.classList.add("track");
    }
    else {
        var track = document.getElementById("added-track").value;
        if (track.length <= 0) return false;
        input.id = input.textContent;
        input.value = track;
        input.classList.add("track");
    }
    input.style.overflowWrap = "anywhere";
    span.append(input);
    var removeBtn = document.createElement("button");
    removeBtn.className = "btn-close border-1 border-dark";
    removeBtn.onclick = function () {
        li.remove();
    };
    span.append(removeBtn);
    li.style.height = "fit-content";
    li.className="list-group-item";
    li.append(span);
    ul.appendChild(li);
}

function sendTrackData() {
    var trackList = document.getElementsByClassName("track")
    const tracks = [];


    for (let i = 0; i < trackList.length; i++) {
        if(trackList[i].value.length > 0)
        tracks[i] = trackList[i].value;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(tracks),//send data directly
        url: '/admin/add-tracks',
        asynch: false,
        success: function () {
            //  alert("success");
        },
        error: function (xhr, status, error) {
            console.log("Error:", error);
        }
    });
    $("#form").submit();
}





/* var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");*/