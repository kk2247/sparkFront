<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.Key" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Search" %><%--
  Created by IntelliJ IDEA.
  User: 22478
  Date: 2019/1/4
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>your search</title>
  <link rel="stylesheet" type="text/css" href="styles.css" />
</head>
<body>
<div id="page">
  <h1>your search</h1>
  <form class="sea" method="post" action="/search">
    <fieldset>
      <input id="s" type="text" name="s"/>
      <input type="submit" value="Submit" id="submitButton" />
      <div id="searchInContainer">
        <input type="radio" name="check" value="site" id="searchSite" checked />
        <label for="searchSite" id="siteNameLabel">搜索</label>
      </div>
    </fieldset>
  </form>
  <div id="resultsDiv">

  </div>
</div>
<div>
  <%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    List<Key> keys= (List<Key>) request.getAttribute("keys");
    List<Search> searches= (List<Search>) request.getAttribute("search");
    if(keys!=null && searches !=null){
  %>
  <div id="search" class="search">
    <%
        for(int i=0;i<searches.size();i++){
    %>
    <div class="search_union">
    <a style="text-align: center ; display: block" href="<%out.print(searches.get(i).getUrl());%>"><%out.print(searches.get(i).getTitle());%></a>
    </div>
    <%
        }
    %>
  </div>
  <div id="keys" class="keys">
    <%
      for(int i=0;i<keys.size();i++){
    %>
    <div class="key_union">
      <form action="/search" method="post">
        <button type="submit" name="s" class="my_button" value=<%out.print(keys.get(i));%> ><%out.print(keys.get(i));%></button>
        <%--<a  style="text-align: center ; display: block" href="#"  name="s"><%out.print(keys.get(i));%></a>--%>
      </form>
    </div>
    <%
      }
    %>
  </div>
  <%
    }
  %>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>

