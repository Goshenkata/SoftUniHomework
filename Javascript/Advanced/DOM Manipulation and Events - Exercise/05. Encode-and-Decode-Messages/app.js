function encodeAndDecodeMessages() {
  let btns = document.querySelectorAll("button");
  let textareas = document.querySelectorAll("textarea");
  btns[0].addEventListener("click", encode);
  btns[1].addEventListener("click", decode);

  function encode() {
    let text = textareas[0].value;
    let out = "";
    for (let index = 0; index < text.length; index++) {
      out += String.fromCharCode(text[index].charCodeAt() + 1);
    }
    textareas[1].value = out;
    textareas[0].value = "";
  }

  function decode() {
    let text = textareas[1].value;
    let out = "";
    for (let index = 0; index < text.length; index++) {
      out += String.fromCharCode(text[index].charCodeAt() - 1);
    }
    textareas[0].value = out;
    textareas[1].value = "";
  }
}

