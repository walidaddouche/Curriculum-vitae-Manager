<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="app" value="/app.js" />


<div id="myApp">
    <div class="container">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/#/candidatDetail/id">CandidatDetail </a>
            <a class="navbar-brand" href="/#">candidats </a>

        </nav>

        <component :is="currentView"></component>

    </div>
</div>
<script src="${app}" type="module"></script>


<%@ include file="/WEB-INF/jsp/footer.jsp"%>