<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Msg Modal Start -->
<div class="modal fade" id="msgToHost" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">Close</span>
				</button>
				<h4 class="msgToHost" id="msgToHostModal">리뷰 남기기</h4>
			</div>
			<div class="modal-body">

				<!-- main -->
				<div id="page-wrapper">
					<div class="container-fluid">

						<!-- 쪽지보내기 -->
						<div class="form-group">
							<form class="form-horizontal" name="writeForm" method="post"
								action="">
								<input type="hidden" name="act" value="messageWrite"> <input
									type="hidden" name="bcode" value="1"> <input
									type="hidden" name="pg" value="1"> <input type="hidden"
									name="key" value=""> <input type="hidden" name="word"
									value="">

								<div class="form-group">
									<label for="receiver">주차장이름</label> <input type="text"
										class="form-control" id="receiver" placeholder="받는사람"
										name="name">
								</div>
								<div class="form-group">
									<label for="subject">제목</label> <input type="text"
										class="form-control" id="subject" placeholder="제목"
										name="content">
								</div>
								<div class="form-group">
									<label for="content">내용</label>
									<textarea class="form-control" rows="10" id="content"
										name="content"></textarea>
								</div>
								<div class="form-group">
									<label for="fileinput" class="control-label">사진 등록하기</label>

									<input type="file" id="fileinput" name="fileinput" readonly="">


								</div>
								<div class="form-group text-center">
									<input class="btn btn-default" type="button" value="등록하기"
										onclick="javascript:messageWrite();"> <input
										class="btn btn-default" type="reset" value="취소">
								</div>

							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>
<!-- Msg Modal End -->

<!-- 쪽지보내기 모달 -->