var webSocket = new WebSocket("ws://192.168.0.7:8888/ta_back/broadsocket");

var discussor_window = document.getElementById("discussorWindow");
var audi_window = document.getElementById("audienceWindow");

var nick_A = document.getElementsByClassName("nickname_A")[0].value;
var nick_B = document.getElementsByClassName("nickname_B")[0].value;

// WebSocket 서버와 접속이 되면 호출되는 함수
webSocket.onopen = function(message) {
	// 콘솔 텍스트에 메시지를 출력한다.
	discussor_window.innerHTML += "<div>Server connect...</div>";
	audi_window.innerHTML += "<div>Server connect...</div>";
};

// WebSocket 서버와 접속이 끊기면 호출되는 함수
webSocket.onclose = function(message) {
	// 콘솔 텍스트에 메시지를 출력한다.
	discussor_window.innerHTML += "<div>Server Disconnect...</div>";
	audi_window.innerHTML += "<div>Server Disconnect...</div>";
};
// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
webSocket.onerror = function(message) {
	// 콘솔 텍스트에 메시지를 출력한다.
	discussor_window.innerHTML += "<div>error...</div>";
	audi_window.innerHTML += "<div>error...</div>";
};

// WebSocket 서버로 부터 메시지가 오면 호출되는 함수
webSocket.onmessage = function(message) {
	var user = document.getElementById("user");
	var discussorA_num = document.getElementsByClassName("nickname_A")[0].id;
	var discussorB_num = document.getElementsByClassName("nickname_B")[0].id;
	var msg = message.data;
	var arr = msg.split(' ');

	if (arr[0] == discussorA_num) {
		// 콘솔 텍스트에 메시지를 출력한다.
		discussor_window.innerHTML += "<div class = 'left' style = 'float: left;color: red;'>" + msg + "</div></br>";
	} else if (arr[0] == discussorB_num) {
		discussor_window.innerHTML += "<div class = 'right' style = 'float: right;color: blue;'>" + msg + "</div></br>";
	}
	else  {
		audi_window.innerHTML += "<div class = 'aud' style = 'float: left;'>" + msg + "</div></br>";
	}
	discussor_window.scrollTop = discussor_window.scrollHeight;
    audi_window.scrollTop = audi_window.scrollHeight;
};

// Send 버튼을 누르면 호출되는 함수
function sendMessage() {
	// 유저명 텍스트 박스 오브젝트를 취득
	var user_num = document.getElementById("user");
	var discussorA_num = document.getElementsByClassName("nickname_A")[0].id;
	var discussorB_num = document.getElementsByClassName("nickname_B")[0].id;

	// 송신 메시지를 작성하는 텍스트 박스 오브젝트를 취득
	var message = document.getElementById("textMessage");
	
	// 콘솔 텍스트에 메시지를 출력한다.
	if (user_num.value == discussorA_num) {
		// 콘솔 텍스트에 메시지를 출력한다.
		discussor_window.innerHTML += "<div class = 'left' style = 'float: left;color: red;'>" + user_num.value + "(me) : " + message.value + "</div></br>";
	} else if (user_num.value == discussorB_num) {
		discussor_window.innerHTML += "<div class = 'right' style = 'float: right;color: blue;'>" + user_num.value + "(me) : " + message.value + "</div></br>";
	}
	else  {
		audi_window.innerHTML += "<div class = 'aud' style = 'float: left;'>" + user_num.value + "(me) : " + message.value + "</div></br>";
	}

	// WebSocket 서버에 메시지를 전송(형식 「{{유저명}}메시지」)
	webSocket.send("{{" + user_num.value + "}}" + message.value);
	// 송신 메시지를 작성한 텍스트 박스를 초기화한다.
	message.value = "";
	
	discussor_window.scrollTop = discussor_window.scrollHeight;
    audi_window.scrollTop = audi_window.scrollHeight;
}
    
// Disconnect 버튼을 누르면 호출되는 함수
function disconnect() {
	// WebSocket 접속 해제
	webSocket.close();
}