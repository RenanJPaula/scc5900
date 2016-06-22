
// Relação de recorrencia: OPT(I) = MAX{ OPT(i + 1), OPT(i + 2) + V[i] }

var valorCasas = [10, 50, 5, 0, 25]
  , memorizacao = [];

function opt(i) {
  if(valorCasas.length <= i) {
    return 0;
  } else {
    return Math.max(opt(i + 1), opt(i + 2) + valorCasas[i]);
  }
}

for (var i = 0; i < valorCasas.length; i++) {
  memorizacao[i] = opt(i);
}

console.log(memorizacao);
