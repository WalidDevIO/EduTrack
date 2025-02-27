import { accessApi } from "../apis"

/**
 * S'assure que un header Authorization est présent et valide sinon retourne une 403.
 * 
 * @param {Express.Request} req 
 * @param {Express.Response} res 
 */
export const loggedRoute = async (req, res) => {
    const token = req.headers.Authorization

    if(!token) {
        res.status(403).send({
            detail: "Vous n'avez pas la permission"
        })
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
    })
    .then(data => data)
}