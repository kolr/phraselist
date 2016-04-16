/**
 *
 * Created by Rodion on 26.03.2016.
 */

var markedItems = [];
var markedCounter = 0;

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
    console.log(markedItems);
}

function unmark(wordValue, input) {
    delete markedItems[markedItems.indexOf(wordValue)];
    markedCounter--;
    input.parentNode.parentNode.classList.remove("item-marked");
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