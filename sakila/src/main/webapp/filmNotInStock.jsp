<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="dao.*" %>
<%@ page import ="java.util.*" %>
<%   
      FilmNotInStockDao fnd = new FilmNotInStockDao();
      Map<String,Object> map =new HashMap<>();
      
      int filmId = 0;
      if(request.getParameter("filmId")!=null){
      filmId= Integer.parseInt(request.getParameter("filmId"));
      System.out.println(filmId + "filmId 디버깅");
      }
      
      int storeId = 0;
      if(request.getParameter("storeId")!=null){
      storeId= Integer.parseInt(request.getParameter("storeId"));
      System.out.println(storeId + "storeId 디버깅");
      }
      
      map = fnd.filmNotInStockCall(filmId, storeId);
      List<Integer> list = (List<Integer>)map.get("list");
      int count =0;
      count = (Integer)(map.get("count"));
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmNotInStock</title>
</head>
<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
   <body>
   <a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
   <form method="post" action="<%=request.getContextPath() %>/filmNotInStock.jsp">
         <div class="container" >
         <table class="table">
         <thead class="thead-dark">
            <h3>film_not_In_Stock</h3>
               <tr>
                    <h5><div><a class="text-danger">[<%=storeId %>]</a>번 store에<a class="text-danger">[<%=filmId %>]</a>번 영화<a class="text-danger">[<%=count %>]</a> 개 출고 되었습니다.</div></h5>
                 <!-- 인벤 id -->
                 <h6>(inventoryID_list)</h6>
                  <%
                     for(int id : list) {
                  %>
               <tr>
                  <td><%=id %></td>
               </tr>
            <%} %>
         </tbody>
      </table>
   </body>
</html> 