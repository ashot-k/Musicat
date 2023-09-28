

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



function addTrack(preloaded) {
    const ul = document.getElementById("trackListing");
    const li = document.createElement("li");
    const input = document.createElement("input");
    const span = document.createElement("span");
    span.className = "d-flex w-100 justify-content-between align-items-center";

    if(preloaded) {
        input.value = preloaded;
        input.id = input.textContent;
        input.classList.add("track");
    }
    else {
        const track = document.getElementById("added-track").value;
        if (track.length <= 0) return false;
        input.id = input.textContent;
        input.value = track;
        input.classList.add("track");
    }
    input.style.overflowWrap = "anywhere";
    span.append(input);
    const removeBtn = document.createElement("button");
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
    const trackList = document.getElementsByClassName("track");
    console.log(trackList);
    const tracks = [];

    if(trackList.length > 0)
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
            $("#form").submit();
        },
        error: function (xhr, status, error) {
            console.log("Error:", error);
        }
    });
}
