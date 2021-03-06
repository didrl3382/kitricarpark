<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.carpark.common.model.ParkingDetailDto"%>
    
<%@include file="/common/common.jsp" %>
<%@include file="/common/header/init.jsp" %> 
<%@include file="/common/side.jsp" %>
  
    
<%
if(memberDto != null) {
   List<ParkingDetailDto> list = (List<ParkingDetailDto>) request.getAttribute("parkingList");
   PageNavigator navigator = (PageNavigator) request.getAttribute("navigator");
%>
<div id="wrapper">
<!-- ****************************************************************************************************************** -->   

      <!-- main -->
      <div id="page-wrapper">
         <div class="container-fluid">
         <br><h3>나의 주차장</h3><br>   
         
         <div class="btn-group text-align:left" role="group" aria-label="...">
			<a class="btn btn-default align-left" href="<%=root %>/parking/register.jsp"> 등록 </a>
		</div>
              
            
              <table  class="table table-hover">
                 <tr style="background: #00cc00">
                    <td>주차장 이름</td>
                    <td width="50">평점</td>
                    <td width="100">사용가능</td>
                    <td width="50">인증</td>
                    <td width="150"></td>
                 </tr>
            
<%
   int size = list.size();
   if(size > 0) {
      for(ParkingDetailDto parkingDetailDto : list) {
%>
				<tr>
                  <td>
                  	<a href="javascript:parkingView('<%=parkingDetailDto.getPark_id()%>');">
                  		<%=parkingDetailDto.getPark_name() %>
                  	</a>
                  </td>
                  <td>
	                 	<%=parkingDetailDto.getPark_avgPoint() %>
                  </td>
<%
		if(parkingDetailDto.getPark_flag() == 0) {
%>
                  <td> X </td>
<%
		} else {
%>
				  <td> O </td>
<%
		}
%>
<%
		if(parkingDetailDto.getPark_visit() == 0) {
%>
                  <td> X </td>
<%
		} else {
%>
				  <td> O </td>
<%
		}
%>
                  <td>
                  	<button type="button" class="btn btn-default btn-xs" onclick="javascript:parkingView('<%=parkingDetailDto.getPark_id()%>');"> 상세보기 </button>
                  	<button type="button" class="btn btn-default btn-xs" onclick="javascript:parkingMvModify('<%=parkingDetailDto.getPark_id()%>');"> 수정 </button>
                  	<button type="button" class="btn btn-default btn-xs" onclick="javascript:parkingDelete('<%=parkingDetailDto.getPark_id()%>');"> 삭제 </button>
				  </td>
<%
      }
%>
               </tr>
              </table>
            </div>
            
            
            
            <nav align="center">
				<ul class="pagination">
				<%=navigator.getNavigator() %>
				</ul>
						
			</nav>
<%
   } else {
%>
              </table>
<center><h3>등록된 주차장이 없습니다</h3></center>   
<br><br>  
<%
   }
%>
          
      </div>
   </div>
<!-- ****************************************************************************************************************** -->   
</div>
<%
} else {
%>
<center><h3>로그인 후 이용해주세요</h3></center>
<%
}
%>

<%@include file="/common/footer.jsp"%>