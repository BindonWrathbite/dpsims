import api from "./axios.ts"

export const getUserName = async () => {
    const response = await api.get('/user')      // No extra argument like 'name'
    const userName = response.data.name          // Extract 'name' from returned object
    return userName
}
