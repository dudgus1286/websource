const form = document.querySelector("form");
form.addEventListener("submit", (e) => {
  e.preventDefault();
  const userid = document.querySelector("#userid");
  const password = document.querySelector("#password");
  const name = document.querySelector("#name");
  const email = document.querySelector("#email");

  const regex = "";

  if (userid.value == "" || userid.value.length < 6 || userid.value.length > 12) {
    alert("아이디를 제대로 입력하시오");
    userid.focus();
  }
});
