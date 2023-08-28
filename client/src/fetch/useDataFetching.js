import { ref } from 'vue';
import { getProducts } from '@/api';

// 마켓 상품 조회
export const fetchProducts = async () => {
  const fetchedData = ref([]);
  const error = ref(null);
  const loading = ref(false);

  try {
    loading.value = true;

    const {
      status,
      data: { data },
    } = await getProducts();
    console.log(data);

    if (status === 200 && data) {
      fetchedData.value = data;
    }
  } catch (error) {
    console.log(error);
    error.value = error;
  } finally {
    loading.value = false;
  }

  return {
    fetchedData,
    error,
    loading,
  };
};
