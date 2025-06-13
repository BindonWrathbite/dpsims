<template>
  <BaseEntityForm
    title="Instrument"
    :entity="form"
    :fields="fields"
    @submit="submitForm"
    @cancel="emit('cancel')"
  />
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'
import BaseEntityForm from './BaseEntityForm.vue'

const props = defineProps<{ instrument?: any | null }>()
const emit = defineEmits(['submitSuccess', 'cancel'])

const form = ref({
  id: props.instrument?.id || null,
  type: props.instrument?.type || '',
  brand: props.instrument?.brand || '',
  serialNumber: props.instrument?.serialNumber || '',
  inventoryNumber: props.instrument?.inventoryNumber || '',
  condition: props.instrument?.condition || '',
  purchaseDate: props.instrument?.purchaseDate || '',
  purchasePrice: props.instrument?.purchasePrice || '',
  location: props.instrument?.location || '',
  assignedStudent: props.instrument?.assignedStudent || '',
  repairs: props.instrument?.repairs || [],
  notes: props.instrument?.notes || []
})

const CONDITION_OPTIONS = [
  'EXCELLENT', 'GOOD', 'FAIR', 'POOR', 'BROKEN'
].map(c => ({ label: c, value: c }))

const fields = computed(() => [
  { key: 'type', label: 'Type', type: 'text' },
  { key: 'brand', label: 'Brand', type: 'text' },
  { key: 'serialNumber', label: 'Serial Number', type: 'text' },
  { key: 'inventoryNumber', label: 'Inventory Number', type: 'text' },
  { key: 'condition', label: 'Condition', type: 'dropdown', options: CONDITION_OPTIONS },
  { key: 'purchaseDate', label: 'Purchase Date', type: 'date' },
  { key: 'purchasePrice', label: 'Purchase Price', type: 'text' },
  { key: 'location', label: 'Location', type: 'text' },
  { key: 'assignedStudent', label: 'Assigned Student', type: 'text' }
])

function submitForm(formData: any) {
  const isEdit = !!formData.id
  const url = isEdit ? `/api/instruments/${formData.id}` : '/api/instruments'

  axios({ method: isEdit ? 'put' : 'post', url, data: formData })
    .then(res => emit('submitSuccess', res.data))
    .catch(err => {
      console.error('Save failed:', err)
      alert('Error saving instrument.')
    })
}
</script>
