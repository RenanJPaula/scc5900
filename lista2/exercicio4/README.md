# Relação de recoorencia:

opt(i) = max{ compra(i), opt(i + 1) }
compra(i) = venda(i + 1) - v[i]
venda(i) = max{ v[i] + opt(i + 2), venda(i + 1) }

# Implementações:

- [Recursivo](./recursivo.js)
- [Iterativo](./iterativo.js)
