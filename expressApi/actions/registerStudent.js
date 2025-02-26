import { studentApi, accessApi } from '../apis'

export const registerStudent = async (student, password) => {
    
    const createdStudent = await fetch(`${studentApi}`, {
        method: "POST",
        body: JSON.stringify(student)
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
        })
    }).then(r => r.ok ? createdStudent : false)
}