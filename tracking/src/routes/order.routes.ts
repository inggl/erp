import express from 'express';
const orderControllers = require("../controllers/order.controller");

const router = express.Router();

router.get("/", orderControllers.getOrders)

module.exports = router;