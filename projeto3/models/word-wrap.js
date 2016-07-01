/**
 * Created by renanjpaula on 01/07/16.
 */
"use strict";
var util = require('./util');

class WordWrap {

    constructor(words, lineWidth) {
      this._memorization = null;
      this._words = words;
      this._lineWidth = lineWidth;
    }

    solve() {
      if(this._memorization == null) {
        const _count = this._words.length
            , _slack = util.createMatrix(_count, _count, 0)
            , _minimum = [0];

        this._memorization = [];

        for (let i = 0; i < _count; i++) {
          _slack[i][i] = this._lineWidth - this._words[i].length;
          for (let j = i + 1; j < _count; j++) {
            _slack[i][j] = _slack[i][j - 1] - this._words[j].length - 1;
          }
        }

        for (let i = 1; i < _count + 1; i++) {
          _minimum[i] = Infinity;
        }

        for (let j = 0; j < _count; j++) {
          for (let i = j; i >= 0 ; i--) {
            let _cost = Infinity;

            if(_slack[i][j] >= 0) {
              _cost = _minimum[i] + (_slack[i][j] * _slack[i][j]);
            }

            if(_minimum[j + 1] > _cost) {
              _minimum[j + 1] = _cost;
              this._memorization[j] = i;
            }
          }
        }
      }

      return this;
    }

    print() {
        const _count = this._words.length
            , _lines = [];

        let i, j = _count;
        while (j > 0) {
            i = this._memorization[j - 1];
            _lines.push(this._words.slice(i, j).join(' '));
            j = i;
        }

        console.log(_lines.reverse().join('\n'));

        return this;
    }
}

module.exports = WordWrap;
