
var elementos = [0, 1, 2, 5, 6, 7, 5, 2, 0];

function dividirConquistar(elementos) {
  var length = elementos.length;

  if(length < 0) {
    return null;
  } else if(length == 1 || length == 2) {
    return elementos[0] > elementos[1] ? elementos[0] : elementos[1];
  } else {
    var meio = length >> 1
      , elemento = elementos[meio]
      , esquerda = elementos[meio - 1]
      , direita = elementos[meio + 1];

    if(elemento > esquerda && elemento > direita) {
      return elemento;
    } else if(elemento > esquerda && elemento < direita) {
      return dividirConquistar(elementos.slice(meio));
    } else {
      return dividirConquistar(elementos.slice(0, meio));
    }
  }
}

console.log(dividirConquistar(elementos));
