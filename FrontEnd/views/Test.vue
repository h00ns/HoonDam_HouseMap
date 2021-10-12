<template>
  <b-container fluid id="main" style="padding:0;">
    <div style="height:30px;"></div>

    <!-- 왼쪽바 -->
    <div id="view" style="height:720px; display:inline-block; width:20%;">
    <div style="margin-top:10px; height:50px; border-bottom:1px solid gray;">
      <b-button @click="list" variant="primary" style="width:100px; margin-right:10px;">목록보기</b-button>
      <b-button @click="mydeal" variant="success" style="width:100px;">찜목록</b-button>
    </div>
    <!-- 목록 -->
    <template v-if="show">
        <div class="apt" @dblclick="move(item)" style="cursor:pointer; margin:10px; height:120px; box-shadow: 5px 5px 5px black; border-radius:30px; border:2px solid black;" v-for="(item) in deals" :key="item.id" >
            <b-row>
                <b-col class="mt-2" style="margin-left:18px;" cols="3">
                    <img src="@/assets/HoonDam_Logo.png" style="display:inline-block; height:100px; width:100px;">
                </b-col>
                <b-col class="mt-3">
                    <div style="margin-left:40px; width:120px; font-weight:bold; font-size:10px;">{{item.aptname}}</div>
                    <div style="margin-left:25px; font-weight:bold;">{{item.dong}}</div>
                    <div style="position:relative; top:10px; left:10px;">
                      <img @click="modal(item)" src="@/assets/search.png" style="cursor:pointer; width:25px;" />
                      <img v-if="mydeals.findIndex(element => element.no == item.no) == -1" src="@/assets/heart.png" @click="zzim(item)" style="cursor:pointer; margin-left:10px; width:25px;"/>
                      <img v-if="mydeals.findIndex(element => element.no == item.no) != -1" src="@/assets/heartRed.png" @click="zzimNo(item)" style="cursor:pointer; margin-left:10px; width:25px;"/>
                        
                    </div>
                </b-col>  
            </b-row>
        </div>
    </template>

    <!-- 세부정보 -->
    <div v-show="showDetail" style="width:600px; height:600px; background-color:white; position:fixed; top:80px; left:30%; z-index:100; border-radius:30px;">
         <div style="margin-top:10px; margin-left:20px; text-align:left;">
            <img  @click="getInsta(detail)" src="@/assets/insta.png" style="width:40px; margin-bottom:7px;"/>
            <h4 style="position:relative; top:4px; font-weight:bold; font-size:30px; display:inline-block; margin-left:10px;">{{detail.aptname}}</h4>
             <b-button pill v-b-toggle.sidebar-1 style="float:right; margin-right:60px;">평단가</b-button>
              <b-sidebar id="sidebar-1" title="평단가" shadow>
        
              </b-sidebar>
         </div>
      <!-- <img src="@/assets/house.png" style="width:520px; height:300px;"> -->
      <div id="roadview" style="margin-left:25px; width:550px;height:300px;"></div>
      <div class="mt-3" style="text-align:left; margin-left:25px;">
        <b-button style="margin-right:10px;" variant="outline-primary">#{{detail.dong}}</b-button>
        <b-button style="margin-right:10px;" variant="outline-primary">#{{detail.dealamount}}만원</b-button>
        <b-button style="margin-right:10px;" variant="outline-primary">#{{detail.area}}m²</b-button>
        <b-button style="margin-right:10px;" variant="outline-primary">#{{detail.floor}}층</b-button>
        <b-button variant="outline-primary">#{{detail.buildyear}}년 건축</b-button>
      </div>
      <div class="mt-2" style="margin-left:15px; text-align:left;">
        <span style="font-weight:bold; margin-left:10px; position:relative; top:2px;">좋아요 {{heart}}개</span>
        <img v-if="mydeals.findIndex(element => element.no == detail.no) == -1" src="@/assets/heart.png" @click="zzim(detail)" style="cursor:pointer; margin-left:2px; width:25px;"/>
        <img v-if="mydeals.findIndex(element => element.no == detail.no) != -1" src="@/assets/heartRed.png" @click="zzimNo(detail)" style="cursor:pointer; margin-left:2px; width:25px;"/>
        <img @click="commentShow" src="@/assets/comment.png" style="cursor:pointer; margin-left:5px; width:25px; display:inline-block;"/>
        <b-form-input v-if="showComment" style="margin-left:10px; width:370px; height:25px; display:inline-block" v-model="comment"/>
        <b-button v-if="showComment" @click="addComment(detail)" variant="primary" style="height:25px; font-size:10px;">등록</b-button>
      </div>
      <b-button style="position:fixed; top:92px; left:66%;" @click="noneDetail" pill variant="danger">X</b-button>
      <div style="height:10px;"></div>
      <div v-for="comment in comments" :key="comment.aptno" style="text-align:left; margin-left:25px;">
        <span style="font-weight:bold">{{comment.writer}}</span> - {{comment.content}}
      </div>
    </div>
    
    <!-- 찜목록 -->
    <template v-if="!show">
      <div class="apt" @dblclick="move(item)" style="cursor:pointer; margin:10px; height:120px; box-shadow: 5px 5px 5px black; border-radius:30px; border:2px solid black;" v-for="(item,idx) in mydeals" :key="idx" >
            <b-row>
                <b-col class="mt-2" style="margin-left:18px;" cols="3">
                    <img src="@/assets/HoonDam_Logo.png" style="display:inline-block; height:100px; width:100px;">
                </b-col>
                <b-col class="mt-3">
                    <div style="margin-left:40px; width:120px; font-weight:bold; font-size:12px;">{{item.aptname}}</div>
                    <div style="margin-left:25px; font-weight:bold;">{{item.dong}}</div>
                    <div style="position:relative; top:10px; left:10px;">
                      <img @click="modal(item)" src="@/assets/search.png" style="cursor:pointer; width:25px;" />
                      <img src="@/assets/heartRed.png" @click="zzimNo(item)" style="margin-left:10px; width:25px;"/>
                    </div>
                </b-col>  
            </b-row>
        </div>
    </template>
  </div>


    <!-- 지도 -->
    <div class="map_wrap" style="height:720px; display:inline-block; width:80%;">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    <ul id="category">
        <li id="BK9" data-order="0"> 
            <span class="category_bg bank"></span>
            은행
        </li>       
        <li id="MT1" data-order="1"> 
            <span class="category_bg mart"></span>
            마트
        </li>  
        <li id="PM9" data-order="2"> 
            <span class="category_bg pharmacy"></span>
            약국
        </li>  
        <li id="OL7" data-order="3"> 
            <span class="category_bg oil"></span>
            주유소
        </li>  
        <li id="CE7" data-order="4"> 
            <span class="category_bg cafe"></span>
            카페
        </li>  
        <li id="CS2" data-order="5"> 
            <span class="category_bg store"></span>
            편의점
        </li>      
    </ul>
    </div>
    
    <!-- 뒤로가기 버튼 -->
    <div id="back">
        <b-button @click="back" pill variant="warning">메인으로</b-button>
    </div>

    <!-- 검색필터 -->
    <div id="filter">
        <div id="top">
            <b-input-group class="mt-2" style="padding:0 10px;">
                <b-form-input placeholder="동으로 검색" v-model="word"/>
                <b-input-group-append>
                <b-button variant="success" @click="search">검색</b-button>
                </b-input-group-append>
            </b-input-group>
            <b-input-group class="mt-2" style="padding:0 10px;">
                <b-form-input disabled placeholder="검색 조건을 설정해주세요."/>
                <b-input-group-append>
                <b-button @click="showfilter" variant="light" id="bt">
                    <img src="@/assets/filter.png" style="width:20px"/>
                </b-button>
                </b-input-group-append>
            </b-input-group>
        </div>
        <div id="bot" v-if="mode">
            <div>
                <h3 style="text-align:left; margin-left:20px;">가격</h3>
                <b-form-input @change="search2" style="width:320px" id="range" v-model="value" type="range" min="0" max="100"></b-form-input>
                <div class="mt-2">{{ value }}억 이하로 조회</div>
            </div>
            <div>
                <h3 style="text-align:left; margin-left:20px;">평수</h3>
                <b-form-input @change="search3" style="width:320px" id="range" v-model="value2" type="range" min="0" max="100"></b-form-input>
                <div class="mt-2">{{ value2 }}평 이하로 조회</div>
            </div>
        </div>
    </div>

    <!-- 평단가 -->
    <b-modal id="modal-scoped" ref="priceShow">
      
    </b-modal>

    <!-- 인스타 모달 -->
    <b-modal id="instaModal" ref="instaModal">
      <img v-for="image in instaImgs" :src="image.media_url" :key="image.id">
    </b-modal>
  </b-container>
