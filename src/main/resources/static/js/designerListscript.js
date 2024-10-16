let currentPage = 1; // 현재 페이지
let itemsPerPage = 20; // 페이지당 디자이너 카드 수
let totalItems = 0; // 전체 디자이너 수 (서버에서 받아와야 함)
let totalPages = 0; // 전체 페이지 수

// 페이지네이션 정보를 담는 객체 생성
const paginationInfo = {
    currentPageNo: 1,
    recordCountPerPage: itemsPerPage,
    pageSize: 5, // 페이지 리스트에 표시될 페이지 개수
    totalRecordCount: 0,
    totalPageCount: 0 // 총 페이지 수 초기화
};

// 페이지네이션 정보를 업데이트하는 함수
function updatePaginationInfo(totalCount) {
    paginationInfo.totalRecordCount = totalCount;
    paginationInfo.totalPageCount = Math.ceil(totalCount / itemsPerPage);
}

// 디자이너 목록을 로드하는 함수 (서버에서 데이터 가져오기)
function loadDesigners(page) {
    // AJAX 또는 Fetch API를 사용하여 서버에서 디자이너 목록을 가져옴
    // 예: fetch(`/api/designers?page=${page}&itemsPerPage=${itemsPerPage}`)
    fetch(`/api/designers?page=${page}&itemsPerPage=${itemsPerPage}`)
        .then(response => response.json())
        .then(data => {
            const designers = data.designers;
            totalItems = data.totalCount; // 서버에서 받아온 전체 디자이너 수
            updatePaginationInfo(totalItems); // 페이지네이션 정보 업데이트

            // 디자이너 목록을 화면에 출력
            const designerListElement = document.getElementById('designerList');
            designerListElement.innerHTML = '';
            designers.forEach(designer => {
                // 디자이너 카드 생성 및 추가
                const designerCard = createDesignerCard(designer);
                designerListElement.appendChild(designerCard);
            });

            // 페이지네이션 버튼 업데이트
            updatePaginationButtons();
        });
}

// 페이지네이션 버튼을 업데이트하는 함수
function updatePaginationButtons() {
    document.getElementById('pageInfo').innerText = `페이지 ${paginationInfo.currentPageNo} / ${paginationInfo.totalPageCount}`;

    document.getElementById('prevPage').disabled = paginationInfo.currentPageNo === 1;
    document.getElementById('nextPage').disabled = paginationInfo.currentPageNo === paginationInfo.totalPageCount;
}

// 페이지 변경 함수
function changePage(page) {
    if (page < 1 || page > paginationInfo.totalPageCount) return;
    paginationInfo.currentPageNo = page;
    loadDesigners(page); // 해당 페이지에 맞는 디자이너 목록을 다시 로드
}

// 이전 페이지
document.getElementById('prevPage').addEventListener('click', function() {
    if (paginationInfo.currentPageNo > 1) {
        changePage(paginationInfo.currentPageNo - 1);
    }
});

// 다음 페이지
document.getElementById('nextPage').addEventListener('click', function() {
    if (paginationInfo.currentPageNo < paginationInfo.totalPageCount) {
        changePage(paginationInfo.currentPageNo + 1);
    }
});

// 페이지네이션 초기 로드
loadDesigners(currentPage);
