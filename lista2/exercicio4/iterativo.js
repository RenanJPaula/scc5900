var cotacao = [1, 2, 3, 0, 2]
    memorizacao = [];

(function opt() {
  for (var i = cotacao.length - 1; i >= 0 ; i--) {
    var maxGanhoSeCompraProximoDia = cotacao.length <= i + 1 ? 0 : memorizacao[i + 1]
      , maxGanhoSeCompraHoje = 0;

    for (var j = i + 1; j < cotacao.length; j++) {
      var ganhoSeVender = cotacao[j] - cotacao[i]
        , melhorProximoValorVenda = cotacao.length <= j + 2 ? 0 : memorizacao[j + 2]
        , ganhoAcumulado = melhorProximoValorVenda + ganhoSeVender;

      if(ganhoAcumulado > maxGanhoSeCompraHoje) {
        maxGanhoSeCompraHoje = ganhoAcumulado;
      }
    }

    memorizacao[i] = Math.max(maxGanhoSeCompraHoje, maxGanhoSeCompraProximoDia);
  }
})();

console.log(memorizacao);
