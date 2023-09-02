<template>
  <div class="flex flex-col w-full px-5">
    <!-- search field -->
    <form @submit.prevent="submit" class="flex flex-col items-center w-full gap-3">
      <!-- <div class="flex flex-wrap gap-1">
        <label v-for="item in contentTypes" :key="item.id" class="flex">
          <input v-model="contentType" :value="item.id" type="checkbox" class="checkbox" />
          <span>{{ item.name }}</span>
        </label>
      </div> -->
      <div class="flex w-full gap-3">
        <input
          v-model="searchWord"
          type="text"
          class="flex-grow input"
          placeholder="검색어를 입력하세요"
        />
        <button class="w-[100px]">검색</button>
      </div>
    </form>

    <!-- result -->
    <div v-if="tripItems && tripItems.length > 0" class="mt-10">
      <h2 class="mb-3 font-semibold">검색 결과</h2>
      <div class="flex flex-col gap-3">
        <router-link
          v-for="item in tripItems"
          :key="item.contentid"
          :to="`/trips/${item.contentid}/${item.contenttypeid}`"
          class="flex gap-3 overflow-hidden"
        >
          <img
            v-if="item.firstimage"
            :src="item.firstimage"
            class="object-cover w-[80px] min-w-[80px] h-[80px] rounded"
          />
          <div
            v-else
            class="w-[80px] h-[80px] bg-zinc-100 rounded flex justify-center items-center"
          >
            <img src="/svg/empty_face.svg" class="opacity-10 w-[30px]" />
          </div>
          <div class="flex flex-col justify-center flex-1 overflow-hidden">
            <div>
              <span class="font-semibold">{{ item.title }}</span>
              <!-- <span>{{ item.contenttypeid }}</span> -->
            </div>
            <div class="w-full text-sm truncate ... text-zinc-400">
              {{ item.addr1 }}
            </div>
            <div class="flex gap-3 mt-1 text-xs">
              <div class="flex items-center gap-1">
                <img src="/svg/star.svg" class="w-[16px]" />
                <span class="text-sm font-semibold">5.0</span>
              </div>
              <div class="flex items-center gap-1">
                <img src="/svg/comment.svg" class="w-[16px]" />
                <span class="text-sm font-semibold">30</span>
              </div>
            </div>
          </div>
          <!-- {{ item.mapx }}
        {{ item.mapy }} -->
        </router-link>
      </div>
    </div>
    <!-- no result -->
    <div v-if="emptyResult" class="flex items-center justify-center text-zinc-400 h-[300px]">
      <i class="mr-1 text-2xl las la-ban"></i>
      <span class="text-sm">검색 결과가 없습니다.</span>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useMainStore } from '@/store';
import { searchTrip } from '@/api';
import { useRouter } from 'vue-router';

const store = useMainStore();
const router = useRouter();

const contentTypes = [
  {
    name: '전체',
    id: 0,
  },
  {
    name: '관광지',
    id: 12,
  },
  {
    name: '문화시설',
    id: 14,
  },
  {
    name: '축제/공연/행사',
    id: 15,
  },
  {
    name: '여행코스',
    id: 25,
  },
  {
    name: '레포츠',
    id: 28,
  },
  {
    name: '숙박',
    id: 32,
  },
  {
    name: '쇼핑',
    id: 38,
  },
  {
    name: '음식점',
    id: 39,
  },
];

const searchWord = ref('');
const emptyResult = ref(false);
// const contentType = ref(contentTypes.map((item) => item.id));
const contentType = ref('');
const tripItems = ref([]);

// watch(contentType, (newValue, oldValue) => {
//   if (newValue.includes(0) && !oldValue.includes(0)) {
//     contentType.value = contentTypes.map((item) => item.id);
//   }
//   if (!newValue.includes(0) && oldValue.includes(0)) {
//     console.log('전체 해제');
//     contentType.value = [];
//   }
// });

const submit = async () => {
  if (!searchWord.value) {
    alert('검색어를 입력하세요.');
    return;
  }

  // 로딩중 출력
  store.state.setLoading(true);

  try {
    const payload = {
      numOfRows: '10',
      pageNo: '1',
      MobileOS: 'ETC',
      MobileApp: 'AppTest',
      _type: 'json',
      listYN: 'Y',
      contentTypeId: '',
      arrange: 'A',
      areaCode: '',
      sigunguCode: '',
      cat1: '',
      cat2: '',
      cat3: '',
      keyword: searchWord.value,
    };

    // const contentTypeNumber = contentType.value.filter((item) => item !== 0);

    // payload['contentTypeId'] = contentTypeNumber.map((item) => {
    //   if (item != 0) {
    //     return item.toString();
    //   }
    // });

    console.log(payload.contentTypeId);

    const {
      status,
      data: {
        response: { body },
      },
    } = await searchTrip(payload);

    if (status === 200 && body) {
      console.log(body);
      tripItems.value = body.items.item;

      // 로딩중 제거
      store.state.setLoading(false);
      if (!body.items) {
        emptyResult.value = true;
      } else {
        emptyResult.value = false;
      }
    }
  } catch (error) {
    console.log(error);
    alert('통신에 오류가 생겼어요!');
    router.go();
  }
};
</script>
