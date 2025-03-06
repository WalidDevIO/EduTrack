<template>
    <VDialog v-model="show">
        <VSheet elevation="5" class="px-16 pb-16 pt-8 mx-auto w-25 rounded-lg">
            <VForm v-model="isFormValid">
                <VTextField type="number" v-model="groups.td"
                    :rules="[v => Number.isInteger(Number.parseInt(v)) && Number.parseInt(v) > 0 || 'Ce champs doit être un nombre !']"
                    placeholder="Groupe de TD" label="Groupe de TD" />

                <VTextField type="number" v-model="groups.tp"
                    :rules="[v => Number.isInteger(Number.parseInt(v)) && Number.parseInt(v) > 0 || 'Ce champs doit être un nombre !']"
                    placeholder="Groupe de TP" label="Groupe de TP" />
            </VForm>
            <VBtn @click="handleChange" class="mt-4" :disabled="!isFormValid">
                Attribuer les groupes
            </VBtn>
        </VSheet>
    </VDialog>

    <Snackbar v-model="showSnackbar" :text="text"/>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { api } from '@/utils/axios';

const isFormValid = ref(false)
const showSnackbar = ref(false)
const text = ref()

const groups = reactive({})

const props = defineProps({
    student: {
        type: Object,
        required: true
    }
})

const show = defineModel('show')

const emit = defineEmits(['change'])

const handleChange = () => {
    api.post(`students/change-groups/${props.student.id}`, groups)
        .then(r => () => {
            showMessage("Groupe attribué !")
            emit('change', groups)
            Object.assign(groups, {
                td: undefined,
                tp: undefined
            })
        })
        .catch(err => {
            if(err.response) showMessage(err.response.data.detail)
            else showMessage("Erreur")
        })
        .finally(() => {
            setTimeout(() => {
                show.value = false
            }, 3000);
        })
}

const showMessage = (msg) => {
    text.value = msg
    showSnackbar.value = true
}
</script>