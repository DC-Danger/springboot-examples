var express = require('express');
var router = express.Router();

router.get('/', function(req, res, next) {
  res.render('bookList', {  });
});

router.get('/info', function(req, res, next) {
    res.render('bookForm', { bookId: req.query.bookId });
});

module.exports = router;
