var map;
var markers = [];  // 기존 마커를 저장할 배열
var largeMarker = null;  // 커진 마커를 저장할 배열
var infoWindow;  // 정보 창 전역 변수로 선언
var autocomplete; // 자동완성 기능을 위한 변수
var directionsService; // 경로 계산 서비스
var directionsRenderer; // 경로 표시
var currentTravelMode = google.maps.TravelMode.DRIVING  ;  // 기본 대중교통 모드

var startMarker = null; // 출발지 마커
var endMarker = null;   // 도착지 마커


window.onload = function() {
    initMap();
};


// 지도 초기화 함수
function initMap() {
    var defaultLocation = { lat: 37.5665, lng: 126.9780 }; // 서울 중심 좌표

    map = new google.maps.Map(document.getElementById('map'), {  // 전역 변수 map에 할당
        zoom: 15,
        center: defaultLocation // 사용자 위치가 안되면 기본 좌표로 설정
    });

    // 여행 모드 설정
   currentTravelMode = google.maps.TravelMode.DRIVING;  // 기본 대중교통 모드

    // 예시로 DirectionsService를 사용하여 경로 안내 요청
    directionsService = new google.maps.DirectionsService();
    directionsRenderer = new google.maps.DirectionsRenderer();
    // DirectionsRenderer를 맵에 표시
    directionsRenderer.setMap(map);

    // DirectionsRequest 객체 설정
    var request = {
        origin: { lat: -34.397, lng: 150.644 },
        destination: { lat: -34.650, lng: 150.744 },
        travelMode: currentTravelMode  // travelMode에 DRIVING 설정
    };

    // 경로 계산 후 지도에 표시
    directionsService.route(request, function(response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            // 응답이 성공적이면 경로를 지도에 렌더링
            directionsRenderer.setDirections(response);
        } else {
            alert('경로 요청 실패: ' + status);
        }
    });


    infoWindow = new google.maps.InfoWindow();  // 정보 창 초기화


    // 장소 자동완성(AutoComplete) 기능 활성화
    autocomplete = new google.maps.places.Autocomplete(
        document.getElementById('searchInput'),
        { types: ['geocode'] } // 검색 타입 제한: 주소 또는 장소
    );

    // 장소가 선택되면 지도의 중심 좌표를 해당 위치로 이동
    autocomplete.addListener('place_changed', function() {
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            alert('해당 장소의 위치를 찾을 수 없습니다.');
            return;
        }

        // 선택한 장소의 좌표로 지도의 중심을 이동
        map.setCenter(place.geometry.location);
        setStartLocation(place.geometry.location, place.name);

        // 선택된 장소에 마커 추가
        addMarker(place.geometry.location, place.name);
    });

    // 사용자의 현재 위치를 가져옵니다.
    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(function (position) {
            // 사용자의 현재 위치를 가져와서 변수에 저장
            var lat = position.coords.latitude;
            var lng = position.coords.longitude;

            map.setCenter({ lat: lat, lng: lng });

            // 마커 추가 (현재 위치에 마커 표시)
            var currentLocationMarker = new google.maps.Marker({
                position: {lat: lat, lng: lng},
                map: map,
                title: '현재 위치',
                icon: {
                    url: "http://maps.google.com/mapfiles/ms/icons/red-dot.png"  // 파란색 마커 아이콘
                }
            });

            // 지도를 사용자 위치로 이동
            map.setCenter({lat: lat, lng: lng});
            // 사용자 위치 주변 미용실 표시
            searchNearby(lat, lng);

            // 지도 드래그 또는 확대/축소 시 미용실 업데이트
            google.maps.event.addListener(map, 'bounds_changed', function () {
                var center = map.getCenter();
                searchNearby(center.lat(), center.lng());
            });
        });
    } else {
        alert('이 브라우저에서는 위치 정보를 지원하지 않습니다.');
    }
}



