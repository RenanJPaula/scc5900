var cotacao = [1, 2, 3, 0, 2]
    memorizacao = [];

function opt(i) {
  if(i == -1 || cotacao.length <= i) {
    return 0;
  } else if(!memorizacao[i]) {
    memorizacao[i] = Math.max(opt(i + 1), compra(i));
  }
  return memorizacao[i];
}

function compra(i) {
  return venda(i + 1) - cotacao[i];
}

function venda(i) {
  if(cotacao.length <= i) {
    return 0;
  } else {
    return Math.max(cotacao[i] + opt(i + 2), venda(i + 1));
  }
}

opt(0);

console.log(memorizacao);
