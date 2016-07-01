/**
 * Created by renanjpaula on 01/07/16.
 */
"use strict";

const WordWrap = require('./models/word-wrap')
    , program = require('commander')
    , fs = require('fs')
    , _INPUT_REGEX = /[ \t\r\n]+/;

program
  .version('1.0.0')
  .option('-f, --file [path]', 'Path of txt file to imput data')
  .parse(process.argv);

fs.readFile(program.file, 'utf8', function(err, data) {
  if (err) throw err;

  let _words = data.split(_INPUT_REGEX);

  new WordWrap(_words.slice(1), _words[0]).solve().print();
});