// 출발지 설정 함수
function setStartLocation(latLng, title) {
    // 기본적으로 현재 위치를 출발지로 설정
    if (!latLng) {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
                title = "현재 위치";
                updateStartLocation(latLng, title);
            }, function () {
                alert('현재 위치를 가져올 수 없습니다.');
            });
        } else {
            alert('이 브라우저에서는 위치 정보를 지원하지 않습니다.');
        }
    } else {
        // 사용자가 입력한 주소에 따라 출발지 설정
        updateStartLocation(latLng, title);
    }
}

// 실제로 출발지 마커를 설정하는 함수
function updateStartLocation(latLng, title) {
    if (startMarker) {
        startMarker.setMap(null);  // 기존 출발지 마커 제거
    }

    startMarker = new google.maps.Marker({
        position: latLng,
        map: map,
        title: title,
        icon: {
            url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"
        }
    });
    if (endMarker) {
        calculateRoute(startMarker.getPosition(), endMarker.getPosition()); // 도착지가 설정되었다면 경로 계산
    }
}

// 도착지 설정 함수 (미용실 선택 시)
function setEndLocation(latLng, title) {
    if (endMarker) {
        endMarker.setMap(null);  // 기존 도착지 마커 제거
    }

    endMarker = new google.maps.Marker({
        position: latLng,
        map: map,
        title: title,
        icon: {
            url: "http://maps.google.com/mapfiles/ms/icons/red-dot.png"
        }
    });

    // 경로 계산 (출발지 마커가 이미 있을 경우)
    if (startMarker) {
        calculateRoute(startMarker.getPosition(), endMarker.getPosition());
    }
}








// 주변 미용실 검색 함수
async function searchNearby(lat, lng) {
    var service = new google.maps.places.PlacesService(map);
    var request = {
        location: {lat: lat, lng: lng},  // 검색할 중심 좌표
        radius: 2000,  // 2km 이내 장소 검색
        keyword:  'hair_salon'
             // 'beauty salon' 또는 'hair salon'으로 바꿔보세요
    };

    // 기존 마커 삭제
    clearMarkers();

    // 주변 장소 검색
    service.nearbySearch(request, function (results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {

            // 검색 결과 미용실을 리스트로 표시
            displaySalonList(results);

            // 장소들에 대한 마커 추가
            for (let i = 0; i < results.length; i++) {
                var place = results[i];
                addMarker(place);  // 새 마커 추가
            }
        } else {
            switch (status) {
                case google.maps.places.PlacesServiceStatus.ZERO_RESULTS:
                    alert('주변에 미용실이 없습니다.');
                    break;
                case google.maps.places.PlacesServiceStatus.OVER_QUERY_LIMIT:
                    alert('API 요청 한도를 초과했습니다.');
                    break;
                case google.maps.places.PlacesServiceStatus.REQUEST_DENIED:
                    alert('요청이 거부되었습니다.');
                    break;
                case google.maps.places.PlacesServiceStatus.INVALID_REQUEST:
                    alert('잘못된 요청입니다.');
                    break;
                case google.maps.places.PlacesServiceStatus.UNKNOWN_ERROR:
                    alert('알 수 없는 오류가 발생했습니다.');
                    break;
                default:
                    alert('알 수 없는 상태: ' + status);
                    break;
            }
            console.error('주변 미용실을 찾을 수 없습니다. 상태: ' + status);
        }
    });
}



