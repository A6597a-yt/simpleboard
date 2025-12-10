// 페이지 로딩되면 실행
window.onload = () => {
    loadUserInfo();
    loadMessages();
};

// 로그인 정보 출력
async function loadUserInfo() {
    const res = await fetch("/me");
    if (res.ok) {
        const user = await res.json();
        document.getElementById("user-info").innerText = `로그인: ${user.username}`;
    }
}

// 메시지 전체 가져오기
async function loadMessages() {
    const res = await fetch("/messages");
    const list = await res.json();

    const box = document.getElementById("message-list");
    box.innerHTML = "";

    list.forEach(m => {
        const div = document.createElement("div");
        div.className = "message";

        div.innerHTML = `
            <span class="author">${m.author}</span><br>
            ${m.message}<br>
            <span class="time">${m.createdAt.replace("T", " ")}</span>
            <span class="delete-btn" onclick="deleteMessage(${m.id})">X</span>
        `;

        box.appendChild(div);
    });
}

// 메시지 작성
async function sendMessage() {
    const msg = document.getElementById("msg").value;

    if (!msg.trim()) return;

    await fetch("/messages", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: msg })
    });

    document.getElementById("msg").value = "";
    loadMessages();
}

// 메시지 삭제
async function deleteMessage(id) {
    await fetch(`/messages/${id}`, { method: "DELETE" });
    loadMessages();
}

// 로그아웃
async function logout() {
    await fetch("/logout", { method: "POST" });
    window.location.href = "/login.html";
}
