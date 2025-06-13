<script setup lang="ts">
import InputText from 'primevue/inputtext'
import Calendar from 'primevue/calendar'
import Textarea from 'primevue/textarea'
import Button from 'primevue/button'
import Dropdown from 'primevue/dropdown'

const props = defineProps<{
  entity: Record<string, any>
  fields: { key: string; label: string; type: string; readonly?: boolean; options?: any[] }[]
  title?: string
}>()

const emit = defineEmits(['submit', 'cancel'])

function submitForm() {
  emit('submit', { ...props.entity })
}
</script>

<template>
  <div class="bg-white text-gray-800 p-6 rounded-2xl shadow max-w-xl w-full border border-gray-200">
    <h2 class="text-2xl font-bold mb-4 text-center">
      {{ entity?.id ? 'Edit' : 'Add New' }} {{ title }}
    </h2>

    <form @submit.prevent="submitForm" class="grid grid-cols-1 gap-4">
      <template v-for="field in fields" :key="field.key">
        <label :for="field.key" class="text-sm font-semibold text-gray-700">
          {{ field.label }}
        </label>

        <InputText
          v-if="['text', 'email', 'number'].includes(field.type)"
          v-model="entity[field.key]"
          :type="field.type"
          :placeholder="field.label"
          :readonly="field.readonly || false"
          class="w-full"
        />

        <Textarea
          v-else-if="field.type === 'textarea'"
          v-model="entity[field.key]"
          :placeholder="field.label"
          autoResize
          class="w-full"
        />

        <Calendar
          v-else-if="field.type === 'date'"
          v-model="entity[field.key]"
          showIcon
          dateFormat="yy-mm-dd"
          :placeholder="field.label"
          class="w-full"
        />

        <Dropdown
          v-else-if="field.type === 'dropdown'"
          v-model="entity[field.key]"
          :options="field.options"
          optionLabel="label"
          optionValue="value"
          class="w-full"
        />
      </template>

      <div class="flex justify-end gap-3 pt-4">
        <Button label="Cancel" severity="secondary" @click="emit('cancel')" />
        <Button label="Save" type="submit" severity="success" />
      </div>
    </form>
  </div>
</template>
