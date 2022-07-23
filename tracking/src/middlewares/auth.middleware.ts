import {Request, Response, NextFunction} from 'express';

const authenticated = (req: Request, res: Response, next: NextFunction) => {
    if (req.isAuthenticated()) {
        return next();
    } else {
        return res.send(401);
    }
}

export default authenticated;