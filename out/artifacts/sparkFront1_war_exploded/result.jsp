<%--
  Created by IntelliJ IDEA.
  User: 22478
  Date: 2019/1/4
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>your search</title>
  <link rel="stylesheet" type="text/css" href="styles.css" />
</head>
<body>
<div id="page">
  <h1>your search</h1>
  <form id="searchForm" method="post">
    <fieldset>
      <input id="key" type="text" />
      <input type="submit" value="Submit" id="submitButton" />
      <div id="searchInContainer">
        <input type="radio" name="check" value="site" id="searchSite" checked />
        <label for="searchSite" id="siteNameLabel">Search</label>
      </div>
    </fieldset>
  </form>
  <div id="resultsDiv"></div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/script.js"></script>
<div style="text-align:center;clear:both">

  <p><a href="#" target="_blank">your search</a></p>
</div>
</body>
</html>

