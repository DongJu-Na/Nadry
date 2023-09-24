<template>
  <div class="flex flex-col my-10">
    <h2 class="px-5 mb-3 text-lg font-semibold">주변을 둘러 보세요</h2>
    <div id="map" class="w-full h-[500px]">
      <!-- input -->
      <div class="absolute z-[99] p-3 w-full">
        <div class="flex items-center w-full p-3 bg-white rounded-md shadow-md">
          <i class="mr-2 text-xl las la-search text-zinc-400"></i>
            <input  
                v-model="searchWord" 
                @keyup.enter="onEnter"
                type="text" 
                placeholder="지금, 경주를 검색해보세요!" 
            />
        </div>
      </div>
    </div>
  </div>
</template>

<style>
    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
    .wrap * {padding: 0;margin: 0;}
    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
    .info .close:hover {cursor: pointer;}
    .info .body {position: relative;overflow: hidden;}
    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
    .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    .info .link {color: #5085BB;}
</style>

<script setup>
import { onMounted, ref } from 'vue';
import { searchTrip } from '@/api';


const searchWord = ref('');

let map,displayMarkers = [];

const submit = async () => {
  if (!searchWord.value) {
    alert('검색어를 입력하세요.');
    return;
  }

  try {
    const payload = {
      numOfRows: 10,
      pageNo: 1,
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

     await searchTrip(payload).then((result)=>{
    const {status, data: {response: { body }}} = result;
      console.log(displayMarkers);

        if (status === 200 && body && body.items.item.length > 0) {
            map.relayout();
            hideMarkers();
            body.items.item.forEach(item => {
              let kakaoPos = new kakao.maps.LatLng(item.mapy, item.mapx);

              addMarker(kakaoPos,item,map);

            });

            let firstSearchMapPos = new kakao.maps.LatLng(body.items.item[0].mapy , body.items.item[0].mapx);
            map.setCenter(firstSearchMapPos);
            showMarkers(map);

        }else{
          alert("검색 결과가 없습니다.");
        }
    });
        
  } catch (error) {
    console.log(error);
  }
};

function addMarker(_position,_item,_map) {
    let marker = new kakao.maps.Marker({
      map: _map,
      position: _position,
      title: _item.title,
      clickable: true
    });

    let imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
    let imageSize = new kakao.maps.Size(37, 45);
    let imageOption = { offset: new kakao.maps.Point(17, 40) };

    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
    let markerPosition = marker.getPosition();

    marker = new kakao.maps.Marker({
      position: markerPosition,
      // image: markerImage
    });

    marker.setMap(_map);
    let imgUrl = (_item.firstimage === null || _item.firstimage === ""  ? "/svg/empty_face.svg" : _item.firstimage);

    let contentHtml =  `<div class="wrap">
                          <div class="info">
                            <div class="title">
                              ${_item.title}
                              <div class="close" onclick="closeOverlay()" title="닫기"></div>
                            </div>
                            <div class="body">
                              <div class="img">
                                <img src=${imgUrl} width="73" height="70">
                              </div>
                              <div class="desc">
                                <div class="ellipsis"></div>
                                <div class="jibun ellipsis">${_item.addr1 + " " + _item.addr2}</div>
                                <div><a href=${"/trips/" + _item.contentid + "/" +_item.contenttypeid} class="link">상세보기</a></div>
                              </div>
                            </div>
                          </div>
                        </div>
                      `;
    
      let overlay = new kakao.maps.CustomOverlay({
          content: contentHtml,
          map: _map,
          position: marker.getPosition()       
      });


      kakao.maps.event.addListener(marker, 'click', function() {
          overlay.setMap(_map);
      });
      
    displayMarkers.push(marker);
    console.debug("push Markers",displayMarkers);
}

function showMarkers(_map) {
    setMarkers(_map);
}

function hideMarkers() {
    setMarkers(null);    
}

function setMarkers(_map) {
    for (var i = 0; i < displayMarkers.length; i++) {
        displayMarkers[i].setMap(_map);
    }
    console.debug("setMarkers",displayMarkers,_map);            
}

function closeOverlay() {
    console.debug("closeOverlay");
    overlay.setMap(null);     
}

const onEnter = () => {
  submit(searchWord.value);
};

const getLocation = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const myPosition = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
      map.setCenter(myPosition);

      const marker = new kakao.maps.Marker({
        position: myPosition,
        map: map,
      });
    });
  } else {
    console.log('getLocation error');
  }
};

const loadMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 7,
  };
  map = new kakao.maps.Map(container, options);

  getLocation();
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    loadMap();
  } else {
    
    const script = document.createElement('script');
    script.src ='//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=' + import.meta.env.VITE_APP_KAKAO_KEY;
    script.onload = () => {
      kakao.maps.load(() => {      
        kakao.maps.load(loadMap);
      });
    }
    
    document.head.appendChild(script);
  }
});
</script>
