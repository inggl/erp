import express, {Application, Request, Response} from 'express';
import morgan from 'morgan'
import logger from './config/logger';

const app: Application = express()

app.use(express.json());
app.use(morgan("tiny"));
app.use(express.static("public"));

const port = process.env.PORT || 8087;

app.get("/", (req: Request, res: Response) => {
    res.send();
});

app.listen(port, () => {
    logger.info(`Server is running on port ${port}`)
});