<!-- InstrumentsView.vue -->
<template>
  <div class="p-6">
    <div v-if="showForm">
      <InstrumentForm
        :instrument="editingInstrument"
        @submitSuccess="handleSubmitSuccess"
        @cancel="handleCancel"
      />
    </div>

    <div v-else>
      <div class="flex justify-between items-center mb-4">
        <h1 class="text-2xl font-bold">Instrument Inventory</h1>
        <Button
          label="Add Instrument"
          icon="pi pi-plus"
          class="p-button-sm"
          @click="handleAddInstrument"
        />
      </div>

      <BaseDataTable
        :items="instruments"
        :columns="instrumentColumns"
        @edit="handleEdit"
        @delete="handleDelete"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import BaseDataTable from '@/components/BaseDataTable.vue'
import InstrumentForm from '@/components/InstrumentForm.vue'

const instruments = ref([])
const showForm = ref(false)
const editingInstrument = ref(null)

const instrumentColumns = [
  { key: 'type', label: 'Type' },
  { key: 'brand', label: 'Brand' },
  { key: 'inventoryNumber', label: 'Inventory #' },
  { key: 'condition', label: 'Condition' },
  { key: 'locationName', label: 'Location' },
  { key: 'assignedStudentName', label: 'Assigned To' },
]

function handleEdit(instrument) {
  editingInstrument.value = instrument
  showForm.value = true
}

async function handleDelete(instrument) {
  if (!confirm(`Delete ${instrument.type} (${instrument.inventoryNumber})?`)) return
  try {
    await axios.delete(`/api/instruments/${instrument.id}`, { withCredentials: true })
    instruments.value = instruments.value.filter(i => i.id !== instrument.id)
  } catch (error) {
    alert('Failed to delete instrument.')
    console.error(error)
  }
}

function handleAddInstrument() {
  editingInstrument.value = null
  showForm.value = true
}

function handleSubmitSuccess(updatedInstrument) {
  const index = instruments.value.findIndex(i => i.id === updatedInstrument.id)
  if (index !== -1) {
    instruments.value[index] = updatedInstrument
  } else {
    instruments.value.push(updatedInstrument)
  }
  showForm.value = false
}

function handleCancel() {
  showForm.value = false
}

onMounted(async () => {
  try {
    const res = await axios.get('/api/instruments', { withCredentials: true })
    instruments.value = res.data.content || res.data // Handle pagination or direct list
  } catch (error) {
    alert('Failed to load instruments.')
    console.error(error)
  }
})
</script>
