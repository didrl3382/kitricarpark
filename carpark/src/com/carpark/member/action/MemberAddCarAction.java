package com.carpark.member.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.action.Action;
import com.carpark.member.model.MemberCarDto;
import com.carpark.member.model.MemberDto;
import com.carpark.member.model.service.MemberReservationServiceImpl;
import com.carpark.member.model.service.MemberServiceImpl;

public class MemberAddCarAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberInfo");
		if (memberDto != null) {
			MemberCarDto memberCarDto = new MemberCarDto();
			
			memberCarDto.setUser_id(request.getParameter("user_id"));
			memberCarDto.setModel(request.getParameter("inputCarName"));
			memberCarDto.setCategory(request.getParameter("carcategory"));
			memberCarDto.setReg_num(request.getParameter("inputCarNum"));

			MemberServiceImpl.getMemberService().addNewCar(memberCarDto);

			ArrayList<MemberCarDto> list = MemberReservationServiceImpl.getMemberReservationService().getCarInfo(request.getParameter("user_id"));
			
			request.setAttribute("park_id", request.getAttribute("park_id"));
			request.setAttribute("mycarinfo", list);

			return "/reservation?act=mvReservation";
		} else
			return "/member/loginFail.jsp";
	}

}

// memberCarDto
// private String car_id;
// private String maker;
// private String model;
// private String category;
// private String reg_num;