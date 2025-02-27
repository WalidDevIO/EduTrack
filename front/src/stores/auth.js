import { api } from "@/utils/axios";
import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const useAuthStore = defineStore('authStore', () => {

    const isLogged = ref(false)
    const studentNumber = ref()
    const role = computed(() => studentNumber.value === "admin" ? "admin" : studentNumber.value ? "student" : undefined)

    const login = async (credentials) => {
        if(isLogged.value) return
        const result = await api.post('/auth/login', credentials)
        .then(r => r.data)
        .then(data => {
            isLogged.value = true
            localStorage.setItem('token', data.token)
            return true
        })
        .catch(err => {
            console.error(err)
            return false
        })

        if(result) {
            studentNumber.value = await api.get('/students/me')
                .then(r => r.data.student.id)
                .catch(err => err.response?.data.detail === "Vous êtes un administrateur" ? "admin" : undefined)
        }

        return result
    }



    const logout = async () => {
        await api.delete(`/auth/logout`).catch(console.error)
        localStorage.removeItem('token')
        isLogged.value
    }

    const deleteAccount = async () => {

    }

    return {
        isLogged,
        studentNumber,
        role,
        login,
        logout
    }

})