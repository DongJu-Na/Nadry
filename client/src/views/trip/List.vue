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

    <div class="flex gap-3 mt-3 text-sm">
      <a @click="testSubmit('영종도')">#영종도</a>
      <a @click="testSubmit('인천공항')">#인천공항</a>
      <a @click="testSubmit('무의도')">#무의도</a>
      <a @click="testSubmit('경주')">#경주</a>
      <a @click="testSubmit('전주')">#전주</a>
      <a @click="testSubmit('해운대')">#해운대</a>
      <a @click="testSubmit('제주')">#제주</a>
    </div>

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
              <div class="font-semibold truncate ...">{{ item.title }}</div>
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

    <!-- pagination -->
    <Pagination
      :pageInfo="pageInfo"
      :items="tripItems"
      @nextPage="pageInfo.pageNo = pageInfo.pageNo + 1"
      @prevPage="pageInfo.pageNo = pageInfo.pageNo - 1"
      @changePage="
        (page) => {
          pageInfo.pageNo = page;
        }
      "
      @fetch="submit"
    />

    <!-- no result -->
    <div v-if="emptyResult" class="flex items-center justify-center text-zinc-400 h-[300px]">
      <i class="mr-1 text-2xl las la-ban"></i>
      <span class="text-sm">검색 결과가 없습니다.</span>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useMainStore } from '@/store';
import { searchTrip } from '@/api';
import { useRouter } from 'vue-router';
import Pagination from '@/components/common/moleclues/Pagination.vue';

const store = useMainStore();
const router = useRouter();

const pageInfo = ref({
  pageNo: 1,
  numOfRows: 10,
  totalCount: 0,
});

const searchWord = ref('');
const emptyResult = ref(false);
const tripItems = ref([]);

const testSubmit = (name) => {
  searchWord.value = name;
  submit();
};

const submit = async () => {
  if (!searchWord.value) {
    alert('검색어를 입력하세요.');
    return;
  }

  // 로딩중 출력
  store.state.setLoading(true);

  try {
    const payload = {
      numOfRows: pageInfo.value.numOfRows,
      pageNo: pageInfo.value.pageNo,
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

    const {
      status,
      data: {
        response: { body },
      },
    } = await searchTrip(payload);

    if (status === 200 && body) {
      // console.log(body);
      tripItems.value = body.items.item;
      pageInfo.value = {
        numOfRows: body['numOfRows'],
        pageNo: body['pageNo'],
        totalCount: body['totalCount'],
      };

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
