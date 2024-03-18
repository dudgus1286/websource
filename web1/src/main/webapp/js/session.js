// 세션값 저장 클릭 시 sessionSet.jsp 이동
document.querySelector("button:nth-child(1)").addEventListener("click", () => {
  location.href = "/scope/sessionSet.jsp";
});

// 세션값 삭제 클릭 시 sessionDel.jsp 이동
document.querySelector("button:nth-child(2)").addEventListener("click", () => {
  location.href = "/scope/sessionDel.jsp";
});

// 세션값 초기화 클릭 시 sessionDel.jsp 이동
document.querySelector("button:nth-child(3)").addEventListener("click", () => {
  location.href = "/scope/sessionInv.jsp";
});
