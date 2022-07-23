import express, {Application, Request, Response} from 'express';
import morgan from 'morgan'
import logger from './config/logger';

const bodyParser = require('body-parser');

const app: Application = express()

const ordersRouter = require('./routes/order.routes');

app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());
app.use(express.json());
app.use(morgan("tiny"));
app.use(express.static("public"));

const port = process.env.PORT || 8087;

app.get("/", (req: Request, res: Response) => {
    res.send();
});

app.use("/orders", ordersRouter);

app.listen(port, () => {
    logger.info(`Server is running on port ${port}`)
});