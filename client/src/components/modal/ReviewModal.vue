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
                  <!-- 프리뷰 이미지 -->
                  <div v-if="previewPhoto">
                    <img :src="previewPhoto" class="rounded" />
                  </div>
                  <!-- 업로드 사진 버튼 -->
                  <label
                    v-else
                    class="rounded-md border border-dashed border-zinc-300 h-[150px] flex justify-center items-center flex-col"
                  >
                    <span class="text-sm text-zinc-400">이곳을 클릭해서 사진을 첨부하세요</span>
                    <input
                      @change="photoInputChange($event)"
                      ref="photoInput"
                      type="file"
                      class="sr-only"
                    />
                  </label>
                  <!-- 위치정보 -->
                  <div
                    v-if="!distance && previewPhoto"
                    class="flex items-center justify-center gap-1 p-3 mt-3 bg-pink-500 rounded-md"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      x="0px"
                      y="0px"
                      width="20"
                      height="20"
                      viewBox="0 0 32 32"
                      class="animate-spin"
                      fill="#fff"
                    >
                      <path
                        d="M 16.03125 4.25 C 15.066406 4.25 14.28125 5.035156 14.28125 6 C 14.28125 6.964844 15.066406 7.75 16.03125 7.75 C 16.996094 7.75 17.78125 6.964844 17.78125 6 C 17.78125 5.035156 16.996094 4.25 16.03125 4.25 Z M 23.09375 6.96875 C 21.988281 6.96875 21.09375 7.863281 21.09375 8.96875 C 21.09375 10.074219 21.988281 10.96875 23.09375 10.96875 C 24.199219 10.96875 25.09375 10.074219 25.09375 8.96875 C 25.09375 7.863281 24.199219 6.96875 23.09375 6.96875 Z M 8.96875 7.40625 C 8.140625 7.40625 7.46875 8.078125 7.46875 8.90625 C 7.46875 9.734375 8.140625 10.40625 8.96875 10.40625 C 9.796875 10.40625 10.46875 9.734375 10.46875 8.90625 C 10.46875 8.078125 9.796875 7.40625 8.96875 7.40625 Z M 26 13.78125 C 24.757813 13.78125 23.75 14.789063 23.75 16.03125 C 23.75 17.273438 24.757813 18.28125 26 18.28125 C 27.242188 18.28125 28.25 17.273438 28.25 16.03125 C 28.25 14.789063 27.242188 13.78125 26 13.78125 Z M 6 14.71875 C 5.308594 14.71875 4.75 15.277344 4.75 15.96875 C 4.75 16.660156 5.308594 17.21875 6 17.21875 C 6.691406 17.21875 7.25 16.660156 7.25 15.96875 C 7.25 15.277344 6.691406 14.71875 6 14.71875 Z M 23.03125 20.59375 C 21.652344 20.59375 20.53125 21.714844 20.53125 23.09375 C 20.53125 24.472656 21.652344 25.59375 23.03125 25.59375 C 24.410156 25.59375 25.53125 24.472656 25.53125 23.09375 C 25.53125 21.714844 24.410156 20.59375 23.03125 20.59375 Z M 8.90625 22.03125 C 8.355469 22.03125 7.90625 22.480469 7.90625 23.03125 C 7.90625 23.582031 8.355469 24.03125 8.90625 24.03125 C 9.457031 24.03125 9.90625 23.582031 9.90625 23.03125 C 9.90625 22.480469 9.457031 22.03125 8.90625 22.03125 Z M 15.96875 23 C 14.3125 23 12.96875 24.34375 12.96875 26 C 12.96875 27.65625 14.3125 29 15.96875 29 C 17.625 29 18.96875 27.65625 18.96875 26 C 18.96875 24.34375 17.625 23 15.96875 23 Z"
                      ></path>
                    </svg>
                    <span class="text-xs font-medium text-white">위치 정보를 확인중입니다.</span>
                  </div>
                  <div
                    v-else-if="distance && previewPhoto && availableDistance"
                    class="p-3 mt-3 rounded-md bg-green-50"
                  >
                    <div class="flex items-center justify-center gap-1">
                      <CheckCircleIcon class="w-5 h-5 text-green-400" aria-hidden="true" />
                      <p class="text-xs font-medium text-green-600">위치 정보가 확인되었습니다.</p>
                    </div>
                  </div>
                  <div
                    v-else-if="distance && previewPhoto && !availableDistance"
                    class="p-3 mt-3 rounded-md bg-rose-50"
                  >
                    <div class="flex items-center justify-center gap-1">
                      <XCircleIcon class="w-5 h-5 text-rose-400" aria-hidden="true" />
                      <p class="text-xs font-medium text-rose-600">
                        사진 또는 회원님의 현재 위치와 여행지와의 거리가 너무 멉니다.
                      </p>
                    </div>
                  </div>

                  <template v-if="availableDistance">
                    <!-- 별점 -->
                    <div class="mt-5">
                      <h3 class="mb-1 text-sm font-medium">별점 선택</h3>
                      <StarRating
                        v-model:rating="reviewRating"
                        increment="0.5"
                        star-size="35"
                        active-color="#11B981"
                        :show-rating="false"
                      />
                    </div>
                    <!-- 리뷰 내용 -->
                    <div class="mt-5">
                      <textarea
                        v-model="reviewContent"
                        id="about"
                        name="about"
                        rows="3"
                        class="block w-full rounded-md border-0 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6 resize-none p-4 h-[200px]"
                        placeholder="리뷰 내용을 입력하세요"
                      />
                    </div>
                  </template>
                </div>
              </div>
              <div class="flex gap-2 mt-5">
                <button
                  type="button"
                  class="inline-flex justify-center px-3 py-2 text-sm font-semibold rounded-md shadow-sm text-zinc-500 basis-1/4 bg-zinc-200/70 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-zinc-600 sm:col-start-2"
                  @click="close"
                >
                  취소
                </button>
                <button
                  type="button"
                  class="inline-flex justify-center flex-1 px-3 py-2 text-sm font-semibold text-white bg-indigo-600 rounded-md shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 sm:col-start-2"
                  @click="submit"
                >
                  작성완료
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
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import { postTripReview } from '@/api';
import { Dialog, DialogPanel, DialogTitle, TransitionChild, TransitionRoot } from '@headlessui/vue';
import { CheckCircleIcon, XCircleIcon } from '@heroicons/vue/20/solid';
import StarRating from 'vue-star-rating';
import exifr from 'exifr';

