import express from 'express';
import { z } from "zod";
import {getOrders, track} from "../controllers/order.controller";
import validate from "../middlewares/validate.middleware";

const router = express.Router();

const trackSchema = z.object({
    body: z.object({

    })
})

router.get("/", getOrders)
router.get("/track", validate(trackSchema), track)

export default router;