package com.carpark.member.message.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.action.Action;
import com.carpark.member.model.MemberDto;
import com.carpark.member.model.MessageDto;
import com.carpark.member.model.service.CommonServiceImpl;
import com.carpark.member.model.service.MemberMessageServiceImpl;
import com.carpark.util.NumberCheck;

public class MemberMessageReplyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberInfo");
		
		int seq = CommonServiceImpl.getCommonService().getNextSeq();
		
		MessageDto messageDto = new MessageDto();
		messageDto.setSeq(seq);
		messageDto.setUserID(memberDto.getUser_id());
		messageDto.setSubject(request.getParameter("subject"));
		messageDto.setContent(request.getParameter("content"));
		messageDto.setBcode(NumberCheck.nullToZero(request.getParameter("bcode")));
		messageDto.setMseq(seq);
		messageDto.setReceiverId(request.getParameter("receiver"));
		messageDto.setMsgFlag(0);
		
		MemberMessageServiceImpl.getMemberMessageService().writeArticle(messageDto);
		List<MessageDto> list = MemberMessageServiceImpl.getMemberMessageService().sendListArticle(memberDto.getUser_id());
		request.setAttribute("sendList", list);
		
		
		return "/message/sendlist.jsp";
	}

}