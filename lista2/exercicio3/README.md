# Relação de recorrência:

A-) opt(I) = max{ opt(i + 1), opt(i + 2) + v[i] }

B-) opt(i, j) = opt(i + 1, j) + opt(i, j + 1)

# Implementações:

- [Recursivo a](./recursivo-a.js)
- [Recursivo b](./recursivo-b.js)
