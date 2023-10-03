<template>
  <div class="flex items-center justify-between px-5 my-5">
    <h2 class="text-2xl font-medium">오늘, 나들이는<br />어디로 떠나볼까요?</h2>
    <table>
      <tbody>
        <tr>
          <td v-for="(item, index) in ultraSrtNcstList" :key="item.category">
            {{ item.korTxt }}
          </td>
        </tr>
        <tr>
          <td v-for="(item, index) in ultraSrtNcstList" :key="item.category">
            {{ item.obsrValue }}{{ item.sign }}
          </td>
        </tr>
      </tbody>
    </table>
    <CircleProfile v-if="store.user.isLoggedIn" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useMainStore } from '@/store';
import CircleProfile from '@/components/common/moleclues/CircleProfile.vue';
import { getUltraSrtNcst } from '@/api';

const store = useMainStore();
const ultraSrtNcstList = ref(null);
const codeInfo = [{ code : 'T1H' , sign : '℃' , korTxt : '기온'},
                  { code : 'RN1' , sign : 'mm' , korTxt : '1시간 강수량' },
                  { code : 'UUU' , sign : 'm/s' , korTxt : '동서바람성분'},
                  { code : 'VVV' , sign : 'm/s' , korTxt : '남북바람성분'},
    	            { code : 'REH' , sign : '%' , korTxt : '습도'},
                  { code : 'PTY' , sign : '' , korTxt : '강수형태'},
                  { code : 'VEC' , sign : 'deg' , korTxt : '풍향'},
                  { code : 'WSD' , sign : 'm/s' , korTxt : '풍속'}];


const fetchUltraSrtNcst = async () => {
  try {
      const today = new Date();
      const year = today.getFullYear();
      const month = String(today.getMonth() + 1).padStart(2, '0');
      const day = String(today.getDate()).padStart(2, '0');

      let baseTime;
      const currentHour = today.getHours();

      if (today.getMinutes() >= 40) {
        baseTime = `${String(currentHour).padStart(2, '0')}00`;
      } else {
        baseTime = `${String(currentHour - 1).padStart(2, '0')}00`;
      }

    const currentDate = `${year}${month}${day}`;
      
    const position = await new Promise((resolve, reject) => {
      navigator.geolocation.getCurrentPosition(resolve, reject);
    });

    // Extract x and y values from geolocation
    const nx = position.coords.latitude; // 위도
    const ny = position.coords.longitude; // 경도
    const { x, y } = dfs_xy_conv("toXY", nx, ny);

    console.log(currentDate,baseTime,nx,ny,x,y);

        const payload = {
          pageNo : 1,
          numOfRows : 8,
          dataType : 'JSON',
          base_date : currentDate,
          base_time : baseTime,
          nx : x,
          ny : y
        };
      
      
        const {
          status,
           data: {
                  response: { body },
            },
        } = await getUltraSrtNcst(payload);
        if (status === 200 && body) {
          if(body.items.item.length > 0){
            let tempArr = [...body.items.item];
                tempArr.forEach(item => {
                    const matchingCodeInfo = codeInfo.find(codeItem => codeItem.code === item.category);
                    if (matchingCodeInfo) {
                        item.sign = matchingCodeInfo.sign;
                        item.korTxt = matchingCodeInfo.korTxt;
                    }
                });
                ultraSrtNcstList.value =  tempArr;
          }
        }
  } catch (error) {
    console.log(error);
  }
};

    function dfs_xy_conv(code, v1, v2) {
      var RE = 6371.00877; // 지구 반경(km)
      var GRID = 5.0; // 격자 간격(km)
      var SLAT1 = 30.0; // 투영 위도1(degree)
      var SLAT2 = 60.0; // 투영 위도2(degree)
      var OLON = 126.0; // 기준점 경도(degree)
      var OLAT = 38.0; // 기준점 위도(degree)
      var XO = 43; // 기준점 X좌표(GRID)
      var YO = 136; // 기1준점 Y좌표(GRID)

        var DEGRAD = Math.PI / 180.0;
        var RADDEG = 180.0 / Math.PI;

        var re = RE / GRID;
        var slat1 = SLAT1 * DEGRAD;
        var slat2 = SLAT2 * DEGRAD;
        var olon = OLON * DEGRAD;
        var olat = OLAT * DEGRAD;

        var sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        var sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        var ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);
        var rs = {};
        if (code == "toXY") {
            rs['lat'] = v1;
            rs['lng'] = v2;
            var ra = Math.tan(Math.PI * 0.25 + (v1) * DEGRAD * 0.5);
            ra = re * sf / Math.pow(ra, sn);
            var theta = v2 * DEGRAD - olon;
            if (theta > Math.PI) theta -= 2.0 * Math.PI;
            if (theta < -Math.PI) theta += 2.0 * Math.PI;
            theta *= sn;
            rs['x'] = Math.floor(ra * Math.sin(theta) + XO); // + 0.5
            rs['y'] = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
        }
        else {
            rs['x'] = v1;
            rs['y'] = v2;
            var xn = v1 - XO;
            var yn = ro - v2 + YO;
            ra = Math.sqrt(xn * xn + yn * yn);
            if (sn < 0.0) - ra;
            var alat = Math.pow((re * sf / ra), (1.0 / sn));
            alat = 2.0 * Math.atan(alat) - Math.PI * 0.5;

            if (Math.abs(xn) <= 0.0) {
                theta = 0.0;
            }
            else {
                if (Math.abs(yn) <= 0.0) {
                    theta = Math.PI * 0.5;
                    if (xn < 0.0) - theta;
                }
                else theta = Math.atan2(xn, yn);
            }
            var alon = theta / sn + olon;
            rs['lat'] = alat * RADDEG;
            rs['lng'] = alon * RADDEG;
        }
        return rs;
    }


onMounted(() => {
  fetchUltraSrtNcst();
});
</script>
