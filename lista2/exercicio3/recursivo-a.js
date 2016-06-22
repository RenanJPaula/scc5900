var valorCasas = [10, 50, 5, 0, 25];

function opt(i) {
  if(valorCasas.length <= i) {
    return 0;
  } else {
    return Math.max(opt(i + 1), opt(i + 2) + valorCasas[i]);
  }
}

console.log(opt(0));
