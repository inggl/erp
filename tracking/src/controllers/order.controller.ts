import {Request, Response} from 'express';

export const getOrders = (req: Request, res: Response) => {
    res.status(200).send("orders");
}

export const track = (req: Request, res: Response) => {
    res.status(200).send();
}