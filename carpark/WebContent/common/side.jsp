<%@page import="com.carpark.member.model.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
function sendReviewList(pg) {
	document.location.href = root+"/review?act=sendReviewList&pg="+pg;
}

function receiveReviewList(pg) {
	document.location.href = root+"/review?act=receiveReviewList&pg="+pg;
}
</script>

	
	<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse" >


			<ul class="nav navbar-nav side-nav" >
				<li><a href="<%=root%>/member?act=mvprofile"><i class="fa fa-fw fa-dashboard"></i>
						Dashboard</a></li>
						
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#profile"><i class="fa fa-user"></i>
						프로필 <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="profile" class="collapse">
						<li><a href="javascript:detailprofile();">프로필 수정</a></li>
						<li><a href="javascript:myowncarinfo();">내 차량 정보</a></li>
						<li><a href="javascript:myfavoriteparking();">즐겨찾는 주차장</a></li>

					</ul></li>
					
				<li><a href="javascript:reservationInfo();"><i class="fa fa-fw fa-edit"></i>
						예약내역</i></a>
					</li>
					
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#message"><i class="fa fa-envelope"></i>
						쪽지 <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="message" class="collapse">
						<li><a href="javascript:messageReceiveList();">받은 쪽지함</a></li>
						<li><a href="javascript:messageSendList();">보낸 쪽지함</a></li>
					</ul></li>
					
						<li><a href="javascript:;" data-toggle="collapse"
					data-target="#review"><i class="fa fa-envelope"></i>
						후기 <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="review" class="collapse">
						<li><a href="javascript:sendReviewList();">작성한 후기</a></li>
						<li><a href="javascript:receiveReviewList();">받은 후기</a></li>
					</ul></li>
					
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#parking"><i class="fa fa-fw fa-wrench"></i>
						주차장 <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="parking" class="collapse">
						<li><a href="javascript:parkingList('<%=pg%>');">내 주차장</a></li>
						<li><a href="<%=root %>/parking/register.jsp">주차장 등록</a></li>
						<li><a href="<%=root %>/call?act=callSendList">주차장 제보</a></li>
					</ul></li>
					
				<li><a href="javascript:mycoin();"><i class="fa fa-fw fa-file"></i>
						코인</a></li>
						
				<li><a href="javascript:reportList('1');"><i class="fa fa-fw fa-wrench"></i>
						신고내역 </a>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
