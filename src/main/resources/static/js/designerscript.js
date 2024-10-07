// 예시 데이터
const reviews = [
    {
        reservationDate: '2024-10-01',
        userid: 'user1',
        reviewcontent: '이 디자이너는 정말 훌륭합니다!',
        servicename: '헤어컷',
        thumbnail: 'image1.jpg',
        price: 30000,
        rating: 5,
        revisiting: 2
    },
    {
        reservationDate: '2024-10-02',
        userid: 'user2',
        reviewcontent: '아주 만족스러웠어요!',
        servicename: '염색',
        thumbnail: 'image2.jpg',
        price: 50000,
        rating: 4,
        revisiting: 1
    },
    // 더 많은 리뷰 데이터...
];

// 테이블에 데이터 추가
const reviewBody = document.getElementById('review-body');
const template = document.getElementById('review-template').innerHTML;

// 리뷰를 반복하여 Mustache로 렌더링
reviews.forEach((review, index) => {
    // 자동으로 num 값을 생성
    const num = index + 1;

    // Mustache 템플릿에 데이터 바인딩
    const rendered = Mustache.render(template, { ...review, num });

    // 렌더링된 HTML을 테이블에 추가
    reviewBody.insertAdjacentHTML('beforeend', rendered);
});