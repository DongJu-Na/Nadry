<template>
  <TransitionRoot as="template" :show="open">
    <Dialog as="div" class="relative z-[9999]" @close="open = false">
      <TransitionChild
        as="template"
        enter="ease-out duration-300"
        enter-from="opacity-0"
        enter-to="opacity-100"
        leave="ease-in duration-200"
        leave-from="opacity-100"
        leave-to="opacity-0"
      >
        <div class="fixed inset-0 transition-opacity bg-gray-500 bg-opacity-75" />
      </TransitionChild>

      <div class="fixed inset-0 z-10 w-screen overflow-y-auto">
        <div
          class="flex items-center justify-center w-full min-h-full p-4 text-center sm:items-center sm:p-0"
        >
          <TransitionChild
            as="template"
            enter="ease-out duration-300"
            enter-from="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
            enter-to="opacity-100 translate-y-0 sm:scale-100"
            leave="ease-in duration-200"
            leave-from="opacity-100 translate-y-0 sm:scale-100"
            leave-to="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
          >
            <DialogPanel
              class="relative w-full px-4 pt-5 pb-4 overflow-hidden text-left transition-all transform bg-white rounded-lg shadow-xl sm:my-8 sm:max-w-lg sm:p-6"
            >
              <div>
                <div class="mt-3 sm:mt-5">
                  <DialogTitle as="h3" class="mb-3 text-base font-semibold leading-6 text-gray-900"
                    >리뷰를 작성하세요</DialogTitle
                  >
                  <div>
                    <img src="https://placehold.co/500x300" />
                  </div>
                  <div class="mt-5">
                    <h3 class="text-sm font-medium">별점 선택</h3>
                    <StarRating
                      v-model:rating="reviewRating"
                      increment="0.5"
                      star-size="40"
                      active-color="#11B981"
                    />
                  </div>
                  <div class="mt-5">
                    <textarea
                      v-model="reviewContent"
                      id="about"
                      name="about"
                      rows="3"
                      class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6 resize-none p-3 h-[200px]"
                      placeholder="리뷰 내용을 입력하세요"
                    />
                  </div>
                </div>
              </div>
              <div class="mt-5 sm:mt-6 sm:grid sm:grid-flow-row-dense sm:grid-cols-2 sm:gap-3">
                <button
                  type="button"
                  class="inline-flex justify-center w-full px-3 py-2 text-sm font-semibold text-white bg-indigo-600 rounded-md shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 sm:col-start-2"
                  @click="submit"
                >
                  작성완료
                </button>
                <button
                  type="button"
                  class="inline-flex justify-center w-full px-3 py-2 mt-3 text-sm font-semibold text-gray-900 bg-white rounded-md shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50 sm:col-start-1 sm:mt-0"
                  @click="$emit('close')"
                  ref="cancelButtonRef"
                >
                  취소
                </button>
              </div>
            </DialogPanel>
          </TransitionChild>
        </div>
      </div>
    </Dialog>
  </TransitionRoot>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import { postTripReview } from '@/api';
import { Dialog, DialogPanel, DialogTitle, TransitionChild, TransitionRoot } from '@headlessui/vue';
import { CheckIcon } from '@heroicons/vue/24/outline';
import StarRating from 'vue-star-rating';

const props = defineProps({
  open: {
    type: Boolean,
    default: false,
  },
});

const route = useRoute();

const reviewContent = ref(null);
const reviewRating = ref(0);
const reviewImage = ref(null);

const submit = async () => {
  if (!reviewContent.value) {
    alert('리뷰 내용을 작성해주세요');
    return;
  }
  if (!reviewRating.value) {
    alert('별점을 작성해주세요');
    return;
  }
  // if (!reviewImage.value) {
  //   alert('사진을 선택해주세요');
  //   return;
  // }
  const contentId = route.params.id;
  const payload = {
    contentId: route.params.id,
    content: reviewContent.value,
    reviewRating: reviewRating.value.toFixed(1),
    // todo: 이미지 추가 해야됨
  };

  try {
    const result = await postTripReview(payload);
    console.log(result);
  } catch (error) {
    console.log(error);
  }
};
</script>
