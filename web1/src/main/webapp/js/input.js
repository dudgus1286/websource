// submit 클릭 시
// 값이 비어 있지 않도록 처리
// age가 숫자인지 확인
const form = document.querySelector("form");
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const id = e.target.querySelector("#id");
  const name = e.target.querySelector("#name");
  const age = e.target.querySelector("#age");

  if (!id.value) {
    alert("아이디를 확인해 주세요");
    id.focus();
    return;
  }
  if (!name.value) {
    alert("이름을 확인해 주세요");
    name.focus();
    return;
  }
  if (!age.value || isNaN(age.value)) {
    alert("나이를 확인해 주세요");
    age.focus();
    return;
  }

  // 검증 완료 후 폼 submit 실행
  form.submit();
});
