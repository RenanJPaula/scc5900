# Solução para o problema:

Para atingirmos O(log n) utilizaremos a estratŕgia de divisão e conquista, onde:

- elemento < esquerda && elemento > direita; Descartar elementos da direita;
- elemento > esquerda && elemento < direita; Descartar elementos da esquerda;
- elemento > esquerda && elemento > direita: Solução encontrada;

# Implementação:

- [Implementação](./recursivo.js);

# Complexidade de tempo de execução:

A quantidade de subdivisões do vetor se dá em **log n** vezes e como o algorítimo descarta o processamento de um dos lados, o seu custo se limita em navegar sobre os lados remanecentes.
