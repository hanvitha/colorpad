var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Expressing' });
});

router.post('/confirm', function(req, res, next) {
  body = req.body;
  console.log(body)
  if (body.color == process.env.COLOR){
    return res.json({'color': 'match'})
  }else{
    // res.status = 404
    return res.send(404)
  }

})

module.exports = router;
