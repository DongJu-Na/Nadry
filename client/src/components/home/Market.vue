<template>
  <div class="flex flex-col px-5 my-20">
    <!-- title -->
    <div class="flex justify-between items-center w-full mb-5">
      <h2 class="text-lg font-semibold">마켓</h2>
      <a href="" class="text-zinc-400 px-1">
        <span class="text-sm font-medium mr-1">내 포인트</span>
        <span class="text-sm font-medium text-black">35,000</span>
        <!-- <i class="las la-arrow-right"></i> -->
      </a>
    </div>
    <!-- list -->
    <ul class="grid grid-cols-2 gap-x-3 gap-y-8">
      <li v-for="item in items" :key="item.id">
        <a href="">
          <img :src="item.image" class="aspect-square object-cover rounded-lg" />
          <dl class="mt-3">
            <dt class="text-sm font-medium">{{ item.name }}</dt>
            <dd class="text-xs text-zinc-400">{{ item.description }}</dd>
            <dd class="flex items-center gap-1">
              <span
                class="bg-lime-500 w-[16px] h-[16px] leading-none flex justify-center items-center rounded-full text-2xs text-white"
                >P</span
              >
              <span class="font-medium">{{ item.price }}</span>
            </dd>
          </dl>
        </a>
      </li>
    </ul>
  </div>
</template>

<script setup>
const items = [
  {
    id: 1,
    name: '한통 양배추',
    description: '아삭한 잎에 깃든 달콤함',
    price: '4,990',
    image: 'https://img-cf.kurly.com/shop/data/goods/1653038077839l0.jpeg',
  },
  {
    id: 2,
    name: '영암 친환경 꿀고구마 2kg',
    description: '포실포실 달콤한 꿀고구마',
    price: '15,200',
    image: 'https://img-cf.kurly.com/shop/data/goods/1648207740760l0.jpeg',
  },
  {
    id: 3,
    name: '친환경 표고버섯 300g',
    description: '요리에 감칠맛을 더하는 재료',
    price: '4,990',
    image: 'https://img-cf.kurly.com/shop/data/goods/1648208639381l0.jpeg',
  },
  {
    id: 4,
    name: '친환경 손질한 잎몸 대파',
    description: '하얀 잎몸 부분만을 담아',
    price: '3,690',
    image: 'https://img-cf.kurly.com/shop/data/goods/1648208723538l0.jpeg',
  },
  {
    id: 5,
    name: '친환경 미니 밤호박 (보짱)',
    description: '작아서 더 꿀맛',
    price: '4,290',
    image: 'https://img-cf.kurly.com/shop/data/goods/1592799489859l0.jpg',
  },
  {
    id: 6,
    name: '친환경 조각 브로콜리 150g',
    description: '간편하게 즐기는 초록 꽃송이',
    price: '4,490',
    image: 'https://img-cf.kurly.com/shop/data/goods/1623999845900l0.jpg',
  },
];
</script>
