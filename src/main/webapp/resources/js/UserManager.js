/**
 * Created by Rodion on 24.06.2016.
 */
window.onload = function () {
    var fieldsIDs = ["inputName1", "inputLastname1", "inputLogin1", "inputEmail1", "inputPassword1"];
    var fields = initialize(fieldsIDs);
    $('#signUp').on('shown.bs.modal', function () {
        for (var i = 0; i < fieldsIDs.length; i++) {
            var field = document.getElementById(fieldsIDs[i]);
            field.onblur = function (e) {
                var tar = e.target;
                var res = fields.get(tar.id).validate(tar.value);
                if (res) {
                    document.getElementById(tar.id + "-error").classList.add("error-hidden")
                } else {
                    document.getElementById(tar.id + "-error").classList.remove("error-hidden")
                }
            }
        }
    });
    wordValidation();
};

function wordValidation() {
    $('add-word-button').on('click', function () {
        var foreignField = new Field("foreign", true, /[a-zA-Zа-яА-Я]{2,30}/i)
        var translationField = new Field("translation", true, /[a-zA-Zа-яА-Я]{2,30}/i)
        if (foreignField.validate(document.getElementById("foreign").value) &&
            translationField.validate(document.getElementById("translation").value)) {
            console.log("good");
        } else {
            console.log("bad");
        }

    });
}


var Field = function (name, required, regexp) {
    if (required != null) this.required = required;
    if (regexp != null) this.regexp = regexp;
    if (name != null) this.name = name;
    this.validate = function (value) {
        return this.regexp.test(value);
    }
    Field.prototype = {
        name: "field",
        regexp: /^[A-z0-9-_+. ,@]+$/i,
        valid: false,
        required: true,
        reset: function () {
            this.valid = false;
        }
    }
};


function initialize(fields) {
    var fieldsMap = new Map();
    for (var i = 0; i < fields.length; i++) {
        fieldsMap.set(fields[i], new Field(fields[i], true, getRegExp(fields[i])));
    }
    return fieldsMap;
};

function getRegExp(fieldName) {
    var regExp = null;
    if (fieldName.toLowerCase().indexOf("name") !== -1) {
        regExp = /[a-zA-Zа-яА-Я]{3,30}/i;
    } else if (fieldName.toLowerCase().indexOf("email") !== -1) {
        regExp = /[A-Z0-9._%-]+@[A-Z0-9._%-]+\.[A-Z]{2,4}/i;
    } else if (fieldName.toLowerCase().indexOf("password") !== -1) {
        regExp = /[^!"№;%:?*()_]{8,30}/;
    }
    return regExp;
}
