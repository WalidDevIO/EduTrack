import { studentApi, accessApi } from '../apis.js'

export const registerStudent = async (student, password) => {
    
    const createdStudent = await fetch(`${studentApi}`, {
        method: "POST",
        body: JSON.stringify(student),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    })
        .then(r => r.json())
        .then(data => data.id)
        .catch(() => false)

    if(!createdStudent) return false

    return await fetch(`${accessApi}/register`, {
        method: "POST",
        body: JSON.stringify({
            username: 'e'+createdStudent,
            password,
            student: createdStudent
        }),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    }).then(r => r.ok ? createdStudent : false)
}