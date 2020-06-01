

var app = new Vue({
	el		: "#app",
	data	: {
		msg : "hello VUE!"
	}
});

var app2 = new Vue({
	el		: "#app-2",
	data	: {
		message : "이 페이지는" + new Date() + "에 로드되었습니다."
	}
});

var app3 = new Vue({
	el		: "#app-3",
	data	: {
		seen : false
	}
});

var app4 = new Vue({
	  el: '#app-4',
	  data: {
	    todos: [
	      { text: 'JavaScript 배우기' },
	      { text: 'Vue 배우기' },
	      { text: '무언가 멋진 것을 만들기' }
	    ]
	  }
	})

var app5 = new Vue(

		{
			el: '#app-5',
			data : {
				newInput : "asdasd"
			},
			methods: {
			  reverseMessage: function () {
				  $.ajax({
					    url: "/crontab/toKor", // 클라이언트가 요청을 보낼 서버의 URL 주소
					    type: "POST",                             // HTTP 요청 방식(GET, POST)
					    ContebtType : "application/json",
					    dataType: "json",                         // 서버에서 보내줄 데이터의 타입
					    data: {
					    	"data" : "20 2 * * *"
					    },                // HTTP 요청과 함께 서버로 보낼 데이터
					    success : function(data){
					    	alert("Rmx"+ data);
					    },complete : function(){
					    	alert("끝끝끝");
					    }
					})
			  }
			}
		}
);
$()
$(document).ready(function(){

});