/**
 *
 * Created by Rodion on 26.03.2016.
 */

var markedItems = [];
var markedCounter = 0;
var deleteEnabled = false;

function mark() {
    var input = event.target;
    var wordValue = input.parentNode.parentNode.childNodes[7].childNodes[0].data;
    if (markedItems.indexOf(wordValue) === -1) {
        markedItems[markedCounter] = wordValue;
        markedCounter++;
        input.parentNode.parentNode.classList.add("item-marked");
    } else {
        unmark(wordValue, input);
    }
    enablingDeleteButton();
    displayEnabling();
}

function unmark(wordValue, input) {
    delete markedItems[markedItems.indexOf(wordValue)];
    markedCounter--;
    input.parentNode.parentNode.classList.remove("item-marked");
    enablingDeleteButton();
}


function enablingDeleteButton() {
    if (markedCounter == 0) {
        deleteEnabled = false;
    } else {
        deleteEnabled = true;
    }
}

function displayEnabling() {
    var deleteAllButton = document.getElementById("deleteAll");
    if (deleteEnabled) {
        if (deleteAllButton.classList.contains("disabled-button")) {
            deleteAllButton.classList.remove("disabled-button");
        }
    } else {
        if (!deleteAllButton.classList.contains("disabled-button")) {
            deleteAllButton.classList.add("disabled-button");
        }
    }
}

// Validation user credentials

document.getElementsByTagName("input").onchange = function (event) {
    console.log("changed");
}


