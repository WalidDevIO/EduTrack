import express from 'express'
import { registerStudent } from './routes/registerStudent.js';

const router = express.Router()

router.post('/register', registerStudent);

export const studentRouter = router;