// 마커 추가 함수
function addMarker(place) {
    var marker = new google.maps.Marker({
        position: place.geometry.location,
        map: map,
        title: place.name,
        icon: {
            url: "http://maps.google.com/mapfiles/ms/icons/green-dot.png"  // 초록색 마커
        }
    });

    // 마커 배열에 저장 (나중에 삭제할 때 사용)
    markers.push(marker);

    // 마커 클릭 시 정보 창 표시
    google.maps.event.addListener(marker, 'click', function () {
        var service = new google.maps.places.PlacesService(map);

        // 세부 정보 요청
        service.getDetails({placeId: place.place_id}, function (result, status) {
            if (status === google.maps.places.PlacesServiceStatus.OK) {
                // 기본 이미지 URL 설정 (사진이 없는 경우 사용할 로고 이미지)
                var defaultImageUrl = '/images/HAIRHUB_LOGO.svg';

                // InfoWindow에 표시할 HTML 내용 생성
                var content = '<div class="info-window-content">' +
                    '<strong>' + result.name + '</strong><br>' +
                    (result.photos && result.photos.length > 0 ?
                        '<img src="' + result.photos[0].getUrl({maxWidth: 200, maxHeight: 200}) + '"><br>' :
                        '<img src="' + defaultImageUrl + '" style="max-width: 200px; max-height: 200px;"><br>') +
                    '주소: ' + result.formatted_address + '<br>' +
                    (result.formatted_phone_number ? '전화번호: ' + result.formatted_phone_number + '<br>' : '') +
                    '</div>';

                // InfoWindow에 내용 설정 및 열기
                infoWindow.setContent(content);
                infoWindow.open(map, marker);

                calculateRoute(startMarker.getPosition(), marker.getPosition());
            }
        });
    });
}




function clearMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}




function displaySalonList(salons) {
    var listElement = document.getElementById('salonList');
    listElement.innerHTML = '';  // 기존 리스트 초기화

    salons.forEach(function (salon, index) {
        var listItem = document.createElement('li');
        listItem.textContent = salon.name;  // 미용실 이름 추가
        listElement.appendChild(listItem);

        // 마커 객체 참조
        var correspondingMarker = markers[index];

        // 리스트 클릭 시 해당 마커에 호버 효과 적용
        listItem.addEventListener('click', function () {
            toggleDetails(listItem, salon, correspondingMarker);
            highlightMarker(correspondingMarker, salon);   // 클릭된 마커를 삭제하고 새로 크게 추가
        });

        // 리스트 hover 시 마커 크기 증가
        listItem.addEventListener('mouseenter', function () {
            highlightMarker(correspondingMarker, salon);  // 호버 시 마커 크기 키우기
        });

        // 리스트에서 마우스가 떠날 때 마커 크기 복구
        listItem.addEventListener('mouseleave', function () {
            resetMarkerSize(correspondingMarker);  // 마커 크기 원래대로
        });

        // 경로 계산 후 시간과 거리 표시
        if (startMarker) {
            calculateRouteWithTime(startMarker.getPosition(), salon.geometry.location, listItem);

            console.log('Start Position: ', startMarker.getPosition());
            console.log('End Position: ', endMarker.getPosition());

        }
    });
}





function calculateRouteWithTime(origin, destination, listItem) {
    if (!origin || !destination) {
        return;
    }

    var request = {
        origin: origin,
        destination: destination,
        travelMode: google.maps.TravelMode.DRIVING    // 대중교통 모드 사용
    };

    directionsService.route(request, function (response, status) {
        if (status === google.maps.DirectionsStatus.OK) {

            directionsRenderer.setDirections(response);

            var route = response.routes[0].legs[0];
            var duration = route.duration.text; // 소요 시간
            var distance = route.distance.text; // 거리

            // 경로와 시간을 리스트 항목에 추가
            var timeElement = document.createElement('span');
            timeElement.textContent = ' | 경로: ' + duration + ' | 거리: ' + distance;
            listItem.appendChild(timeElement);
        } else {
            console.error('경로 계산 오류: ' + status);
            alert('경로를 계산할 수 없습니다. 상태: ' + status);  // 상태 코드 출력
        }
    });
}



// 경로 계산 함수
function calculateRoute(origin, destination) {
    if (!origin || !destination) {
        return;
    }

    var request = {
        origin: origin,
        destination: destination,
        travelMode: currentTravelMode
    };

    directionsService.route(request, function (response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            directionsRenderer.setDirections(response);
            var route = response.routes[0].legs[0];
            var content = infoWindow.getContent();
            content += '<br>운전 시간: ' + route.duration.text + ' | 거리: ' + route.distance.text;
            infoWindow.setContent(content);
        } else {
            console.error('경로 계산 오류: ' + status);
            alert('경로를 계산할 수 없습니다. 상태: ' + status);
        }
    });
}





