//네이버 지도 API 스크립트 동적으로 삽입
var script = document.createElement('script');
script.type = 'text/javascript';
script.src = 'https://openapi.map.naver.com/openapi/v3/maps.js?clientId=2h91dnhpm6&submodules=services';

document.head.appendChild(script);

// 스크립트 로드 후 지도 초기화 함수 호출
script.onload = function() {
    if (typeof naver !== 'undefined' && naver.maps) {
        console.log("네이버 지도 API 가 로드되었습니다.");
        initMap();
    } else {
        console.error("네이버 지도 API 가 로드되지 않았습니다.");
    }
};

// 지도 초기화 함수
function initMap() {

    // 네이버 지도 객체 생성
    var mapOptions = {
        center: new naver.maps.LatLng(37.5665, 126.9780), // 서울의 위도, 경도
        zoom: 10
    };

    // 네이버 지도 객체 생성
    var map = new naver.maps.Map('map', mapOptions);

    // 사용자의 현재 위치 가져오기
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var userLat = position.coords.latitude;
            var userLng = position.coords.longitude;
            var userLocation = new naver.maps.LatLng(userLat, userLng);

            // 지도의 중심을 사용자 위치로 설정
            map.setCenter(userLocation);

            // 사용자 위치에 마커 추가
            new naver.maps.Marker({
                position: userLocation,
                map: map,
                title: '현재 위치'
            });
        });
    } else {
        alert("현재 위치를 가져올 수 없습니다.");
    }

    // 검색 입력 필드 초기화
    var searchInput = document.getElementById('searchInput');
    var places = new naver.maps.services.Places();

    // 검색 기능
    searchInput.addEventListener('input', function() {
        var query = searchInput.value;

        if (query.length > 0) {
            places.keywordSearch(query, function(status, response) {
                if (status === naver.maps.Service.Status.OK) {
                    var salons = response.items;  // 검색 결과 미용실 리스트

                    // 미용실 리스트 업데이트
                    var salonList = document.getElementById('salonList');
                    salonList.innerHTML = '';  // 기존 리스트 초기화

                    salons.forEach(function(salon) {
                        var li = document.createElement('li');
                        li.textContent = salon.title;
                        salonList.appendChild(li);

                        // 미용실 위치에 마커 추가
                        new naver.maps.Marker({
                            position: new naver.maps.LatLng(salon.latitude, salon.longitude),
                            map: map
                        });
                    });
                }
            });
        }
    });
}


