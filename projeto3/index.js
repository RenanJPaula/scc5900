/**
 * Created by renanjpaula on 28/06/16.
 */
"use strict";

class WordWrap {

    constructor(lineWidth = 0, words = "") {
        this._wordLengths = [];
        this._lineWidth = lineWidth;
        this._words = words.split(' ');
        this._solution = undefined;
        for (let i = 0; i < this._words.length; i++) {
            this._wordLengths[i] = this._words[i].length;
        }
    }

    solve() {
        if (!this._solution) {
            this._solution = [];

            const _EXTRAS = []
                , _LINE_COST = []
                , _N = this._wordLengths.length
                , _OPTIMAL_WORDS_COST = [0];

            for (let i = 0; i <= _N; i++) {
                _EXTRAS[i] = [];
                _LINE_COST[i] = [];
                for (let j = 0; j <= _N; j++) {
                    _EXTRAS[i][j] = 0;
                }
            }

            for (let i = 1; i <= _N; i++) {
                _EXTRAS[i][i] = this._lineWidth - this._wordLengths[i - 1];
                for (let j = i + 1; j <= _N; j++) {
                    _EXTRAS[i][j] = _EXTRAS[i][j - 1] - this._wordLengths[j - 1] - 1;
                }
            }

            for (let i = 1; i <= _N; i++) {
                for (let j = i; j <= _N; j++) {
                    if (_EXTRAS[i][j] < 0)
                        _LINE_COST[i][j] = Infinity;
                    else if (j == _N && _EXTRAS[i][j] >= 0)
                        _LINE_COST[i][j] = 0;
                    else
                        _LINE_COST[i][j] = _EXTRAS[i][j] * _EXTRAS[i][j];
                }
            }


            for (let j = 1; j <= _N; j++) {
                _OPTIMAL_WORDS_COST[j] = Infinity;
                for (let i = 1; i <= j; i++) {
                    if (_OPTIMAL_WORDS_COST[i - 1] != Infinity && _LINE_COST[i][j] != Infinity && (_OPTIMAL_WORDS_COST[i - 1] + _LINE_COST[i][j] < _OPTIMAL_WORDS_COST[j])) {
                        _OPTIMAL_WORDS_COST[j] = _OPTIMAL_WORDS_COST[i - 1] + _LINE_COST[i][j];
                        this._solution[j] = i;
                    }
                }
            }

            return this;
        }
    }

    print(p = this._solution, n = this._wordLengths.length) {
        let k;
        if (p[n] == 1)
            k = 1;
        else
            k = this.print(p, p[n] - 1) + 1;

        console.log(this._words.slice(p[n] - 1, n).join(' '));
        return k;
    }
}

(function () {
    new WordWrap(6, "aaa bb cc ddddd").solve().print();
})();

