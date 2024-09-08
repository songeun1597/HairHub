document.addEventListener('DOMContentLoaded', function () {
    const container = document.querySelector('#best-desiner');
    const leftButton = document.querySelector('.scroll-button.left');
    const rightButton = document.querySelector('.scroll-button.right');

    const cardWidth = document.querySelector('.photo-card').offsetWidth + 20; // 카드 너비 + 여백

    leftButton.addEventListener('click', () => {
        // container.scrollBy({
        //     left: -cardWidth, /* 이동할 거리 */
        //     behavior: 'smooth'
        // });
        container.prepend(container.lastElementChild);
    });

    rightButton.addEventListener('click', () => {
        // container.scrollBy({
        //     right: cardWidth, /* 이동할 거리 */
        //     behavior: 'smooth'
        // });
        container.append(container.firstElementChild);
    });
});
