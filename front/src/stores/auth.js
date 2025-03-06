import { api } from "@/utils/axios";
import { defineStore } from "pinia";
import { ref, computed, onMounted } from "vue";
import { useRouter } from "vue-router";

export const useAuthStore = defineStore('authStore', () => {

    onMounted(() => {
        if(localStorage.getItem('token')) {
            checkConnection()
        }
    })

    const isLogged = ref(false)
    const studentNumber = ref()
    const role = computed(() => studentNumber.value === "admin" ? "admin" : studentNumber.value ? "student" : undefined)
    const router = useRouter()

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
            await checkConnection()
        }

        return result
    }

    const checkConnection = async () => {
        if(!localStorage.getItem('token')) return false
        return await api.get('/students/me')
            .then(r => {
                studentNumber.value = r.data.student.id
                isLogged.value = true
                return true
            })
            .catch(err => {
                if(err.response?.status === 403) return false
                studentNumber.value = err.response?.data.detail === "Vous êtes un administrateur" ? "admin" : undefined
                isLogged.value = true
                return true
            })
    }

    const logout = async (makeRequest = true) => {
        if(makeRequest) await api.delete(`/auth/logout`).catch(console.error)
        localStorage.removeItem('token')
        isLogged.value = false
        router.push('/login')
    }

    return {
        isLogged,
        studentNumber,
        role,
        login,
        logout,
        checkConnection
    }

})