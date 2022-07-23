import {Request, Response} from 'express';

const getOrders = (req: Request, res: Response) => {
    res.status(200).send()
}

module.exports = {
    getOrders
}