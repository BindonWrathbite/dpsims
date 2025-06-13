<!-- BaseDataTable.vue -->
<template>
  <div class="overflow-x-auto rounded shadow border border-gray-200">
    <table class="w-full table-auto text-left text-sm text-gray-800">
      <thead class="bg-gray-100">
      <tr>
        <th
          v-for="col in columnsToRender"
          :key="col.key"
          class="p-3 font-semibold border-b border-gray-300"
        >
          {{ col.label }}
        </th>
      </tr>
      </thead>
      <tbody>
      <template v-for="(item, index) in items" :key="item[keyField] ?? index">
        <tr
          @click="toggleExpanded(index)"
          class="border-b border-gray-200 hover:bg-gray-50 cursor-pointer"
        >
          <td
            v-for="col in columnsToRender"
            :key="col.key"
            class="p-3 whitespace-nowrap"
          >
            {{ item[col.key] ?? '—' }}
          </td>
        </tr>

        <!-- Expanded row -->
        <tr v-if="expandedIndex === index" class="bg-gray-50">
          <td :colspan="columnsToRender.length" class="p-4 text-sm text-gray-700">
            <div class="grid grid-cols-2 gap-x-4 gap-y-2 mb-4">
              <template v-for="(value, key) in getExtraFields(item)" :key="key">
                <div class="font-semibold text-gray-600">{{ formatLabel(key) }}:</div>
                <div>{{ value ?? '—' }}</div>
              </template>
            </div>
            <div class="flex justify-end gap-2">
              <button
                @click.stop="emit('edit', item)"
                class="px-3 py-1 bg-yellow-500 text-black rounded hover:bg-yellow-600 text-sm"
              >
                Edit
              </button>
              <button
                @click.stop="emit('delete', item)"
                class="px-3 py-1 bg-red-600 text-white rounded hover:bg-red-700 text-sm"
              >
                Delete
              </button>
            </div>
          </td>
        </tr>
      </template>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'

interface Column {
  key: string
  label: string
}

const props = defineProps<{
  items: any[]
  columns?: Column[]
  keyField?: string
}>()
const emit = defineEmits(['edit', 'delete'])

const columnsToRender = computed(() => {
  if (props.columns?.length) return props.columns
  const sample = props.items[0] || {}
  return Object.keys(sample)
    .filter((key) => key !== 'id')
    .map((key) => ({
      key,
      label: key.charAt(0).toUpperCase() + key.slice(1)
    }))
})

const expandedIndex = ref<number | null>(null)
function toggleExpanded(index: number) {
  expandedIndex.value = expandedIndex.value === index ? null : index
}

function getExtraFields(item: any) {
  const visibleKeys = columnsToRender.value.map(col => col.key)
  const hiddenKeys = ['id', 'value', 'password', 'locationId']

  const result: Record<string, any> = {}
  Object.entries(item).forEach(([key, value]) => {
    if (!visibleKeys.includes(key) && !hiddenKeys.includes(key)) {
      result[key] = value
    }
  })
  return result
}

function formatLabel(key: string) {
  return key
    .replace(/([A-Z])/g, ' $1')
    .replace(/^./, str => str.toUpperCase())
}
</script>
