var express = require('express');
const { HttpError } = require('http-errors');
var router = express.Router();
const request = require('request');

var counter_url = process.env.COUNTER_SERVICE +"/count/"+process.env.COLOR

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Expressing' });
});

router.post('/confirm', function(req, res, next) {
  body = req.body;
  console.log(body)
  if (body.color == process.env.COLOR){
    request(counter_url, body, (err, res, body) => {
      if(err){
        console.log("Error counting " + res.statusCode)
        return
      }
      console.log(body + "Counted");
    });
    return res.json({'color': 'match'})
  }else{
    // res.status = 404
    return res.send(404)
  }

})

module.exports = router;