const props = defineProps({
  open: {
    type: Boolean,
    default: false,
  },
  areaCode: {
    type: String,
  },
  contentTypeId: {
    type: String,
  },
  originPosition: {
    type: Object,
  },
});

const emit = defineEmits(['close']);

const route = useRoute();

const reviewContent = ref(null);
const reviewRating = ref(0);
const reviewImage = ref(null);
const photoInput = ref(null);
const previewPhoto = ref(null);

const photoPosition = ref(null);
const userPosition = ref(null);
const distance = ref(null);
const availableDistance = ref(false);

// 거리비교 상수(km)
const DISTANCE_LIMIT = 200;

// 사진 메타 정보 & 유저 위치 조회 & distance 계산
const getPosition = async (file) => {
  try {
    const { latitude, longitude } = await exifr.gps(file);
    photoPosition.value = { lat: latitude, lng: longitude };
    distance.value = calculateDistance(
      photoPosition.value.lat,
      photoPosition.value.lng,
      Number(props.originPosition.lat),
      Number(props.originPosition.lng)
    );
  } catch (error) {
    console.log('사진 position 정보 없음');
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        userPosition.value = { lat: position.coords.latitude, lng: position.coords.longitude };
        distance.value = calculateDistance(
          userPosition.value.lat,
          userPosition.value.lng,
          Number(props.originPosition.lat),
          Number(props.originPosition.lng)
        );
      });
    } else {
      console.log('error');
    }
  }
};

// 두 지점의 위도와 경도를 사용하여 거리 계산
const calculateDistance = (lat1, lng1, lat2, lng2) => {
  // console.log(lat1, lng1, lat2, lng2);
  const R = 6371; // 지구 반지름 (단위: 킬로미터)
  const dLat = deg2rad(lat2 - lat1);
  const dLon = deg2rad(lng2 - lng1);

  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const distance = R * c; // 거리 (단위: 킬로미터)

  console.log(distance);

  if (distance <= DISTANCE_LIMIT) {
    availableDistance.value = true;
  } else {
    availableDistance.value = false;
  }

  return distance;
};

// 각도를 라디안으로 변환
const deg2rad = (deg) => deg * (Math.PI / 180);

const photoInputChange = async (e) => {
  const files = e.target.files;
  const reader = new FileReader();
  reviewImage.value = files;
  reader.onload = (data) => {
    previewPhoto.value = data.target.result;
    // console.log(data);
  };
  reader.readAsDataURL(files[0]);
  const position = getPosition(files[0]);
  // console.log('getPosition', await position);
};

const submit = async () => {
  if (!availableDistance.value) {
    alert('위치 정보가 확인되지 않으면 리뷰를 작성하실 수 없어요!');
    return;
  }
  if (!reviewContent.value) {
    alert('리뷰 내용을 작성해주세요');
    return;
  }
  if (!reviewRating.value) {
    alert('별점을 작성해주세요');
    return;
  }
  if (!reviewImage.value) {
    alert('사진을 선택해주세요');
    return;
  }
  const payload = {
    contentId: route.params.id,
    content: reviewContent.value,
    reviewRating: reviewRating.value.toFixed(1),
    reviewImage: reviewImage.value[0],
    posX: props.originPosition.lng,
    posY: props.originPosition.lat,
    realPosX: photoPosition.value ? photoPosition.value.lng : userPosition.value.lng,
    realPosY: photoPosition.value ? photoPosition.value.lat : userPosition.value.lat,
    contentTypeId: props.contentTypeId,
    areaCode: props.areaCode,
  };
  console.log(payload);

  try {
    const { status, data } = await postTripReview(payload);
    console.log(status, data);
    if (status === 200 && data.resultCode === '0000') {
      alert('리뷰 작성 성공');
      emit('close', true);
    } else if (status === 200 && data.resultCode === '0007') {
      alert('이미 리뷰를 작성하셨습니다.');
      emit('close', false);
    }
  } catch (error) {
    console.log(error);
  }
};

const close = () => {
  reviewContent.value = null;
  reviewRating.value = 0;
  reviewImage.value = null;
  photoInput.value = null;
  previewPhoto.value = null;
  photoPosition.value = null;
  userPosition.value = null;
  distance.value = null;
  availableDistance.value = false;
  emit('close', false);
};
</script>
