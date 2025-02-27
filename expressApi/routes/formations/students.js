import { studentApi } from "../../apis.js";
import { adminRoute } from "../protection/loggedRoute.js";

export async function getStudents(req, res) {
    const logged = await adminRoute(req, res)
    if(!logged) return

    const formationId = parseInt(req.params.id)

    const students = await fetch(`${studentApi}?formation=${formationId}`).then(r => r.json())

    res.send(students)
}