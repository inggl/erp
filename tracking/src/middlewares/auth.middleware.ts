import {Request, Response, NextFunction} from 'express';

const authenticated = (req: Request, res: Response, next: NextFunction) => {
    if (req.isAuthenticated()) {
        return next();
    }

    res.redirect("/");
}

export default authenticated;