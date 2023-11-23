<%@ include file="headers.jsp" %>

<c:url var="home" value="/aaa"/>
<c:url var="app" value="/candidat.js"/>

<div id="myApp">

    <div class="container">
        <h1>My application</h1>
        <p>{{ message }}</p>
        <p>list = <span v-for="element in list">{{element}} - </span></p>
        <p>counter = {{counter}}</p>
    </div>
</div>

<script src="${app}"></script>

