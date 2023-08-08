var draggedItem = null;

function dragStart(event) {
    draggedItem = event.target;
    event.dataTransfer.setData('text/plain', ''); // Required for Firefox
}

function dragOver(event) {
    event.preventDefault();
}

function dragEnter(event) {
    event.target.classList.add('drag-over');
}

function dragLeave(event) {
    event.target.classList.remove('drag-over');
}

function drop(event) {
    event.preventDefault();
    event.target.classList.remove('drag-over');
    var parent = draggedItem.parentNode;
    var nextSibling = draggedItem.nextSibling;
    if (nextSibling === event.target) {
        parent.insertBefore(draggedItem, nextSibling.nextSibling);
    } else {
        parent.insertBefore(draggedItem, event.target.nextSibling);
    }
}