// 경로 방법 설정 함수
function setTravelMode(mode) {
    currentTravelMode = mode;
    if (startMarker && endMarker) {
        calculateRoute(startMarker.getPosition(), endMarker.getPosition());
    }
}





// 마커 크기 키우기 및 기존 마커 삭제
function highlightMarker(marker, salon) {
    // 기존 커진 마커가 있으면 제거
    if (largeMarker) {
        largeMarker.setMap(null);
    }

    // 새로운 커진 마커 생성
    largeMarker = new google.maps.Marker({
        position: marker.getPosition(),
        map: map,
        title: salon.name,
        icon: {
            url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png",
            scaledSize: new google.maps.Size(50, 50)  // 크기 증가
        }
    });
}



// 마커 크기 원래대로 복구
function resetMarkerSize(marker) {
    marker.setIcon({
        url: "http://maps.google.com/mapfiles/ms/icons/green-dot.png",
        scaledSize: new google.maps.Size(32, 32)  // 기본 크기
    });
}




// 상세 정보 표시 함수 (경로 계산 포함)
function showDetails(listItem, salon, marker) {
    // 이미 상세 정보가 보이는 경우, 더 이상 추가하지 않음
    if (listItem.querySelector('.salon-details')) {
        return; // 이미 상세 정보가 표시된 상태라면 종료
    }

    // 상세 정보를 표시
    listItem.classList.add('salon-selected');

    var service = new google.maps.places.PlacesService(map);
    service.getDetails({placeId: salon.place_id}, function (result, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            var defaultImageUrl = '/images/HAIRHUB_LOGO.svg';
            var details = document.createElement('div');
            details.className = 'salon-details';
            details.innerHTML =
                (result.photos && result.photos.length > 0 ?
                    '<img src="' + result.photos[0].getUrl({maxWidth: 200, maxHeight: 200}) + '"><br>' :
                    '<img src="' + defaultImageUrl + '" style="max-width: 200px; max-height: 200px;"><br>') +
                '주소: ' + result.formatted_address + '<br>' +
                (result.formatted_phone_number ? '전화번호: ' + result.formatted_phone_number + '<br>' : '') +
                (result.website ? '홈페이지: <a href="' + result.website + '" target="_blank">' + result.website + '</a><br>' : '');

            listItem.appendChild(details);
            details.style.display = 'block';

            // 경로 계산 (현재 위치에서 미용실까지)
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    var origin = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
                    var destination = salon.geometry.location;

                    // Directions 서비스로 경로 계산
                    var request = {
                        origin: origin,
                        destination: destination,
                        travelMode: google.maps.TravelMode.DRIVING // 자동차 이동 모드
                    };

                    directionsService.route(request, function (response, status) {
                        if (status === google.maps.DirectionsStatus.OK) {
                            // 경로 표시
                            directionsRenderer.setDirections(response);

                            // 경로와 시간을 상세정보에 추가
                            var route = response.routes[0].legs[0];
                            details.innerHTML += '<br>운전 시간: ' + route.duration.text + ' | 거리: ' + route.distance.text;

                            // 업데이트된 내용으로 InfoWindow 재설정
                            infoWindow.setContent(details.innerHTML);
                        } else {
                            alert('경로를 계산할 수 없습니다.');
                        }
                    });
                });
            }
        }
    });
}




// 상세 정보 숨기기
function hideDetails(listItem) {
    var details = listItem.querySelector('.salon-details');
    if (details) {
        details.style.display = 'none';
        listItem.removeChild(details);
    }

    // 클릭 해제 시 글씨 굵기 초기화
    listItem.classList.remove('salon-selected');
}




// 상세 정보 토글 함수
function toggleDetails(listItem, salon, marker) {
    var details = listItem.querySelector('.salon-details');
    if (details) {
        hideDetails(listItem);
    } else {
        showDetails(listItem, salon, marker);
    }

}