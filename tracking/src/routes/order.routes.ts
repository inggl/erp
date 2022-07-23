import express from 'express';
import {getOrders} from "../controllers/order.controller";

const router = express.Router();

router.get("/", getOrders)

export default router;