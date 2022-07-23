import express, {Application, Request, Response} from 'express';
import morgan from 'morgan';
import * as dotenv from 'dotenv';
import logger from './config/logger';
import ordersRouter from './routes/order.routes';
import passport from 'passport';
import * as process from "process";
import helmet from "helmet";
import {keycloakStrategy} from "./config/auth";

// Config env
dotenv.config();

// Create express application
const app: Application = express();

app.use(express.json());
app.use(express.urlencoded({extended: true}));
app.use(morgan("tiny"));
app.use(express.static("public"));
app.use(helmet());
app.use(passport.initialize());

const port = process.env.PORT || 8087;

passport.use(keycloakStrategy);

app.get("/", (req: Request, res: Response) => {
    res.send();
});

app.use("/orders", passport.authenticate('oauth2', {session: false}), ordersRouter);

app.listen(port, () => {
    logger.info(`Server is running on port ${port}`)
});