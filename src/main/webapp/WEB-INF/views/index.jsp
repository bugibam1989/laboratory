<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/4.4.1-1/css/bootstrap.css">
</head>
<body>
	Hello world
	<div id="app">{{ msg }}</div>
	<div id="app-2">
		<span v-bind:title="message"> 내 위에 잠시 마우스를 올리면 동적으로 바인딩 된
			title을 볼 수 있습니다! </span>
	</div>
	<div id="app-3">
		<p v-if="seen">이제 나를 볼 수 있어요</p>
	</div>
	<div id="app-4">
	  <ol>
	    <li v-for="todo in todos">
	      {{ todo.text }}
	    </li>
	  </ol>
	</div>
	<div id="app-5">
		<button v-on:click="reverseMessage">ADD</button>
		<input v-model="newInput" class="" />
	</div>



	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
	<script src="/webjars/bootstrap/4.4.1-1/js/bootstrap.js"></script>

	<script src="/js/commonJs.js"></script>

</body>
</html>