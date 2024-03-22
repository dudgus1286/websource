// submit 발생 시
// submit 기능 중지
// code 4자리 숫자 값 있는지 확인
// 전부 다 값이 있다면 submit
const form = document.querySelector("form");
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const code = document.querySelector("#code");

  if (!code.value || code.value.length != 4 || isNaN(code.value)) {
    alert("code 값이 비어있거나 값이 4자리의 숫자가 아닙니다.");
    code.focus();
    return;
  }
  e.target.submit();
});
