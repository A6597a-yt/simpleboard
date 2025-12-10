async function login() {
    const username = document.getElementById("username").value;

    const res = await fetch("/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username })
    });

    if (res.ok) {
        // 로그인 성공 → 게시판 이동
        window.location.href = "/board.html";
    } else {
        document.getElementById("error").innerText = "로그인 실패: 존재하지 않는 사용자";
    }
}
