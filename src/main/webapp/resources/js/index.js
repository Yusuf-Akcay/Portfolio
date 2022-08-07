const yusuf = "Yusuf.".split("");

const developer = "Entwickler.".split("");

const timeOutSeconds = 0.4;
const element = document.getElementById("text");

function writeText1(textArray, currentChar = 0) {
    setTimeout( () => {
            element.innerHTML += textArray[currentChar];
            return currentChar + 1 !== textArray.length ?
                writeText1(textArray, currentChar + 1) :
                deleteText1(textArray, textArray.length);
        },
        timeOutSeconds * 1000
    );
}

function deleteText1(textArray, currentChar = 0) {
    setTimeout( () => {
            const elementTextArray = element.innerHTML.split("");
            element.innerHTML = elementTextArray.splice(0, currentChar - 1).join("");
            return elementTextArray.length !== 0 ?
                deleteText1(textArray, elementTextArray.length - 1) :
                writeText2(developer, 0);
        },
        timeOutSeconds * 1000
    );
}

function writeText2(textArray, currentChar = 0) {
    setTimeout( () => {
            element.innerHTML += textArray[currentChar];
            return currentChar + 1 !== textArray.length ?
                writeText2(textArray, currentChar + 1) :
                deleteText2(textArray, textArray.length);
        },
        timeOutSeconds * 1000
    );
}

function deleteText2(textArray, currentChar = 0) {
    setTimeout( () => {
            const elementTextArray = element.innerHTML.split("");
            element.innerHTML = elementTextArray.splice(0, currentChar - 1).join("");
            return elementTextArray.length !== 0 ?
                deleteText2(textArray, elementTextArray.length - 1) :
                writeText1(yusuf, 0);
        },
        timeOutSeconds * 1000
    );
}

writeText1(yusuf);