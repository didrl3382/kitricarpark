<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.carpark.member.model.*,com.carpark.util.*"%>
    
<%@include file="/common/common.jsp" %>
<%@include file="/common/header/init.jsp" %> 
<%@include file="/common/side.jsp" %>

<%
if(memberDto != null) {
List<ReportDto> list = (List<ReportDto>) request.getAttribute("reportList");
PageNavigator navigator = (PageNavigator) request.getAttribute("navigator");
%>

<div id="wrapper">
<!-- ****************************************************************************************************************** -->	

		<!-- main -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<br><h3>신고내역</h3><br>
					
				
				<!-- 신고하기 모달창 -->
				<%@ include file="/report/write.jsp" %>
				
				<!-- 신고내역 -->
				<div class="table">
				  <table class="table table-hover" style="text-align:center">
				  	<tr style="background: #00cc00">
				  		<td width="150"><b>신고대상</b></td>
				  		<td><b>제목</b></td>
				  		<td width="80"><b>시간</b></td>
				  		<td width="50">처리</td>
				  		<td width="80"></td>
				  	</tr>
				
<%
int size = list.size();
if(size > 0) {
	for(ReportDto reportDto : list) {
%>
					<!-- 신고내역 -->
					<tr>
						<td width="150"><a href="javascript:reportView('<%=reportDto.getSeq() %>');"><%=reportDto.getReport_id() %></a></td>
						<td><a href="javascript:reportView('<%=reportDto.getSeq() %>');"><%=reportDto.getSubject() %></a></td>
						<td width="80"><%=reportDto.getLogtime() %></td>
<%
		if(reportDto.getReport_flag() == 0) {
%>
						<td width="50"> X </td>
						<td width="80"><input type="button" class="btn btn-default" value="취소" onclick="javascript:reportDelete('<%=reportDto.getSeq()%>');"></td>
<%
		} else {
%>
						<td width="50"> O </td>
						<td width="80"></td>
<%
		}
%>
					</tr>
<%
	}
%>
				  </table>
				</div>

<%
} else {
%>
				  </table>
				</div>
<center><h3>신고내역이 없습니다</h3></center>	
<br><br>  
<%
}
%>


			<!-- 페이지 네비게이션/검색 -->
			<nav align="center">
				<!-- <div class="form-group">
				<form class="form-inline" name="reportSearchForm" method="get" action="">
					<input type="hidden" name="act" value="reportSearch">
					<input type="hidden" name="bcode" value="3">
					<input type="hidden" name="pg" value="<%=pg%>">
					<select name="key">
						<option value="subject">제목</option>
						<option value="contents">내용</option>
						<option value="report_id">신고대상</option>
					</select>
					<input type="text" name="word" id="word" value="">
					<input type="button" class="btn btn-default" value="검색" onclick="javascript:reportSearch();">
					<input type="button" class="btn btn-default" value="전체목록" onclick="javascript:reportList('1');">
				</form>
				</div> -->
				<ul class="pagination">
				<%=navigator.getNavigator() %>
				</ul>
						
			</nav>

					
		</div>
	</div>
<!-- ****************************************************************************************************************** -->	
</div>
<%
} else {
%>
<center>
<h3>로그인 후 이용해주세요</h3>
</center>
<%
}
%>

<%@include file="/common/footer.jsp"%>