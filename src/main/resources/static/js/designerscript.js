
// 영업시간 설정 (예: 10시부터 20시까지)
const workStartHour = 10;
const workEndHour = 20;
const timeSlotsContainer = document.getElementById("timeSlots");

// 예약된 시간을 서버로부터 받아왔다고 가정 (예: 13:00, 15:30은 예약된 상태)
const bookedTimes = {"2024-10-11": ["13:00", "15:30"],
    "2024-10-12": ["12:00", "16:00"],"2024-10-15": ["13:00", "15:30"],"2024-10-16": ["12:00", "16:00"],}

function isBookedTime(date, timeSlot) {
    const bookedForDate = bookedTimes[date] || []; // 해당 날짜의 예약된 시간 목록을 가져옴
    return bookedForDate.includes(timeSlot); // 해당 시간이 예약된 시간인지 확인
}

// 현재 시간을 기준으로 2시간 후의 시간을 반환하는 함수
function getMinTime() {
    const now = new Date();
    now.setHours(now.getHours() + 2); // 현재 시간에서 2시간 더하기
    now.setMinutes(Math.ceil(now.getMinutes() / 30) * 30); // 30분 단위로 올림
    return now;
}


// 현재 시간을 비교해 버튼 비활성화 여부를 결정하는 함수
function isPastTime(slotTime) {
    const now = new Date(); // 현재 시간
    const selectedDateInput = document.querySelector("#date").value; // 선택된 날짜 가져오기
    const selectedDate = new Date(selectedDateInput); // 선택된 날짜를 Date 객체로 변환

    const [hours, minutes] = slotTime.split(":").map(Number);
    selectedDate.setHours(hours, minutes, 0, 0); // 선택된 날짜에 시간 설정

// 현재 날짜가 선택된 날짜와 동일할 경우에만 시간 비교
    if (selectedDate.toDateString() === now.toDateString()) {
        return selectedDate < getMinTime(); // 현재 시간과 비교
    }

// 과거 날짜는 자동으로 비활성화
    return selectedDate < now;
}

// 시간 슬롯을 동적으로 생성하는 함수
function generateTimeSlots() {
    const selectedDate = document.querySelector("#date").value;

    for (let hour = workStartHour; hour < workEndHour; hour++) {
        for (let minute of [0, 30]) {
            const timeSlot = `${String(hour).padStart(2, '0')}:${String(minute).padStart(2, '0')}`;

// 버튼 생성
            const button = document.createElement("button");
            button.textContent = timeSlot;
            button.classList.add("time-slot-button");

// 예약된 시간이거나 과거 시간이면 비활성화
            if (isBookedTime(selectedDate, timeSlot) || isPastTime(timeSlot)) {
                button.disabled = true;
                button.classList.add("disabled"); // 스타일 추가
            }

// 클릭 이벤트
            button.addEventListener("click", () => {
// 사용자가 선택한 시간을 처리
                console.log(`예약 시간 선택: ${timeSlot}`);
            });

// 컨테이너에 버튼 추가
            timeSlotsContainer.appendChild(button);
        }
    }
}
// 시간 슬롯 생성
generateTimeSlots();


// Flatpickr 초기화 (날짜 선택)
flatpickr("#date", {
    inline: true, // 페이지에 달력이 항상 표시되도록 설정
    defaultDate: "today", // 오늘 날짜를 기본으로 설정
    minDate: "today", // 오늘 이전 날짜는 선택할 수 없게 설정
    onChange: function() {
// 날짜가 변경될 때마다 시간 슬롯을 재생성
        timeSlotsContainer.innerHTML = ""; // 기존 슬롯 제거
        generateTimeSlots(); // 새로운 슬롯 생성
    },
    onReady: function() {
// Flatpickr가 준비되었을 때 시간 슬롯을 생성
        timeSlotsContainer.innerHTML = ""; // 기존 슬롯 제거
        generateTimeSlots(); // 시간 슬롯 처음 한번만 생성
    }
});


// Flatpickr 시간 설정 (30분 간격, 2시간 이후부터 선택 가능)
flatpickr("#time", {
    enableTime: true, // 시간 선택 활성화
    noCalendar: true, // 달력 숨기기 (시간만 선택)
    dateFormat: "H:i", // 시간 형식 설정 (24시간 형식)
    time_24hr: true, // 24시간 형식 사용
    minuteIncrement: 30, // 30분 간격으로 설정
    inline: true, // 시간 선택을 버튼 형태로 보이게 설정
    onReady: function(selectedDates, dateStr, instance) {
        instance.set('minTime', getMinTime()); // 최소 시간 설정

    },
    onOpen: function(selectedDates, dateStr, instance) {
        instance.set('minTime', getMinTime()); // 달력이 열릴 때 최소 시간 다시 설정

    }
});

