/**
 *
 * Created by Rodion on 26.03.2016.
 */

var markedItems = [];
var markedCounter = 0;

function mark() {
    var input = event.target;
    var wordValue = input.value;
    if (markedItems.indexOf(wordValue) === -1) {
        markedItems[markedCounter] = wordValue;
        markedCounter++;
        input.parentNode.parentNode.classList.add("item-marked");
    } else {
        unmark(wordValue, input);
    }
    trashIconResolver();
    console.log(markedItems);
}

function unmark(wordValue, input) {
    delete markedItems[markedItems.indexOf(wordValue)];
    markedCounter--;
    input.parentNode.parentNode.classList.remove("item-marked");
}

function trashIconResolver() {
    var trash = document.getElementById("trash");
    if (markedCounter === 0) {
        trash.classList.add("hidden");
    } else {
        trash.classList.remove("hidden");
    }
}

function removeItems() {
    var removed = cleanMarkedItemsList();
    //var phrasesForm = document.getElementById("phrases");
    //phrasesForm.method = "DELETE";
    ////phrasesForm.submit();
    //var removedJSON = JSON.stringify(removed);
    //console.log(removedJSON);
    $.ajax({
        url: "phrases?" + $.param({words: removed}),
        type: "delete",
        //dataType: 'json',
        //data: {words: JSON.stringify(removed)},
        success: function () {
            alert("ok");
        }
    })
}

function removeItem(resource) {
    $.ajax({
        url: "phrases",
        type: "delete",
        data: resource,
        success: function () {
            var removedElement = document.getElementById(resource);
            removedElement.remove();
        }
    });
}

function cleanMarkedItemsList() {
    var cleanedList = [];
    for (var i = 0; i < markedItems.length; i++) {
        if (markedItems[i] !== undefined) {
            cleanedList.push(markedItems[i]);
        }
    }
    markedItems = [];
    return cleanedList;
}