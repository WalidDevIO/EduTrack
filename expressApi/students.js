import express from 'express'
import { registerStudent } from './actions/registerStudent';

const router = express.Router()

router.post('/register', registerStudent);

export const studentRouter = router;