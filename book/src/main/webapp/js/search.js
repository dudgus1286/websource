// submit 발생 시
// submit 기능 중지
// criteria, keyword 값 있는지 확인
// code는 4자리 인지도 확인
// 전부 다 값이 있다면 submit
const form = document.querySelector("form");
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const criteria = document.querySelector("#criteria");
  const keyword = document.querySelector("#keyword");

  if (criteria.value == "검색 조건 선택") {
    alert("검색 조건을 선택해주세요.");
    criteria.focus();
    return;
  } else if (!keyword.value) {
    alert("검색어를 입력해주세요.");
    keyword.focus();
    return;
  } else if (criteria.value == "code" && (isNaN(keyword.value) || keyword.value.length != 4)) {
    alert("도서코드로 검색 시 검색어는 4자리의 숫자여야 합니다");
    keyword.focus();
    return;
  }
  e.target.submit();
});
