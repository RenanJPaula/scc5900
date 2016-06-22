var m = 3
  , n = 3;

function opt(i, j) {
  if((m == 1 && n == 1) || (i >= m || j >= n)) {
    return 0;
  } else if(i == m - 1 && j == n - 1) {
    return 1;
  } else {
    return opt(i + 1, j) + opt(i, j + 1);
  }
}

console.log(opt(0, 0));
