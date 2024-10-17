let currentPage = 1; // 현재 페이지
let itemsPerPage = 20; // 페이지당 디자이너 카드 수
let totalItems = 0; // 전체 디자이너 수 (서버에서 받아와야 함)
let totalPages = 0; // 전체 페이지 수

// 페이지네이션 정보를 담는 객체 생성
const paginationInfo = {
    recordCountPerPage: itemsPerPage,
    pageSize: 5, // 페이지 리스트에 표시될 페이지 개수
    totalRecordCount: 0,
    totalPageCount: 0 // 총 페이지 수 초기화
};

// 디자이너 목록을 서버로부터 가져오는 함수
function fetchDesigners(page) {
    // 예: 서버에서 데이터를 가져오는 API 경로
    const url = `/designerList?page=${page}`;

    fetch(url)
        .then(response => response.json()) // 응답을 JSON으로 변환
        .then(data => {
            // 새로운 디자이너 목록으로 UI 갱신
            updateDesignerList(data.designers);
            totalPages = data.totalPages; // totalPages 업데이트
            updatePaginationInfo(currentPage, totalPages);

        })
        .catch(error => {
            console.error('디자이너 데이터를 가져오는 중 오류 발생:', error);
        });
}


// 페이지네이션 정보를 업데이트하는 함수
function updatePaginationInfo(currentPage, totalPages) {
    document.getElementById('pageInfo').textContent = `페이지 ${currentPage} / ${totalPages}`;
}

function updateDesignerList(designers) {
    const designerListContainer = document.querySelector('.designer-list');
    designerListContainer.innerHTML = ''; // 기존 목록 초기화

    designers.forEach(designer => {
        const designerCard = document.createElement('a');
        designerCard.href = `/designer/${designer.designerId}`;
        designerCard.className = 'designer-card';

        designerCard.innerHTML = `
            <div class="image-wrapper">
                <div class="designer-image">
                    <img src="${designer.designerPictureId}" alt="${designer.designerNickname}의 사진">
                </div>
            </div>
            <div class="designer-info">
                <h2>${designer.designerNickname}</h2>
                <div class="designer-inner">
                    <h5 class="rating">별점: <span class="star-rating">${designer.rating}</span></h5>
                    <h6>미용실: ${designer.salonName}</h6>
                    <p>주소: ${designer.address}</p>
                </div>
            </div>
        `;

        designerListContainer.appendChild(designerCard);
    });
}


// 페이지 변경 함수
function changePage(newPage) {
    if (newPage < 1 || newPage > totalPages) return; // 페이지 범위를 벗어나지 않도록 제한
    currentPage = newPage;
    fetchDesigners(currentPage); // 새로운 페이지의 디자이너 목록을 불러옴
}

// 이전 페이지
document.getElementById('prevPage').addEventListener('click', function() {
    if (currentPage  > 1) {
        changePage(currentPage  - 1);
    }
});

// 다음 페이지
document.getElementById('nextPage').addEventListener('click', function() {
    if (currentPage  < totalPages) {
        changePage(currentPage  + 1);
    }
});

// 초기화
fetchDesigners(currentPage); // 첫 페이지의 데이터를 가져옴
