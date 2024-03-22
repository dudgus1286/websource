// submit 발생 시
// submit 기능 중지
// code, title, writer, price 값 있는지 확인
// code는 4자리 인지도 확인
// 전부 다 값이 있다면 submit
const form = document.querySelector("form");
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const code = document.querySelector("input#code");
  const title = document.querySelector("input#title");
  const writer = document.querySelector("input#writer");
  const price = document.querySelector("input#price");

  //   if (code.value && title.value && writer.value && price.value) {
  //     if (code.value.length == 4) {
  //       form.submit();
  //     }
  //   }
  if (!code.value || code.value.length != 4 || isNaN(code.value)) {
    alert("code 값이 비어있거나 값이 4자리의 숫자가 아닙니다.");
    code.focus();
    return;
  } else if (!title.value) {
    alert("title 값이 비어있습니다.");
    title.focus();
    return;
  } else if (!writer.value) {
    alert("writer 값이 비어있습니다.");
    writer.focus();
    return;
  } else if (!price.value || isNaN(price.value)) {
    alert("price 값이 비어있습니다.");
    price.focus();
    return;
  }
  e.target.submit();
});