</template>



<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=434a94d7b26baaf498367a7031f16015&libraries=services,clusterer,drawing"></script>
<script>
import http from '@/util/http-common';

export default {
    name: "main",
    components: {
    },
    data () {
      return {
        libraries: ['services', 'clusterer', 'drawing'],
        mode:false,
        value:100,
        value2:100,
        map:null,
        show:true,
        detail:[],
        word:'',  // 검색어
        clusterer: null,
        neLa:'',
        neMa:'',
        swLa:'',
        swMa:'',
        showDetail:false,
        showComment:false,
        comment:'',
      }
    },
    mounted() {
         
    },
    methods: {
        initMap () {
            // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
            var placeOverlay = new kakao.maps.CustomOverlay({zIndex:1}), 
            contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다 
            markers = [], // 마커를 담을 배열입니다
            currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다

            const container = document.querySelector('#map')
            const options = {
                center: new kakao.maps.LatLng(37.565644204641984, 126.97803902768429),
                level: 4
            }
            const map = new kakao.maps.Map(container, options)
            this.map = map;
            this.$store.state.map = map;
            map.setMaxLevel(6);

            // 장소 검색 객체를 생성합니다
            var ps = new kakao.maps.services.Places(map); 

            // 지도에 idle 이벤트를 등록합니다
            kakao.maps.event.addListener(map, 'idle', searchPlaces);

            // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다 
            contentNode.className = 'placeinfo_wrap';

            // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
            // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다 
            addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
            addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

            // 커스텀 오버레이 컨텐츠를 설정합니다
            placeOverlay.setContent(contentNode);  

            // 각 카테고리에 클릭 이벤트를 등록합니다
            addCategoryClickEvent();

            // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
            function addEventHandle(target, type, callback) {
                if (target.addEventListener) {
                    target.addEventListener(type, callback);
                } else {
                    target.attachEvent('on' + type, callback);
                }
            }

            // 카테고리 검색을 요청하는 함수입니다
            function searchPlaces() {
                if (!currCategory) {
                    return;
                }
                
                // 커스텀 오버레이를 숨깁니다 
                placeOverlay.setMap(null);

                // 지도에 표시되고 있는 마커를 제거합니다
                removeMarker();
                
                ps.categorySearch(currCategory, placesSearchCB, {useMapBounds:true}); 
            }

            // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
            function placesSearchCB(data, status, pagination) {
                if (status === kakao.maps.services.Status.OK) {

                    // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
                    displayPlaces(data);
                } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
                    // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

                } else if (status === kakao.maps.services.Status.ERROR) {
                    // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
                    
                }
            }

            // 지도에 마커를 표출하는 함수입니다
            function displayPlaces(places) {

                // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
                // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
                var order = document.getElementById(currCategory).getAttribute('data-order');

                

                for ( var i=0; i<places.length; i++ ) {

                        // 마커를 생성하고 지도에 표시합니다
                        var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

                        // 마커와 검색결과 항목을 클릭 했을 때
                        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
                        (function(marker, place) {
                            kakao.maps.event.addListener(marker, 'click', function() {
                                displayPlaceInfo(place);
                            });
                        })(marker, places[i]);
                }
            }

            // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
            function addMarker(position, order) {
                var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
                    imageSize = new kakao.maps.Size(27, 28),  // 마커 이미지의 크기
                    imgOptions =  {
                        spriteSize : new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
                        spriteOrigin : new kakao.maps.Point(46, (order*36)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                        offset: new kakao.maps.Point(11, 28) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
                    },
                    markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                        marker = new kakao.maps.Marker({
                        position: position, // 마커의 위치
                        image: markerImage 
                    });

                marker.setMap(map); // 지도 위에 마커를 표출합니다
                markers.push(marker);  // 배열에 생성된 마커를 추가합니다

                return marker;
            }

            // 지도 위에 표시되고 있는 마커를 모두 제거합니다
            function removeMarker() {
                for ( var i = 0; i < markers.length; i++ ) {
                    markers[i].setMap(null);
                }   
                markers = [];
            }

            // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
            function displayPlaceInfo (place) {
                var content = '<div class="placeinfo">' +
                                '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';   

                if (place.road_address_name) {
                    content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                                '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
                }  else {
                    content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
                }                
              
                content += '    <span class="tel">' + place.phone + '</span>' + 
                            '</div>' + 
                            '<div class="after"></div>';

                contentNode.innerHTML = content;
                placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
                placeOverlay.setMap(map);  
            }


            // 각 카테고리에 클릭 이벤트를 등록합니다
            function addCategoryClickEvent() {
                var category = document.getElementById('category'),
                    children = category.children;

                for (var i=0; i<children.length; i++) {
                    children[i].onclick = onClickCategory;
                }
            }

            // 카테고리를 클릭했을 때 호출되는 함수입니다
            function onClickCategory() {
                var id = this.id,
                    className = this.className;

                placeOverlay.setMap(null);

                if (className === 'on') {
                    currCategory = '';
                    changeCategoryClass();
                    removeMarker();
                } else {
                    currCategory = id;
                    changeCategoryClass(this);
                    searchPlaces();
                }
            }

            // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
            function changeCategoryClass(el) {
                var category = document.getElementById('category'),
                    children = category.children,
                    i;

                for ( i=0; i<children.length; i++ ) {
                    children[i].className = '';
                }

                if (el) {
                    el.className = 'on';
                } 
            } 

            var clusterer = new kakao.maps.MarkerClusterer({
              map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
              averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
              minLevel: 3 // 클러스터 할 최소 지도 레벨 
            });
            this.clusterer = clusterer;

              let markerss = this.deals.map(apt => {
                  return new kakao.maps.Marker({
                    position : new kakao.maps.LatLng(apt.lat, apt.lng)
                  });
                });
              clusterer.addMarkers(markersS);

              // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
            var mapTypeControl = new kakao.maps.MapTypeControl();

            // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
            // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
            map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

            // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
            var zoomControl = new kakao.maps.ZoomControl();
            map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
            
            kakao.maps.event.addListener(map, 'bounds_changed', () => {
              if(this.show){

                clusterer.clear();

              // 지도 영역정보를 얻어옵니다 
              var bounds = map.getBounds();
              // 영역정보의 남서쪽 정보를 얻어옵니다 
              var swLatlng = bounds.getSouthWest();
              let swLa = swLatlng.La;
              let swMa = swLatlng.Ma;
              this.swLa = swLa;
              this.swMa = swMa;

              // 영역정보의 북동쪽 정보를 얻어옵니다 
              var neLatlng = bounds.getNorthEast();
              let neLa = neLatlng.La;
              let neMa = neLatlng.Ma;
              this.neLa = neLa;
              this.neMa = neMa;

            this.$store.dispatch('loadDeal', {
              key : '',
              word: '',
              neLa: neLa,
              neMa: neMa,
              swLa : swLa,
              swMa: swMa,
              dealamount: this.value * 10,
              area: this.value2 * 3,
              heart: 0,
            })

              let markers = this.deals.map(apt => {
                return new kakao.maps.Marker({
                  position : new kakao.maps.LatLng(apt.lat, apt.lng)
                  });
                });
              clusterer.addMarkers(markers);

              kakao.maps.event.addListener(clusterer, 'clusterclick', function(cluster) {
                
                // 현재 지도 레벨에서 1레벨 확대한 레벨
              var level = map.getLevel()-1;

              // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
              map.setLevel(level, {anchor: cluster.getCenter()});
          });
          }
          });
        },
        back(){
            this.$router.push('/');
        },
        showfilter(){
            this.mode = !this.mode;
        },
       move(data){
            var moveLatLon = new kakao.maps.LatLng(data.lat, data.lng)
            this.map.setCenter(moveLatLon);
        },
        modal(item){
          http.
          get('/housedeal/comment', {
            params : {
              aptno : item.no
            }
          })
          .then(({data}) => {
            this.$store.dispatch('loadComment', data)
          })
          .catch(()=>{
            alert('에러 발생')
          });

          var roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
          var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
          var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

          var position = new kakao.maps.LatLng(item.lat, item.lng);

          // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
          roadviewClient.getNearestPanoId(position, 230, function(panoId) {
              roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
          });

          this.detail = item;
          this.showDetail = true;
        },
        noneDetail(){
          this.showDetail = false;
        },
        list(){
          this.show = true;

          this.clusterer.clear();

              let markers = this.deals.map(apt => {
                   return new kakao.maps.Marker({
                    position : new kakao.maps.LatLng(apt.lat, apt.lng)
                  });
                });
              this.clusterer.addMarkers(markers);
        },
        mydeal(){
          this.show = false;

          this.clusterer.clear();

              let markers = this.mydeals.map(apt => {
                   return new kakao.maps.Marker({
                    position : new kakao.maps.LatLng(apt.lat, apt.lng)
                  });
                });
          this.clusterer.addMarkers(markers);
        },
        // 동으로 검색
        search(){
            this.$store.dispatch('searchDeal',{
              word: this.word,
              value: this.value,
              value2: this.value2,
            }).then(()=>{

              this.clusterer.clear();

              let markers = this.deals.map(apt => {
                   return new kakao.maps.Marker({
                    position : new kakao.maps.LatLng(apt.lat, apt.lng)
                  });
                });
              this.clusterer.addMarkers(markers);
            })
        },
        // 가격으로 검색
        search2(){
          this.$store.dispatch('loadDeal', {
              key : '',
              word: '',
              neLa: this.neLa,
              neMa: this.neMa,
              swLa : this.swLa,
              swMa: this.swMa,
              dealamount: this.value * 10,
              area: this.value2 * 3,
            })
            .then(()=>{
              this.clusterer.clear();

              let markers = this.deals.map(apt => {
                   return new kakao.maps.Marker({
                    position : new kakao.maps.LatLng(apt.lat, apt.lng)
                  });
                });
              this.clusterer.addMarkers(markers);
            })
        },
        // 평수로 검색
        search3(){
           this.$store.dispatch('loadDeal', {
              key : '',
              word: '',
              neLa: this.neLa,
              neMa: this.neMa,
              swLa : this.swLa,
              swMa: this.swMa,
              dealamount: this.value * 10,
              area: this.value2 * 3,
            })
            .then(()=>{
              this.clusterer.clear();

              let markers = this.deals.map(apt => {
                   return new kakao.maps.Marker({
                    position : new kakao.maps.LatLng(apt.lat, apt.lng)
                  });
                });
              this.clusterer.addMarkers(markers);
            })
        },
        // 찜하기
        zzim(item){
          this.$store.dispatch('zzim',item);
        },
        // 찜취소
        zzimNo(item){
          this.$store.dispatch('zzimNo',item);
        },
        // 댓글창 
        commentShow(){
          this.showComment = !this.showComment;
        },
        // 댓글달기
        addComment(apt) {
          this.$store.dispatch('addComment', {
            content: this.comment,
            aptno : apt.no,
            writer : localStorage.getItem('userId')
          })
          this.comment = ''
        },
        // 인스타
        getInsta(detail) {
          console.log(detail)
          this.$store.dispatch('getInsta', detail.dong)
          this.$refs['instaModal'].show()
        },
        // 평단가
        price(){
            this.$refs['priceShow'].show()
        }
    },
    computed : {
      deals(){
            return this.$store.state.deals;
      },
      mydeals(){
            return this.$store.state.mydeals;
      },
      comments() {
           return this.$store.state.comments;
      },
      heart() {
            return this.$store.state.heart;
      },
      instaImgs() {
        return this.$store.state.insta;
      },
    },
     created(){
      http
      .get('/housedeal',{
        params: {
        key : '',
        word: '',
        neLa: 126.9858048953828,
        neMa: 37.572132142795276,
        swLa : 126.970256567982,
        swMa: 37.5591549061606,
        dealamount: this.value,
        area: this.value2 * 3,
      }
      })
      .then( ({data}) => {
        this.$store.dispatch('setDeal', data)
        if (window.kakao && window.kakao.maps) {
        this.initMap()
        } else {
        const script = document.createElement('script')
        script.onload = () => kakao.maps.load(this.initMap);
        script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=434a94d7b26baaf498367a7031f16015&libraries=services,clusterer,drawing'

        document.head.appendChild(script)
        }
      })
      .catch(() => {
      });

      this.$store.dispatch('load_zzim');
      
    },
}
</script>


