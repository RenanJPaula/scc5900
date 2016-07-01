/**
 * Created by renanjpaula on 01/07/16.
 */
"use strict";

module.exports.createMatrix = (rows = 0, cols = 0, defaultValue = undefined) => {
  let _m = [];

  for(let i = 0; i < rows; i++) {
    _m[i] = [];
    for (var j = 0; j < cols; j++) {
      _m[i][j] = defaultValue;
    }
  }

  return _m;
};
