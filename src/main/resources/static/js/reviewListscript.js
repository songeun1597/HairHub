/*

let currentPage = 1;
const reviewsPerPage = 10;

function changePage(page) {
    if (page < 1 || page > totalPages) return;
    currentPage = page;
    // 리뷰 리스트 갱신 로직 추가
    updateReviewList();
}

function updateReviewList() {
    const startIndex = (currentPage - 1) * reviewsPerPage;
    const endIndex = startIndex + reviewsPerPage;

    // 서버에서 받은 리뷰 데이터를 slice로 자르고 페이지에 반영
    const paginatedReviews = reviews.slice(startIndex, endIndex);
    renderReviews(paginatedReviews);
}

*/
function filterReviews() {
    const filter = document.getElementById("filter").value;
    window.location.href = `/reviewList?filter=${filter}`;
}
