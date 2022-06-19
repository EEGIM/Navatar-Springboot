const bar = document.getElementById('bar');
const nav = document.getElementById('navbar');
const close = document.getElementById('close');


if (bar) {
    bar.addEventListener('click', () => {
        nav.classList.add('active')
    })
}

if (close) {
    close.addEventListener('click', () => {
        nav.classList.remove('active')
    })
}

/* 리뷰 별 선택하기 */
const allStars = document.querySelectorAll('.star');

        allStars.forEach((star, i) => {
            star.onclick = function () {
                let current_star_level = i + 1;

                allStars.forEach((star, j) => {
                    if (current_star_level >= j + 1) {
                    star.innerHTML = '<i class="fa-solid fa-star"></i>';
                } else {
                    star.innerHTML = '<i class="fa-regular fa-star"></i>';
                }

                })
            }
        })