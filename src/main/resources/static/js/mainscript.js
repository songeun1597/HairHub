document.addEventListener('DOMContentLoaded', function () {
    const container = document.querySelector('#best-designer');
    const leftButton = document.querySelector('.scroll-button.left');
    const rightButton = document.querySelector('.scroll-button.right');
    const cardWidth = document.querySelector('.photo-card').offsetWidth + 20; // 카드 너비 + 여백

    leftButton.addEventListener('click', () => {
        container.prepend(container.lastElementChild);
    });

    rightButton.addEventListener('click', () => {
        container.append(container.firstElementChild);
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const container = document.querySelector('#real-review');

    // 두 번째 .scroll-button.left를 선택
    const secondLeftButton = document.querySelectorAll('.scroll-button.left')[1];
    // 두 번째 .scroll-button.right를 선택
    const secondRightButton = document.querySelectorAll('.scroll-button.right')[1];

    const cardWidth = document.querySelector('.photo-card').offsetWidth + 20; // 카드 너비 + 여백

    secondLeftButton.addEventListener('click', () => {
        container.prepend(container.lastElementChild);
    });

    secondRightButton.addEventListener('click', () => {
        container.append(container.firstElementChild);
    });
});


/*

document.addEventListener('DOMContentLoaded', function () {
    function setupSlider(containerSelector) {
        const container = document.querySelector(containerSelector);
        const leftButton = container.querySelector('.scroll-button.left');
        const rightButton = container.querySelector('.scroll-button.right');
        const cardWidth = container.querySelector('.photo-card').offsetWidth + 20; // 카드 너비 + 여백

        // 요소가 존재하는지 확인
        if (!container || !leftButton || !rightButton) {
            console.error(`${containerSelector} 요소를 찾을 수 없습니다.`);
            return;
        }

        leftButton.addEventListener('click', () => {
            container.prepend(container.lastElementChild);
        });

        rightButton.addEventListener('click', () => {
            container.append(container.firstElementChild);
        });
    }

    // best-designer와 real-review 슬라이더 설정
    setupSlider('#best-designer');
    setupSlider('#real-review');
});
*/
