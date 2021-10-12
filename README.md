![HoonDam_Logo](https://user-images.githubusercontent.com/78480984/136928200-d37d0d1b-7b8c-4cb1-9d4d-a0df926dfcd5.png)

# HoonDam | HappyHouse 🏠

- 서비스명 : HoonDam 훈담
- 개발기간 : 2021.05.20 ~ 2021.05.28 (9일)
- 팀명 : 훈담
- 삼성 청년 SW 아카데미 1학기 프로젝트 경진 대회 우수상 수상 🏆
<br>

# 📌서비스 소개

   ### 부동산 매물 거래 웹 서비스
   - 맵 api를 활용하여 부동산 매물의 위치, 가격, 로드뷰 등을 확인할 수 있으며
   - 찜기능, 리뷰 기능을 통하여 사용자의 편의성을 높힘 

  ### HoonDam이 제공하는 주요 기능
  - 실제 지도에서 매물별 위치, 가격, 로드뷰 등의 정보를 확인할 수 있음
  - 조회를 원하는 지도로 포커싱을 이동시키면 해당 지역의 매물 리스트를 볼 수 있음
  - 찜기능, 리뷰 기능을 통하여 편의성을 높힘
  - 주변 상가의 정보를 마커를 통해 확인할 수 있음

<br>

# 👩 팀원 역할
| 팀원 | 역할 | 담당 업무
| ------ | ------ | ------ |
| 황지훈 | 팀장 | 프론트엔드 Main, 백엔드 Sub 개발 |
| 김담영 | 팀원 | 프론트엔드 Sub, 백엔드 Main 개발 |

<br>

# 💻 기술 스택
> ### Front : Vue.js , BootStrap
> ### Back : SpringBoot
> ### DB : Mysql

<br>

# 💾 ERD
![image](https://user-images.githubusercontent.com/78480984/136929343-fe402839-c6af-49c4-9a4a-606c9e006f3e.png)

<br>

# 💻 구현

### [메인화면]
![image](https://user-images.githubusercontent.com/78480984/136930028-a8d91d9b-f296-4762-bdc5-4b40b66c8631.png)

<br>

### [로그인, 회원가입]
![image](https://user-images.githubusercontent.com/78480984/136930140-3de8b519-760e-40b8-84a2-9480047824d8.png)
![image](https://user-images.githubusercontent.com/78480984/136930166-c6c0e923-062d-499d-99f8-76576e80d9dc.png)

<br>

### [거래페이지]
![image](https://user-images.githubusercontent.com/78480984/136930328-4a96996b-ce44-4e62-9ab1-561baed0ee09.png)

> 마커를 통해 매물의 위치를 표시함.

<br>

### [검색필터, 상가정보 출력]
![image](https://user-images.githubusercontent.com/78480984/136930466-bdc3b98d-0d5b-45ef-a37f-17a7f0b04ba6.png)

> 우측 상단의 검색 필터를 통하여 가격/평수 범위 지정 및 "동" 키워드로 검색 가능함.<br>
> 상단의 은행, 마트, 약국, 주유소, 카페, 편의점 버튼 클릭시 해당 상가의 위치 마커로 지도에 표시함.<br>
> 마커 클릭시 해당 상가의 정보 출력.

<br>

### [매물 세부 정보창]
![image](https://user-images.githubusercontent.com/78480984/136930883-7e853bc4-e714-4742-85d4-adaf0f05c472.png)

> 해당 지역의 로드뷰를 통하여 주변 정보를 확인할 수 있음.<br>
> 리뷰댓글과 좋아요 기능을 통하여 해당 매물에 대한 평가를 남길 수 있음.

<br>

### [인기 매물]
![image](https://user-images.githubusercontent.com/78480984/136931029-d2bb2137-1bd5-4a06-a073-21578792401f.png)

> 앞서 말한 좋아요 기능을통해 사용자들이 좋아요한 TOP5의 매물을 좌측 슬라이드바로 표시함.

<br>

### [공지사항 , Q&A]
![image](https://user-images.githubusercontent.com/78480984/136931185-8e157512-397e-46c4-9302-add97851b301.png)
![image](https://user-images.githubusercontent.com/78480984/136931194-f196a3c4-74de-4b3e-8269-4221d2e60813.png)
![image](https://user-images.githubusercontent.com/78480984/136931224-a0c89323-6616-45b9-a564-31274dca8052.png)

> 공지사항, Q&A 등록, 열람, 수정, 삭제 CRUD. 

<br>

### [마이페이지, 관리자페이지]
![image](https://user-images.githubusercontent.com/78480984/136931293-197b48cd-4002-4109-b69e-d8a590e3a5af.png)
![image](https://user-images.githubusercontent.com/78480984/136931307-d8a6f46e-91a1-46ae-854f-73e768652ede.png)

> 마이페이지에서는 회원 정보 수정 및 탈퇴 기능 포함.<br>
> 관리자페이지에서는 사용자들의 정보 열람 가능 및 회원 추방 기능.

<br>



