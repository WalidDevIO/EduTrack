import { accessApi } from "../apis.js"

/**
 * S'assure que un header Authorization est présent et valide sinon retourne une 403.
 * 
 * @param {Express.Request} req 
 * @param {Express.Response} res 
 */
export const loggedRoute = async (req, res) => {
    const token = req.headers.authorization

    if(!token) {
        res.status(403).send({
            detail: "Vous n'avez pas la permission"
        })
        return false;
    }

    return await fetch(`${accessApi}/me`, {
        headers: {
            'Authorization': token
        }
    })
    .then(r => {
        if(r.ok) return r.json()
        else res.status(403).send({
            detail: "Vous n'avez pas la permission"
        })
        return false
    })
    .then(data => data.ok ? data : false)
}

export const adminRoute = async (req, res) => {
    const logged = await loggedRoute(req, res)
    if(logged) {
        if(logged.studentNumber === null) {
            return true
        } else {
            res.status(403).send({
                detail: "Vous n'avez pas la permission"
            })
        }
    }
    return false
}