<style scoped>
#back{
    position:fixed;
    display:inline-block;
    top:690px;
    left:93%;
    z-index:100;
}

#filter{
    position:fixed;
    display:inline-block;
    left:73%;
    top:80px;
    width:350px;
    z-index:100;
    background-color:whitesmoke;
    border:1px solid black;
}

#top{
    height:103px;
}

#bot{
    height:450px;
}

#bt{
    width:60px;
}

#view{
    overflow-y:scroll;
    overflow-x:hidden;
}

.apt:hover{
  background-color:rgb(222, 228, 233);
}

.map_wrap, .map_wrap * {margin:0; padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap {position:relative;width:100%;height:350px;}
#category {position:absolute;top:10px;left:10px;border-radius: 5px; border:1px solid #909090;box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);background: #fff;overflow: hidden;z-index: 2;}
#category li {float:left;list-style: none;width:50px;border-right:1px solid #acacac;padding:6px 0;text-align: center; cursor: pointer;}
#category li.on {background: #eee;}
#category li:hover {background: #ffe6e6;border-left:1px solid #acacac;margin-left: -1px;}
#category li:last-child{margin-right:0;border-right:0;}
#category li span {display: block;margin:0 auto 3px;width:27px;height: 28px;}
#category li .category_bg {background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png) no-repeat;}
#category li .bank {background-position: -10px 0;}
#category li .mart {background-position: -10px -36px;}
#category li .pharmacy {background-position: -10px -72px;}
#category li .oil {background-position: -10px -108px;}
#category li .cafe {background-position: -10px -144px;}
#category li .store {background-position: -10px -180px;}
#category li.on .category_bg {background-position-x:-46px;}
.placeinfo_wrap {position:absolute;bottom:28px;left:-150px;width:300px;}
.placeinfo {position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;padding-bottom: 10px;background: #fff;}
.placeinfo:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.placeinfo_wrap .after {content:'';position:relative;margin-left:-12px;left:50%;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.placeinfo a, .placeinfo a:hover, .placeinfo a:active{color:#fff;text-decoration: none;}
.placeinfo a, .placeinfo span {display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
.placeinfo span {margin:5px 5px 0 5px;cursor: default;font-size:13px;}
.placeinfo .title {font-weight: bold; font-size:14px;border-radius: 6px 6px 0 0;margin: -1px -1px 0 -1px;padding:10px; color: #fff;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.placeinfo .tel {color:#0f7833;}
.placeinfo .jibun {color:#999;font-size:11px;margin-top:0;}
</style>