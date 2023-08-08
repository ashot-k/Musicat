
const tracks = [];
$(document).ready(function () {
    $('#imageFile').change(function () {
        showImage(this);
    });
})
function showImage(fileInput) {
    file = fileInput.files[0];

    reader = new FileReader();
    reader.onload = function (e) {
        $('#preview').attr('src', e.target.result)
    };

    reader.readAsDataURL(file);
}

function addTrack(preloaded) {
    var ul = document.getElementById("trackListing");
    var li = document.createElement("li");
    var name = document.createElement("h6");
    var span = document.createElement("span");
    span.className = "d-flex w-100 justify-content-between align-items-center";

    console.log(preloaded);
    if(preloaded) {
        name.textContent = preloaded;
        name.id = name.textContent;

    }
    else {
        var track = document.getElementById("added-track").value;
        if (track.length <= 0) return false;
        name.id = name.textContent;
        name.textContent = track;
        tracks.push(track);
        console.log(tracks);
    }
    name.style.overflowWrap = "anywhere";
    span.append(name);
    var removeBtn = document.createElement("button");
    removeBtn.className = "btn-close border-1 border-dark";
    removeBtn.onclick = function () {
        removeTrack(name, li);
    };
    span.append(removeBtn);
    li.style.height = "fit-content";
    li.className="list-group-item";
    li.append(span);
    ul.appendChild(li);
    sendTrackData();
}





function removeTrack(name, li) {
    li.remove();
    tracks.splice(tracks.indexOf(name.textContent), 1);
    console.log(tracks);
    sendTrackData();
}


function sendTrackData() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        //   dataType: "json",
        data: JSON.stringify(tracks),//send data directly
        url: '/admin/add-tracks',
        asynch: false,
        success: function () {
            //  alert("success");
        },
        error: function (xhr, status, error) {
            console.log("Error:", error);
            //   alert("Request failed: " + error);
        }
    });
}





/* var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");*/