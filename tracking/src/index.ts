import express, {Application, Request, Response} from 'express';
import morgan from 'morgan';
import * as dotenv from 'dotenv';
import logger from './config/logger';
import ordersRouter from './routes/order.routes';
import passport from 'passport';
import * as process from "process";
import expressSession from "./config/session";
import {Strategy} from "openid-client";
import client from "./config/auth";
import authenticated from "./middlewares/auth.middleware";

// Config env
dotenv.config();

// Create express application
const app: Application = express();

app.use(express.json());
app.use(express.urlencoded({extended: true}));
app.use(morgan("tiny"));
app.use(express.static("public"));
app.use()
app.use(passport.initialize());
app.use(passport.authenticate('session'));
app.use(expressSession);

// Create strategy
passport.use('oidc', new Strategy({client}, (tokenSet: { claims: () => any; }, userInfo: any, done: (arg0: null, arg1: any) => any) => {
    return done(null, tokenSet.claims())
}));

passport.serializeUser((user: Express.User, done) => {
   done(null, user);
});

passport.deserializeUser((user: Express.User | false | null, done) => {
    done(null, user);
});

const port = process.env.PORT || 8087;

app.get("/", authenticated, (req: Request, res: Response) => {
    res.send();
});

app.use("/orders", ordersRouter);

app.listen(port, () => {
    logger.info(`Server is running on port ${port}`)
});