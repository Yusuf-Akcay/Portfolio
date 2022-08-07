function setMarginRight() {
    document.getElementById("form").style.marginRight  = "20em";
}

function removeMarginRight() {
    document.getElementById("form").style.marginRight  = "0px";
}

function categorytoggle(){
    if (! document.querySelector(".speedDial").classList.contains("toggleActive")) {
        document.querySelector(".speedDial").classList.add("toggleActive");
        document.querySelector(".ui-button-icon-only").classList.add("hiddenIcon");
    } else {
        document.querySelector(".speedDial").classList.remove("toggleActive");
        document.querySelector(".ui-button-icon-only").classList.remove("hiddenIcon");
    }
}